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
import Instructions.DashedLine;
import Instructions.Instruction;
import Instructions.InvalidInstruction;
import Instructions.Line;
import Instructions.Move;
import Instructions.Oval;
import Instructions.Rectangle;
import Instructions.SolidCircle;
import Instructions.SolidOval;
import Instructions.SolidRectangle;
import Instructions.Spiral;
import Instructions.Text;

public class InstructionFile {
	private static File file;
	private static String[] strInstructions;
	private static String[] strInstructionsAndErrors;
	
	public InstructionFile(File file) {
		InstructionFile.file = file;
		resetText();
	}
	
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
	
	public static void saveFile() {
		System.out.println(file.getName());
		GUIPanel.UpdateTitle(file.getName());
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
			Instruction executableInstruction; //Create an instance of an instruction
			
			//Create a new object based on the instruction
			switch(instructionLine[0]) {
				case "MOVE":
					executableInstruction = Move.Check(instructionLine, lineNumber);
					break;
				case "LINE":
					executableInstruction = Line.Check(instructionLine, lineNumber);
					break;
				case "DASHED_LINE":
					executableInstruction = DashedLine.Check(instructionLine, lineNumber);
					break;
				case "RECTANGLE":
					executableInstruction = Rectangle.Check(instructionLine, lineNumber);
					break;
				case "SOLID_RECTANGLE":
					executableInstruction = SolidRectangle.Check(instructionLine, lineNumber);
					break;
				case "SPIRAL":
					executableInstruction = Spiral.Check(instructionLine, lineNumber);
					break;
				case "OVAL":
					executableInstruction = Oval.Check(instructionLine, lineNumber);
					break;
				case "SOLID_OVAL":
					executableInstruction = SolidOval.Check(instructionLine, lineNumber);
					break;
				case "CIRCLE":
					executableInstruction = Circle.Check(instructionLine, lineNumber);
					break;
				case "SOLID_CIRCLE":
					executableInstruction = SolidCircle.Check(instructionLine, lineNumber);
					break;
				case "TEXT":
					executableInstruction = Text.Check(instructionLine, lineNumber);
					break;
				case "COLOUR":
					executableInstruction = Colour.Check(instructionLine, lineNumber);
					break;
				case "CLEAR":
					executableInstruction = Clear.Check(instructionLine, lineNumber);
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

	public static String[] getInstructions() {
		return strInstructions;
	}
}