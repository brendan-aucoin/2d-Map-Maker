package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JPanel;

import assets.BufferedImageLoader;
import assets.Texture;
import gui.Display;
import gui.EnterInformationGUI;
import gui.MenuBar;
import managers.MouseManager;
import panels.MapMakerPanel;
import panels.Panel;
import panels.TilePanel;
import tiles.TileHandler;

public class Game implements Runnable{
	private boolean running;
	private Thread thread;
	private Canvas canvas;
	private BufferedImageLoader bufferedImageLoader;
	private JPanel panel;
	private MouseManager mouseManager;
	private Display display;
	private TileHandler tileHandler;
	private MenuBar menuBar;
	private EnterInformationGUI enterInformationGUI;
	//all the panels.
	private ArrayList<Panel> panelList;
	private Panel mapMakerPanel;
	private Panel tilePanel;
	//static variables
	public static String TITLE = "Map Maker";
	@SuppressWarnings("unused")
	private Texture texture;
	public Game() {
		panel = new JPanel();
		running = false;
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(Display.WIDTH,Display.HEIGHT));
		canvas.setMaximumSize(new Dimension(Display.WIDTH,Display.HEIGHT));
		canvas.setMinimumSize(new Dimension(Display.WIDTH,Display.HEIGHT));
		panel.add(canvas);
		panelList = new ArrayList<Panel>();
		mouseManager = new MouseManager(this);
		display = new Display();
	}
	
	private void init() {
		canvas.setFocusable(true);
		canvas.addMouseListener(mouseManager);
		canvas.addMouseMotionListener(mouseManager);
		canvas.addMouseWheelListener(mouseManager);
		
		
		bufferedImageLoader = new BufferedImageLoader();
		texture = new Texture(bufferedImageLoader);
		
		tileHandler = new TileHandler();
		
		//the panels
		mapMakerPanel = new MapMakerPanel(tileHandler);
		tilePanel = new TilePanel(mapMakerPanel,tileHandler);
		
		panelList.add(mapMakerPanel);
		panelList.add(tilePanel);
		for(Panel p : panelList) {
			p.init();
		}
		enterInformationGUI = new EnterInformationGUI(tileHandler,(TilePanel)tilePanel,(MapMakerPanel)mapMakerPanel);
		menuBar = new MenuBar((MapMakerPanel)mapMakerPanel,enterInformationGUI);
		menuBar.init();
		//end of init
		texture =null;
		display.getFrame().setJMenuBar(menuBar);
		display.getFrame().add(panel);
		display.getFrame().setVisible(true);
	}
	
	public void update() {
		for(Panel p : panelList) {
			p.update();
		}
	}
	
	public void render() {
		BufferStrategy bs = canvas.getBufferStrategy();
		if(bs == null){canvas.createBufferStrategy(3);return;}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		canvas.paint(g);
		g.setColor(Color.WHITE);
		g.fillRect(0,0,Display.WIDTH,Display.HEIGHT);
		//start of rendering code
		
		for(Panel p:panelList) {
			p.render(g2d);
		}
		
		//end of rendering code
		g.dispose();
		bs.show();
	}
	
	/*all the thread stuff*/
	public void run() {
		init();
		int fps = 60;
		double timePerTick = 1000000000/fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer =0;
		@SuppressWarnings("unused")
		int ticks = 0;
		while(running) {
			now = System.nanoTime();
			delta+=(now-lastTime)/timePerTick;
			timer+= now - lastTime;
			lastTime = now;
			if(delta>=1) {
				update();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >1000000000) {
				//System.out.println("Updates: and frames "  +ticks);
				ticks =0;
				timer = 0;
			}
		}
		
		stop();
	}
	
	public synchronized void start() {
		if(running) {return;}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running) {return;}
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public JPanel getPanel() {return panel;}
	public ArrayList<Panel> getPanelList(){return panelList;}
}
