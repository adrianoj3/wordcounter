package com.prz.wordcounter;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class ChartGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private JFreeChart chart; 
	
	public ChartGUI(JFreeChart chart) {
		setSize(1000, 700);
		setVisible(true);
		setTitle("Word Occurance Chart");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setLayout(null);
		setResizable(false);
		this.chart = chart;
		
		this.add(createPanel(chart));
		createSaveButton();
	}

	public ChartPanel createPanel(JFreeChart chart) {
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(0,0,1000,600);
		return chartPanel;
	}
	
	public void createSaveButton() {
		JButton saveButton = new JButton("Save to file");
		saveButton.setBounds(850, 615, 100, 25);
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String fileName = JOptionPane.showInputDialog("File name: ");
				if(fileName != null) {
					new BarChart().saveCharAsJPG(chart, fileName);
					JOptionPane.showMessageDialog(null, "Chart saved successfully");	
				}
				fileName = null;
			}
		});
		
		this.add(saveButton);
		
	}
}
