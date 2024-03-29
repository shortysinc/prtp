package tp.pr5.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import tp.pr5.Rotation;

public class InstructionPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GUIController controller;
	
	private JButton moveButton;
	private JButton quitButton;
	private JButton turnButton;
	private JComboBox<Rotation> turnCombo;
	private JTextField instructionText;
	private JButton pickButton;
	private JButton dropButton;
	private JButton operateButton;
	
	private RobotPanel robotPanel;

	public InstructionPanel(GUIController controller, RobotPanel robotPanel){
		super();
		this.controller=controller;
		this.robotPanel= robotPanel;
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
	
	/**
	 * Executes a QUIT instruction
	 */
	public void quitActionPerformed(ActionEvent arg0) {
		int exit= JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?", 
				"Confirmation", JOptionPane.YES_NO_OPTION);
		if (exit==JOptionPane.YES_OPTION)
		{
			controller.executeQuitAction();
		}
	}
	
	/**
	 * Executes a MOVE instruction
	 */
	public void moveActionPerformed(ActionEvent arg0){
		controller.executeMoveAction();
	}
	
	/**
	 * Executes a TURN instruction
	 * @param arg0
	 */
	public void turnActionPerformed(ActionEvent arg0){
		Rotation rotation = (Rotation) this.turnCombo.getSelectedItem();
		controller.executeTurnAction(rotation);
	}
	
	/**
	 * Executes a pick Action
	 */
	public void pickActionPerformed(ActionEvent arg0){
		String idItem=this.instructionText.getText();
		if(!idItem.isEmpty()){
			controller.executePickAction(idItem);
		} else {
			JOptionPane.showMessageDialog(null, "No Item to pick");
		}
	}
	
	/**
	 * Executes a DROP instruction
	 */
	public void dropActionPerformed(ActionEvent arg0){
		String idItem=this.robotPanel.getSelectedItem();
		if(idItem!=null){
			controller.executeDropAction(idItem);
		}else{
			JOptionPane.showMessageDialog(null, "No item selected");
		}
	}

	/**
	 * Executes a DROP instruction
	 */
	public void operateActionPerformed(ActionEvent arg0){
		String idItem=this.robotPanel.getSelectedItem();
		if(idItem!=null){
			controller.executeOperateAction(idItem);
		} else {
			JOptionPane.showMessageDialog(null, "No item selected");
		}
	}
	
	@Override
	public void setEnabled(boolean enable){
		super.setEnabled(enable);
		this.moveButton.setEnabled(enable);
		this.dropButton.setEnabled(enable);
		this.instructionText.setEnabled(enable);
		this.operateButton.setEnabled(enable);
		this.pickButton.setEnabled(enable);
		this.turnButton.setEnabled(enable);
		this.turnCombo.setEnabled(enable);		
	}
}
