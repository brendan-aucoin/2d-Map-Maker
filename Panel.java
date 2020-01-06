package panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import tiles.Tile;

public abstract class Panel {
	protected int mouseX,mouseY;
	protected Rectangle bounds;
	
	public Panel(Rectangle bounds) {
		super();
		this.bounds = bounds;
	}
	
	public abstract void update();
	public abstract void render(Graphics2D g);
	public abstract void init();
	public Rectangle getBounds() {
		return bounds;
	}
	

	  /*checks if a x and y location are in the bounds of a width and height*/
	  protected boolean mouseOver(int mx,int my,int x,int y,int width,int height)
	 {
	   if(mx > x && mx < x+width)
	   {
	     if(my > y && my <y +height)
	     {
	       return true;
	     }
	   }
	   return false;
	 }
	  
	  protected boolean mouseOver(Tile t) {
		  if(mouseX > t.getX() && mouseX < t.getX() + t.getWidth()) {
			  if(mouseY > t.getY() && mouseY < t.getY() + t.getHeight()) {
				  return true;
			  }
		  }
		  return false;
	  }
	  
	  /*checks if an x and y position is over a rectangle*/
	  protected boolean mouseOver(int mx,int my,Rectangle bounds)
	 {
	   if(mx > bounds.x && mx < bounds.x+bounds.width)
	   {
	     if(my > bounds.y && my <bounds.y +bounds.height)
	     {
	       return true;
	     }
	   }
	   return false;
	 }
	  
	  
	  /*checks if your mouse if over a rectangle*/
	  protected boolean mouseOver(Rectangle bounds)
	 {
	   if(mouseX > bounds.x && mouseX < bounds.x+bounds.width)
	   {
	     if(mouseY > bounds.y && mouseY <bounds.y +bounds.height)
	     {
	       return true;
	     }
	   }
	   return false;
	 }
	  

	  /*if you are over a certain bounds change its colour of the graphics*/
	  protected void changeColour(Graphics2D g,Rectangle bounds,Color newCol,Color oldCol)
	 {
	  if(mouseOver(mouseX,mouseY,bounds.x,bounds.y,bounds.width,bounds.height)) {g.setColor(newCol);}
	  else {g.setColor(oldCol);}
	 }
	  /*if you are over a certain bounds change its colour of the graphics*/
	  protected void changeColour(Graphics2D g,int x,int y, int width,int height,Color newCol,Color oldCol)
	 {
	  if(mouseOver(mouseX,mouseY,x,y,width,height)) {g.setColor(newCol);}
	  else {g.setColor(oldCol);}
	 }
	  /*if you are over a certain bounds change its colour of the graphics*/
	  protected void changeColour(Graphics g,Rectangle bounds,Color newCol,Color oldCol)
	 {
	  if(mouseOver(mouseX,mouseY,bounds.x,bounds.y,bounds.width,bounds.height)) {g.setColor(newCol);}
	  else {g.setColor(oldCol);}
	 }
	  /*if you are over a certain bounds change its colour of the graphics*/
	  protected void changeColour(Graphics g,int x,int y, int width,int height,Color newCol,Color oldCol)
	 {
	  if(mouseOver(mouseX,mouseY,x,y,width,height)) {g.setColor(newCol);}
	  else {g.setColor(oldCol);}
	 }
	  /*moving mouse*/
	  public void mouseMoved(MouseEvent e){
	    this.mouseX = e.getX();
	    this.mouseY = e.getY();
	  }
	  /*implemented methods.*/
	  public void mouseDragged(MouseEvent e) {}
	 public void mouseClicked(MouseEvent arg0) {}
	 public void mouseEntered(MouseEvent arg0) {}
	 public void mouseExited(MouseEvent arg0) {}
	 public void mousePressed(MouseEvent arg0) {}
	 public void mouseReleased(MouseEvent arg0) {}
	public void mouseWheelMoved(MouseWheelEvent arg0) {}
	  
	public int getMouseX() {return mouseX;}


	public void setMouseX(int mouseX) {this.mouseX = mouseX;}


	public int getMouseY() {return mouseY;}


	public void setMouseY(int mouseY) {this.mouseY = mouseY;}
	  
	  
}
