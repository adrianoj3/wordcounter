package com.prz.wordcounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DataProcesor {
	
	/**
	 * Metoda odpowidzialna za podzielenie wejsciowego tekstu na slowa.
	 * 
	 * @param inputString
	 *            - caly tekst
	 * @return zwraca list wszystkich slow
	 */
	public List<String> splitInputIntoWords(String inputString) {
		List<String> allWords = new ArrayList<String>();
		// Dzielenie wyrazu. Argument funkcji split ma postaæ \\znakDzielacyWyraz | \\znakDzielacyWyraz
		// Do znakow zastosowana jest alternatywa w postaci |
		String[] arrayOfWords = inputString.split(" |\\.|\\,|\\:|\\!|\\?|\\'|\"|\\||\\<|\\<|\\-|\\[|\\]|\\*|\\+|\\;|\\{|\\}|\\_|\\=|\\(|\\)|\\\t|\\—");
		allWords = Arrays.asList(arrayOfWords);

		ArrayList<String> finalWordsList = new ArrayList<String>();
		for (String item : allWords) {
			if (item.length() != 0)
				finalWordsList.add(item);
		}

		return finalWordsList;
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
	public Map<Integer, Integer> countWords(ArrayList<String> words) {
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

	public String getData(File file) {
//		File file = new File("C:\\src\\Java\\Eclipse\\Nowe\\wordcounter\\files\\data.txt");
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
}
