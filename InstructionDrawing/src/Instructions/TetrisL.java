package Instructions;

public class TetrisL extends Instruction{
	private static String instruction = "TETRIS_L", about = "TETRIS_L <SIDE> <WIDTH> <HEIGHT>: Draws a clear L block from the video game Tetris from the current X & Y coordinates on the given side with the given width and height.\n";
	private static int numParameters = 2;
	private int side, width, height;
	
	//Called For Comparison & Instruction Directions
	public TetrisL() {
		super(instruction, numParameters, about);
	}

	//Called For Later Execution Only After Check Has Completed Successfully
	public TetrisL(String[] parameters, boolean isValid, String validityReason, int lineNumber) {
		super(instruction, numParameters, about);
		this.isValid = isValid;
		this.validityReason = validityReason;
		this.lineNumber = lineNumber;
		
		if(isValid) {
			side = Integer.parseInt(parameters[1]);
			width = Integer.parseInt(parameters[2]);
			height = Integer.parseInt(parameters[3]);
		}
	}

	//Called To Make Sure The Parameters Are Valid
	public static TetrisL Check(String[] parameters, int lineNumber) {
		boolean isValid = true;
		String validityReason = "";
		int side, width, height;
		
		//Check if the correct amount of parameters are passed
		if(parameters.length - 1 != 3) {
			isValid = false;
			validityReason = "3 Numbers Are Expected";
			return new TetrisL(parameters, isValid, validityReason, lineNumber);
		}
		
		//Check the strings can be converted to numbers successfully
		try{
			side = Integer.parseInt(parameters[1]);
			width = Integer.parseInt(parameters[2]);
			height = Integer.parseInt(parameters[3]);
		} catch (NumberFormatException ex) {
			isValid = false;
			validityReason = "Only Numbers Are Allowed";
			return new TetrisL(parameters, isValid, validityReason, lineNumber);
		}

		//Check the side is valid
		if(side < 0 && side > 3) {
			isValid = false;
			validityReason = "The Side Must Be 0-3";
			return new TetrisL(parameters, isValid, validityReason, lineNumber);
		}
		
		//Check the numbers are valid coordinates
		String coordinateCheck = GUI.GUIPanel.getDrawingPanel().checkShapeSize(width, height);
		if(!coordinateCheck.equals("OKAY")) {
			isValid = false;
			validityReason = coordinateCheck;
		}
		
		return new TetrisL(parameters, isValid, validityReason, lineNumber);
	}
	
	public void execute() {
		GUI.GUIPanel.getDrawingPanel().drawTetrisL(side, width, height);
	}
}