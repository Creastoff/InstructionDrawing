package GUI;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//TODO: Write Specific Content For The Help Items

public class HelpDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private static final String IntroductionText = "My 2nd Year University Assignment. Designed To Take User Input (Instructions) & Output Them As Graphics. By default it is supplied with a basic set of instructions.";
	private static final String InstructionsText = "The program accepts the following instructions with the corresponding parameters:\n\n" +
													"MOVE <X> <Y>: Moves the pen position to the given X & Y coordinates.\n" +
													"LINE <X> <Y>: Draws a line from the current X & Y coordinates to the given X & Y coordinates.\n" +
													"DASHED_LINE <X> <Y>: Draws a dashed line from the current X & Y coordinates to the given X & Y coordinates.\n" +
													"CIRCLE <R>: Draws a clear circle at the current X & Y coordinates with the given radius.\n" +
													"SOLID_CIRCLE <R>: Draws a solid circle at the current X & Y coordinates with the given radius.\n" +
													"TEXT \"<TEXT-HERE>\": Draws text at the current X & Y coordinates.\n" +
													"COLOUR <R> <G> <B>: Changes the current pen colour to the given Red, Green, & Blue Values.\n" + 
													"CLEAR: Clears the drawing panel by drawing a rectangle over the current content.\n";
	private static final String ControlsText = "There are many controls that provide with an interface for the program:\n\n" +
												"File:\n" + 
													"      Open File: Open a previously made .txt file.\n" +
													"      Save File As: Save the current text contents to a .txt file.\n" +
													"      Save Image: Save the current graphics panel content to a .png file.\n" +
													"      Quit: Quits the program and checks with the user if they wish to save the current text contents.\n" +
												"Help:\n" + 
													"      About: Displays the version number and author.\n" +
													"      Help Contents: Displays this window.\n" +
												"Instruction Panel:\n" +
													"      Draw: Translates the current instructions to the graphics panel.\n" +
													"      Clear Canvas: Clears the graphics panel by drawing a rectangle over the current content.\n" +
													"      Clear Text: Clears the current contents of the text area.\n" +
													"      Reset Text: Loads the current contents of the file into the text box if a file has been loaded.\n";
	private InformationArea informationPanel = new InformationArea(IntroductionText); //Panel To Display Information
    
	public HelpDialog() {
		informationPanel.setLineWrap(true);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setTitle("Help");

		//List Of Help Items
		String[] selections = {"Introduction", "Instructions", "Controls"};
		@SuppressWarnings({"rawtypes","unchecked"})
		JList list = new JList(selections);
	    list.setSelectedIndex(0);
	    
	    //Listener For List Box - Changes Panel Contents Depending On Selection
	    list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				String value = list.getSelectedValue().toString();
				
				switch(value) {
					case "Introduction":
						informationPanel.setText(IntroductionText);
						break;
					case "Instructions":
						informationPanel.setText(InstructionsText);
						break;
					case "Controls":
						informationPanel.setText(ControlsText);
						break;
				}
			}
	    });
	    
	    //Add everything together
		add(list);
		add(informationPanel);
		setSize(650, 400);
		setResizable(false);
	}
	
	private class InformationArea extends JTextArea {
		private static final long serialVersionUID = 1L;
		
		public InformationArea(String str) {
			setSize(new Dimension(500, 700));
			setText(str);
			setEditable(false);
		}
	}
}