package Instructions;

public class Spiral extends Instruction{
	private static String instruction = "SPIRAL", about = "SPIRAL <WIDTH> <HEIGHT> <DENSITY> <SIZE>: Draws a spiral from the current X & Y coordinates with the given width and height.\n		The line density is how close each new line is to the last and the size is for how long it should spiral for.\n\n";
	private static int numParameters = 4;
	private int width, height, lineDensity, size;
	
	//Called For Comparison & Instruction Directions
	public Spiral() {
		super(instruction, numParameters, about);
	}
	
	//Called For Later Execution Only After Check Has Completed Successfully
	public Spiral(String[] parameters, boolean isValid, String validityReason, int lineNumber) {
		super(instruction, numParameters, about);
		this.isValid = isValid;
		this.validityReason = validityReason;
		this.lineNumber = lineNumber;
		
		if(isValid) {
			width = Integer.parseInt(parameters[1]);
			height = Integer.parseInt(parameters[2]);
			lineDensity = Integer.parseInt(parameters[3]);
			size = Integer.parseInt(parameters[4]);
		}
	}

	//Called To Make Sure The Parameters Are Valid
	public static Spiral Check(String[] parameters, int lineNumber) {
		boolean isValid = true;
		String validityReason = "";
		int width, height, lineDensity, size;
		
		//Check if the correct amount of parameters are passed
		if(parameters.length - 1 != 4) {
			isValid = false;
			validityReason = "4 Numbers Are Expected";
			return new Spiral(parameters, isValid, validityReason, lineNumber);
		}
		
		//Check the strings can be converted to numbers successfully
		try{
			width = Integer.parseInt(parameters[1]);
			height = Integer.parseInt(parameters[2]);
			lineDensity = Integer.parseInt(parameters[3]);
			size = Integer.parseInt(parameters[4]);
		} catch (NumberFormatException ex) {
			isValid = false;
			validityReason = "Only Numbers Are Allowed";
			return new Spiral(parameters, isValid, validityReason, lineNumber);
		}
		
		//Check the numbers are valid - width and height can be as high as possible
		if(width < 0 || height < 0 || lineDensity < 0 || size < 0) {
			isValid = false;
			validityReason = "Only Positive Numbers Are Allowed";
		}
		
		return new Spiral(parameters, isValid, validityReason, lineNumber);
	}
	
	public void execute() {
		GUI.GUIPanel.getDrawingPanel().drawSpiral(width, height, lineDensity, size);
	}
}