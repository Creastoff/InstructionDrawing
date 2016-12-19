package Instructions;
import java.awt.Color;

public class Colour extends Instruction{
	private static String instruction = "COLOUR", about = "COLOUR <R> <G> <B>: Changes the current pen colour to the given Red, Green, & Blue Values.\n";;
	private static int numParameters = 3;
	private Color colour;
	
	public Colour() {
		super(instruction, numParameters, about);
	}

	public Colour(String[] parameters, boolean isValid, String ValidityReason, int lineNumber) {
		super(instruction, numParameters, about);
		this.isValid = isValid;
		this.ValidityReason = ValidityReason;
		this.lineNumber = lineNumber;
		
		if(isValid) {
			colour = new Color(Integer.parseInt(parameters[1]), Integer.parseInt(parameters[2]), Integer.parseInt(parameters[3]));
		}
	}
	
	public static Colour Check(String[] parameters, int lineNumber) {
		boolean isValid = true;
		String ValidityReason = "";
		int r, g, b;
		
		//Check if the correct amount of parameters are passed
		if(parameters.length - 1 != 3) {
			isValid = false;
			ValidityReason = "3 Numbers Are Expected";
			return new Colour(parameters, isValid, ValidityReason, lineNumber);
		}
		
		//Check the strings can be converted to numbers successfully
		try{
			r = Integer.parseInt(parameters[1]);
			g = Integer.parseInt(parameters[2]);
			b = Integer.parseInt(parameters[3]);
		} catch (NumberFormatException ex) {
			isValid = false;
			ValidityReason = "Only Numbers Are Allowed. They must no smaller than 0 and no larger than 255";
			return new Colour(parameters, isValid, ValidityReason, lineNumber);
		}
		
		//Check the numbers are valid RGB numbers
		String colourCheck = checkRGB(r, g, b);
		if(!colourCheck.equals("OKAY")) {
			isValid = false;
			ValidityReason = colourCheck;
		}
		
		return new Colour(parameters, isValid, ValidityReason, lineNumber);
	}
	
	public static String checkRGB(int r, int g, int b) {
		String message = "OKAY";
		
		//Red, Green & Blue
		if((r < 0 || r > 255) || (g < 0 || g > 255) || (b < 0 || b > 255)) {
			message = "All 3 values must no smaller than 0 and no larger than 255";
		}
		
		return message;
	}
	
	public void execute() {
		GUI.GUIPanel.getDrawingPanel().setColor(colour);
	}
}
