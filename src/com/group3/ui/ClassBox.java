package com.group3.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
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
	private boolean addBorder = false;
	private MouseListener popupListener;
	private MouseDragListener dragListener;
	private ClassBoxListener classBoxListener;
	private Stack<JTextArea> textStack;

	
	/**
	 * A view representing a Class Box
	 * 
	 * @param title title of the Class Box, listed in its window and its first field
	 * @param id integer id used as a reference to this boxes data component
	 */
	public ClassBox(String title, ViewManager viewRef) {
		super(title, true); //JInternalFrame title, resizable

		this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		this.setBackground(Color.GRAY.brighter());
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		this.textStack = new Stack<JTextArea>();


		createTitleBox();
		createPopupMenu(viewRef);
		createTextBox();
		dragClassBox();
		
	}

	/**
	 * adds dragging capability 
	 */
	private void dragClassBox() {
		this.dragListener = new MouseDragListener(this);
		this.addMouseListener(this.dragListener);
		this.addMouseMotionListener(this.dragListener);

	}
	
	/**
	 * Set up the right click context manager
	 * @param viewRef View Manager reference the class box uses to remove itself when deleted
	 */
	private void createPopupMenu(ViewManager viewRef) {
        JMenuItem menuItem;
        this.classBoxListener = new ClassBoxListener(viewRef, this);
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
        this.addComponentListener(this.classBoxListener);
	}
	/**
	 * Adds an editable title to the class Box
	 * Border is added to the bottom of the JPanel to reflect the border added to text Boxes
	 * Cursor is set to a custom move cursor to show the user is able to drag this classBox from this JPanel
	 */
	public void createTitleBox() {

		JPanel titl = new JPanel();

		JTextArea titleText = new JTextArea(title,1,1);
		titleText.setBackground(Color.GRAY.brighter());
		titl.add(titleText);
		titl.setBackground(Color.GRAY.brighter());
		titl.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, Color.BLACK));
		
		titl.addFocusListener(this.classBoxListener);
		titl.setCursor(moveCursor());

		this.add(titl);
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
		if(this.addBorder) {
			textArea.setBorder(BorderFactory.createMatteBorder(4, 0, 0, 0, Color.BLACK));
		} else {
			this.addBorder = true;
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
	
	public String[] getArrayRepresentation() {
		String[] content = new String[this.textStack.size() + 1];
		content[0] = this.getTitle();
		
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
		Image image = toolkit.getImage("image/cursor.png");
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
		this.setTitle(pieces[1]);
		
		for(int i = 2; i < pieces.length; ++i) {
			if(i > 2) {
				//first text box already created
				this.createTextBox();
			}
			JTextArea textArea = this.textStack.lastElement();
			textArea.setText(pieces[i]);
		}
	}

}
