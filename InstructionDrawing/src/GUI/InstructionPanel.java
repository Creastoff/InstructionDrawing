package GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Components.InstructionFile;

public class InstructionPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private static JPanel topPanel = new JPanel();
	private static JPanel bottomPanel = new JPanel();
	private static JPanel bottomRightPanel = new JPanel();
	private static JLabel lblGraphicsWidth = new JLabel(), lblGraphicsHeight = new JLabel();
	private static JTextArea txtInstructions = new JTextArea("MOVE 100 200\nCOLOUR 255 0 0\nTEXT \"This text should not be seen!\"\nCLEAR\nCOLOUR 255 255 255\nTEXT \"This is WHITE text that should be seen!\"\nMOVE 100 220\nCOLOUR 0 255 255\nLINE 300 220\nCOLOUR 255 255 0\nSOLID_CIRCLE 10\nMOVE 100 225\nLINE 300 225\nCIRCLE 10", 43, 30);
	private static String[] arrButtonTitles = {"Draw", "Clear Canvas", "Clear Text", "Reset Text"};
	
	/*
	 * Creates; Textbox with border & scrollbar to be shown when necessary
	 * 			Button to draw to the canvas
	 * 			Button to clear the canvas
	 * 			Button to clear the textbox
	 */
	public InstructionPanel() {
		//Label
		Dimension graphicsSize = GraphicsPanel.getGraphicsSize();
		lblGraphicsWidth.setText("Horizontal Maximum: " + graphicsSize.getWidth());
		lblGraphicsHeight.setText("Vertical Maximum: " + graphicsSize.getHeight());
		
		//Text Box
		Border border = BorderFactory.createLineBorder(Color.black);
		txtInstructions.setBorder(border);
		txtInstructions.getDocument().addDocumentListener(new TextAreaListener());
		JScrollPane scrollPane = new JScrollPane(txtInstructions, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//Create Buttons & Add Listeners
		for(String str : arrButtonTitles) {
			JButton temp = new JButton(str);
			temp.addActionListener(new ButtonListener());
			bottomRightPanel.add(temp);
		}
		
		//Label Panel
		topPanel.add(lblGraphicsWidth);
		topPanel.add(lblGraphicsHeight);
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		
		//TextBox & Button Panel
		bottomPanel.add(scrollPane);
		bottomPanel.add(bottomRightPanel);
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		bottomRightPanel.setLayout(new BoxLayout(bottomRightPanel, BoxLayout.Y_AXIS));
		
		//Added Together
		add(topPanel);
		add(bottomPanel);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	
	//Update The Text Panel To Reflect A File Contents
	public static void UpdateTextContents(String[] instructions) {
		txtInstructions.setText("");
		for(String str : instructions) {
			if(str != null) {
				txtInstructions.append(str + "\n");
			}
		}
	}
	
	//Return The Current Contents Of The Instruction Contents
	public static JTextArea getInstructionBox() {
		return InstructionPanel.txtInstructions;
	}
	
	//Update The Title Of The Window If The Text Has Been Modified
	private class TextAreaListener implements DocumentListener{
		public void changedUpdate(DocumentEvent e) {
			GUIPanel.onTextBoxUpdate();
		}

		public void insertUpdate(DocumentEvent e) {
			GUIPanel.onTextBoxUpdate();
		}

		public void removeUpdate(DocumentEvent e) {
			GUIPanel.onTextBoxUpdate();
		}
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
		
		//Attempt To Execute The Instructions If The Text Box Is Not Empty
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
		
		//Resets Text If There Is A Loaded File
		public void ResetText() {
			InstructionFile.resetText();
		}
	}
}