package Instructions;

public class DashedLine extends Instruction{
	private static String instruction = "DASHED_LINE", about = "DASHED_LINE <X> <Y>: Draws a dashed line from the current X & Y coordinates to the given X & Y coordinates.\n";
	private static int numParameters = 2;
	private int x, y;
	
	public DashedLine() {
		super(instruction, numParameters, about);
	}
	
	public DashedLine(String[] parameters, boolean isValid, String ValidityReason, int lineNumber) {
		super(instruction, numParameters, about);
		this.isValid = isValid;
		this.ValidityReason = ValidityReason;
		this.lineNumber = lineNumber;
		
		if(isValid) {
			x = Integer.parseInt(parameters[1]);
			y = Integer.parseInt(parameters[2]);
		}
	}
	
	public static DashedLine Check(String[] parameters, int lineNumber) {
		boolean isValid = true;
		String ValidityReason = "";
		int x, y;
		
		//Check if the correct amount of parameters are passed
		if(parameters.length - 1 != 2) {
			isValid = false;
			ValidityReason = "2 Numbers Are Expected";
			return new DashedLine(parameters, isValid, ValidityReason, lineNumber);
		}
		
		//Check the strings can be converted to numbers successfully
		try{
			x = Integer.parseInt(parameters[1]);
			y = Integer.parseInt(parameters[2]);
		} catch (NumberFormatException ex) {
			isValid = false;
			ValidityReason = "Only Numbers Are Allowed";
			return new DashedLine(parameters, isValid, ValidityReason, lineNumber);
		}
		
		//Check the numbers are valid coordinates
		String coordinateCheck = GUI.GUIPanel.getDrawingPanel().checkCoordinates(x, y);
		if(!coordinateCheck.equals("OKAY")) {
			isValid = false;
			ValidityReason = coordinateCheck;
		}
		
		return new DashedLine(parameters, isValid, ValidityReason, lineNumber);
	}
	
	public void execute() {
		GUI.GUIPanel.getDrawingPanel().drawDashedLine(x, y);
	}
}