package ui;	
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import org.jfree.data.xy.XYDataset;

import controller.Controller;
import modell.DataModel;
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
	private DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>();
	private JLabel connectedLabel;
	private JButton connectButton;
	private JTextField voltageText;
	private JTextField currentText;
	private JTextField powerText;
	private JTextField setpointText;
	private JTextField errorDeltaText;
	private JTextField errorText;
	private JLabel pwmLabel;
	private JTextField pwmText;
	private JTextField tempText;
	//
	private XYDataset voltageDataset = DataSetFactory.createDataset(10);
	//
	private Graph voltageGraph;
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
	private static final String PWM = "Pwm: ";
	private static final String TEMPERATURE = "Temperature: ";
	
	public View(Controller controller){
		this.controller = controller;
		createView();
		initListeners();
	}
	
	/**
	 * Create Main view
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
	
	/**
	 * Create left panel.
	 * 
	 * @param parent widget
	 */
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
	
	/**
	 * Create Charts panel
	 * 
	 * @param parent widget
	 */
	private void createRightPanel(JFrame parent){
		rightPanel.setLayout(new GridLayout(4,1));
		this.voltageGraph = new Graph(this.voltageDataset, "Voltage", 0, 15);
		JButton button2 = new JButton("B2");
		JButton button3 = new JButton("B3");
		JButton button4 = new JButton("B4");
		rightPanel.add(this.voltageGraph);
		rightPanel.add(button2);
		rightPanel.add(button3);
		rightPanel.add(button4);
		//
		parent.add(rightPanel);
	}
	
	/**
	 * Create upper left part/
	 * 
	 * @param parent widget
	 */
	private void createUpperLeftPart(JPanel parent){
		SpringLayout layout = (SpringLayout)parent.getLayout();
		//Com Port Label
		JLabel comPortLabel = new JLabel(COM_PORT);
		parent.add(comPortLabel);
		layout.putConstraint(SpringLayout.WEST, comPortLabel, 10, SpringLayout.WEST, parent);
		layout.putConstraint(SpringLayout.NORTH, comPortLabel, 10, SpringLayout.NORTH, parent);
		//Com Combo Box
		this.comCombo= new JComboBox<String>();
		this.comCombo.setModel(this.comboBoxModel);
		setComComboItems(controller.getAvailableComPorts());
		this.comCombo.setPreferredSize(defaultTextFieldDimension);
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
	
	/**
	 * Create middle left part.
	 * 
	 * @param parent widget
	 */
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
		JLabel errorDeltaLabel = new JLabel(ERROR);
		parent.add(errorDeltaLabel);
		layout.putConstraint(SpringLayout.WEST, errorDeltaLabel, 10, SpringLayout.WEST, parent);
		layout.putConstraint(SpringLayout.NORTH, errorDeltaLabel, 20, SpringLayout.SOUTH, setpointLabel);
		//
		this.errorDeltaText = new JTextField();
		errorDeltaText.setEditable(false);
		errorDeltaText.setPreferredSize(defaultTextFieldDimension);
		parent.add(errorDeltaText);
		layout.putConstraint(SpringLayout.WEST, errorDeltaText, 0, SpringLayout.WEST, this.comCombo);
		layout.putConstraint(SpringLayout.SOUTH, errorDeltaText, 4, SpringLayout.SOUTH, errorDeltaLabel);
		//
		JLabel errorLabel = new JLabel(ERROR);
		parent.add(errorLabel);
		layout.putConstraint(SpringLayout.WEST, errorLabel, 10, SpringLayout.WEST, parent);
		layout.putConstraint(SpringLayout.NORTH, errorLabel, 20, SpringLayout.SOUTH, errorDeltaLabel);
		//
		this.errorText = new JTextField();
		errorText.setEditable(false);
		errorText.setPreferredSize(defaultTextFieldDimension);
		parent.add(errorText);
		layout.putConstraint(SpringLayout.WEST, errorText, 0, SpringLayout.WEST, this.comCombo);
		layout.putConstraint(SpringLayout.SOUTH, errorText, 4, SpringLayout.SOUTH, errorLabel);
		//
		this.pwmLabel = new JLabel(PWM);
		parent.add(pwmLabel);
		layout.putConstraint(SpringLayout.WEST, pwmLabel, 10, SpringLayout.WEST, parent);
		layout.putConstraint(SpringLayout.NORTH, pwmLabel, 20, SpringLayout.SOUTH, errorLabel);
		//
		this.pwmText = new JTextField();
		pwmText.setEditable(false);
		pwmText.setPreferredSize(defaultTextFieldDimension);
		parent.add(pwmText);
		layout.putConstraint(SpringLayout.WEST, pwmText, 0, SpringLayout.WEST, this.comCombo);
		layout.putConstraint(SpringLayout.SOUTH, pwmText, 4, SpringLayout.SOUTH, pwmLabel);
	}
	
	/**
	 * Create lower left panel.
	 * 
	 * @param parent widget
	 */
	private void createLowerLeftPart(JPanel parent){
		SpringLayout layout = (SpringLayout)parent.getLayout();
		JLabel tempLabel = new JLabel(TEMPERATURE);
		parent.add(tempLabel);
		layout.putConstraint(SpringLayout.WEST, tempLabel, 10, SpringLayout.WEST, parent);
		layout.putConstraint(SpringLayout.NORTH, tempLabel, 30, SpringLayout.SOUTH, this.pwmLabel);
		//
		this.tempText = new JTextField();
		tempText.setEditable(false);
		tempText.setPreferredSize(defaultTextFieldDimension);
		parent.add(tempText);
		layout.putConstraint(SpringLayout.WEST, tempText, 0, SpringLayout.WEST, this.comCombo);
		layout.putConstraint(SpringLayout.NORTH, tempText, 4, SpringLayout.SOUTH, tempLabel);
	}

	/**
	 * Add listeners.
	 */
	private void initListeners(){
		//button press listener
		this.connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (connectButton.getText().equals(CONNECT)){
					controller.connect((String) comCombo.getSelectedItem());
				} else {
					controller.disconnect();
				}
			}
		});
		//com port changed listener
		this.comCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.setSelectedComPort((String) comCombo.getSelectedItem());
			}
		});
		
		//listener for combo list drop down
		this.comCombo.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				setComComboItems(controller.getAvailableComPorts());
			}
			
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				// Not implemented	
			}
			
			public void popupMenuCanceled(PopupMenuEvent e) {
				// Not implemented
			}
		});
		
	}
	
	/**-----------============PUBLIC==========-----------**/
	
	/**
	 * Set status to ready to connect
	 * 
	 * @param enabled
	 */
	public void setReadyToConnect(boolean enabled){
		this.comCombo.setEnabled(enabled);
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
	
	/**
	 * Setter for Connect button enabled state.
	 * @param enabled
	 */
	public void setConnectButtonEnabled(boolean enabled){
			this.connectButton.setEnabled(enabled);
	}
	
	/**
	 * Update Combo box items.
	 * @param comList to be updated with.
	 */
	public void setComComboItems(String[] comList){
		this.comboBoxModel.removeAllElements();
		for (String item: comList){
			this.comboBoxModel.addElement(item);
		}
		this.comCombo.setSelectedIndex(-1);
	}
	
	/**
	 * Update readings. This should be called every time a new reading was made.
	 * 
	 * @param data to update with.
	 */
	public void update(DataModel data){
		if (null != data){
			this.voltageText.setText(data.getVoltage() + " [V]");
			this.currentText.setText(data.getCurrent() + " [A]");
			this.powerText.setText(data.getPower() + " [W]");
			this.setpointText.setText(data.getSetpoint() + " [W]");
			this.errorDeltaText.setText(data.getErrorDelta() +" [W]");
			this.errorText.setText(data.getError() + " [%]");
			this.pwmText.setText(data.getPwm() + "[%]");
			this.tempText.setText(data.getTemperature() + " [C]");
		}
		//TODO: update graphs
	}
}
