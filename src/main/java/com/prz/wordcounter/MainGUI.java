package com.prz.wordcounter;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.jfree.chart.JFreeChart;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int windowHeight = 150;
	private static final int windowWidth = 200;
	private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	public File selectedFile;
	public JFreeChart chart;
	
	public MainGUI(JFreeChart chart) {
		this.chart = chart;
		setBasicFrameSettings();
		setButton();
		SetMainLayout();
	}
	
	public File getSelectedFile() {
		return selectedFile;
	}

	public JFileChooser createFileChooser() {
		JFileChooser fileChooser = new JFileChooser();
		JLabel fileNameLabel = new JLabel();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
		    selectedFile = fileChooser.getSelectedFile();
		    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
		    
		    if(selectedFile != null) {
		    	fileNameLabel.setText("Selected file: " + selectedFile.getName());
		    } else {
		    	
		    }
		    
		    fileNameLabel.setBounds(windowWidth/2 - 40, 120, 200, 30);
		    this.add(fileNameLabel);
		}
		
		return fileChooser;
	}
	
	public void SetMainLayout() {
		JLabel titleLabel = new JLabel("Word Counter");
		titleLabel.setBounds(windowWidth/2 - 40, 30, 80, 30);
		this.add(titleLabel);
		
		JLabel chooseLabel = new JLabel("Choose file with data:");
		chooseLabel .setBounds(windowWidth/2 - 40, 60, 80, 30);
		this.add(chooseLabel );
	}
	
	public void setButton() {
		JButton browseButton = new JButton("Browse");
		browseButton.setBounds(windowWidth/2 - browseButton.getWidth()/2, 90, 100, 30);
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					createFileChooser();
			}
		});
		
		
		JButton generateButton = new JButton("Generate chart");
		generateButton.setBounds(windowWidth/2 - generateButton.getWidth()/2, 200, 150, 30);
		generateButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new ChartGUI(chart);
			}
		});
		
		this.add(browseButton);
		this.add(generateButton);
		
	}
	
	public void setBasicFrameSettings() {
		setSize(windowHeight, windowWidth);
		setVisible(true);
		setTitle("Word Counter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setMinimumSize(new Dimension(500, 400));
		setResizable(false);
		setLayout(null);
	}
}
