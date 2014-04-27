import javax.swing.*;

import java.awt.BorderLayout;

import java.awt.event.*;

public class hbEvent extends JFrame{
	
	private JPanel panel;
	private JPanel panelEast;
	
	private JLabel server;
	private JLabel port;
	private JLabel message;
	private JLabel continuous;
	private JLabel iterations;
	
	private JButton go;
	private JButton save;
	
	private JTextField serverField;
	private JTextField portField;
	private JTextField iterationsField;
	private JTextField messageField;
	
	private JCheckBox contBox;
	
	private JTextArea output;
	
	private final int WIDTH = 800;
	private final int HEIGHT = 800;
	
	public hbEvent(){
		super("HeartBleed Proof of Concept");
		
		setSize(WIDTH,HEIGHT);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		buildPanel();
		add(panel,BorderLayout.NORTH);

		
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
				go, save
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
		
		go = new JButton("Go");
		save = new JButton("Save");
		
		output = new JTextArea(40,60);
		
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
		panel.add(go);
		panel.add(save);

		panel.add(output);
		
	}
}
