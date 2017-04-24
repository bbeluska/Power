package ui;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import javax.swing.*;
public class View{
	private final GraphicsEnvironment graphicsEnviroment = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private final int screenWidth = graphicsEnviroment.getDefaultScreenDevice().getDisplayMode().getWidth();
	private final int screenHeight = graphicsEnviroment.getDefaultScreenDevice().getDisplayMode().getHeight();
	
	public View(){
		//Create and set up the window.
		JFrame mainFrame = new JFrame("Power");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBounds(50, 50, screenWidth*2 / 3, screenHeight*2 / 3);
		GridLayout mainLayout = new GridLayout(1,2,1,1);
		mainFrame.getContentPane().setLayout(mainLayout);
		
		createLeftPanel(mainFrame);
		
		createRightPanel(mainFrame);
		
		//Display the window.
		mainFrame.setVisible(true);
	}
	
	private void createLeftPanel(JFrame parentFrame){
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(1,2));
		JButton button1 = new JButton("B1");
		JButton button2 = new JButton("B2");
		leftPanel.add(button1);
		leftPanel.add(button2);
		//
		parentFrame.getContentPane().add(leftPanel);
	}
	
	private void createRightPanel(JFrame parentFrame){
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout(1,2));
		JButton button3 = new JButton("B3");
		JButton button4 = new JButton("B4");
		rightPanel.add(button3);
		rightPanel.add(button4);
		//
		parentFrame.getContentPane().add(rightPanel);
	}
}
