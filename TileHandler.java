package tiles;

import java.util.ArrayList;

import assets.Texture;

public class TileHandler {
	private final ArrayList<Tile> tiles;
	public TileHandler() {
		tiles = new ArrayList<Tile>();
		for(int i =0; i < Texture.tiles.length;i++) {
			if(Texture.tiles[i]!=null) {
				tiles.add(new Tile(0,0,Texture.tiles[i],i));
			}
		}
	}
	
	public void fillTiles() {
		tiles.clear();
		for(int i =0; i < Texture.tiles.length;i++) {
			if(Texture.tiles[i]!=null) {
				tiles.add(new Tile(0,0,Texture.tiles[i],i));
			}
		}
	}
	public static Tile getTile(int x,int y,int value) {
		return new Tile(x,y,Texture.tiles[value],value);
	}
	
	public ArrayList<Tile> getTiles(){
		return tiles;
	}
	
}
