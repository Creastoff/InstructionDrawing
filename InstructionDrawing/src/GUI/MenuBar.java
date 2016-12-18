package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import Components.InstructionFile;

	//Dynamically Creates A Menu Bar Depending On What Is Inside The Below Array
	public class MenuBar extends JMenuBar {
		private static final long serialVersionUID = 1L;
		private static final String[][] arrMenuNames = 	{ 
												{"File", "The File Menu, Allowing A User To; Load, Save, & Export Images", "Open File", "Save File As", "Save Image", "Quit"},
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
					menuItem.setEnabled(true);
					
					tempMenu.add(menuItem);
				}
				
				this.add(tempMenu);
			}
		}
		
		public class AboutDialog extends JDialog {
			private static final long serialVersionUID = 1L;
			
			public AboutDialog() {
				setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
				setTitle("About");
				JLabel author = new JLabel("Written By Christopher Davey");
				JLabel version = new JLabel("Version " + GUIPanel.getVersion());
				JButton close = new JButton("Close");
				close.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						dispose();
					}
				});
				
				author.setAlignmentX(0.5f);
				version.setAlignmentX(0.5f);
				close.setAlignmentX(0.5f);
				
				add(new JLabel(" ")); //Spacer
				add(author);
				add(version);
				add(close);
				setSize(300, 150);
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
				
				System.out.println(strTextContents);
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
			
			//Quits the program after asking if the user would like to change anything
			public void quit_menu() {
				System.out.println("Quit-TODO");
			}
		}
	}