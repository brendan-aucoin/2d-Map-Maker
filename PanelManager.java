package managers;

import java.awt.Graphics2D;

import panels.Panel;

public class PanelManager {
	private Panel currentPanel;
	public PanelManager() {
		
	}
	public void update() {
		if(currentPanel!=null) {
			currentPanel.update();}
	}
	
	public void render(Graphics2D g) {
		if(currentPanel!= null) {
			currentPanel.render(g);}
	}
	public Panel getCurrentPanel() {
		return currentPanel;
	}
	public void setCurrentPanel(Panel currentPanel) {
		this.currentPanel = currentPanel;
	}
}
