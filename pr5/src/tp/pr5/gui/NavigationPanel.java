package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import tp.pr5.Direction;
import tp.pr5.NavigationObserver;
import tp.pr5.PlaceInfo;

public class NavigationPanel extends JPanel implements NavigationObserver{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JLabel robotImage;
	private int row;
	private int column;
	//private JPanel cityPanel;
	private JTextArea log;
	private PlaceCell[][] cells= new PlaceCell[11][11];

	public NavigationPanel(){
		super();
		this.build();
		this.column = this.row = 5;
		
	}
	
	private void build(){
		
		this.setLayout(new BorderLayout());
		
		this.robotImage= new JLabel();
		
		//ImageIcon icon = new ImageIcon("src/tp/pr4/gui/images/walleWest.png");
		//ImageIcon icon = new ImageIcon(getClass().getResource("src/tp/pr4/gui/images/walleWest.png"));
		//this.robotImage.setIcon(icon);
		
		JPanel cityPanel= new JPanel();
		cityPanel.setBorder(new TitledBorder("City Map"));
		cityPanel.setLayout(new GridLayout(11,11));
		
		JPanel logPanel = new JPanel();
		logPanel.setBorder(new TitledBorder("Log"));
		logPanel.setLayout(new BorderLayout());
		log = new JTextArea(8,100);
		log.setEditable(false);
		logPanel.add(new JScrollPane(log),BorderLayout.CENTER);
		
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				cells[i][j]=new PlaceCell(this);
				cityPanel.add(cells[i][j]);
				cells[i][j].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						for (int i = 0; i < 11; i++) {
							for (int j = 0; j < 11; j++) {
								if(e.getSource().equals(cells[i][j]) && cells[i][j].visited()){
									NavigationPanel.this.log.setText(cells[i][j].getDescriptionPlace());
								}
							}
						}
						
						
					}
				});
			}
		}
		
		
		
		this.add(robotImage,BorderLayout.WEST);
		this.add(cityPanel,BorderLayout.CENTER);
		this.add(logPanel,BorderLayout.SOUTH);
		
		
	}
	
	/**
	 * Returns the PlaceCell object of the current position on the city
	 * @return The current cell
	 */
	public PlaceCell getCurrentCell(){
		return null;
	}
	
	/**
	 * Changes the current cell according to the player movement
	 * @param dir Direction of the movement
	 */
	public void move(Direction dir){
		
	}
	
	/**
	 * Displays the place description on the text area
	 * @param description The place that will be shown
	 */
	public void showCurrentPlace(java.lang.String description){
		
	}
	
	
	/*public void setInitialPlace(Place place, Direction direction){
		PlaceCell cell = this.cells[this.row][this.column];
		cell.setPlace(place);
		cell.activate();
		this.log.setText(cell.getDescriptionPlace());
		//this.updateDirection(direction);
	}
	
	public void updateCell(Direction direction, Place currentPlace){
		
		PlaceCell lastCell= this.cells[row][column];
		lastCell.desactivate();
		
		this.column	+=	direction.getHorizontalMovement();
		this.row	+=	direction.getVerticalMovement();
		
		PlaceCell cell=this.cells[row][column];
		cell.setPlace(currentPlace);
		cell.activate();
		this.log.setText(cell.getDescriptionPlace());
	}
	
	public void closeCell(){
		PlaceCell cell=this.cells[row][column];
		cell.close();
	}
	
	public void updateLog(){
		PlaceCell cell=this.cells[row][column];
		this.log.setText(cell.getDescriptionPlace());
	}*/

	@Override
	public void headingChanged(Direction newHeading) {
		try{
			this.robotImage.setIcon(new ImageIcon(ImageIcon.class.getResource("/tp/pr5/gui/images/"+newHeading.getImage())));
		} catch (Exception e){
			//continue
		}
	}

	@Override
	public void initNavigationModule(PlaceInfo initialPlace, Direction heading) {
		this.headingChanged(heading);
	}

	@Override
	public void robotArrivesAtPlace(Direction heading, PlaceInfo place) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void placeScanned(PlaceInfo placeDescription) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void placeHasChanged(PlaceInfo placeDescription) {
		// TODO Auto-generated method stub
		
	}
}
