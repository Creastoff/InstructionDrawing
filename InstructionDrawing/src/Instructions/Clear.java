package Instructions;

public class Clear extends Instruction{
	private static String instruction = "CLEAR", about = "CLEAR: Clears the drawing panel by drawing a rectangle over the current content.\n";;
	private static int numParameters = 0;
	
	public Clear() {
		super(instruction, numParameters, about);
	}

	public Clear(boolean isValid, String ValidityReason, int lineNumber) {
		super(instruction, numParameters, about);
		this.isValid = isValid;
		this.ValidityReason = ValidityReason;
		this.lineNumber = lineNumber;
	}
	
	public static Clear Check(String[] parameters, int lineNumber) {
		boolean isValid = true;
		String ValidityReason = "";
		
		//Check if the correct amount of parameters are passed
		if(parameters.length - 1 != 0) {
			isValid = false;
			ValidityReason = "No Parameters Are Expected";
		}
		
		return new Clear(isValid, ValidityReason, lineNumber);
	}
	
	public void execute() {
		GUI.GUIPanel.getDrawingPanel().clear();
	}
}