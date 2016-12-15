package Instructions;

public class Instruction{
	protected boolean isValid = true;
	protected String ValidityReason = "";
	private String instruction;
	private int numParameters;
	protected int lineNumber;
	
	protected Instruction(String instruction, int numParameters) {
		this.instruction = instruction;
		this.numParameters = numParameters;
	}
	
	public void execute() {
		System.out.println("This instance is not intended to be executed");
	}
	
	public String getInstruction() {
		return this.instruction;
	}
	
	public int getNumParameters() {
		return this.numParameters;
	}
	
	public int getLineNumber() {
		return this.lineNumber;
	}
	
	public String toString() {
		if(numParameters == 1) return this.instruction + " takes " + this.numParameters + " parameter.";
		else return this.instruction + " takes " + this.numParameters + " parameters.";
	}
	
	public boolean getIsValid() {
		return this.isValid;
	}
	
	public String getValidityReason() {
		return this.ValidityReason;
	}
}