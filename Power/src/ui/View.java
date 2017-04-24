package ui;	
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
public class View{
	private final GraphicsEnvironment graphicsEnviroment = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private final int screenWidth = graphicsEnviroment.getDefaultScreenDevice().getDisplayMode().getWidth();
	private final int screenHeight = graphicsEnviroment.getDefaultScreenDevice().getDisplayMode().getHeight();
	private final int windowWidth = screenWidth * 2 / 3;
	private final int windowHeight = screenHeight * 2 / 3;
	private final Dimension defaultTextFieldDimension = new Dimension(90,24);
	private final Color darkRed = new Color(128,0,0);
	private final Color darkGreen = new Color(0,128,0);
	//
	private Controller controller;
	private JPanel rightPanel = new JPanel();
	private JComboBox<String> comCombo;
	private JLabel connectedLabel;
	private JButton connectButton;
	private JTextField voltageText;
	private JTextField currentText;
	private JTextField powerText;
	private JTextField setpointText;
	private JLabel errorLabel;
	private JTextField errorText;
	private JTextField tempText;
	
	//
	private static final int LEFT_PANEL_WIDTH = 200;
	private static final String COM_PORT = "Com Port:";
	private static final String CONNECT = "Connect";
	private static final String DISCONNECT = "Disconnect";
	private static final String CONNECTED = "Conected";
	private static final String DISCONNECTED = "Disconnected";
	private static final String VOLTAGE = "Voltage: ";
	private static final String CURRENT = "Current: ";
	private static final String POWER = "Power: ";
	private static final String SETPOINT = "Setpoint: ";
	private static final String ERROR = "Error: ";
	private static final String TEMPERATURE = "Temperature: ";
	
	public View(Controller controller){
		this.controller = controller;
		createView();
		initListeners();
		//getters and setters();
	}
	
	/**
	 * Create visual elements
	 */
	private void createView(){
		JFrame mainFrame = new JFrame("Power");
		SpringLayout springLayout = new SpringLayout();
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBounds(50, 50, windowWidth, windowHeight);
		mainFrame.getContentPane().setLayout(springLayout);

		createLeftPanel(mainFrame);
		createRightPanel(mainFrame);
		
		springLayout.putConstraint(SpringLayout.WEST, rightPanel, LEFT_PANEL_WIDTH,	SpringLayout.WEST, mainFrame);

		mainFrame.setVisible(true);
	}
	
	private void createLeftPanel(JFrame parent){
		JPanel leftPanel = new JPanel();
		SpringLayout layout = new SpringLayout();
		
		leftPanel.setPreferredSize(new Dimension(LEFT_PANEL_WIDTH,windowHeight));
		leftPanel.setLayout(layout);
		
		createUpperLeftPart(leftPanel);
		createMiddleLeftPart(leftPanel);
		createLowerLeftPart(leftPanel);
		
		parent.add(leftPanel);
	}
	
	private void createRightPanel(JFrame parent){
		rightPanel.setLayout(new GridLayout(4,1));
		JButton button1 = new JButton("B1");
		JButton button2 = new JButton("B2");
		JButton button3 = new JButton("B3");
		JButton button4 = new JButton("B4");
		rightPanel.add(button1);
		rightPanel.add(button2);
		rightPanel.add(button3);
		rightPanel.add(button4);
		//
		parent.add(rightPanel);
	}
	
