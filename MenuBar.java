package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import file_loader.FileLoader;
import panels.MapMakerPanel;
import tiles.Tile;

public class MenuBar extends JMenuBar implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JMenu loadSpriteSheet,saveMap;
	private JMenuItem load,save;
	private FileLoader fileLoader;
	private Tile[][] grid;
	private EnterInformationGUI enterInformationGUI;
	public MenuBar(MapMakerPanel mapMakerPanel,EnterInformationGUI enterInformationGUI) {
		super();
		this.grid = mapMakerPanel.getGrid();
		this.enterInformationGUI = enterInformationGUI;
	}
	public void init() {
		fileLoader = new FileLoader();
		//the menus
		loadSpriteSheet = new JMenu("Load Sprite Sheet");
		saveMap = new JMenu("Save Map");

		//the items
		load = new JMenuItem("Load");
		load.addActionListener(this);
		save = new JMenuItem("Save");
		save.addActionListener(this);
		
		loadSpriteSheet.add(load);
		saveMap.add(save);
		
		add(loadSpriteSheet);
		add(saveMap);
	}
	private String showSaveFileChooser() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		chooser.setDialogTitle("select location to save");
		//chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		
		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile().toString();
		}
		else {
			return null;
		}
	}
	private boolean replaceFile(FileLoader fileLoader) {
		if(fileLoader.exists()) {
			int replace = MessageDisplayer.displayYesNoMessage(null,"That file already exists. Would you like to overwrite that file?", "Replace file?");
			if(replace == JOptionPane.NO_OPTION) {
				return false;
			}
			else {
				return true;
			}
		}
		return true;
	}
	private void clickOnSave() {
		//name your file
		String name = showSaveFileChooser();
		if(name != null) {
			fileLoader.setFileName(name + ".txt");
		}
		else {
			fileLoader.setFileName("mappo1.txt");
		}
		
		if(!replaceFile(fileLoader)) {
			return;
		}
		
		fileLoader.createFile();
		
		//read the blocks on the screen
		for(int x =0; x < grid.length;x++) {
			for(int y = 0; y < grid[x].length;y++) {
				if(grid[x][y] != null) {
					fileLoader.write(String.valueOf(grid[x][y].getValue()) + " ");
				}
				else {
					fileLoader.write("-1" + " ");
				}
			}
			fileLoader.writeln("\n");
		}
		fileLoader.closeOutput();
		MessageDisplayer.displayMessage("Your file has been saved");
		//refresh the screen.
		for(int x =0; x < grid.length; x++) {
			for(int y =0; y < grid[x].length;y++) {
				grid[x][y] =null;
			}
		}
	}
	
	private void clickOnLoad() {
		if(!enterInformationGUI.getFrame().isVisible()) {
			enterInformationGUI.setVisibility(true);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == save) {
			clickOnSave();
		}
		else if(e.getSource() == load) {
			clickOnLoad();
		}
	}
}
