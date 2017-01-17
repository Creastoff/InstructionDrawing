package Components;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import GUI.GUIPanel;
import GUI.InstructionPanel;
import Instructions.Instruction;
import Instructions.InvalidInstruction;

public class InstructionFile {
	private static File file;
	private static String[] strInstructions;
	private static String[] strInstructionsAndErrors;
	
	//Permanently Store The Loaded File
	public InstructionFile(File file) {
		InstructionFile.file = file;
		resetText();
	}
	
	//Reset The Text To The Loaded File
	public static void resetText() {
		if(file != null) {
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
		} else GUIPanel.ShowMessage("Please Select A File Before Attempting To Reset", "Please Select A File");
	}
	
	//Updates the instructions variable with the textbox contents
	public static void parseInstructions() {
		strInstructions = InstructionPanel.getInstructionBox().getText().split("\n");
		strInstructionsAndErrors = strInstructions;
		//Used to collect any errors & execute instructions
		Instruction[] instructions = new Instruction[strInstructions.length];
		int lineNumber = 1;
		
		//Loop over each line
		for(String line : strInstructions) {
			//Create an array with each part of the line
			String[] instructionLine = line.split(" ");
			Object executableInstruction = null; //An instance of an instruction
			
			//Create a new object based on the instruction with method reflection
			for(Instruction instruction : GUIPanel.getValidInstructions()) {
				if(instructionLine[0].equals(instruction.getInstruction())) {
					Method instructionMethod;
					try {
						instructionMethod = instruction.getClass().getMethod("Check", String[].class, int.class);
						executableInstruction = instructionMethod.invoke(instruction, instructionLine, lineNumber);
					} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
			
			//If the instruction cannot be found then create an invalid instruction object
			if(executableInstruction == null) {
				executableInstruction = new InvalidInstruction(instructionLine, lineNumber);
			}
			
			instructions[lineNumber - 1] = (Instruction) executableInstruction;
			lineNumber++;
		}
		
		//Check if the instruction is valid - if not add the reason for its invalidity to the list
		List<String> lstErrors = new ArrayList<String>();		
		for(Instruction instruction : instructions) {
			if(!instruction.getIsValid()) {
				strInstructionsAndErrors[instruction.getLineNumber() - 1] += ": " + instruction.getValidityReason();
				lstErrors.add("Line " + instruction.getLineNumber() + ": " + instruction.getValidityReason());
			}
		}
		
		//If there are errors then output them
		if(lstErrors.size() != 0) {			
			InstructionPanel.UpdateTextContents(strInstructionsAndErrors);
		}//If there aren't any errors then execute the instructions instead
		else {
			for(Instruction instruction : instructions) {
				instruction.execute();
			}
		}
	}
}