package Instructions;

public class Circle extends Instruction{
	private static String instruction = "CIRCLE", about = "CIRCLE <R>: Draws a clear circle at the current X & Y coordinates with the given radius.\n";
	private static int numParameters = 1;
	private int r;
	
	public Circle() {
		super(instruction, numParameters, about);
	}
	
	public Circle(String[] parameters, boolean isValid, String ValidityReason, int lineNumber) {
		super(instruction, numParameters, about);
		this.isValid = isValid;
		this.ValidityReason = ValidityReason;
		this.lineNumber = lineNumber;
		
		if(isValid) {
			r = Integer.parseInt(parameters[1]);
		}
	}
	
	public static Circle Check(String[] parameters, int lineNumber) {
		boolean isValid = true;
		String ValidityReason = "";
		int r;
		
		//Check if the correct amount of parameters are passed
		if(parameters.length - 1 != 1) {
			isValid = false;
			ValidityReason = "1 Number Is Expected";
			return new Circle(parameters, isValid, ValidityReason, lineNumber);
		}
		
		//Check the strings can be converted to numbers successfully
		try{
			r = Integer.parseInt(parameters[1]);
		} catch (NumberFormatException ex) {
			isValid = false;
			ValidityReason = "Only Numbers Are Allowed";
			return new Circle(parameters, isValid, ValidityReason, lineNumber);
		}

		//Check if the radius is a negative number
		if(r <= 0) {
			isValid = false;
			ValidityReason = "Circle Radius Must Be A Positive Number";
		}
		
		return new Circle(parameters, isValid, ValidityReason, lineNumber);
	}
	
	public void execute() {
		GUI.GUIPanel.getDrawingPanel().drawCircle(r);
	}
}
