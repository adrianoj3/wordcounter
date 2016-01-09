package com.prz.wordcounter;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.jfree.chart.JFreeChart;

/**
 * Hello world!
 *
 */
public class App extends JFrame{
	private static final long serialVersionUID = 1L;
	private static final int windowHeight = 150;
	private static final int windowWidth = 200;
	private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

	public static File selectedFile;
	
	public App() {
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
				generateChat();
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
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
	}

	public void generateChat() {
		BarChart chartCreator = new BarChart();
		DataProcesor dataProcesor = new DataProcesor();
		List<String> wordsList = new ArrayList<String>();
		Map<Integer, Integer> statistics = new HashMap<Integer, Integer>();
		
		if(selectedFile != null) {
			String data = dataProcesor.getData(selectedFile);
			wordsList = dataProcesor.splitInputIntoWords(data);
			statistics = dataProcesor.countWords((ArrayList<String>) wordsList);
			JFreeChart chart = chartCreator.createBarChart(statistics);
			new ChartGUI(chart);
		} else {
			JOptionPane.showMessageDialog(this, "Choose the file first");
		}
	}

	
	public static void main(String[] args) {
		
		new App();
		
	}
}
