package GUI;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import Instructions.Circle;
import Instructions.Clear;
import Instructions.Colour;
import Instructions.Instruction;
import Instructions.Line;
import Instructions.Move;
import Instructions.SolidCircle;
import Instructions.Text;

public class GUIPanel {
	private static JFrame frame = new JFrame("Assignment");
	private static GraphicsPanel drawingPanel = new GraphicsPanel();
	private static InstructionPanel textPanel = new InstructionPanel();
	public static final List<Instruction> lstValidInstructions = new ArrayList<Instruction>();
	
	public GUIPanel() {
		lstValidInstructions.add(new Move());
		lstValidInstructions.add(new Line());
		lstValidInstructions.add(new Circle());
		lstValidInstructions.add(new SolidCircle());
		lstValidInstructions.add(new Clear());
		lstValidInstructions.add(new Colour());
		lstValidInstructions.add(new Text());
		
		//Initialise The Window Of The Program
		frame.setSize(1500, 800);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Create The Menu Bar
		MenuBar menuBar = new MenuBar();
		frame.setJMenuBar(menuBar);
		
		/*
		 * Create An Area For Drawing To & Writing Instructions
		*/
		//Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout());// - Sets The Layout For The Drawing & Instruction Panel
		//Add Drawing Panel & Instruction Panel
		mainPanel.add(drawingPanel);
		mainPanel.add(textPanel);

		//Joining Everything
		frame.add(mainPanel);
		frame.setVisible(true);
	}
	
	//Set The Title Of The Frame Window
	public static void UpdateTitle(String str) {
		if(!(frame.getTitle().charAt(frame.getTitle().length() - 1) == '*')) {
			frame.setTitle(str + "*");
		}
		else frame.setTitle(str);
	}
	
	//Opens A Message Box
	public static void ShowMessage(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static GraphicsPanel getDrawingPanel() {
		return GUIPanel.drawingPanel;
	}
}