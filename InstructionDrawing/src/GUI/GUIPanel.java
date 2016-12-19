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
	private static String frameTitle = " - Instruction Drawer";
	private static JFrame frame = new JFrame("Untitled" + frameTitle);
	private static GraphicsPanel drawingPanel = new GraphicsPanel();
	private static InstructionPanel textPanel = new InstructionPanel();
	public static final List<Instruction> lstValidInstructions = new ArrayList<Instruction>();
	private static final double version = 1.0;
	
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
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState( frame.getExtendedState()|JFrame.MAXIMIZED_BOTH );

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
	}
	
	public void setVisible() {
		frame.setVisible(true);
	}
	
	//Set The Title Of The Frame Window
	public static void UpdateTitle(String str) {
		frame.setTitle(str + frameTitle);
	}
	
	public static String GetFrameTitle() {
		return frame.getTitle();
	}

	//Called When The Text Box Is Edited To Change The Title
	public static void onTextBoxUpdate(String str) {
		if(!(frame.getTitle().charAt(0) == '*')) {
			frame.setTitle("*" + str);
		}
		else frame.setTitle(str);
	}
	
	public static int GetFrameWidth() {
		return frame.getWidth();
	}
	
	public static int GetFrameHeight() {
		return frame.getHeight();
	}
	
	//Opens A Message Box
	public static void ShowMessage(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static GraphicsPanel getDrawingPanel() {
		return GUIPanel.drawingPanel;
	}
	
	public static double getVersion() {
		return version;
	}
}