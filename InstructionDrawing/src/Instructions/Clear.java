package Instructions;

public class Clear extends Instruction{

	public Clear() {
		super("CLEAR", 0);
	}

	public Clear(boolean isValid, String ValidityReason, int lineNumber) {
		super("CLEAR", 0);
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