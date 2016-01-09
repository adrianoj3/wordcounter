package com.prz.wordcounter;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class ChartGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	public ChartGUI(JFreeChart chart) {
		setSize(1000, 600);
		setVisible(true);
		setTitle("Word Occurance Chart");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		this.add(createPanel(chart));
	}

	public ChartPanel createPanel(JFreeChart chart) {
		return new ChartPanel(chart);
	}
}
