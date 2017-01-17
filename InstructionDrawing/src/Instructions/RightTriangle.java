package Instructions;

public class RightTriangle extends Instruction{
	private static String instruction = "RIGHT_TRIANGLE", about = "RIGHT_TRIANGLE <WIDTH> <HEIGHT>: Draws an isosceles triangle from the current X & Y coordinates with the given width and height.\n";
	private static int numParameters = 2;
	private int width, height;
	
	//Called For Comparison & Instruction Directions
	public RightTriangle() {
		super(instruction, numParameters, about);
	}
	
	//Called For Later Execution Only After Check Has Completed Successfully
	public RightTriangle(String[] parameters, boolean isValid, String validityReason, int lineNumber) {
		super(instruction, numParameters, about);
		this.isValid = isValid;
		this.validityReason = validityReason;
		this.lineNumber = lineNumber;
		
		if(isValid) {
			width = Integer.parseInt(parameters[1]);
			height = Integer.parseInt(parameters[2]);
		}
	}
	
	//Called To Make Sure The Parameters Are Valid
	public static RightTriangle Check(String[] parameters, int lineNumber) {
		boolean isValid = true;
		String validityReason = "";
		int width, height;
		
		//Check if the correct amount of parameters are passed
		if(parameters.length - 1 != 2) {
			isValid = false;
			validityReason = "2 Numbers Are Expected";
			return new RightTriangle(parameters, isValid, validityReason, lineNumber);
		}
		
		//Check the strings can be converted to numbers successfully
		try{
			width = Integer.parseInt(parameters[1]);
			height = Integer.parseInt(parameters[2]);
		} catch (NumberFormatException ex) {
			isValid = false;
			validityReason = "Only Numbers Are Allowed";
			return new RightTriangle(parameters, isValid, validityReason, lineNumber);
		}

		//Check the numbers result in valid coordinates
		String coordinateCheck = GUI.GUIPanel.getDrawingPanel().checkShapeSize(width, height);
		if(!coordinateCheck.equals("OKAY")) {
			isValid = false;
			validityReason = coordinateCheck;
		}
		
		return new RightTriangle(parameters, isValid, validityReason, lineNumber);
	}
	
	public void execute() {
		GUI.GUIPanel.getDrawingPanel().drawRightTriangle(width, height);
	}
}