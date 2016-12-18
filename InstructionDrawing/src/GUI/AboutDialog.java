package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

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