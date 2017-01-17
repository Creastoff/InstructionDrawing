package Instructions;

public class SolidCircle extends Instruction{
	private static String instruction = "SOLID_CIRCLE", about = "SOLID_CIRCLE <R>: Draws a solid circle at the current X & Y coordinates with the given radius.\n";
	private static int numParameters = 1;
	private int r;
	
	//Called For Comparison & Instruction Directions
	public SolidCircle() {
		super(instruction, numParameters, about);
	}
	
	//Called For Later Execution Only After Check Has Completed Successfully
	public SolidCircle(String[] parameters, boolean isValid, String validityReason, int lineNumber) {
		super(instruction, numParameters, about);
		this.isValid = isValid;
		this.validityReason = validityReason;
		this.lineNumber = lineNumber;
		
		if(isValid) {
			r = Integer.parseInt(parameters[1]);
		}
	}
	
	//Called To Make Sure The Parameters Are Valid
	public static SolidCircle Check(String[] parameters, int lineNumber) {
		boolean isValid = true;
		String validityReason = "";
		int r;
		
		//Check if the correct amount of parameters are passed
		if(parameters.length - 1 != 1) {
			isValid = false;
			validityReason = "1 Number Is Expected";
			return new SolidCircle(parameters, isValid, validityReason, lineNumber);
		}
		
		//Check the strings can be converted to numbers successfully
		try{
			r = Integer.parseInt(parameters[1]);
		} catch (NumberFormatException ex) {
			isValid = false;
			validityReason = "Only Numbers Are Allowed";
			return new SolidCircle(parameters, isValid, validityReason, lineNumber);
		}

		//Check if the radius is a negative number
		if(r <= 0) {
			isValid = false;
			validityReason = "Circle Radius Must Be A Positive Number";
		}
		
		return new SolidCircle(parameters, isValid, validityReason, lineNumber);
	}
	
	public void execute() {
		GUI.GUIPanel.getDrawingPanel().drawSolidCircle(r);
	}
}