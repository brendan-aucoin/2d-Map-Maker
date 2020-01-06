package panels;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import tiles.Tile;
import tiles.TileHandler;

public class MapMakerPanel extends Panel {
	private int currentTileIndex;
	private Tile [][] grid;
	@SuppressWarnings("unused")
	private TileHandler tileHandler;
	public MapMakerPanel(TileHandler tileHandler) {
		super(new Rectangle(0,0,Tile.WIDTH*16,Tile.HEIGHT*10));
		currentTileIndex = -1;
		this.tileHandler = tileHandler;
	}
	
	@Override
	public void init() {
		grid = new Tile[getBounds().height/Tile.HEIGHT][getBounds().width/Tile.WIDTH];
	}
	@Override
	public void update() {
		//System.out.println(currentTileIndex);
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.RED);
		g.draw(bounds);
		
		for(int x = 0; x < grid.length; x++) {
			for(int y = 0; y < grid[x].length;y++) {
			Tile tile = grid[x][y];
				if(tile != null) {
					tile.render(g);
				}
			}
		}
		if(currentTileIndex >=0) {
			Tile t = TileHandler.getTile(mouseX, mouseY, currentTileIndex);
			g.drawImage(t.getImage(),t.getX() - t.getWidth()/2,t.getY()-t.getHeight()/2,t.getWidth(),t.getHeight(),null);
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		super.mouseMoved(e);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		int tempMouseX = mouseX/Tile.WIDTH;
		int tempMouseY = mouseY/Tile.HEIGHT;
		if(e.getButton() == MouseEvent.BUTTON1) {//left click
			if(currentTileIndex >= 0) {
				grid[tempMouseY][tempMouseX] = TileHandler.getTile(tempMouseX, tempMouseY, currentTileIndex);
			}
		}
		
		else if(e.getButton() == MouseEvent.BUTTON3) {//right click
			if(currentTileIndex >= 0) {
				grid[tempMouseY][tempMouseX] = null;
			}
		}
	}
	
	public Tile[][] getGrid(){
		return grid;
	}
	public int getCurrentTileIndex() {return currentTileIndex;}
	public void setCurrentTileIndex(int currentTileIndex) {this.currentTileIndex = currentTileIndex;}
}
