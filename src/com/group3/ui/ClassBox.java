package com.group3.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import com.group3.ui.listener.ClassBoxListener;
import com.group3.ui.listener.MouseDragListener;
import com.group3.ui.listener.MouseEventListener;


/**
 * @author Connor Mahaffey
 * 		   David Mengel
 */
@SuppressWarnings("serial")
public class ClassBox extends JInternalFrame {
	
	private int id = 0;
	private boolean addBorder = false, selectable = false;
	private MouseListener popupListener;
	private ClassBoxListener classBoxListener;
	private JTextArea titleTextArea;
	private JPanel titlePanel;
	private Stack<JTextArea> textStack;

	
	/**
	 * A view representing a Class Box
	 * 
	 * @param title title of the Class Box, listed in its window and its first field
	 * @param id integer id used as a reference to this boxes data component
	 */
	public ClassBox(String title, ViewManager viewRef) {
		super("", true); //JInternalFrame title, resizable

		this.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));
		this.setBackground(Color.GRAY.brighter());
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		this.textStack = new Stack<JTextArea>();

		BasicInternalFrameUI basic = (BasicInternalFrameUI)this.getUI();
		basic.setNorthPane(null);
		
		this.classBoxListener = new ClassBoxListener(viewRef, this);
		this.addInternalFrameListener(this.classBoxListener);
        this.addComponentListener(this.classBoxListener);

		createTitleBox(title);
		createPopupMenu(viewRef);
		createTextBox();
		addDrag();
		
	}

	/**
	 * adds dragging capability 
	 */
	private void addDrag() {
		MouseDragListener dragListener = new MouseDragListener(this);
		this.titlePanel.addMouseListener(dragListener);
		this.titlePanel.addMouseMotionListener(dragListener);
	}
	
	/**
	 * Set up the right click context manager
	 * @param viewRef View Manager reference the class box uses to remove itself when deleted
	 */
	private void createPopupMenu(ViewManager viewRef) {
        JMenuItem menuItem;
        //Create the popup menu.
        JPopupMenu popup = new JPopupMenu();
        menuItem = new JMenuItem("Add Section");
        menuItem.addActionListener(this.classBoxListener);
        popup.add(menuItem);
        menuItem = new JMenuItem("Remove Section");
        menuItem.addActionListener(this.classBoxListener);
        popup.add(menuItem);
        menuItem = new JMenuItem("Delete Class Box");
        menuItem.addActionListener(this.classBoxListener);
        popup.add(menuItem);
 
        this.popupListener = new MouseEventListener(popup);
        this.addMouseListener(this.popupListener);
	}
	
	/**
	 * Adds an editable title to the class Box
	 * Border is added to the bottom of the JPanel to reflect the border added to text Boxes
	 * Cursor is set to a custom move cursor to show the user is able to drag this classBox from this JPanel
	 */
	public void createTitleBox(String title) {

		this.titlePanel = new JPanel();

		this.titleTextArea = new JTextArea(title, 1, 1);
		this.titleTextArea.setBackground(Color.GRAY.brighter());
		this.titleTextArea.setForeground(Color.BLACK);
		this.titleTextArea.setFont(new Font("Times New Roman", Font.BOLD, 14));
		this.titlePanel.add(this.titleTextArea);
		this.titlePanel.setBackground(Color.GRAY.brighter());
		this.titlePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, Color.BLACK));
		
		this.titlePanel.addFocusListener(this.classBoxListener);
		this.titlePanel.setCursor(moveCursor());

		this.add(this.titlePanel);
	}
	
	/**
	 * Adds a new textbox to the Class Box.
	 * 
	 * Dividers are created by adding a matte border to the top of every
	 * text box added after the first.
	 */
	public void createTextBox() {
		JTextArea textArea = new JTextArea();
		textArea.setBackground(Color.GRAY.brighter());
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textArea.setForeground(Color.BLACK);
		if(this.addBorder) {
			textArea.setBorder(
					BorderFactory.createCompoundBorder(
							BorderFactory.createMatteBorder(4, 0, 0, 0, Color.BLACK), 
							BorderFactory.createEmptyBorder(5, 5, 5, 5)
						)
					);
		} else {
			this.addBorder = true;
			textArea.setMargin(new Insets(5, 5, 5, 5));
		}

		textArea.addMouseListener(this.popupListener);
		textArea.addFocusListener(this.classBoxListener);
		this.add(textArea);
		this.textStack.add(textArea);
		
		this.revalidate();
	}
	
	/**
	 * Removes the most recent text area added.
	 */
	public void removeTextBox() {
		if(this.textStack.size() > 1) {
			JTextArea mostRecent = this.textStack.pop();
			this.remove(mostRecent);
		}
		
		this.revalidate();
	}
	
	/**
	 * @return string array representation of the data within the text boxes, starting with the title box
	 */
	public String[] getArrayRepresentation() {
		String[] content = new String[this.textStack.size() + 1];
		content[0] = this.titleTextArea.getText();
		
		for(int i = 0; i < this.textStack.size(); ++i) {
			content[i + 1] = textStack.get(i).getText();
		}
		
		return content;
	}

	/**
	 * Set the default size to 200 by 200
	 */
	public Dimension getPreferredSize() {
		return new Dimension(200, 200);
	}
	
	/**
	 * Set the unique id of this Class Box, which is uses to communicate
	 * with the Data Manager.
	 * 
	 * This can only be set once, afterwards it will not change.
	 * 
	 * @param id unique id to give this object
	 */
	public void setId(int id) {
		if(this.id == 0 && id != 0) {
			this.id = id;
		}
	}
	/**
	 * creates a custom cursor using a local png file. 
	 * Reason is Mac systems appear to not recognize when using Java's own 'MOVE_CURSOR' 
	 * 
	 * @return c returns the custom cursor
	 */
	public Cursor moveCursor() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage(ClassLoader.getSystemResource("cursor.png"));
		Cursor c = toolkit.createCustomCursor(image, new Point(10, 10), "img");
		
		return c;
	}
	
	/**
	 * @return the unique id that ties this object to its data member
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Fill this Class Box with saved data.
	 * 
	 * @param data the data to parse
	 */
	public void loadFromTextData(String data) {
		String[] pieces = data.split(";");
		
		String[] info = pieces[0].split(" ");
		int posX = Integer.parseInt(info[0]);
		int posY = Integer.parseInt(info[1]);
		int width = Integer.parseInt(info[2]);
		int height = Integer.parseInt(info[3]);
		this.setLocation(posX, posY);
		this.setSize(width, height);
		this.titleTextArea.setText(pieces[1]);
		
		for(int i = 2; i < pieces.length; ++i) {
			if(i > 2) {
				//first text box already created
				this.createTextBox();
			}
			JTextArea textArea = this.textStack.lastElement();
			textArea.setText(pieces[i]);
		}
	}
	
	/**
	 * @return can this component be selected for a relationship
	 */
	public boolean isSelectable() {
		return this.selectable;
	}
	
	/**
	 * Set whether this Class Box can be selected.
	 * 
	 * This option gets turned on when a relationship has been chosen and Class Boxes for the
	 * relationship are getting selected.
	 * 
	 * @param s the value to set the selectable property
	 */
	public void setSelectable(boolean s) {
		this.selectable = s;
	}
	
	/**
	 * Sets the border color of the class box
	 * @param color color to set the border to
	 */
	public void setBorderColor(Color color) {
		this.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, color));
	}

}
