/*
 * This class was generated from newAnnotationJobForm.xml using Abeille Forms
 * Designer.
 */
package gate.crowdsource.classification;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class NewClassificationJobForm extends JPanel
{
   JTextField m_title = new JTextField();
   JEditorPane m_instructions = new JEditorPane();
   JTable m_commonOptions = new JTable();
   JLabel m_status = new JLabel();
   JButton m_addRowButton = new JButton();
   JButton m_removeRowButton = new JButton();
   JButton m_upButton = new JButton();
   JButton m_downButton = new JButton();
   JTextField m_caption = new JTextField();
   JLabel m_commentCheckboxLabel = new JLabel();
   JLabel m_commentCaptionLabel = new JLabel();
   JCheckBox m_commentCheckbox = new JCheckBox();
   JTextField m_commentCaption = new JTextField();

   /**
    * Default constructor
    */
   public NewClassificationJobForm()
   {
      initializePanel();
   }

   /**
    * Adds fill components to empty cells in the first row and first column of the grid.
    * This ensures that the grid spacing will be the same as shown in the designer.
    * @param cols an array of column indices in the first row where fill components should be added.
    * @param rows an array of row indices in the first column where fill components should be added.
    */
   void addFillComponents( Container panel, int[] cols, int[] rows )
   {
      Dimension filler = new Dimension(10,10);

      boolean filled_cell_11 = false;
      CellConstraints cc = new CellConstraints();
      if ( cols.length > 0 && rows.length > 0 )
      {
         if ( cols[0] == 1 && rows[0] == 1 )
         {
            /** add a rigid area  */
            panel.add( Box.createRigidArea( filler ), cc.xy(1,1) );
            filled_cell_11 = true;
         }
      }

      for( int index = 0; index < cols.length; index++ )
      {
         if ( cols[index] == 1 && filled_cell_11 )
         {
            continue;
         }
         panel.add( Box.createRigidArea( filler ), cc.xy(cols[index],1) );
      }

      for( int index = 0; index < rows.length; index++ )
      {
         if ( rows[index] == 1 && filled_cell_11 )
         {
            continue;
         }
         panel.add( Box.createRigidArea( filler ), cc.xy(1,rows[index]) );
      }

   }

   /**
    * Helper method to load an image file from the CLASSPATH
    * @param imageName the package and name of the file to load relative to the CLASSPATH
    * @return an ImageIcon instance with the specified image file
    * @throws IllegalArgumentException if the image resource cannot be loaded.
    */
   public ImageIcon loadImage( String imageName )
   {
      try
      {
         ClassLoader classloader = getClass().getClassLoader();
         java.net.URL url = classloader.getResource( imageName );
         if ( url != null )
         {
            ImageIcon icon = new ImageIcon( url );
            return icon;
         }
      }
      catch( Exception e )
      {
         e.printStackTrace();
      }
      throw new IllegalArgumentException( "Unable to load image: " + imageName );
   }

   /**
    * Method for recalculating the component orientation for 
    * right-to-left Locales.
    * @param orientation the component orientation to be applied
    */
   public void applyComponentOrientation( ComponentOrientation orientation )
   {
      // Not yet implemented...
      // I18NUtils.applyComponentOrientation(this, orientation);
      super.applyComponentOrientation(orientation);
   }

   public JPanel createPanel()
   {
      JPanel jpanel1 = new JPanel();
      FormLayout formlayout1 = new FormLayout("FILL:4DLU:NONE,LEFT:DEFAULT:NONE,FILL:4DLU:NONE,FILL:MIN(75DLU;DEFAULT):GROW(1.0),FILL:DEFAULT:NONE,FILL:4DLU:NONE","TOP:30DLU:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,FILL:MIN(90DLU;DEFAULT):GROW(1.0),CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,FILL:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:MIN(90DLU;DEFAULT):GROW(1.0),CENTER:2DLU:NONE");
      CellConstraints cc = new CellConstraints();
      jpanel1.setLayout(formlayout1);

      JLabel jlabel1 = new JLabel();
      jlabel1.setText("Job title");
      jpanel1.add(jlabel1,new CellConstraints(2,3,1,1,CellConstraints.RIGHT,CellConstraints.DEFAULT));

      m_title.setName("title");
      m_title.setToolTipText("The title of the Figure Eight job.");
      jpanel1.add(m_title,cc.xywh(4,3,2,1));

      JLabel jlabel2 = new JLabel();
      jlabel2.setText("Instructions");
      jpanel1.add(jlabel2,cc.xywh(2,11,4,1));

      m_instructions.setName("instructions");
      m_instructions.setSelectionEnd(695);
      m_instructions.setSelectionStart(695);
      m_instructions.setText("<p>This task is about selecting the correct classification for a specific named entity.  You will be presented with a snippet of text within which one entity will be highlighted.  Your task is to select the most appropriate label for the entity from the presented list of options.</p>\n<ul>\n  <li>If you are sure that none of the available options are correct then select <em>None of the above</em>.</li>\n  <li>If several of the available options are potentially appropriate, and you cannot tell from the context which is the correct one, then select <em>I cannot decide</em>.</li>\n  <li>If the highlighted text is not in fact a named entity at all, then select <em>Not an entity</em>.</li>\n</ul>");
      m_instructions.setToolTipText("The instructions for the task, which will be presented to users in a collapsible panel at the top of the page.  This is interpreted as HTML, so any < and & signs must be escaped as &lt; and &amp; respectively.");
      JScrollPane jscrollpane1 = new JScrollPane();
      jscrollpane1.setViewportView(m_instructions);
      jscrollpane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      jscrollpane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      jpanel1.add(jscrollpane1,cc.xywh(2,13,4,1));

      JLabel jlabel3 = new JLabel();
      jlabel3.setText("Common options");
      jpanel1.add(jlabel3,cc.xywh(2,15,4,1));

      m_commonOptions.setName("commonOptions");
      m_commonOptions.setSelectionBackground(new Color(153,153,153));
      m_commonOptions.setSelectionForeground(new Color(0,0,0));
      m_commonOptions.setToolTipText("Common options that should be shown for all units in this job, in addition to the specific options for each task.  Typically these are choices like \"none of the above\".");
      JScrollPane jscrollpane2 = new JScrollPane();
      jscrollpane2.setViewportView(m_commonOptions);
      jscrollpane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      jscrollpane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      jpanel1.add(jscrollpane2,cc.xywh(2,17,3,9));

      m_status.setName("status");
      m_status.setText("<html><p>Please provide a job title, instructions, and any common options you want to apply to all tasks.</p></html>");
      jpanel1.add(m_status,cc.xywh(1,1,6,1));

      m_addRowButton.setName("addRowButton");
      m_addRowButton.setToolTipText("Add a new row below the current one	");
      jpanel1.add(m_addRowButton,cc.xy(5,17));

      m_removeRowButton.setName("removeRowButton");
      m_removeRowButton.setToolTipText("Delete the selected row(s)");
      jpanel1.add(m_removeRowButton,cc.xy(5,19));

      m_upButton.setName("upButton");
      m_upButton.setToolTipText("Move the currently selected row(s) up	");
      jpanel1.add(m_upButton,cc.xy(5,21));

      m_downButton.setName("downButton");
      m_downButton.setToolTipText("Move the currently selected row(s) down");
      jpanel1.add(m_downButton,cc.xy(5,23));

      JLabel jlabel4 = new JLabel();
      jlabel4.setText("Task caption");
      jpanel1.add(jlabel4,new CellConstraints(2,5,1,1,CellConstraints.RIGHT,CellConstraints.DEFAULT));

      m_caption.setName("caption");
      m_caption.setSelectionEnd(51);
      m_caption.setSelectionStart(51);
      m_caption.setText("Which of the following describes \"{{entity}}\" best?");
      m_caption.setToolTipText("<html><p>The question that will be presented to the user.</p><p>The caption may contain a placeholder {{entity}}, which will be replaced by the text of the entity that is the target of the current task.</p></html>");
      jpanel1.add(m_caption,cc.xywh(4,5,2,1));

      m_commentCheckboxLabel.setName("commentCheckboxLabel");
      m_commentCheckboxLabel.setText("Allow free-text comment");
      jpanel1.add(m_commentCheckboxLabel,new CellConstraints(2,7,1,1,CellConstraints.RIGHT,CellConstraints.DEFAULT));

      m_commentCaptionLabel.setName("commentCaptionLabel");
      m_commentCaptionLabel.setText("Caption for comment field");
      jpanel1.add(m_commentCaptionLabel,new CellConstraints(2,9,1,1,CellConstraints.RIGHT,CellConstraints.DEFAULT));

      m_commentCheckbox.setName("commentCheckbox");
      m_commentCheckbox.setSelected(true);
      jpanel1.add(m_commentCheckbox,cc.xy(4,7));

      m_commentCaption.setName("commentCaption");
      m_commentCaption.setSelectionEnd(7);
      m_commentCaption.setSelectionStart(7);
      m_commentCaption.setText("Comment");
      jpanel1.add(m_commentCaption,cc.xywh(4,9,2,1));

      addFillComponents(jpanel1,new int[]{ 2,3,4,5,6 },new int[]{ 2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26 });
      return jpanel1;
   }

   /**
    * Initializer
    */
   protected void initializePanel()
   {
      setLayout(new BorderLayout());
      add(createPanel(), BorderLayout.CENTER);
   }


}
