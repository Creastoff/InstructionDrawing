package Instructions;

public class Spiral extends Instruction{
	private int width, height, lineDensity, size;
	
	public Spiral() {
		super("SPIRAL", 4);
	}
	
	public Spiral(String[] parameters, boolean isValid, String ValidityReason, int lineNumber) {
		super("SPIRAL", 4);
		this.isValid = isValid;
		this.ValidityReason = ValidityReason;
		this.lineNumber = lineNumber;
		
		if(isValid) {
			width = Integer.parseInt(parameters[1]);
			height = Integer.parseInt(parameters[2]);
			lineDensity = Integer.parseInt(parameters[3]);
			size = Integer.parseInt(parameters[4]);
		}
	}
	
	public static Spiral Check(String[] parameters, int lineNumber) {
		boolean isValid = true;
		String ValidityReason = "";
		int width, height, lineDensity, size;
		
		//Check if the correct amount of parameters are passed
		if(parameters.length - 1 != 4) {
			isValid = false;
			ValidityReason = "4 Numbers Are Expected";
			return new Spiral(parameters, isValid, ValidityReason, lineNumber);
		}
		
		//Check the strings can be converted to numbers successfully
		try{
			width = Integer.parseInt(parameters[1]);
			height = Integer.parseInt(parameters[2]);
			lineDensity = Integer.parseInt(parameters[3]);
			size = Integer.parseInt(parameters[4]);
		} catch (NumberFormatException ex) {
			isValid = false;
			ValidityReason = "Only Numbers Are Allowed";
			return new Spiral(parameters, isValid, ValidityReason, lineNumber);
		}
		
		//Check the numbers are valid - width and height can be as high as possible
		if(width < 0 || height < 0 || lineDensity < 0 || size < 0) {
			isValid = false;
			ValidityReason = "Only Positive Numbers Are Allowed";
		}
		
		return new Spiral(parameters, isValid, ValidityReason, lineNumber);
	}
	
	public void execute() {
		GUI.GUIPanel.getDrawingPanel().drawSpiral(width, height, lineDensity, size);
	}
}