package by.epam.traning_center.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epam.traning_center.model.Element;
import by.epam.traning_center.model.Node;
import by.epam.traning_center.model.Text;

public class IndividualDOMParser {
	private Pattern findTag;
	private Pattern findText;
	private List<String> allElementsList;
	private Element rootElement;

	public IndividualDOMParser() {
		findTag = Pattern
				.compile("<[^>]+>[^<]+<[^>]+>|<[^>]+>|<[a-zA-Z]+:[a-zA-Z-]+\\s");
		findText = Pattern.compile(">[^<]+<");
	}

	public void readFile(String fileName) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		allElementsList = new ArrayList<String>();
		String readedLine = null;
		try {
			readedLine = reader.readLine();
			while ((readedLine = reader.readLine()) != null) {
				String result = takeContent(readedLine);
				if (result != null) {
					Matcher matcher = findText.matcher(result);
					if (matcher.find()) {
						allElementsList.add(result.substring(0,
								matcher.start() + 1));
						allElementsList.add(result.substring(
								matcher.start() + 1, matcher.end() - 1));
						allElementsList.add(result.substring(matcher.end() - 1,
								result.length()));
					} else {
						allElementsList
								.add(result.substring(0, result.length()));
					}

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		processingAllList();
	}

	private String takeContent(String inputString) {
		Matcher matcher = findTag.matcher(inputString);
		if (matcher.find())
			return matcher.group();
		return null;

	}

	private void processingAllList() {
		LinkedList<Node> nodeList = new LinkedList<Node>();
		for (String iterableString : allElementsList) {
			if (iterableString.substring(0, 2).equals("</")) {
				Element penultimateElement = (Element) nodeList.getLast();
				nodeList.remove(penultimateElement);
				if (nodeList.isEmpty()) {
					rootElement = penultimateElement;
				} else {
					Element lastElement = (Element) nodeList.getLast();
					nodeList.remove(lastElement);
					lastElement.addNode(penultimateElement);
					nodeList.addLast(lastElement);
				}
			} else if (iterableString.startsWith("<")) {
				Element iterableElement = new Element(iterableString.substring(
						1, iterableString.length() - 1));
				nodeList.add(iterableElement);
			} else {
				Element lastElement = (Element) nodeList.getLast();
				nodeList.remove(lastElement);
				Text text = new Text(iterableString);
				lastElement.addNode(text);
				nodeList.addLast(lastElement);
			}
			// System.out.println(iterableString);
		}
		// System.out.println(rootElement.getTagName());
	}

	public Element getRootElement() {
		return rootElement;
	}

}
