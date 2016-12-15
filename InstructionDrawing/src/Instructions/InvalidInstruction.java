package Instructions;

public class InvalidInstruction extends Instruction{
	public InvalidInstruction(String[] instructionLine, int lineNumber) {
		super("INVALID", 0);
		this.isValid = false;
		this.ValidityReason = "Unknown Instruction - '" + instructionLine[0] + "'";
		this.lineNumber = lineNumber;
	}
}