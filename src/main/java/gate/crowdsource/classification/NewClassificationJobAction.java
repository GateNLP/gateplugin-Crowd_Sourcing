/*
 *  NewClassificationJobAction.java
 *
 *  Copyright (c) 1995-2014, The University of Sheffield. See the file
 *  COPYRIGHT.txt in the software or at http://gate.ac.uk/gate/COPYRIGHT.txt
 *
 *  This file is part of GATE (see http://gate.ac.uk/), and is free
 *  software, licenced under the GNU Library General Public License,
 *  Version 3, June 2007 (in the distribution as file licence.html,
 *  and also available at http://gate.ac.uk/gate/licence.html).
 *  
 *  $Id: NewClassificationJobAction.java 18290 2014-09-04 12:14:12Z ian_roberts $
 */
package gate.crowdsource.classification;

import gate.gui.MainFrame;
import gate.util.GateRuntimeException;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.InputStream;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

/**
 * Action to present a dialog prompting the user for the information required to
 * create a new CrowdFlower job, and then attempt to actually create it.
 */
public class NewClassificationJobAction extends AbstractAction {
  private static final long serialVersionUID = -2270327475458761446L;

  private EntityClassificationJobBuilder jobBuilder;
  
  public NewClassificationJobAction(EntityClassificationJobBuilder jobBuilder) {
    super("Create a new Figure Eight job");
    putValue(SHORT_DESCRIPTION,
            "Create a new job on Figure Eight to be populated by this PR");
    this.jobBuilder = jobBuilder;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      final NewClassificationJobForm panel = new NewClassificationJobForm();
      
      panel.setPreferredSize(new Dimension(600, 500));
      
      initListeners(panel);

      // put the panel in a JOptionPane
      final JTable commonOptionsTable = panel.m_commonOptions;
      final DefaultTableModel optionsTableModel = new DefaultTableModel(
              new String[][] {
                new String[] { "none", "None of the above" },
                new String[] { "cannot_decide", "I cannot decide" },
                new String[] { "nae", "Not an entity" },
              },
              new String[] { "Value", "Description" });
      commonOptionsTable.setModel(optionsTableModel);
      commonOptionsTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
      AbstractButton addButton = panel.m_addRowButton;
      addButton.setIcon(MainFrame.getIcon("Add"));
      addButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          int insertPoint = commonOptionsTable.getSelectionModel().getMaxSelectionIndex() + 1;
          if(insertPoint == 0) {
            // nothing was selected, insert at the end
            insertPoint = optionsTableModel.getRowCount();
          }
          optionsTableModel.insertRow(insertPoint, new String[] { "", "" });
          commonOptionsTable.getSelectionModel().setSelectionInterval(insertPoint, insertPoint);
        }
      });
      AbstractButton removeButton = panel.m_removeRowButton;
      removeButton.setIcon(MainFrame.getIcon("Remove"));
      removeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          int[] selected = commonOptionsTable.getSelectedRows();
          for(int i = selected.length - 1; i >= 0; i--) {
            optionsTableModel.removeRow(selected[i]);
          }
        }
      });
      AbstractButton upButton = panel.m_upButton;
      upButton.setIcon(MainFrame.getIcon("up"));
      upButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          int start = commonOptionsTable.getSelectionModel().getMinSelectionIndex();
          int end = commonOptionsTable.getSelectionModel().getMaxSelectionIndex();
          if(start > 0) {
            optionsTableModel.moveRow(start, end, start - 1);
            commonOptionsTable.getSelectionModel().setSelectionInterval(start - 1, end - 1);
          }
        }
      });
      AbstractButton downButton = panel.m_downButton;
      downButton.setIcon(MainFrame.getIcon("down"));
      downButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          int start = commonOptionsTable.getSelectionModel().getMinSelectionIndex();
          int end = commonOptionsTable.getSelectionModel().getMaxSelectionIndex();
          if(end >= 0 && end < optionsTableModel.getRowCount() - 1) {
            optionsTableModel.moveRow(start, end, start + 1);
            commonOptionsTable.getSelectionModel().setSelectionInterval(start + 1, end + 1);
          }
        }
      });

      final JOptionPane optionPane = new JOptionPane(panel, JOptionPane.PLAIN_MESSAGE,
              JOptionPane.OK_CANCEL_OPTION);
      final JDialog dialog  = new JDialog(MainFrame.getInstance(), "New classification job", true);
      dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
      dialog.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
          optionPane.setValue(Integer.valueOf(JOptionPane.CLOSED_OPTION));
        }
      });
      dialog.setContentPane(optionPane);
      optionPane.addPropertyChangeListener(new PropertyChangeListener() {
        
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
          if(JOptionPane.VALUE_PROPERTY.equals(evt.getPropertyName())) {
            Object value = optionPane.getValue();
            if (value == JOptionPane.UNINITIALIZED_VALUE) {
              //ignore reset
              return;
            }
 
            // Reset the JOptionPane's value.
            optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);
            if(Integer.valueOf(JOptionPane.OK_OPTION).equals(value)) {
              boolean valid = verify(panel);
              if(valid) {
                // hide the dialog
                dialog.setVisible(false);
                // do the job in a background thread
                new Thread(new Runnable() {
                  public void run() {
                    try {
                      MainFrame.lockGUI("Creating Figure Eight job");
                      try {
                        @SuppressWarnings("unchecked")
                        List<List<String>> commonOptions = optionsTableModel.getDataVector();
                        
                        jobBuilder.setJobId(jobBuilder.crowdFlowerClient.createClassificationJob(
                                panel.m_title.getText().trim(),
                                panel.m_instructions.getText(),
                                panel.m_caption.getText().trim(),
                                commonOptions,
                                panel.m_commentCheckbox.isSelected()
                                  ? panel.m_commentCaption.getText().trim()
                                  : null));
                        // if the creation was successful we can dispose the dialog
                        SwingUtilities.invokeLater(new Runnable() {
                          public void run() {
                            dialog.dispose();
                          }
                        });
                      } finally {
                        MainFrame.unlockGUI();
                      }
                    } catch(Exception ex) {
                      ex.printStackTrace();
                      SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                          JOptionPane.showMessageDialog(dialog,
                                  "Error creating the Figure Eight job, see the messages pane for details",
                                  "Error creating job",
                                  JOptionPane.ERROR_MESSAGE);
                          dialog.setVisible(true);
                        }
                      });
                    }
                  }
                }).start();
              }
            } else {
              // cancel or close
              dialog.setVisible(false);
              dialog.dispose();
            }
          }
        }
      });
      dialog.setResizable(true);
      dialog.pack();
      dialog.setLocationRelativeTo(dialog.getParent());
      dialog.setVisible(true);
    } catch(Exception ex) {
      throw new GateRuntimeException(ex);
    }
  }

  protected boolean verify(NewClassificationJobForm panel) {
    boolean valid = true;
    JLabel statusLabel = panel.m_status;
    if("".equals(panel.m_title.getText().trim())) {
      statusLabel.setText("<html><p>A job title is required</p></html>");
      statusLabel.setIcon(MainFrame.getIcon("Invalid"));
      valid = false;
    } else if("".equals(panel.m_caption.getText().trim())) {
      statusLabel.setText("<html><p>A caption is required</p></html>");
      statusLabel.setIcon(MainFrame.getIcon("Invalid"));
      valid = false;
    } else if(panel.m_commentCheckbox.isSelected()
            && "".equals(panel.m_commentCaption.getText().trim())) {
      statusLabel.setText("<html><p>A caption for the \"comment\" field is required</p></html>");
      statusLabel.setIcon(MainFrame.getIcon("Invalid"));
      valid = false;      
    }
    return valid;
  }
  
  protected void initListeners(NewClassificationJobForm panel) {
    final JLabel commentLabel = panel.m_commentCaptionLabel;
    final JTextField commentField = panel.m_commentCaption;
    final JCheckBox commentCheckbox = panel.m_commentCheckbox;
    commentCheckbox.addChangeListener(new ChangeListener() {
      
      @Override
      public void stateChanged(ChangeEvent e) {
        commentLabel.setEnabled(commentCheckbox.isSelected());
        commentField.setEnabled(commentCheckbox.isSelected());
      }
    });
  }


}
