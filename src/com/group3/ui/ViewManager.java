package com.group3.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.group3.Main;
import com.group3.data.DataManager;
import com.group3.ui.listener.MenuListener;
import com.group3.ui.listener.WindowContainerListener;
import com.sun.glass.events.KeyEvent;

/**
 * @author Connor Mahaffey
 */
public class ViewManager {
	
	private DataManager dataRef;
	private boolean exit;
	
	private JFrame windowFrame;
	private UMLScene umlScene;
	
	//Arrays to provide the KeyEvents and KeyMasks for menu options
	private int[] keyArray = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57};
	private int[] maskArray = {8, 2, 1};
	
	/**
	 * Create listeners for our windows which report back to this object.
	 * 
	 * Create our main window, which has a Menu Bar and a Window Manager
	 * for our UML Diagram itself.
	 */
	public ViewManager() {
		/* Listeners */
		WindowContainerListener windowContainerListener 
			= new WindowContainerListener(this);
		MenuListener menuListener = new MenuListener(this);
		
		/* Window Frame */
		this.windowFrame = new JFrame();
		this.windowFrame.setTitle("UML Editor " + Main.version);
		this.windowFrame.setLocationRelativeTo(null); //get window to be centered
		this.windowFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		this.windowFrame.addWindowListener(windowContainerListener);
		
		/* Menu Bar */
		this.windowFrame.setJMenuBar(createMenuBar(menuListener));
		
		/* UML Diagram Background and Windows */
		this.umlScene = new UMLScene();
		this.umlScene.setDragMode(JDesktopPane.LIVE_DRAG_MODE);
		this.windowFrame.add(umlScene);
		
		this.windowFrame.pack();
		this.windowFrame.setVisible(true);
	}
	
	/**
	 * TODO: Add more types, fill in actions, possibly add submenus for Connectors
	 * @param menuListener the listener object for the menu bar.
	 * @return the JMenuBar for the program
	 */
	private JMenuBar createMenuBar(MenuListener menuListener) {
		Font font = new Font("Times New Roman", Font.PLAIN, 18);
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu file = new JMenu("File");
		file.setFont(font);
		/* setAccelerator sets keyboard shortcuts for the actions*/
		file.add(createMenuItem("Open", font, menuListener, keyArray[1], maskArray[0]));
		file.add(createMenuItem("Save", font, menuListener, keyArray[2], maskArray[0]));
		file.add(createMenuItem("Exit", font, menuListener, keyArray[3], maskArray[0]));
		menuBar.add(file);
		
		JMenu add = new JMenu("Add");
		add.setFont(font);
		add.add(createMenuItem("Class Box", font, menuListener, keyArray[1], maskArray[1]));
		add.add(createMenuItem("Connector", font, menuListener, keyArray[2], maskArray[1]));
		add.add(createMenuItem("Some Other Thing", font, menuListener, keyArray[3], maskArray[1]));
		menuBar.add(add);
		
		return menuBar;
	}
	
	/**
	 * Shortcut for creating a JMenuItem
	 * @param text text of the option
	 * @param font font of the option
	 * @param listener listener for the option
	 * @return a JMenuItem
	 */
	private JMenuItem createMenuItem(String text, Font font, MenuListener listener, int key, int mask) {
		/* KeyEvent.VK_T allows the user to toggle through the menu by pressing T
		 * They have to be in the menu for this to work. */
		JMenuItem item = new JMenuItem(text, KeyEvent.VK_T);
		item.setAccelerator(KeyStroke.getKeyStroke(key ,mask));
		item.addActionListener(listener);
		item.setFont(font);
		
		return item;
	}
	
	public void addClassBox() {
		ClassBox classBox = new ClassBox("Test");//TODO: remove
		classBox.setLocation(30, 30);
		classBox.setVisible(true);
		classBox.setSize(200, 300);
		
		int id = this.dataRef.addClassBoxData(classBox);
		classBox.setId(id);
		
		this.umlScene.add(classBox);
		
		try {
			classBox.setSelected(true);
		} catch (PropertyVetoException e) {
			System.err.println("Class Box could not be selected.");
		}
	}
	
	/**
	 * TODO: Signal DataManager to save. Dispose of Views.
	 */
	public void doExit() {
		//save here, when exit is set to true, it will exit *exactly then*
		this.exit = true;
		System.out.println(this.dataRef.toString()); //TODO: Remove this debug output
		
		System.exit(0);
	}
	
	/**
	 * @return has the user told the view to exit
	 */
	public boolean isExiting() {
		return this.exit;
	}
	
	/**
	 * @param dm reference to the Data Manager
	 */
	public void setDataManager(DataManager dataManager) {
		this.dataRef = dataManager;
	}
}
