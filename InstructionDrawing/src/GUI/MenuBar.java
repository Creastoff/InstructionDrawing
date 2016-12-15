package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Components.InstructionFile;

	//Dynamically Creates A Menu Bar Depending On What Is Inside The Below Array
	public class MenuBar extends JMenuBar{
		private static final long serialVersionUID = 1L;
		private static final String[][] arrMenuNames = 	{ 
												{"File", "The File Menu, Allowing A User To; Load, Save, & Export Images", "Open File", "Save File", "Quit"},
												{"Help", "The Help Menu, Displaying Information About The Program & How To Use It", "About"}
											};

		public MenuBar() {
			//Creates A Temporary Menu Item & Corresponding Sub-Items Which Is Added To The Menu Bar
			for(int i = 0; i < arrMenuNames.length; i++) {
				JMenu tempMenu = new JMenu(arrMenuNames[i][0]);
				//TODO - Create A Function Which Returns A Suitable Mnemonic
				//tempMenu.setMnemonic();
				tempMenu.getAccessibleContext().setAccessibleDescription(arrMenuNames[i][1]);
				
				//Creates Sub-Items For The Menu Bar Item
				for(int j = 2; j < arrMenuNames[i].length; j++) {
					//TODO - Create A Function Which Returns A Suitable Mnemonic
					JMenuItem menuItem = new JMenuItem(arrMenuNames[i][j]);
					menuItem.getAccessibleContext().setAccessibleDescription(arrMenuNames[i][j]);
					menuItem.addActionListener(new MenuActionListener());
					
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
			
			//Constructs a new InstructionFile object with the selected file
			public void open_file_menu() {
				final JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(null);
				if(fc.getSelectedFile() != null) {
					InstructionFile file = new InstructionFile(fc.getSelectedFile());
				}
			}

			//Outputs the current textbox to a file of their choosing
			public void save_file_menu() {
				System.out.println("Save File-TODO");
			}

			//Displays information about the program
			public void about_menu() {
				System.out.println("About-TODO");
			}
			
			public void quit_menu() {
				System.out.println("Quit-TODO");
			}
		}
	}