	private void createUpperLeftPart(JPanel parent){
		SpringLayout layout = (SpringLayout)parent.getLayout();
		//Com Port Label
		JLabel comPortLabel = new JLabel(COM_PORT);
		parent.add(comPortLabel);
		layout.putConstraint(SpringLayout.WEST, comPortLabel, 10, SpringLayout.WEST, parent);
		layout.putConstraint(SpringLayout.NORTH, comPortLabel, 10, SpringLayout.NORTH, parent);
		//Com Combo Box
		this.comCombo= new JComboBox<String>(controller.getAvailableComPorts());
		this.comCombo.setPreferredSize(defaultTextFieldDimension);
		this.comCombo.setSelectedIndex(-1);
		parent.add(this.comCombo);
		layout.putConstraint(SpringLayout.WEST, this.comCombo, 5, SpringLayout.EAST, comPortLabel);
		layout.putConstraint(SpringLayout.SOUTH, this.comCombo, 4, SpringLayout.SOUTH, comPortLabel);
		//Connect Button
		this.connectButton = new JButton(CONNECT);
		this.connectButton.setEnabled(false);
		this.connectButton.setPreferredSize(new Dimension(100,24));
		parent.add(this.connectButton);
		layout.putConstraint(SpringLayout.WEST, this.connectButton, 0, SpringLayout.WEST, this.comCombo);
		layout.putConstraint(SpringLayout.NORTH, this.connectButton, 10, SpringLayout.SOUTH, this.comCombo);
		//Connected Label
		this.connectedLabel = new JLabel(DISCONNECTED);
		connectedLabel.setForeground(darkRed);
		parent.add(connectedLabel);
		layout.putConstraint(SpringLayout.WEST, this.connectedLabel, 10, SpringLayout.WEST, parent);
		layout.putConstraint(SpringLayout.NORTH, this.connectedLabel, 10, SpringLayout.SOUTH, this.connectButton);
	}
	
	private void createMiddleLeftPart(JPanel parent){
		SpringLayout layout = (SpringLayout)parent.getLayout();
		JLabel voltageLabel = new JLabel(VOLTAGE);
		parent.add(voltageLabel);
		layout.putConstraint(SpringLayout.WEST, voltageLabel, 10, SpringLayout.WEST, parent);
		layout.putConstraint(SpringLayout.NORTH, voltageLabel, 80, SpringLayout.SOUTH, this.connectButton);
		//
		this.voltageText = new JTextField();
		voltageText.setEditable(false);
		voltageText.setPreferredSize(defaultTextFieldDimension);
		parent.add(voltageText);
		layout.putConstraint(SpringLayout.WEST, voltageText, 0, SpringLayout.WEST, this.comCombo);
		layout.putConstraint(SpringLayout.SOUTH, voltageText, 4, SpringLayout.SOUTH, voltageLabel);
		//
		JLabel currentLabel = new JLabel(CURRENT);
		parent.add(currentLabel);
		layout.putConstraint(SpringLayout.WEST, currentLabel, 10, SpringLayout.WEST, parent);
		layout.putConstraint(SpringLayout.NORTH, currentLabel, 15, SpringLayout.SOUTH, voltageLabel);
		//
		this.currentText = new JTextField();
		currentText.setEditable(false);
		currentText.setPreferredSize(defaultTextFieldDimension);
		parent.add(currentText);
		layout.putConstraint(SpringLayout.WEST, currentText, 0, SpringLayout.WEST, this.comCombo);
		layout.putConstraint(SpringLayout.SOUTH, currentText, 4, SpringLayout.SOUTH, currentLabel);
		//
		JLabel powerLabel = new JLabel(POWER);
		parent.add(powerLabel);
		layout.putConstraint(SpringLayout.WEST, powerLabel, 10, SpringLayout.WEST, parent);
		layout.putConstraint(SpringLayout.NORTH, powerLabel, 15, SpringLayout.SOUTH, currentLabel);
		//
		this.powerText = new JTextField();
		powerText.setEditable(false);
		powerText.setPreferredSize(defaultTextFieldDimension);
		parent.add(powerText);
		layout.putConstraint(SpringLayout.WEST, powerText, 0, SpringLayout.WEST, this.comCombo);
		layout.putConstraint(SpringLayout.SOUTH, powerText, 4, SpringLayout.SOUTH, powerLabel);
		//
		JLabel setpointLabel = new JLabel(SETPOINT);
		parent.add(setpointLabel);
		layout.putConstraint(SpringLayout.WEST, setpointLabel, 10, SpringLayout.WEST, parent);
		layout.putConstraint(SpringLayout.NORTH, setpointLabel, 20, SpringLayout.SOUTH, powerLabel);
		//
		this.setpointText = new JTextField();
		setpointText.setEditable(false);
		setpointText.setPreferredSize(defaultTextFieldDimension);
		parent.add(setpointText);
		layout.putConstraint(SpringLayout.WEST, setpointText, 0, SpringLayout.WEST, this.comCombo);
		layout.putConstraint(SpringLayout.SOUTH, setpointText, 4, SpringLayout.SOUTH, setpointLabel);
		//
		this.errorLabel = new JLabel(ERROR);
		parent.add(this.errorLabel);
		layout.putConstraint(SpringLayout.WEST, this.errorLabel, 10, SpringLayout.WEST, parent);
		layout.putConstraint(SpringLayout.NORTH, this.errorLabel, 20, SpringLayout.SOUTH, setpointLabel);
		//
		this.errorText = new JTextField();
		errorText.setEditable(false);
		errorText.setPreferredSize(defaultTextFieldDimension);
		parent.add(errorText);
		layout.putConstraint(SpringLayout.WEST, errorText, 0, SpringLayout.WEST, this.comCombo);
		layout.putConstraint(SpringLayout.SOUTH, errorText, 4, SpringLayout.SOUTH, this.errorLabel);
	}
	
