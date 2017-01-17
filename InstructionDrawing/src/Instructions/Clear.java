package Instructions;

public class Clear extends Instruction{
	private static String instruction = "CLEAR", about = "CLEAR: Clears the drawing panel by drawing a rectangle over the current content.\n";;
	private static int numParameters = 0;
	
	//Called For Comparison & Instruction Directions
	public Clear() {
		super(instruction, numParameters, about);
	}

	//Called For Later Execution Only After Check Has Completed Successfully
	public Clear(boolean isValid, String validityReason, int lineNumber) {
		super(instruction, numParameters, about);
		this.isValid = isValid;
		this.validityReason = validityReason;
		this.lineNumber = lineNumber;
	}
	
	//Called To Make Sure The Parameters Are Valid
	public static Clear Check(String[] parameters, int lineNumber) {
		boolean isValid = true;
		String validityReason = "";
		
		//Check if the correct amount of parameters are passed
		if(parameters.length - 1 != 0) {
			isValid = false;
			validityReason = "No Parameters Are Expected";
		}
		
		return new Clear(isValid, validityReason, lineNumber);
	}
	
	public void execute() {
		GUI.GUIPanel.getDrawingPanel().clear();
	}
}