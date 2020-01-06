package panels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import gui.Display;
import tiles.Tile;
import tiles.TileHandler;

public class TilePanel extends Panel{
	private ArrayList<Tile>tiles;
	private MapMakerPanel mapMakerPanel;
	private int currentIndex;
	public TilePanel(Panel mapMakerPanel,TileHandler tileHandler) {
		super(new Rectangle(mapMakerPanel.getBounds().x + mapMakerPanel.getBounds().width+1,0,Display.WIDTH - (mapMakerPanel.getBounds().x + mapMakerPanel.getBounds().width),Display.HEIGHT- 30));
		this.mapMakerPanel = (MapMakerPanel)mapMakerPanel;
		currentIndex = 0;
		this.tiles = tileHandler.getTiles();
	}
	
	public void init() {
	}
	public void update() {
		
	}
	
	private void drawTiles(Graphics2D g) {
		int counter =0;
		for(int y = currentIndex; y < tiles.size()/(getBounds().width/(Tile.WIDTH/2));y++) {
			for(int x =0; x < getBounds().width/(Tile.WIDTH/2); x++) {
				if(counter < tiles.size()) {
					tiles.get(counter).setX(getBounds().x + (Tile.WIDTH/2*x));
					tiles.get(counter).setY(getBounds().y + (Tile.HEIGHT/2*y));
					tiles.get(counter).setWidth(Tile.WIDTH/2);
					tiles.get(counter).setHeight(Tile.HEIGHT/2);
					g.drawImage(tiles.get(counter).getImage(), tiles.get(counter).getX(),tiles.get(counter).getY(),tiles.get(counter).getWidth(),tiles.get(counter).getHeight(),null);
					counter++;
				}
			}
		}
	}
	
	private void drawEffectsOnTiles(Graphics2D g) {
		for(Tile t : tiles) {
			if(mouseOver(t)) {
				g.setColor(Color.MAGENTA);
				g.setStroke(new BasicStroke(2));
				g.drawRect(t.getX(),t.getY(),t.getWidth(),t.getHeight());
			}
			
			if(t.getValue() == mapMakerPanel.getCurrentTileIndex()) {
				g.setColor(Color.WHITE);
				g.setStroke(new BasicStroke(2));
				g.drawRect(t.getX(),t.getY(), t.getWidth(), t.getHeight());
			}
		}
	}
	
	public void render(Graphics2D g) {
		drawTiles(g);
		drawEffectsOnTiles(g);
	}
	
	
	public void mouseMoved(MouseEvent e) {
		super.mouseMoved(e);
	}
	
	public void mousePressed(MouseEvent e) {
		for(Tile t : tiles) {
			if(mouseOver(t)) {
				mapMakerPanel.setCurrentTileIndex(t.getValue());
			}
		}
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(e.getWheelRotation() >0) {//down
			if(currentIndex > (-tiles.size()/(getBounds().width/(Tile.WIDTH/2))) + getBounds().height/(Tile.HEIGHT/2) - 1) {
				currentIndex--;
			}
		}
		
		else if(e.getWheelRotation() <0) {//up
			if(currentIndex !=0) {
				currentIndex++;
			}
			
		}
	}
	
	public void setTiles(TileHandler tileHandler) {
		this.tiles = tileHandler.getTiles();
	}
}
