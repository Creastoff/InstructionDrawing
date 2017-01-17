package Instructions;

public class Text extends Instruction{
	private static String instruction = "TEXT", about = "TEXT \"<TEXT-HERE>\": Draws text at the current X & Y coordinates.\n";
	private static int numParameters = 1;
	private String text = "";

	//Called For Comparison & Instruction Directions
	public Text() {
		super(instruction, numParameters, about);
	}

	//Called For Later Execution Only After Check Has Completed Successfully
	public Text(String[] parameters, boolean isValid, String validityReason, int lineNumber) {
		super(instruction, numParameters, about);
		this.isValid = isValid;
		this.validityReason = validityReason;
		this.lineNumber = lineNumber;
		
		if(isValid) {
			for(int i = 1; i < parameters.length; i++) {
				text += parameters[i] + " ";
			}
		}
	}

	//Called To Make Sure The Parameters Are Valid
	public static Text Check(String[] parameters, int lineNumber) {
		boolean isValid = true;
		String validityReason = "", text = "";
		
		//Check if the amount of parameters is greater than 1
		if(!(parameters.length > 1)) {
			isValid = false;
			validityReason = "Quoted Text Must Follow The Text Instruction";
			return new Text(parameters, isValid, validityReason, lineNumber);
		}
		
		//Concatenate Parameters To Make One String
		for(int i = 1; i < parameters.length; i++) {
			text += parameters[i] + " ";
		}
		
		//Check if the first parameter's first value is a quotation
		//Check if the last parameter's last value is a quotation
		char startChar = text.charAt(0);
		char endChar = text.charAt(text.length() - 2);
		
		if(!((startChar == '"') && (endChar == '"'))) {
			isValid = false;
			validityReason = "Text Must Be Enclosed With Quotation Marks";
		}
		
		return new Text(parameters, isValid, validityReason, lineNumber);
	}
	
	public void execute() {
		GUI.GUIPanel.getDrawingPanel().drawText(text);
	}
}
