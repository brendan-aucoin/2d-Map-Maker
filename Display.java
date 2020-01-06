package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import game.Game;

public class Display {
	private JFrame f;
	public static final int WIDTH = 1200 ,HEIGHT =700;
	public Display() {
		f = GUI.setFrame(f, Game.TITLE, new Dimension(WIDTH,HEIGHT), new BorderLayout(), false,true);
		Image image = new ImageIcon(this.getClass().getResource("/images/schnauzer icon.png")).getImage();
		f.setIconImage(image);
	}
	
	public JFrame getFrame() {
		return f;
	}
}
