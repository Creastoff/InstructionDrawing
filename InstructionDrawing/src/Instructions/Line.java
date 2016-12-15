package Instructions;

public class Line extends Instruction{
	private int x, y;
	
	public Line() {
		super("LINE", 2);
	}
	
	public Line(String[] parameters, boolean isValid, String ValidityReason, int lineNumber) {
		super("LINE", 2);
		this.isValid = isValid;
		this.ValidityReason = ValidityReason;
		this.lineNumber = lineNumber;
		
		if(isValid) {
			x = Integer.parseInt(parameters[1]);
			y = Integer.parseInt(parameters[2]);
		}
	}
	
	public static Line Check(String[] parameters, int lineNumber) {
		boolean isValid = true;
		String ValidityReason = "";
		int x, y;
		
		//Check if the correct amount of parameters are passed
		if(parameters.length - 1 != 2) {
			isValid = false;
			ValidityReason = "2 Numbers Are Expected";
			return new Line(parameters, isValid, ValidityReason, lineNumber);
		}
		
		//Check the strings can be converted to numbers successfully
		try{
			x = Integer.parseInt(parameters[1]);
			y = Integer.parseInt(parameters[2]);
		} catch (NumberFormatException ex) {
			isValid = false;
			ValidityReason = "Only Numbers Are Allowed";
			return new Line(parameters, isValid, ValidityReason, lineNumber);
		}
		
		//Check the numbers are valid coordinates
		String coordinateCheck = GUI.GUIPanel.getDrawingPanel().checkCoordinates(x, y);
		if(!coordinateCheck.equals("OKAY")) {
			isValid = false;
			ValidityReason = coordinateCheck;
		}
		
		return new Line(parameters, isValid, ValidityReason, lineNumber);
	}
	
	public void execute() {
		GUI.GUIPanel.getDrawingPanel().drawLine(x, y);
	}
}