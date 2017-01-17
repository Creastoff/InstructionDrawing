package Instructions;

public class SolidOval extends Instruction{
	private static String instruction = "SOLID_OVAL", about = "SOLID_OVAL <WIDTH> <HEIGHT>: Draws a solid oval at the current X & Y coordinates with the given width and height.\n";
	private static int numParameters = 2;
	private int r1, r2;
	
	//Called For Comparison & Instruction Directions
	public SolidOval() {
		super(instruction, numParameters, about);
	}
	
	//Called For Later Execution Only After Check Has Completed Successfully
	public SolidOval(String[] parameters, boolean isValid, String validityReason, int lineNumber) {
		super(instruction, numParameters, about);
		this.isValid = isValid;
		this.validityReason = validityReason;
		this.lineNumber = lineNumber;
		
		if(isValid) {
			r1 = Integer.parseInt(parameters[1]);
			r2 = Integer.parseInt(parameters[2]);
		}
	}
	
	//Called To Make Sure The Parameters Are Valid
	public static SolidOval Check(String[] parameters, int lineNumber) {
		boolean isValid = true;
		String validityReason = "";
		int r1, r2;
		
		//Check if the correct amount of parameters are passed
		if(parameters.length - 1 != 2) {
			isValid = false;
			validityReason = "1 Number Is Expected";
			return new SolidOval(parameters, isValid, validityReason, lineNumber);
		}
		
		//Check the strings can be converted to numbers successfully
		try{
			r1 = Integer.parseInt(parameters[1]);
			r2 = Integer.parseInt(parameters[2]);
		} catch (NumberFormatException ex) {
			isValid = false;
			validityReason = "Only Numbers Are Allowed";
			return new SolidOval(parameters, isValid, validityReason, lineNumber);
		}

		//Check if the radius is a negative number
		if(r1 <= 0 || r2 <= 0) {
			isValid = false;
			validityReason = "Oval Height & Width Must Be A Positive Number";
		}
		
		return new SolidOval(parameters, isValid, validityReason, lineNumber);
	}
	
	public void execute() {
		GUI.GUIPanel.getDrawingPanel().drawSolidOval(r1, r2);
	}
}