package managers;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import game.Game;
import panels.Panel;

public class MouseManager implements MouseListener,MouseMotionListener,MouseWheelListener{
	private int mouseX,mouseY;
	private Game game;
	public MouseManager(Game game) {
		mouseX = 0;
		mouseY =0;
		this.game = game;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		for(Panel p : game.getPanelList()) {
			if(mouseOver(p.getBounds())) {
				p.mouseWheelMoved(e);
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		for(Panel p : game.getPanelList()) {
			if(mouseOver(p.getBounds())) {
				p.mouseDragged(e);
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		for(Panel p : game.getPanelList()) {
			if(mouseOver(p.getBounds())) {
				p.mouseMoved(e);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for(Panel p : game.getPanelList()) {
			if(mouseOver(p.getBounds())) {
				p.mouseClicked(e);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		for(Panel p : game.getPanelList()) {
			if(mouseOver(p.getBounds())) {
				p.mouseEntered(e);
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		for(Panel p : game.getPanelList()) {
			if(mouseOver(p.getBounds())) {
				p.mouseExited(e);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for(Panel p : game.getPanelList()) {
			if(mouseOver(p.getBounds())) {
				p.mousePressed(e);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for(Panel p : game.getPanelList()) {
			if(mouseOver(p.getBounds())) {
				p.mouseReleased(e);
			}
		}
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
	  
}
