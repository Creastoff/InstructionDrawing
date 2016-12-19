package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import Components.InstructionFile;

	//Dynamically Creates A Menu Bar Depending On What Is Inside The Below Array
	public class MenuBar extends JMenuBar {
		private static final long serialVersionUID = 1L;
		private static final String[][] arrMenuNames = 	{ 
												{"File", "The File Menu, Allowing A User To; Load, Save, & Export Images", "F", "Open File", "O", "Save File As", "S", "Save Image", "I", "Quit", "Q"},
												{"Help", "The Help Menu, Displaying Information About The Program & How To Use It", "H", "About", "A", "Help Contents", "H"}
											};

		public MenuBar() {
			char mnemonic;
			//Creates A Temporary Menu Item & Corresponding Sub-Items Which Is Added To The Menu Bar
			for(int i = 0; i < arrMenuNames.length; i++) {
				JMenu tempMenu = new JMenu(arrMenuNames[i][0]);
				tempMenu.getAccessibleContext().setAccessibleDescription(arrMenuNames[i][1]);
				mnemonic = arrMenuNames[i][2].charAt(0);
				tempMenu.setMnemonic(mnemonic);
				
				//Creates Sub-Items For The Menu Bar Item
				for(int j = 3; j < arrMenuNames[i].length; j+=2) {
					JMenuItem menuItem = new JMenuItem(arrMenuNames[i][j]);
					menuItem.getAccessibleContext().setAccessibleDescription(arrMenuNames[i][j]);
					mnemonic = arrMenuNames[i][j+1].charAt(0);
					menuItem.setMnemonic(mnemonic);
					menuItem.addActionListener(new MenuActionListener());
					menuItem.setEnabled(true);
					
					tempMenu.add(menuItem);
				}
				
				this.add(tempMenu);
			}
		}
		
		/*
		 * The file menu listener - it executes the correct method depending on the button pressed,
		 * this is not however completed with a typical set of if-else statements, they are invoked with
		 * a technique called "Method Reflection". This is why the methods will appear to be unused.
		 */
		@SuppressWarnings("unused")
		private class MenuActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				/*
				 * Loops through the above array to find the button with the same name as the command.
				 * This is done in order to prevent the application from attempting to execute a
				 * method which doesn't exist.
				 */
				for(int i = 0; i < arrMenuNames.length; i++) {
					for(int j = 2; j <arrMenuNames[i].length; j++) {
						if(e.getActionCommand().equals(arrMenuNames[i][j])){
							//Change to match the method name
							String methodName = arrMenuNames[i][j].replaceAll(" ", "_").toLowerCase() + "_menu";
						
							//Run the method
							try {
								java.lang.reflect.Method method = this.getClass().getMethod(methodName);
								method.invoke(this);
							//Getting Method Errors
							} catch (NoSuchMethodException ex) {
								ex.printStackTrace();
							} catch (SecurityException ex) {
								ex.printStackTrace();
							//Invoking Method Errors
							} catch (InvocationTargetException ex) {
								ex.printStackTrace();
							} catch (IllegalAccessException ex) {
								ex.printStackTrace();
							}
						}
					}
				}
			}
			
			final JFileChooser fc = new JFileChooser();
			final FileNameExtensionFilter txtFilter = new FileNameExtensionFilter(".txt", "txt");
			final FileNameExtensionFilter pngFilter = new FileNameExtensionFilter(".png", "png");
			
			//Constructs a new InstructionFile object with the selected file
			public void open_file_menu() {
				fc.setFileFilter(txtFilter);
				fc.showOpenDialog(null);
				if(fc.getSelectedFile() != null) {
					InstructionFile file = new InstructionFile(fc.getSelectedFile());
				}
			}
			
			//Outputs the current textbox to a file of their choosing - overwrites by default
			public void save_file_as_menu() {
				fc.setFileFilter(txtFilter);
				int saveValue = fc.showSaveDialog(null);
				String[] strTextContents = InstructionPanel.getInstructionBox().getText().split("\n");
				if(saveValue == JFileChooser.APPROVE_OPTION) {
					try {
						FileWriter writer = new FileWriter(fc.getSelectedFile() + ".txt");
						for(String str : strTextContents) {
							writer.write(str);
							writer.write(System.getProperty("line.separator"));
						}
						
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			//Outputs the current image to a file of their choosing
			public void save_image_menu() {
				fc.setFileFilter(pngFilter);
				int saveValue = fc.showSaveDialog(null);
				BufferedImage image = GUIPanel.getDrawingPanel().getImage();
		        if(saveValue == JFileChooser.APPROVE_OPTION) {
		            try {
		                ImageIO.write(image, "png", new File(fc.getSelectedFile().getAbsolutePath() + ".png"));
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
			}

			//Displays information about the program
			public void about_menu() {
				AboutDialog about = new AboutDialog();
				about.setVisible(true);
			}
			
			//Displays information about the program
			public void help_contents_menu() {
				HelpDialog help = new HelpDialog();
				help.setVisible(true);
			}
			
			//Quits the program after asking if the user would like to change anything
			public void quit_menu() {
				if(GUIPanel.GetFrameTitle().substring(0, 1).equals("*")) {
					 int result = JOptionPane.showConfirmDialog(null, "Do You Want To Save Before Quiting?", "Quit", JOptionPane.YES_NO_CANCEL_OPTION);
					 
					 switch(result) {
					 	case 0:
					 		save_file_as_menu();
					 		System.exit(0);
					 		break;
					 	case 1:
					 		System.exit(0);
					 		break;
					 	case 2:
					 		return;
					 }
				} else {
					System.exit(0);
				}
			}
		}
	}