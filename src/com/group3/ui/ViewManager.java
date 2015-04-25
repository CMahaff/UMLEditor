package com.group3.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.KeyboardFocusManager;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.io.File;

import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.group3.Main;
import com.group3.data.DataManager;
import com.group3.ui.listener.MenuListener;
import com.group3.ui.listener.MouseEventListener;
import com.group3.ui.listener.RelationshipMouseEventListener;
import com.group3.ui.listener.UMLSceneManager;
import com.group3.ui.listener.WindowContainerListener;

/**
 * @author Connor Mahaffey
 * 		   David Mengel
 */
public class ViewManager {
	
	private DataManager dataRef;
	private boolean exit, showRelationshipHint, showRelationshipRemovalHint, removing;
	
	private JFrame windowFrame;
	private UMLScene umlScene;
	
	//Arrays to provide the KeyEvents and KeyMasks for menu options
	private KeyboardShortcuts keyboardShortcuts = new KeyboardShortcuts();
	private File saveFile = null;
	
	private RelationshipSelectionManager relSelManager;
	
	private MouseListener popupListener;
	
	
	/**
	 * Create listeners for our windows which report back to this object.
	 * 
	 * Create our main window, which has a Menu Bar and a Window Manager
	 * for our UML Diagram itself.
	 */
	public ViewManager(DataManager dataRef) {
		
		this.dataRef = dataRef;
		
		/* Listeners */
		WindowContainerListener windowContainerListener 
			= new WindowContainerListener(this);
		MenuListener menuListener = new MenuListener(this);

		
		/* Window Frame */
		this.windowFrame = new JFrame();
		this.windowFrame.setTitle("UML Editor " + Main.version);
		this.windowFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		this.windowFrame.addWindowListener(windowContainerListener);
		KeyboardFocusManager.getCurrentKeyboardFocusManager()
			.addKeyEventDispatcher(windowContainerListener);
		this.windowFrame.addComponentListener(windowContainerListener);
		
		/* Menu Bar */
		this.windowFrame.setJMenuBar(createMenuBar(menuListener));

		/* UML Diagram Background and Windows */
		this.umlScene = new UMLScene(this.dataRef);
		this.umlScene.setDragMode(JDesktopPane.LIVE_DRAG_MODE);
		this.umlScene.setDoubleBuffered(true);
		this.umlScene.setBackground(Color.WHITE);
		this.umlScene.setPreferredSize(new Dimension(800, 600));
		UMLSceneManager umlSceneManager = new UMLSceneManager(this.dataRef);
		this.umlScene.setDesktopManager(umlSceneManager);
		
		JScrollPane scrollPane = new JScrollPane(this.umlScene);

		
		createRelationshipPopupMenu(menuListener, this.umlScene);
		
		this.windowFrame.add(scrollPane);
		this.windowFrame.pack();
		this.windowFrame.setVisible(true);
		this.windowFrame.setLocationRelativeTo(null); //get window to be centered
		
	
		this.showRelationshipHint = true;
		this.showRelationshipRemovalHint = true;
		
		
	}
	

	private void createRelationshipPopupMenu(MenuListener menuListener, UMLScene window) {
		
		JPopupMenu popup = new JPopupMenu();
		JMenuItem menuItem;
		menuItem = new JMenuItem("Delete Relationship");
		menuItem.addActionListener(menuListener);
		popup.add(menuItem);
		menuItem = new JMenuItem("Add Cardinality");
		menuItem.addActionListener(menuListener);
		popup.add(menuItem);

		this.popupListener = new RelationshipMouseEventListener(popup, window);
		window.addMouseListener(this.popupListener);
		

}
	
	/**
	 * TODO: Add more types, fill in actions, possibly add submenus for Connectors
	 * @param menuListener the listener object for the menu bar.
	 * @return the JMenuBar for the program
	 */
	private JMenuBar createMenuBar(MenuListener menuListener) {
		//adds native feel to Mac Systems
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "UML Editor");
		Font font = new Font("Times New Roman", Font.PLAIN, 18);
		
		JMenuBar menuBar = new JMenuBar();
			
	
		JMenu file = new JMenu("File");
		file.setFont(font);
		file.add(createMenuItem("New", font, menuListener, "CTRL", "N"));
		file.add(createMenuItem("Open", font, menuListener, "CTRL", "O"));
		file.add(createMenuItem("Save", font, menuListener, "CTRL", "S"));
		file.add(createMenuItem("Save As", font, menuListener, "CTRL", "A"));
		file.add(createMenuItem("Exit", font, menuListener, "CTRL", "E"));
		
		menuBar.add(file);
		
		JMenu edit = new JMenu("Edit");
		edit.setFont(font);

