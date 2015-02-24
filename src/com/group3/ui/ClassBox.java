package com.group3.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import com.group3.ui.listener.ClassBoxListener;
import com.group3.ui.listener.PopupListener;


/**
 * @author Connor Mahaffey
 * 		   David Mengel
 */
@SuppressWarnings("serial")
public class ClassBox extends JInternalFrame {
	
	private int id = 0;
	private boolean addBorder = false;
	private MouseListener popupListener;
	private ClassBoxListener classBoxListener;
	private Stack<JTextArea> textStack;
	
	/**
	 * A view representing a Class Box
	 * 
	 * @param title title of the Class Box, listed in its window and its first field
	 * @param id integer id used as a reference to this boxes data component
	 */
	public ClassBox(String title, ViewManager viewRef) {
		super(title, true); //JInternalFrame title, resizability
		
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		this.textStack = new Stack<JTextArea>();
		
		createPopupMenu(viewRef);
		createTextBox();
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
 
        this.popupListener = new PopupListener(popup);
        this.addMouseListener(this.popupListener);
        this.addComponentListener(this.classBoxListener);
	}
	
	/**
	 * Adds a new textbox to the Class Box.
	 * 
	 * Dividers are created by adding a matte border to the top of every
	 * text box added after the first.
	 */
	public void createTextBox() {
		JTextArea textArea = new JTextArea();
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
