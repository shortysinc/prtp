package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import tp.pr5.RobotEngine;
import tp.pr5.RobotEngineObserver;
import tp.pr5.items.InventoryObserver;
import tp.pr5.items.Item;

public class RobotPanel extends JPanel implements RobotEngineObserver, InventoryObserver{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JLabel fuel=new JLabel();
	public JLabel recycledMaterial=new JLabel();
	public JTable inventoryTable;
	
	public RobotPanel(){
		super();
		this.build();
	}
	
	private void build(){
		
		this.setBorder(new TitledBorder("Robot Info"));
		this.setLayout(new BorderLayout());
		
		JLabel fuelLabel = new JLabel("Fuel");
		JLabel recycledLabel = new JLabel("Recycled");
		
		//this.fuel.setText(this.robot.getFuel()+"");
		//this.recycledMaterial.setText(this.robot.getRecycledMaterial()+"");
		
		String[][] inventory=new String[0][0];
		 
		this.inventoryTable = new JTable(new inventoryModel(inventory));
		JPanel energy= new JPanel();

		energy.add(fuelLabel);
		energy.add(this.fuel);
		energy.add(recycledLabel);
		energy.add(this.recycledMaterial);
		
		this.add(energy,BorderLayout.NORTH);
		this.add(new JScrollPane(this.inventoryTable),BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(300, 125));
		
	}
	
	class inventoryModel extends AbstractTableModel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public String[] cols={"Id","Description"};
		
		public String[][] data;
		
		public inventoryModel(String[][] data){
			this.data=data;
		}
		
		@Override
		public String getColumnName(int col)
        {
            return cols[col];
        }
		
		@Override
		public int getRowCount(){
			return data.length;
		}

		@Override
		public int getColumnCount() {
			return cols.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return data[rowIndex][columnIndex];
		}
	
	}
	
	/**
	 * Returns the name of the item selected by the user on the table
	 * @return The name of the item selected
	 */
	public String getSelectedItem(){
		int pos=this.inventoryTable.getSelectedRow();
		if(pos>=0){
			return this.inventoryTable.getValueAt(this.inventoryTable.getSelectedRow(),0).toString();
		}
		return null;
	}
	
	

	/*public void updateStats(){
		this.updateFuel();
		this.updateRecycledMaterial();
	}
	
	public void updateFuel(){
		this.fuel.setText(this.robot.getFuel()+"");
	}
	
	public void updateRecycledMaterial(){
		this.recycledMaterial.setText(this.robot.getRecycledMaterial()+"");
	}

	public void updateItems(Item[] items) {
		String[][] datos=new String[items.length][2];
		for (int i = 0; i < items.length; i++) {
			datos[i][0]=items[i].getId();
			datos[i][1]=items[i].toString();
		}
		inventoryTable.setModel(new inventoryModel(datos));
		//inventoryTable.repaint();
		
	}*/

	@Override
	public void inventoryChange(List<Item> inventory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inventoryScanned(String inventoryDescription) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemScanned(String description) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemEmpty(String itemName) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void communicationCompleted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {
		this.fuel.setText(""+fuel);
		this.recycledMaterial.setText(""+recycledMaterial);
	}

	@Override
	public void robotSays(String message) {
		// TODO Auto-generated method stub
		
	}
	
}
