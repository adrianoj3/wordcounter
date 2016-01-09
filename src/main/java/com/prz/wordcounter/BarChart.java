package com.prz.wordcounter;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChart {

	private DefaultCategoryDataset dataset;
	private static String axisY = "Ilosc wystapien";
	private static String axisX = "Dlugosc slow";

	/**
	 * Metoda odpowiedzialna za stworzenie i wypelnienie danymi wu
	 * 
	 * @param data
	 */
	public JFreeChart createBarChart(Map<Integer, Integer> data) {
		dataset = new DefaultCategoryDataset();

		for (Map.Entry<Integer, Integer> word : data.entrySet()) {
			dataset.setValue(word.getValue(), axisY, word.getKey());
		}

		JFreeChart chart = ChartFactory.createBarChart(
				"Wystepowanie slow o okreslonej dlugosci", axisX, axisY,
				dataset, PlotOrientation.VERTICAL, false, true, false);

		return chart;
	}

	public void saveCharAsJPG(JFreeChart chart) {
		File charFile = new File("BarChart.jpg");

		try {
			if (charFile.exists())
				charFile.createNewFile();

			ChartUtilities
					.saveChartAsJPEG(
							new File(
									"C:\\src\\Java\\Eclipse\\Nowe\\wordcounter\\files\\chart.jpg"),
							chart, 1000, 600);
			System.out.println("File has been created");
		} catch (IOException e) {
			System.err.println("Problem occurred creating chart.");
		}
	}
}
