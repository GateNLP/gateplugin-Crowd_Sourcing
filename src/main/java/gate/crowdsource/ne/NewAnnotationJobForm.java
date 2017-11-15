/*
 * This class was generated from newAnnotationJobForm.xml using Abeille Forms
 * Designer.
 */
package gate.crowdsource.ne;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class NewAnnotationJobForm extends JPanel
{
   JLabel m_titleLabel = new JLabel();
   JTextField m_title = new JTextField();
   JLabel m_instructionsLabel = new JLabel();
   JEditorPane m_instructions = new JEditorPane();
   JLabel m_status = new JLabel();
   JLabel m_captionLabel = new JLabel();
   JTextField m_caption = new JTextField();
   JLabel m_noEntitiesCaptionLabel = new JLabel();
   JTextField m_noEntitiesCaption = new JTextField();
   JLabel m_noEntitiesErrorLabel = new JLabel();
   JTextField m_noEntitiesError = new JTextField();
   JLabel m_commentCheckboxLabel = new JLabel();
   JLabel m_commentCaptionLabel = new JLabel();
   JCheckBox m_commentCheckbox = new JCheckBox();
   JTextField m_commentCaption = new JTextField();

   /**
    * Default constructor
    */
   public NewAnnotationJobForm()
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
      FormLayout formlayout1 = new FormLayout("FILL:4DLU:NONE,LEFT:DEFAULT:NONE,FILL:4DLU:NONE,FILL:MIN(75DLU;DEFAULT):GROW(1.0),FILL:4DLU:NONE","TOP:30DLU:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,FILL:MIN(90DLU;DEFAULT):GROW(1.0),CENTER:2DLU:NONE");
      CellConstraints cc = new CellConstraints();
      jpanel1.setLayout(formlayout1);

      m_titleLabel.setName("titleLabel");
      m_titleLabel.setText("Job title");
      m_titleLabel.setHorizontalAlignment(JLabel.TRAILING);
      m_titleLabel.setHorizontalTextPosition(JLabel.LEADING);
      jpanel1.add(m_titleLabel,new CellConstraints(2,3,1,1,CellConstraints.RIGHT,CellConstraints.DEFAULT));

      m_title.setName("title");
      m_title.setToolTipText("The title of the CrowdFlower job.");
      jpanel1.add(m_title,cc.xy(4,3));

      m_instructionsLabel.setName("instructionsLabel");
      m_instructionsLabel.setText("Instructions");
      jpanel1.add(m_instructionsLabel,cc.xywh(2,15,3,1));

      m_instructions.setName("instructions");
      m_instructions.setSelectionEnd(468);
      m_instructions.setSelectionStart(468);
      m_instructions.setText("<p>For this task you need to indicate whether each snippet of text contains any mentions of person names, and if so which words these are. Click on words to highlight them, and your task is to highlight all the words that form part of a person name.</p>\n<p>If there are <i>no</i> person names within the snippet of text, then do not highlight any words, instead tick the \"there are no person names in this sentence\" checkbox to confirm that you have verified this.</p>");
      m_instructions.setToolTipText("The instructions for the task, which will be presented to users in a collapsible panel at the top of the page.  This is interpreted as HTML, so any < and & signs must be escaped as &lt; and &amp; respectively.");
      JScrollPane jscrollpane1 = new JScrollPane();
      jscrollpane1.setViewportView(m_instructions);
      jscrollpane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      jscrollpane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      jpanel1.add(jscrollpane1,cc.xywh(2,17,3,1));

      m_status.setName("status");
      m_status.setText("<html><p>Please provide a job title, instructions, and suitable captions and error messages.</p></html>");
      jpanel1.add(m_status,cc.xywh(1,1,5,1));

      m_captionLabel.setName("captionLabel");
      m_captionLabel.setText("Task caption");
      m_captionLabel.setHorizontalAlignment(JLabel.TRAILING);
      m_captionLabel.setHorizontalTextPosition(JLabel.LEADING);
      jpanel1.add(m_captionLabel,new CellConstraints(2,5,1,1,CellConstraints.RIGHT,CellConstraints.DEFAULT));

      m_caption.setName("caption");
      m_caption.setSelectionEnd(62);
      m_caption.setSelectionStart(62);
      m_caption.setText("Please annotate any mentions of person names in this sentence.");
      m_caption.setToolTipText("<html><p>The question that will be presented to the user. This should mention the annotation type they are looking for.</p></html>");
      jpanel1.add(m_caption,cc.xy(4,5));

      m_noEntitiesCaptionLabel.setName("noEntitiesCaptionLabel");
      m_noEntitiesCaptionLabel.setText("Caption for \"no entities\" checkbox");
      m_noEntitiesCaptionLabel.setHorizontalAlignment(JLabel.TRAILING);
      m_noEntitiesCaptionLabel.setHorizontalTextPosition(JLabel.LEADING);
      jpanel1.add(m_noEntitiesCaptionLabel,new CellConstraints(2,7,1,1,CellConstraints.RIGHT,CellConstraints.DEFAULT));

      m_noEntitiesCaption.setName("noEntitiesCaption");
      m_noEntitiesCaption.setSelectionEnd(42);
      m_noEntitiesCaption.setSelectionStart(42);
      m_noEntitiesCaption.setText("There are no person names in this sentence");
      m_noEntitiesCaption.setToolTipText("<html><p>The caption for the \"there are no entities\" checkbox.</p><p>Customize as appropriate to the target annotation type and snippet type (sentence, Tweet, etc.)</p></html>");
      jpanel1.add(m_noEntitiesCaption,cc.xy(4,7));

      m_noEntitiesErrorLabel.setName("noEntitiesErrorLabel");
      m_noEntitiesErrorLabel.setText("Error message if \"no entities\" not checked");
      m_noEntitiesErrorLabel.setHorizontalTextPosition(JLabel.LEADING);
      jpanel1.add(m_noEntitiesErrorLabel,cc.xy(2,9));

      m_noEntitiesError.setName("noEntitiesError");
      m_noEntitiesError.setScrollOffset(107);
      m_noEntitiesError.setSelectionEnd(93);
      m_noEntitiesError.setSelectionStart(93);
      m_noEntitiesError.setText("If there are no person names in this sentence, you must confirm this by ticking the checkbox.");
      m_noEntitiesError.setToolTipText("<html><p>The error message displayed if the user has not marked any annotations <i>and</i> also has not ticked the checkbox.</p><p>Customize as appropriate to the target annotation type and snippet type (sentence, Tweet, etc.)</p></html>");
      jpanel1.add(m_noEntitiesError,cc.xy(4,9));

      m_commentCheckboxLabel.setName("commentCheckboxLabel");
      m_commentCheckboxLabel.setText("Allow free-text comment");
      jpanel1.add(m_commentCheckboxLabel,new CellConstraints(2,11,1,1,CellConstraints.RIGHT,CellConstraints.DEFAULT));

      m_commentCaptionLabel.setName("commentCaptionLabel");
      m_commentCaptionLabel.setText("Comment field caption");
      jpanel1.add(m_commentCaptionLabel,new CellConstraints(2,13,1,1,CellConstraints.RIGHT,CellConstraints.DEFAULT));

      m_commentCheckbox.setName("commentCheckbox");
      m_commentCheckbox.setSelected(true);
      jpanel1.add(m_commentCheckbox,cc.xy(4,11));

      m_commentCaption.setName("commentCaption");
      m_commentCaption.setSelectionEnd(7);
      m_commentCaption.setSelectionStart(7);
      m_commentCaption.setText("Comment");
      jpanel1.add(m_commentCaption,cc.xy(4,13));

      addFillComponents(jpanel1,new int[]{ 2,3,4,5 },new int[]{ 2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18 });
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
