package tp.pr4.gui;

import java.awt.*;
import java.awt.event.*;


import javax.swing.*;
import javax.swing.border.*;

import tp.pr4.RobotEngine;
import tp.pr4.Rotation;
import tp.pr4.instructions.DropInstruction;
import tp.pr4.instructions.MoveInstruction;
import tp.pr4.instructions.OperateInstruction;
import tp.pr4.instructions.PickInstruction;
import tp.pr4.instructions.QuitInstruction;
import tp.pr4.instructions.TurnInstruction;

public class InstructionPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RobotEngine robot;
	
	public JButton moveButton;
	public JButton quitButton;
	public JButton turnButton;
	public JComboBox<Rotation> turnCombo;
	public JTextField instructionText;
	public JButton pickButton;
	public JButton dropButton;
	public JButton operateButton;

	public InstructionPanel(RobotEngine robot){
		super();
		this.robot=robot;
		this.build();
	}
	
	public void build() 
	{
		this.setBorder(new TitledBorder("Instructions"));
		this.setLayout(new GridLayout(4, 2));
		this.setPreferredSize(new Dimension(300, 125));
		
		
		this.moveButton= new JButton("MOVE");
		this.quitButton= new JButton("QUIT");
		this.turnButton= new JButton("TURN");
		
		Rotation[] turnOptions={Rotation.LEFT,Rotation.RIGHT};		
		this.turnCombo= new JComboBox<Rotation>(turnOptions);
		
		this.instructionText= new JTextField();
		this.pickButton= new JButton("PICK");
		this.dropButton= new JButton("DROP");
		this.operateButton= new JButton("OPERATE");
		
		this.pickButton.setBounds(41, 11, 89, 15);
		this.dropButton.setBounds(41, 11, 89, 15);
		this.operateButton.setBounds(41, 11, 89, 15);
		
		this.add(moveButton);
		this.add(quitButton);
		this.add(turnButton);
		this.add(turnCombo);
		this.add(pickButton);
		this.add(instructionText);
		this.add(dropButton);
		this.add(operateButton);
		
		this.moveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				moveActionPerformed(arg0);
			}
		});
		
		this.quitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				quitActionPerformed(arg0);
			}
		});
		
		this.turnButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				turnActionPerformed(arg0);
			}
		});
		
		this.pickButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pickActionPerformed(arg0);
			}
		});
		
		this.dropButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dropActionPerformed(arg0);
			}
		});

		this.operateButton.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				operateActionPerformed(arg0);
			}
		});
	}
	
	public void quitActionPerformed(ActionEvent arg0) {
		int exit= JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?", 
				"Confirmation", JOptionPane.YES_NO_OPTION);
		if (exit==JOptionPane.YES_OPTION)
		{
			QuitInstruction quit=new QuitInstruction();
			robot.communicateRobot(quit);
		}
	}
	
	public void moveActionPerformed(ActionEvent arg0){
		MoveInstruction move=new MoveInstruction();
		robot.communicateRobot(move);
	}
	
	public void turnActionPerformed(ActionEvent arg0){
		Rotation rotation = (Rotation) this.turnCombo.getSelectedItem();
		TurnInstruction turn=new TurnInstruction(rotation);
		robot.communicateRobot(turn);
	}
	
	public void pickActionPerformed(ActionEvent arg0){
		String idItem=this.instructionText.getText();
		if(!idItem.equalsIgnoreCase("")){
			PickInstruction pick=new PickInstruction(idItem);
			robot.communicateRobot(pick);
		} else {
			JOptionPane.showMessageDialog(null, "no object to pick");
		}
	}
	
	public void dropActionPerformed(ActionEvent arg0){
		String idItem=robot.getRobotPanel().getSelectedItem();
		if(idItem!=null){
			DropInstruction drop=new DropInstruction(idItem);
			robot.communicateRobot(drop);
		}else{
			JOptionPane.showMessageDialog(null, "no object selected");
		}
	}

	public void operateActionPerformed(ActionEvent arg0){
		String idItem=robot.getRobotPanel().getSelectedItem();
		if(idItem!=null){
			OperateInstruction operate=new OperateInstruction(idItem);
			robot.communicateRobot(operate);
		} else {
			JOptionPane.showMessageDialog(null, "no object selected");
		}
	}
}
