package tiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Tile {
	private int x,y;
	private BufferedImage image;
	private final int value;
	private int width,height;
	public static final int WIDTH = 64,HEIGHT = 64;
	
	
	public Tile(int x,int y,BufferedImage image,final int value) {
		this.x = x;
		this.y = y;
		this.image = image;
		this.value = value;
		this.width = WIDTH;
		this.height = HEIGHT;
	}
	
	public Tile(int x,int y,int width,int height,BufferedImage image,final int value) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.image = image;
		this.value = value;
	}
	
	public boolean equals(Tile t) {
		if(this.x == t.x &&this.y == t.y && this.width == t.width && this.height == t.height && this.value == t.value) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String toString() {
		return "Tile # " + value;
	}
	
	public void render(Graphics2D g) {
		//g.drawImage(image,x * WIDTH,y*HEIGHT,WIDTH,HEIGHT,null);
	///	g.drawImage(image, x*width, y*height, width, height, null);
		g.drawImage(image, x*width, y*height, width,height,null);
	}
	
	public int getX() {return x;}
	public void setX(int x) {this.x = x;}
	public int getY() {return y;}
	public void setY(int y) {this.y = y;}
	public BufferedImage getImage() {return image;}
	public void setImage(BufferedImage image) {this.image = image;	}
	public int getValue() {return value;}
	public int getWidth() {return width;}
	public void setWidth(int width) {this.width = width;}
	public int getHeight() {return height;}
	public void setHeight(int height) {this.height = height;}
	
}
