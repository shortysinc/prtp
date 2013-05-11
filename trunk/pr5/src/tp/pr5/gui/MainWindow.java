package tp.pr5.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import tp.pr5.RobotEngine;
import tp.pr5.RobotEngineObserver;
import tp.pr5.instructions.UndoInstruction;

public class MainWindow extends JFrame implements RobotEngineObserver{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GUIController controller;
	
	public InstructionPanel InstructionPanel;
	public RobotPanel robotPanel;
	public NavigationPanel navigationPanel;
	public InfoPanel infoPanel;

	/**
	 * Creates the window and the panels using Swing Components. It stores a reference to the RobotEngine object and provides the panels to the robot engine in order to communicate the simulation events.
	 * @param gc The RobotEngine that receives the instructions performed by the action panel
	 */
	public MainWindow(GUIController gc){
		
		super();
		this.controller=gc;
		this.build();
		
		//add observers -----------------------------------------------
		this.controller.registerEngineObserver(this);
		
		this.controller.registerEngineObserver(this.robotPanel);
		this.controller.registerItemContainerObserver(this.robotPanel);
		
		this.controller.registerRobotObserver(this.navigationPanel);
		
		this.controller.registerEngineObserver(this.infoPanel);
		this.controller.registerItemContainerObserver(this.infoPanel);
		this.controller.registerRobotObserver(this.infoPanel);
		//-------------------------------------------------------------
		
		this.setTitle("WALL·E The garbage collector");
		this.pack();
		this.setVisible(true);
	}
	
	private void build(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar bar= new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu edit = new JMenu("Edit");
		JMenuItem quit= new JMenuItem("Quit");
		JMenuItem undo= new JMenuItem("Undo");
		file.add(quit);
		edit.add(undo);
		
		bar.add(file);
		bar.add(edit);
		
		//#######################--DIMENSIONES--###############################
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		final int WIDTH = screenSize.width;
		final int HEIGHT = screenSize.height;
		this.setSize(WIDTH/2+150,HEIGHT/2);
		this.setLocationRelativeTo(null);
		//####################################################################
		
		this.setJMenuBar(bar);
		
		JPanel containerPanel= new JPanel(new BorderLayout());
		
		this.InstructionPanel = new  InstructionPanel(this.controller);
		this.InstructionPanel.setBounds(20, 30, 25, 15);
		
		this.robotPanel = new RobotPanel();
		
		JSplitPane splitPanel= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,this.InstructionPanel,this.robotPanel);
		this.navigationPanel= new NavigationPanel();
		
		this.infoPanel = new InfoPanel();
		
		containerPanel.add(splitPanel,BorderLayout.NORTH);
		containerPanel.add(this.navigationPanel,BorderLayout.CENTER);
		containerPanel.add(this.infoPanel,BorderLayout.SOUTH);
		
		this.add(containerPanel);
		containerPanel.setPreferredSize(new Dimension(800, 500));
		
		quit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				quitActionPerformed(arg0);
			}
		});
		
		undo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//MainWindow.this.robot.communicateRobot(new UndoInstruction());
				//gc.avisarUndoInstruction();
			}
		});
	}
	
	public void quitActionPerformed(ActionEvent arg0) {
		this.InstructionPanel.quitActionPerformed(arg0);
	}

	@Override
	public void raiseError(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void communicationHelp(String help) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void engineOff(boolean atShip) {
		this.dispose();
	}

	@Override
	public void communicationCompleted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void robotSays(String message) {
		// TODO Auto-generated method stub
		
	}
}
