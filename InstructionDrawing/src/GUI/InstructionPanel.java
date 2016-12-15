package GUI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import Components.InstructionFile;

public class InstructionPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private static JPanel instructionSubPanel = new JPanel();
	private static JTextArea txtInstructions = new JTextArea("LINE 250 250", 43, 30);
	private static String[] arrButtonTitles = {"Draw", "Clear Canvas", "Clear Text"};
	
	/*
	 * Creates; Textbox with border & scrollbar to be shown when necessary
	 * 			Button to draw to the canvas
	 * 			Button to clear the canvas
	 * 			Button to clear the textbox
	 */
	public InstructionPanel() {
		//Text Box
		Border border = BorderFactory.createLineBorder(Color.black);
		txtInstructions.setBorder(border);
		JScrollPane scrollPane = new JScrollPane(txtInstructions, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//Create Buttons & Add Listeners
		for(String str : arrButtonTitles) {
			JButton temp = new JButton(str);
			temp.addActionListener(new ButtonListener());
			instructionSubPanel.add(temp);
		}
		
		//Added Together
		this.add(scrollPane);
		instructionSubPanel.setLayout(new BoxLayout(instructionSubPanel, BoxLayout.PAGE_AXIS));
		this.add(instructionSubPanel);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
	}
	
	public static void UpdateTextContents(String[] instructions) {
		txtInstructions.setText("");
		for(String str : instructions) {
			if(str != null) {
				txtInstructions.append(str + "\n");
			}
		}
	}
	
	public static JTextArea getInstructionBox() {
		return InstructionPanel.txtInstructions;
	}
	
	/*
	 * The file menu listener - it executes the correct method depending on the button pressed,
	 * this is not however completed with a typical set of if-else statements, they are invoked with
	 * a technique called "Method Reflection". This is why the methods will appear to be unused.
	 */
	@SuppressWarnings("unused")
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for(String str : arrButtonTitles) {
				if(e.getActionCommand().equals(str)) {
					String methodName = str.replaceAll(" ", "");
					
					//Run The Method
					try {
						java.lang.reflect.Method method = this.getClass().getMethod(methodName);
						method.invoke(this);
					//Getting Method Errors
					} catch (NoSuchMethodException ex) {
						ex.printStackTrace();
					} catch (SecurityException ex) {
						ex.printStackTrace();
					//Invoking Method Errors
					} catch (InvocationTargetException ex) {
						ex.printStackTrace();
					} catch (IllegalAccessException ex) {
						ex.printStackTrace();
					}
				}
			}
		}
		
		public void Draw() {
			GUIPanel.getDrawingPanel().clear();
			if(InstructionPanel.txtInstructions.getText().equals("")) {
				GUIPanel.ShowMessage("Please write some instructions.", "Error");
			} else InstructionFile.parseInstructions();
		}
		
		public void ClearCanvas() {
			GUIPanel.getDrawingPanel().clear();
		}
		
		public void ClearText() {
			InstructionPanel.txtInstructions.setText("");
		}
	}
}