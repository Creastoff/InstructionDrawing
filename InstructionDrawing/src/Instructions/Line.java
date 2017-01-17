package Instructions;

public class Line extends Instruction{
	private static String instruction = "LINE", about = "LINE <X> <Y>: Draws a line from the current X & Y coordinates to the given X & Y coordinates.\n";
	private static int numParameters = 2;
	private int x, y;
	
	//Called For Comparison & Instruction Directions
	public Line() {
		super(instruction, numParameters, about);
	}
	
	//Called For Later Execution Only After Check Has Completed Successfully
	public Line(String[] parameters, boolean isValid, String validityReason, int lineNumber) {
		super(instruction, numParameters, about);
		this.isValid = isValid;
		this.validityReason = validityReason;
		this.lineNumber = lineNumber;
		
		if(isValid) {
			x = Integer.parseInt(parameters[1]);
			y = Integer.parseInt(parameters[2]);
		}
	}
	
	//Called To Make Sure The Parameters Are Valid
	public static Line Check(String[] parameters, int lineNumber) {
		boolean isValid = true;
		String validityReason = "";
		int x, y;
		
		//Check if the correct amount of parameters are passed
		if(parameters.length - 1 != 2) {
			isValid = false;
			validityReason = "2 Numbers Are Expected";
			return new Line(parameters, isValid, validityReason, lineNumber);
		}
		
		//Check the strings can be converted to numbers successfully
		try{
			x = Integer.parseInt(parameters[1]);
			y = Integer.parseInt(parameters[2]);
		} catch (NumberFormatException ex) {
			isValid = false;
			validityReason = "Only Numbers Are Allowed";
			return new Line(parameters, isValid, validityReason, lineNumber);
		}
		
		//Check the numbers are valid coordinates
		String coordinateCheck = GUI.GUIPanel.getDrawingPanel().checkCoordinates(x, y);
		if(!coordinateCheck.equals("OKAY")) {
			isValid = false;
			validityReason = coordinateCheck;
		}
		
		return new Line(parameters, isValid, validityReason, lineNumber);
	}
	
	public void execute() {
		GUI.GUIPanel.getDrawingPanel().drawLine(x, y);
	}
}