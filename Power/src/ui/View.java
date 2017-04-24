package ui;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import javax.swing.*;
public class View{
	private final GraphicsEnvironment graphicsEnviroment = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private final int screenWidth = graphicsEnviroment.getDefaultScreenDevice().getDisplayMode().getWidth();
	private final int screenHeight = graphicsEnviroment.getDefaultScreenDevice().getDisplayMode().getHeight();
	
	public View(){
		//Create and set up the window.
		JFrame frame = new JFrame("Power");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(50, 50, screenWidth*2 / 3, screenHeight*2 / 3);
		frame.getContentPane().setLayout(new GridLayout(1,2));
		createLeftPanel();
		createRightPanel();
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(1,2));
		//
		JButton button1 = new JButton("B1");
		JButton button2 = new JButton("B2");
		JButton button3 = new JButton("B3");
		JButton button4 = new JButton("B4");
		leftPanel.add(button1);
		leftPanel.add(button2);
		leftPanel.add(button3);
		leftPanel.add(button4);
		
		frame.getContentPane().add(leftPanel);
		
		//Display the window.
		frame.setVisible(true);
	}
	
	private void createLeftPanel(){
		
	}
	
	private void createRightPanel(){
		
	}
}
