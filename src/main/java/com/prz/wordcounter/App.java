package com.prz.wordcounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.jfree.chart.JFreeChart;

/**
 * Hello world!
 *
 */
public class App {
	public String inputText;
	public static List<String> words;

	/**
	 * Metoda odpowidzialna za podzielenie wejsciowego tekstu na slowa.
	 * 
	 * @param inputString
	 *            - caly tekst
	 * @return zwraca list wszystkich slow
	 */
	public static List<String> splitInputIntoWords(String inputString) {
		words = new ArrayList<String>();
		String[] w = inputString.split(" |\\.|\\,|\\:|\\!|\\?|\\'|\"");
		words = Arrays.asList(w);

		ArrayList<String> word2 = new ArrayList<String>();
		for (String item : words) {
			if (item.length() != 0)
				word2.add(item);
		}

		return word2;
	}

	/**
	 * Metoda odpowiedzialna za zliczenie ilosci wystapien slow o konkretnej
	 * dlugosci.
	 * 
	 * @param words
	 *            - lista zawierajaca wszystkie slowa
	 * @return zwraca mape w postaci: key - dlugosc slowa value - ilosc
	 *         wystapien slowa o danej dlugosci
	 */
	public static Map<Integer, Integer> countWords(ArrayList<String> words) {
		Map<Integer, Integer> wordCountList = new HashMap<Integer, Integer>();
		for (String word : words) {
			if (wordCountList.get(word.length()) == null)
				wordCountList.put(word.length(), 1);
			else
				wordCountList.put(word.length(),
						wordCountList.get(word.length()) + 1);
		}

		return wordCountList;
	}

	public static String getData() {
		File file = new File("C:\\src\\Java\\Eclipse\\Nowe\\wordcounter\\files\\data.txt");
		StringBuffer stringBuffer = new StringBuffer();
		
		try {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				stringBuffer.append(scanner.nextLine());
			}
						
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
		
		String data = stringBuffer.toString();
		return data;
	}

	public static void main(String[] args) {

		// ####### GENERATE CHART ####### 
		//getData (file)
		//split (String inputData)
		//count (ArrayList slowa)
		//makeChart(Map statistics)
		
		String data = getData();
		List<String> slowa = new ArrayList<String>();
		slowa = splitInputIntoWords(data);

		for (String item : slowa) {
			System.out.println(item);
		}

		Map<Integer, Integer> statistics = new HashMap<Integer, Integer>();

		statistics = countWords((ArrayList<String>) slowa);
		for (Map.Entry<Integer, Integer> item : statistics.entrySet()) {
			System.out.println(item.getKey() + " literowych slow jest: "
					+ item.getValue());
		}

		BarChart chartCreator = new BarChart();
		JFreeChart chart = chartCreator.createBarChart(statistics);
		chartCreator.saveCharAsJPG(chart);
		// GUI.setChart(chart);
//		new ChartGUI(chart);
		new MainGUI(chart);

	}
}
