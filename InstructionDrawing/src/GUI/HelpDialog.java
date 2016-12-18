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
	InformationArea informationPanel = new InformationArea("My 2nd Year University Assignment. Designed To Take User Input (Instructions) & Output Them As Graphics"); //Panel To Display Information
    
	public HelpDialog() {
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
						informationPanel.setText("My 2nd Year University Assignment. Designed To Take User Input (Instructions) & Output Them As Graphics");
						break;
					case "Instructions":
						informationPanel.setText("Instructions");
						break;
					case "Controls":
						informationPanel.setText("Controls");
						break;
				}
			}
	    });
	    
	    //Add everything together
		add(list);
		add(informationPanel);
		setSize(800, 800);
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