package com.group3Test.ui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JDesktopPane;


/**
 * @author Connor Mahaffey
 */
@SuppressWarnings("serial")
public class UMLScene extends JDesktopPane {
	
	private static final int SLEEP_TIME = 15;//ms
	
	private int tempPos = 25, tempDir = 1; //TODO: Remove temp, illustration only
	
	/**
	 * Update our arrow drawings on a per-frame basis. A SLEEP_TIME of 15 will give
	 * us ~67 fps. Without this sleep time, faster machines will run this code faster.
	 * 
	 * Note: If we start drawing a lot, we should also measure how long it takes for each
	 * draw call to occur, and subtract it from the SLEEP_TIME. Otherwise, a very slow
	 * computer may take 10ms to draw, then sleep 15ms, while a good computer will take 1ms
	 * to draw and sleep for 15ms. As it is, this draws near instantly anyway.
	 * 
	 * TODO: Change to only use repaint on JInternalWindow movements?
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.drawString("This is where arrows will go. Recalculate where they should " +
					 "be each frame and it will appear to do smooth animation.", 25, tempPos);
		if(tempPos == 400) {
			tempDir = -1;
		}
		if(tempPos == 25) {
			tempDir = 1;
		}
		tempPos += tempDir;
		
		try {
			Thread.sleep(SLEEP_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		repaint();
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(800, 600);
	}
}
