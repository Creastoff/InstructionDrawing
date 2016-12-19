package Instructions;

public class Instruction{
	protected boolean isValid = true;
	protected String ValidityReason = "";
	private String instruction, about;
	private int numParameters;
	protected int lineNumber;
	
	protected Instruction(String instruction, int numParameters, String about) {
		this.instruction = instruction;
		this.numParameters = numParameters;
		this.about = about;
	}
	
	public void execute() {
		System.out.println("This instance is not intended to be executed");
	}
	
	public String getInstruction() {
		return this.instruction;
	}
	
	public String getAbout() {
		return this.about;
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