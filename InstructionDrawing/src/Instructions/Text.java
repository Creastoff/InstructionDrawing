package Instructions;

public class Text extends Instruction{
	private String text = "";
	
	public Text() {
		super("TEXT", 1);
	}
	
	public Text(String[] parameters, boolean isValid, String ValidityReason, int lineNumber) {
		super("TEXT", 1);
		this.isValid = isValid;
		this.ValidityReason = ValidityReason;
		this.lineNumber = lineNumber;
		
		if(isValid) {
			for(int i = 1; i < parameters.length; i++) {
				text += parameters[i] + " ";
			}
		}
	}
	
	public static Text Check(String[] parameters, int lineNumber) {
		boolean isValid = true;
		String ValidityReason = "", text = "";
		
		//Check if the amount of parameters is greater than 1
		if(!(parameters.length > 1)) {
			isValid = false;
			ValidityReason = "Quoted Text Must Follow The Text Instruction";
			return new Text(parameters, isValid, ValidityReason, lineNumber);
		}
		
		for(int i = 1; i < parameters.length; i++) {
			text += parameters[i] + " ";
		}
		
		//Check if the first parameter's first value is a quotation
		//Check if the last parameter's last value is a quotation
		char startChar = text.charAt(0);
		char endChar = text.charAt(text.length() - 2);
		
		if(!((startChar == '"') && (endChar == '"'))) {
			isValid = false;
			ValidityReason = "Text Must Be Enclosed With Quotation Marks";
		}
		
		return new Text(parameters, isValid, ValidityReason, lineNumber);
	}
	
	public void execute() {
		
		GUI.GUIPanel.getDrawingPanel().drawText(text);
	}
}
