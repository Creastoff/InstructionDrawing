package Instructions;

public class Rectangle extends Instruction{
	private int width, height;
	
	public Rectangle() {
		super("RECTANGLE", 2);
	}
	
	public Rectangle(String[] parameters, boolean isValid, String ValidityReason, int lineNumber) {
		super("RECTANGLE", 2);
		this.isValid = isValid;
		this.ValidityReason = ValidityReason;
		this.lineNumber = lineNumber;
		
		if(isValid) {
			width = Integer.parseInt(parameters[1]);
			height = Integer.parseInt(parameters[2]);
		}
	}
	
	public static Rectangle Check(String[] parameters, int lineNumber) {
		boolean isValid = true;
		String ValidityReason = "";
		int width, height;
		
		//Check if the correct amount of parameters are passed
		if(parameters.length - 1 != 2) {
			isValid = false;
			ValidityReason = "2 Numbers Are Expected";
			return new Rectangle(parameters, isValid, ValidityReason, lineNumber);
		}
		
		//Check the strings can be converted to numbers successfully
		try{
			width = Integer.parseInt(parameters[1]);
			height = Integer.parseInt(parameters[2]);
		} catch (NumberFormatException ex) {
			isValid = false;
			ValidityReason = "Only Numbers Are Allowed";
			return new Rectangle(parameters, isValid, ValidityReason, lineNumber);
		}

		//Check the numbers are valid coordinates
		String coordinateCheck = GUI.GUIPanel.getDrawingPanel().checkShapeSize(width, height);
		if(!coordinateCheck.equals("OKAY")) {
			isValid = false;
			ValidityReason = coordinateCheck;
		}
		
		return new Rectangle(parameters, isValid, ValidityReason, lineNumber);
	}
	
	public void execute() {
		GUI.GUIPanel.getDrawingPanel().drawRectangle(width, height);
	}
}