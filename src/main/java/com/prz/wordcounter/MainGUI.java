package com.prz.wordcounter;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	public MainGUI() {
		setSize(500, 400);
		setVisible(true);
		setTitle("Word Counter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
	
}
