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
import tp.pr5.Place;
import tp.pr5.PlaceInfo;

public class NavigationPanel extends JPanel implements NavigationObserver{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel robotImage;
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
				/*cells[i][j].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						for (int i = 0; i < 11; i++) {
							for (int j = 0; j < 11; j++) {
								if(e.getSource().equals(cells[i][j]) && cells[i][j].visited()){
									//NavigationPanel.this.log.setText(cells[i][j].getDescriptionPlace());
									showCurrentPlace(cells[i][j].toString());
								}
							}
						}
						
						
					}
				});*/
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
		return this.cells[row][column];
	}
	
	/**
	 * Changes the current cell according to the player movement
	 * @param dir Direction of the movement
	 */
	private void move(Direction dir){
		this.column	+=	dir.getHorizontalMovement();
		this.row	+=	dir.getVerticalMovement();
	}
	
	/**
	 * Displays the place description on the text area
	 * @param description The place that will be shown
	 */
	private void showCurrentPlace(String description){
		this.log.setText(description);
	}

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
		PlaceCell cell = getCurrentCell();
		cell.arriveAt(initialPlace);
		this.placeScanned(initialPlace);
		this.headingChanged(heading);
	}

	@Override
	public void robotArrivesAtPlace(Direction heading, PlaceInfo place) {
		PlaceCell lastCell= getCurrentCell();
		lastCell.left();
		
		this.move(heading);
		
		PlaceCell cell=getCurrentCell();
		cell.arriveAt(place);
		this.placeScanned(place);
		
	}

	@Override
	public void placeScanned(PlaceInfo placeDescription) {
		this.showCurrentPlace(placeDescription.getDescription());		
	}

	@Override
	public void placeHasChanged(PlaceInfo placeDescription) {
		this.showCurrentPlace(placeDescription.getDescription());
	}

	@Override
	public void undone(Direction direction) {
		PlaceCell lastCell= this.cells[row+direction.getVerticalMovement()][column+direction.getHorizontalMovement()];
		lastCell.undo();
	}
}
