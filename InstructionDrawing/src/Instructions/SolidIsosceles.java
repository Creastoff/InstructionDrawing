package Instructions;

public class SolidIsosceles extends Instruction{
	private static String instruction = "SOLID_ISOSCELES", about = "SOLID_ISOSCELES <WIDTH> <HEIGHT>: Draws a solid isosceles triangle from the current X & Y coordinates with the given width and height.\n";
	private static int numParameters = 2;
	private int width, height;
	
	public SolidIsosceles() {
		super(instruction, numParameters, about);
	}
	
	public SolidIsosceles(String[] parameters, boolean isValid, String ValidityReason, int lineNumber) {
		super(instruction, numParameters, about);
		this.isValid = isValid;
		this.ValidityReason = ValidityReason;
		this.lineNumber = lineNumber;
		
		if(isValid) {
			width = Integer.parseInt(parameters[1]);
			height = Integer.parseInt(parameters[2]);
		}
	}
	
	public static SolidIsosceles Check(String[] parameters, int lineNumber) {
		boolean isValid = true;
		String ValidityReason = "";
		int width, height;
		
		//Check if the correct amount of parameters are passed
		if(parameters.length - 1 != 2) {
			isValid = false;
			ValidityReason = "2 Numbers Are Expected";
			return new SolidIsosceles(parameters, isValid, ValidityReason, lineNumber);
		}
		
		//Check the strings can be converted to numbers successfully
		try{
			width = Integer.parseInt(parameters[1]);
			height = Integer.parseInt(parameters[2]);
		} catch (NumberFormatException ex) {
			isValid = false;
			ValidityReason = "Only Numbers Are Allowed";
			return new SolidIsosceles(parameters, isValid, ValidityReason, lineNumber);
		}

		//Check the numbers result in valid coordinates
		String coordinateCheck = GUI.GUIPanel.getDrawingPanel().checkShapeSize(width, height);
		if(!coordinateCheck.equals("OKAY")) {
			isValid = false;
			ValidityReason = coordinateCheck;
		}
		
		return new SolidIsosceles(parameters, isValid, ValidityReason, lineNumber);
	}
	
	public void execute() {
		GUI.GUIPanel.getDrawingPanel().drawSolidIsosceles(width, height);
	}
}