		edit.add(createMenuItem("Class Box", font, menuListener, "ALT", "C"));
		JMenuItem relationship = new JMenu("Relationship");
		relationship.setFont(font);
		relationship.add(createMenuItem("Association", font, menuListener, "ALT", "B"));
		relationship.add(createMenuItem("Dependency", font, menuListener, "ALT", "D"));
		relationship.add(createMenuItem("Aggregation", font, menuListener, "ALT", "A"));
		relationship.add(createMenuItem("Composition", font, menuListener, "ALT", "I"));
		relationship.add(createMenuItem("Generalization", font, menuListener, "ALT", "G"));
		edit.add(relationship);
		edit.add(createMenuItem("Remove Relationship", font, menuListener, "CTRL", "R"));
		menuBar.add(edit);
		
		JMenu window = new JMenu("Window");
		window.setFont(font);
		window.add(createMenuItem("Increase Window Size", font, menuListener, "CTRL", "I"));
		window.add(createMenuItem("Decrease Window Size", font, menuListener, "CTRL", "D"));
		
		menuBar.add(window);
		
		return menuBar;
	}
	
	/**
	 * Shortcut for creating a JMenuItem
	 * @param text text of the option
	 * @param font font of the option
	 * @param listener listener for the option
	 * @return a JMenuItem
	 */
	private JMenuItem createMenuItem(String text, Font font, MenuListener listener, String key, String mask) {
		/* KeyEvent.VK_T allows the user to toggle through the menu by pressing T
		 * They have to be in the menu for this to work. */
		int keyStroke = keyboardShortcuts.checkKey(key);
		int keyMask = keyboardShortcuts.checkKey(mask);
		
		JMenuItem item = new JMenuItem(text, KeyEvent.VK_T);
		item.setAccelerator(KeyStroke.getKeyStroke(keyMask , keyStroke));
		item.addActionListener(listener);
		item.setFont(font);
		
		return item;
	}
	
	/**
	 * Runs the UI routine to create a Class Box,
	 * including prompting for the name
	 */
	public void addClassBox() {
		Object[] titleText = null;
		String title = (String)JOptionPane.
				showInputDialog(windowFrame, "Name of the Class Box?", 
								"Class Box Name", JOptionPane.PLAIN_MESSAGE, 
								null , titleText, null);
		if(title == null) {
			//user hit cancel
			return;
		}

		ClassBox classBox = new ClassBox(title, this);
		classBox.setLocation(30, 30);

		classBox.setVisible(true);
		classBox.setSize(200, 300);
		
		int id = this.dataRef.addClassBoxData(classBox.getX(), classBox.getY(), 
											  classBox.getWidth(), classBox.getHeight(), title);
		classBox.setId(id);
		
		this.umlScene.add(classBox);
		
		try {
			classBox.setSelected(true);
		} catch (PropertyVetoException e) {
			System.err.println("Class Box could not be selected.");
		}
		
		this.umlScene.repaint();
	}
	
	/**
	 * Removes a Class Box from the view
	 * @param classBox the Class Box to remove
	 */
	public void removeClassBox(ClassBox classBox) {
		classBox.doDefaultCloseAction();
		this.dataRef.removeClassBoxData(classBox.getId());
		this.umlScene.remove(classBox);
		this.umlScene.repaint();
	}
	
	
	/**
	 * TODO: Signal DataManager to save. Dispose of Views.
	 */
	public void doExit() {
		//save here, when exit is set to true, it will exit *exactly then*
		this.exit = true;
		
		System.exit(0);
	}
	
	/**
	 * Runs the UI routines to open a new (empty) UML diagram.
	 */
	public void newUML() {
		this.umlScene.removeAll();
		this.dataRef.clearData();
		this.umlScene.repaint();
		this.saveFile = null;
		this.windowFrame.setTitle("UML Editor " + Main.version);
	}
	
	/**
	 * Runs the UI routines to open a UML diagram.
	 */
	public void open() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Open UML Diagram");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);		
		
		FileFilter filter = new FileNameExtensionFilter("UML Diagram Format", "uml");
		fileChooser.setFileFilter(filter);
		int choice = fileChooser.showOpenDialog(this.windowFrame);
		
		if(choice == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			if(!file.getAbsoluteFile().toString().toLowerCase().endsWith(".uml")) {
				//TODO: Remove?
				JOptionPane.showMessageDialog(null, 
						  "Cannot open file that do not end in .uml!", 
						  "Error!",
						  JOptionPane.ERROR_MESSAGE);
			} else {
				//clear old boxes out
				this.umlScene.removeAll();
				
				//load text data from file
				this.saveFile = file;
				this.windowFrame.setTitle("UML Editor " + Main.version + " - " + file.getName());
				String[] classBoxes = this.dataRef.loadModel(this.saveFile);
				
				//create new class boxes and add them back to the model
				int maxWidth = 800, maxHeight = 600;
				for(int i = 0; i < classBoxes.length - 1; ++i) {
					ClassBox classBox = new ClassBox("", this);
					classBox.loadFromTextData(classBoxes[i]);
					classBox.setVisible(true);
					
					int id = this.dataRef.addClassBoxData(classBox.getX(), classBox.getY(),
														  classBox.getWidth(), classBox.getHeight(), 
														  classBox.getArrayRepresentation());
					classBox.setId(id);
					
					if(classBox.getX() + classBox.getWidth() > maxWidth) {
						maxWidth = classBox.getX() + classBox.getWidth();
					}
					if(classBox.getY() + classBox.getHeight() > maxHeight) {
						maxHeight = classBox.getY() + classBox.getHeight();
					}
					
					this.umlScene.add(classBox);
				}
				//set the window size big enough to display all the classes boxes that were added
				this.umlScene.setPreferredSize(new Dimension(maxWidth, maxHeight));
				this.umlScene.revalidate();
				//redraws the new class boxes
				this.umlScene.repaint();
			}
		}
	}
	
	/**
	 * Runs the UI routine to save a UML diagram
	 * that we already opened and/or saved. This
	 * will not prompt for a location to save.
	 * 
	 * If we don't have a name to save the file as,
	 * this will call saveAs().
	 */
	public void save() {
		if(this.saveFile == null) {
			saveAs();
		} else {
			dataRef.saveModel(this.saveFile);
			JOptionPane.showMessageDialog(null, 
										  this.saveFile.getName() + " saved!", 
										  "Saved",
										  JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * Runs the UI routines to save a file
	 * with a given name.
	 */
	public void saveAs() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save UML As");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);		
		
		FileFilter filter = new FileNameExtensionFilter("UML Diagram Format", "uml");
		fileChooser.setFileFilter(filter);
		int choice = fileChooser.showSaveDialog(this.windowFrame);
		
		if(choice == JFileChooser.APPROVE_OPTION) {
			this.saveFile = fileChooser.getSelectedFile();
			if(!this.saveFile.getAbsoluteFile().toString().toLowerCase().endsWith(".uml")) {
				this.saveFile = new File(this.saveFile.getAbsoluteFile().toString().concat(".uml"));
			}
			dataRef.saveModel(this.saveFile);
			this.windowFrame.setTitle("UML Editor " + Main.version + " - " + this.saveFile.getName());
		}
	}
	
	/**
	 * Many events trigger the view to update the data representation of the Class Box. For
	 * text entry, this is a FocusLost event, meaning the contents of the text will be saved
	 * when you select another text area, another text box, or open the save menu.
	 * 
	 * This fails when a key binding is used to save the UML diagram, the keybinding
	 * does not trigger a FocusChange event. This method will find the currently selected
	 * Class Box and force its data to update before saving.
	 */
	public void updateSelectedClassBoxChanges() {
		ClassBox classBox = (ClassBox) this.umlScene.getSelectedFrame();
		if(classBox != null) {
			this.dataRef.updateClassBoxData(classBox.getId(), classBox.getX(), classBox.getY(), 
											classBox.getWidth(), classBox.getHeight(), 
											classBox.getArrayRepresentation());
		}
	}
	
	/**
	 * @return has the user told the view to exit
	 */
	public boolean isExiting() {
		return this.exit;
	}
	
	/**
	 * @return Reference to the Data Manager
	 */
	public DataManager getDataManager() {
		return this.dataRef;
	}
	
	/**
	 * Calls the repaint method on the UML diagram.
	 * 
	 * This is used to ensure arrows are redrawn as window components change.
	 */
	public void repaintUML() {
		this.umlScene.repaint();
	}
	
	/**
	 * Begins a relationship selection event.
	 * 
	 * There must be at least two class boxes, on the first run, this method
	 * will show a dialog explaining the relationship linking process.
	 * 
	 * @param relationshipType the type of relationship being created
	 */
	public void startRelationshipSelection(int relationshipType) {
		removing = false;
		if(this.umlScene.getComponents().length < 2) {
			JOptionPane.showMessageDialog(this.windowFrame,
										  "You have at least 2 Class Boxes on screen to " +
										  "form a relationship", "Error",
										  JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(this.showRelationshipHint) {
			JOptionPane.showMessageDialog(this.windowFrame, 
					  					  "Select two Class Boxes to link with the relationship.\n\n" +
					  					  "Hit ESC to cancel relationship placement.",
					  					  "Hint", JOptionPane.INFORMATION_MESSAGE);
			this.showRelationshipHint = false;
		}
		
		this.umlScene.setBackground(Color.GRAY);
		
		ClassBox classBox = (ClassBox)this.umlScene.getSelectedFrame();
		if(classBox != null) {
			try {
				classBox.setSelected(false);
			} catch (PropertyVetoException e) {
				System.err.println("Class Box could not be unselected.");
				System.exit(1);
			}
		}
		
		this.relSelManager = new RelationshipSelectionManager(this, relationshipType);
		
		for(JInternalFrame entry : this.umlScene.getAllFrames()) {
			ClassBox c = (ClassBox)entry;
			c.setSelectable(true);
		}
	}
	
	/**
	 * Begins a relationship removal selection event.
	 * 
	 * There must be at least two class boxes, and a relationship created. On the 
	 * first run, it will show a dialog explaining the relationship removal process.
	 * 
	 */
	
public void startRelationshipRemovalSelection() {
		removing = true;
		if(this.umlScene.getComponents().length < 2 | this.dataRef.getRelationshipData().isEmpty()) {
			JOptionPane.showMessageDialog(this.windowFrame,
										  "You must have at least 2 Class Boxes and a relationship " +
										  "on screen to remove a relationship", "Error",
										  JOptionPane.ERROR_MESSAGE);
			return;
		}
				

		
		if(this.showRelationshipRemovalHint) {
			JOptionPane.showMessageDialog(this.windowFrame, 
					  					  "Select two Class Boxes to remove the relationship between them.\n\n" +
					  					  "Hit ESC to cancel the relationship removal.",
					  					  "Hint", JOptionPane.INFORMATION_MESSAGE);
			this.showRelationshipRemovalHint = false;
		}
		
		this.umlScene.setBackground(Color.RED.darker().darker());
		
		ClassBox classBox = (ClassBox)this.umlScene.getSelectedFrame();
		if(classBox != null) {
			try {
				classBox.setSelected(false);
			} catch (PropertyVetoException e) {
				System.err.println("Class Box could not be unselected.");
				System.exit(1);
			}
		}
		
		this.relSelManager = new RelationshipSelectionManager(this);

		
		for(JInternalFrame entry : this.umlScene.getAllFrames()) {
			ClassBox c = (ClassBox)entry;
			c.setSelectable(true);
		}
		
	}

	
	/**
	 * This methods ends a selection event and changes the view back to normal.
	 * 
	 * startRelationshipSelection() must be called before this method
	 */
	public void endRelationshipSelection() {
		
		if(this.umlScene.getBackground() == Color.WHITE) {
			return;
		}
		
		this.umlScene.setBackground(Color.WHITE);
		
		for(JInternalFrame entry : this.umlScene.getAllFrames()) {
			ClassBox c = (ClassBox)entry;
			c.setSelectable(false);
			c.setBorderColor(Color.BLACK);
		}
	}
	
	/**
	 * Resize the main UML window manually. If the new difference would make the window
	 * less than 200 pixels in size in either direction, ignore the change
	 * @param difference the difference, in pixel width/height, to apply to the window
	 */
	public void resizeWindow(int difference) {
		if(this.umlScene.getWidth() + difference < 200 || this.umlScene.getHeight() + difference < 200) {
			return;
		}
		this.umlScene.setPreferredSize(
				new Dimension(this.umlScene.getWidth() + difference, 
							  this.umlScene.getHeight() + difference));
		this.umlScene.revalidate();
	}
	
	/**
	 * Resizes the UML window when the window size is bigger than it.
	 * This ensures that class boxes placed when the window is maximized
	 * can be scrolled to if the window is shrunk again.
	 */
	public void resizeToFit() {
		Dimension windowSize = this.windowFrame.getSize();
		Dimension umlSize = this.umlScene.getPreferredSize();
		if(windowSize.getWidth() > umlSize.getWidth() + 40 || windowSize.getHeight() > umlSize.getHeight() + 90) {
			this.umlScene.setPreferredSize(new Dimension(windowSize.width - 40, windowSize.height - 90));
		}
	}
	
	/**
	 * Get a reference to the Relationship Selection Manager,
	 * which helps keep track of which Class Boxes are selected.
	 * 
	 * @return the Relationsip Selection Manager
	 */
	public RelationshipSelectionManager getRelationshipSelectionManager() {
		return this.relSelManager;
	}
	
	/**
	 * Get a reference to the removing variable,
	 * which helps determine whether or not we are currently in
	 * the adding relationship or removing relationship operation. 
	 * 
	 */
	public boolean getRemoving() {
		return this.removing;
	}
	
	
/**
 * Gets a reference to the UMLScene
 * 
 * @return is the umlScene that is being referenced
 */
	public UMLScene getUMLScene() {
		return this.umlScene;
	}
}

