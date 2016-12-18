package Components;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import GUI.GUIPanel;
import GUI.InstructionPanel;
import Instructions.Circle;
import Instructions.Clear;
import Instructions.Colour;
import Instructions.Instruction;
import Instructions.InvalidInstruction;
import Instructions.Line;
import Instructions.Move;
import Instructions.SolidCircle;
import Instructions.Text;

public class InstructionFile {
	private static File file;
	private static String[] strInstructions;
	
	public InstructionFile(File file) {
		InstructionFile.file = file;

		//Sets the instructions array to the file contents
		List<String> lstLines = new ArrayList<String>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(InstructionFile.file));
			String line;
			
			while((line = reader.readLine()) != null) {
				lstLines.add(line);
			}
			
			reader.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			strInstructions = lstLines.toArray(new String[]{});
		}
		
		GUI.InstructionPanel.UpdateTextContents(strInstructions); //Update The Text Box Content
		GUIPanel.UpdateTitle(InstructionFile.file.getName()); //Update The Frame's Title
	}
	
	public static void saveFile() {
		System.out.println(file.getName());
		GUIPanel.UpdateTitle(file.getName());
	}
	
	//Updates the instructions variable with the textbox contents
	public static void parseInstructions() {
		strInstructions = InstructionPanel.getInstructionBox().getText().split("\n");
		//Used to collect any errors & execute instructions
		Instruction[] instructions = new Instruction[strInstructions.length];
		int lineNumber = 1;
		
		//Loop over each line
		for(String line : strInstructions) {
			//Create an array with each part of the line
			String[] instructionLine = line.split(" ");
			Instruction executableInstruction; //Create an instance of an instruction
			
			//Create a new object based on the instruction
			switch(instructionLine[0]) {
				case "LINE":
					executableInstruction = Line.Check(instructionLine, lineNumber);
					break;
				case "MOVE":
					executableInstruction = Move.Check(instructionLine, lineNumber);
					break;
				case "CIRCLE":
					executableInstruction = Circle.Check(instructionLine, lineNumber);
					break;
				case "SOLID_CIRCLE":
					executableInstruction = SolidCircle.Check(instructionLine, lineNumber);
					break;
				case "CLEAR":
					executableInstruction = Clear.Check(instructionLine, lineNumber);
					break;
				case "COLOUR":
					executableInstruction = Colour.Check(instructionLine, lineNumber);
					break;
				case "TEXT":
					executableInstruction = Text.Check(instructionLine, lineNumber);
					break;
				default:
					executableInstruction = new InvalidInstruction(instructionLine, lineNumber);
					break;
			}
			instructions[lineNumber - 1] = executableInstruction;
			lineNumber++;
		}
		
		//Check if the instruction is valid - if not add the reason for its invalidity to the list
		List<String> lstErrors = new ArrayList<String>();		
		for(Instruction instruction : instructions) {
			if(!instruction.getIsValid()) {
				lstErrors.add("Line " + instruction.getLineNumber() + ": " + instruction.getValidityReason());
			}
		}
		
		//If there are errors then output them
		if(lstErrors.size() != 0) {
			System.out.println("Outputing");
			for(String str : lstErrors) {
				System.out.println(str);
			}
		}//If there aren't any errors then execute the instructions instead
		else {
			System.out.println("Executing");
			for(Instruction instruction : instructions) {
				instruction.execute();
			}
		}
	}

	public static String[] getInstructions() {
		return strInstructions;
	}
}