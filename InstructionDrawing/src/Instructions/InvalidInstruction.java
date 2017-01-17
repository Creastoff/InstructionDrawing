package Instructions;

//Allows The Parser To Know If There Are Any Invalid Instructions And What To Output
public class InvalidInstruction extends Instruction{
	public InvalidInstruction(String[] instructionLine, int lineNumber) {
		super("INVALID", 0, "");
		this.isValid = false;
		this.validityReason = "Unknown Instruction - '" + instructionLine[0] + "'";
		this.lineNumber = lineNumber;
	}
}