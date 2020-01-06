package assets;

import java.awt.image.BufferedImage;

public class Texture {
	public static BufferedImage[] tiles;
	public Texture(BufferedImageLoader loader) {
		tiles = new BufferedImage[600];
		BufferedImage tileSheetImage = loader.loadImage("images","blankspritesheet.png");
		SpriteSheet tileSheet = new SpriteSheet(tileSheetImage);
		tiles= readFromSheet(tiles,tileSheet,13,16,48,48);
	}
	
	/*this method crops out images from a spritesheet assuming there is a constant width,height and you know how many rows and columns there are.*/
	  public static BufferedImage[] readFromSheet(BufferedImage[] sprites, SpriteSheet sheet,int rows,int cols,int width,int height)
	  {
	    for(int i = 0; i < rows; i++)
	    {
	      for(int x = 0; x < cols; x++)
	      {
	        sprites[x + (i*cols)] = sheet.crop(x*width, i*height, width, height);
	      }
	    }
	    return sprites;
	  }
}
