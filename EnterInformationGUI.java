package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import assets.SpriteSheet;
import assets.Texture;
import file_chooser.JSystemFileChooser;
import panels.MapMakerPanel;
import panels.TilePanel;
import tiles.TileHandler;

public class EnterInformationGUI implements ActionListener{
	private JFrame f;
	private JPanel p,titlePanel,loadPanel;
	private GridBagConstraints c = new GridBagConstraints();
	private JTextField imagePathTf,tileWidthTf,tileHeightTf,rowsTf,columnsTf;
	private JButton loadButton,loadImageButton;
	private String imagePath;
	private TilePanel tilePanel;
	private TileHandler tileHandler;
	private MapMakerPanel mapMakerPanel;
	public EnterInformationGUI(TileHandler tileHandler,TilePanel tilePanel,MapMakerPanel mapMakerPanel) {
		init();
		imagePath = null;
		this.tileHandler = tileHandler;
		this.tilePanel = tilePanel;
		this.mapMakerPanel = mapMakerPanel;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loadButton) {
			if(imagePath== null || tileWidthTf.getText() == null || tileHeightTf.getText() ==null || rowsTf.getText() == null||
					columnsTf.getText() ==null) {MessageDisplayer.displayMessage("Please enter valid information");return;}
			try {
			int tileWidth = Integer.parseInt(tileWidthTf.getText());
			int tileHeight = Integer.parseInt(tileHeightTf.getText());
			int rows = Integer.parseInt(rowsTf.getText());
			int columns = Integer.parseInt(columnsTf.getText());
			
			try {
				BufferedImage sheetImage = ImageIO.read(new File(imagePath));
				SpriteSheet sheet = new SpriteSheet(sheetImage);
				Texture.tiles = Texture.readFromSheet(Texture.tiles, sheet, rows, columns, tileWidth,tileHeight);
				tileHandler.fillTiles();
				tilePanel.setTiles(tileHandler);
				for(int x =0; x < mapMakerPanel.getGrid().length;x++) {
					for(int y =0; y < mapMakerPanel.getGrid()[x].length;y++) {
						mapMakerPanel.getGrid()[x][y] = null;
					}
				}
				f.setVisible(false);
				imagePath = null;
				tileWidthTf.setText("");
				tileHeightTf.setText("");
				rowsTf.setText("");
				columnsTf.setText("");
				imagePathTf.setText("");
			}catch(IOException ex) {ex.printStackTrace();return;}
			
			}catch(Exception ex) {
				MessageDisplayer.displayMessage("Please enter valid information");
				return;
			}
		}
		
		else if(e.getSource() == loadImageButton) {
			JSystemFileChooser fileChooser = new JSystemFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("images", "png","jpg");
			fileChooser.setFileFilter(filter);
			int buttonPressed = fileChooser.showOpenDialog(null);
			if(buttonPressed == JFileChooser.APPROVE_OPTION) {
				imagePath = fileChooser.getSelectedFile().getPath();
				imagePathTf.setText(imagePath);
			}
			else {
				return;
			}
		}
	}
	public void init() {
		//the frame
		f = GUI.setFrame(f, "Enter Information", new Dimension(450,300), new BorderLayout(), false,false);
		Image image = new ImageIcon(this.getClass().getResource("/images/settingsIcon.png")).getImage();
		f.setIconImage(image);
		//the panel
		p = new JPanel(new GridBagLayout());
		p.setBackground(new Color(216,191,216));
		
		titlePanel = new JPanel(new GridBagLayout());
		titlePanel.add(GUI.setLabel("Load Sprite Sheet", new Font("arial",Font.BOLD,45), new Color(245,245,245)));
		titlePanel.setBackground(new Color(216,191,216));
		
		loadPanel = new JPanel(new GridBagLayout());
		loadPanel.setBackground(new Color(216,191,216));
		//all the components
		imagePathTf = new JTextField(10);
		imagePathTf.setFont(new Font("arial",Font.PLAIN,15));
		imagePathTf.setEditable(false);
		tileWidthTf = new JTextField(5);
		tileHeightTf = new JTextField(5);
		rowsTf = new JTextField(5);
		columnsTf = new JTextField(5);
		
		loadImageButton = new JButton("Load Image");
		loadImageButton.setPreferredSize(new Dimension(100,20));
		loadImageButton.setBackground(new Color(255,255,224));
		loadImageButton.setBorder(new RoundedBorder(5));
		loadImageButton.addActionListener(this);
		
		loadButton = new JButton("Load");
		loadButton.setPreferredSize(new Dimension(150,40));
		loadButton.setBackground(new Color(255,255,224));
		loadButton.setBorder(new RoundedBorder(10));
		loadButton.setFont(new Font("arial",Font.PLAIN,30));
		loadButton.addActionListener(this);
		
		addComponentsToPanel();
		f.add(p,BorderLayout.CENTER);
		f.add(titlePanel, BorderLayout.NORTH);
		f.add(loadPanel, BorderLayout.SOUTH);
	}
	
	private void addComponentsToPanel() {
		c.insets = new Insets(5,5,5,5);
		c.weightx = 0.5f;
		
		//the image tf
		c.gridx = 0;
		c.gridy =1;
		p.add(GUI.setLabel("Load Sprite Sheet", new Font("arial",Font.PLAIN,12), Color.BLACK),c);
		
		c.gridx = 1;
		c.gridy = 1;
		p.add(imagePathTf,c);
		//the image button
		c.gridx = 2;
		c.gridy = 1;
		p.add(loadImageButton,c);
		
		//the width of tile
		c.gridx = 0;
		c.gridy = 2;
		p.add(GUI.setLabel("Width of a tile", new Font("arial",Font.PLAIN,12), Color.BLACK),c);
		
		c.gridx = 1;
		c.gridy = 2;
		p.add(tileWidthTf,c);
		
		//the height of tile
		c.gridx = 2;
		c.gridy = 2;
		p.add(GUI.setLabel("Height of a tile", new Font("arial",Font.PLAIN,12), Color.BLACK),c);
		
		c.gridx = 3;
		p.add(tileHeightTf,c);
		
		//the rows
		c.gridx = 1;
		c.gridy = 3;
		p.add(rowsTf,c);
		
		c.gridx = 0;
		c.gridy = 3;
		p.add(GUI.setLabel("How many rows", new Font("arial",Font.PLAIN,12), Color.BLACK),c);
		//the columns
		c.gridx = 2;
		c.gridy = 3;
		p.add(GUI.setLabel("how many columns", new Font("arial",Font.PLAIN,12), Color.BLACK),c);
		
		c.gridx = 3;
		p.add(columnsTf,c);
		
		
		//the load panel and button
		c.insets = new Insets(1,1,1,1);
		c.gridx = 1;
		c.gridy =1;
		loadPanel.add(loadButton,c);
		
		
	}
	
	public void setVisibility(boolean visible) {
		f.setVisible(visible);
	}
	
	public JFrame getFrame() {return f;}
}