	private void createLowerLeftPart(JPanel parent){
		SpringLayout layout = (SpringLayout)parent.getLayout();
		JLabel tempLabel = new JLabel(TEMPERATURE);
		parent.add(tempLabel);
		layout.putConstraint(SpringLayout.WEST, tempLabel, 10, SpringLayout.WEST, parent);
		layout.putConstraint(SpringLayout.NORTH, tempLabel, 30, SpringLayout.SOUTH, this.errorLabel);
		//
		this.tempText = new JTextField();
		tempText.setEditable(false);
		tempText.setPreferredSize(defaultTextFieldDimension);
		parent.add(tempText);
		layout.putConstraint(SpringLayout.WEST, tempText, 0, SpringLayout.WEST, this.comCombo);
		layout.putConstraint(SpringLayout.NORTH, tempText, 4, SpringLayout.SOUTH, tempLabel);
	}

	private void initListeners(){
		this.connectButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (connectButton.getText().equals(CONNECT)){
					controller.connect();
				} else {
					controller.disconnect();
				}
			}
		});
		
		this.comCombo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.setSelectedComPort((String) comCombo.getSelectedItem());
			}
		});
		
	}
	
	//public
	
	
	public void setConnectButtonConnect(boolean enabled){
		if (enabled){
			this.connectButton.setText(CONNECT);
			this.connectedLabel.setText(DISCONNECTED);
			this.connectedLabel.setForeground(this.darkRed);
		} else{
			this.connectButton.setText(DISCONNECT);
			this.connectedLabel.setText(CONNECTED);
			this.connectedLabel.setForeground(this.darkGreen);
		}
	}
	
	public void setConnectButtonEnabled(boolean enabled){
			this.connectButton.setEnabled(enabled);
	}
	
	public void setComboEnabled(boolean enabled){
		this.comCombo.setEnabled(enabled);
	}
	
	public void setVoltage(String voltage){
		this.voltageText.setText(voltage);
	}
	
	public void setCurrent(String current){
		this.currentText.setText(current);
	}
	
	public void setPower(String power){
		this.powerText.setText(power);
	}
	
	public void setSetpoint(String setpoint){
		this.setpointText.setText(setpoint);
	}
	
	public void setError(String error){
		this.errorText.setText(error);
	}
	
	public void setTemperature(String temperature){
		this.tempText.setText(temperature);
	}
}
