package by.epam.traning_center.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epam.traning_center.entity.Attribute;
import by.epam.traning_center.entity.Document;
import by.epam.traning_center.entity.Element;
import by.epam.traning_center.entity.Node;
import by.epam.traning_center.entity.Text;

public class IndividualDOMParser {
	private Pattern findTag;
	private Pattern findText;
	private Pattern findAttribute;
	private List<String> allElementsList;
	private Document document;

	public IndividualDOMParser() {
		findTag = Pattern.compile("<[^>]+>[^<]+<[^>]+>|<[^>]+>");
		findText = Pattern.compile(">[^<]+<");
		findAttribute = Pattern.compile("[a-zA-Z]+[:]*[a-zA-Z]+=\"[^\"]+\"");
	}

	public void parseFile(String fileName) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		allElementsList = new ArrayList<String>();
		String buffer = null;
		String readedLine = null;
		try {
			readedLine = reader.readLine();
			while ((readedLine = reader.readLine()) != null) {
				buffer += readedLine;
				String result = takeContent(buffer);
				if (result != null) {
					buffer = null;
					Matcher matcherText = findText.matcher(result);
					// System.out.println(result);
					if (matcherText.find()) {
						allElementsList.add(result.substring(0,
								matcherText.start() + 1));
						allElementsList
								.add(result.substring(matcherText.start() + 1,
										matcherText.end() - 1));
						allElementsList.add(result.substring(
								matcherText.end() - 1, result.length()));
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
		LinkedList<Node> nodeStack = new LinkedList<Node>();
		for (String iterableString : allElementsList) {
			if (iterableString.substring(0, 2).equals("</")) {
				Element penultimateElement = (Element) nodeStack.getLast();
				nodeStack.remove(penultimateElement);
				if (nodeStack.isEmpty()) {
					document = new Document(penultimateElement);
				} else {
					Element lastElement = (Element) nodeStack.getLast();
					nodeStack.remove(lastElement);
					lastElement.addNode(penultimateElement);
					nodeStack.addLast(lastElement);
				}
			} else if (iterableString.startsWith("<")) {
				Element iterableElement = checkElementName(iterableString);
				findAttribute(iterableString, iterableElement);
				nodeStack.add(iterableElement);
			} else {
				Element lastElement = (Element) nodeStack.getLast();
				nodeStack.remove(lastElement);
				Text text = new Text(iterableString);
				lastElement.addNode(text);
				nodeStack.addLast(lastElement);
			}
		}
	}

	private Element checkElementName(String name) {
		Element iterableElement = null;
		if (name.indexOf(" ") > 1) {
			iterableElement = new Element(name.substring(1, name.indexOf(" ")));
		} else {
			iterableElement = new Element(name.substring(1, name.length() - 1));
		}
		return iterableElement;
	}

	private void findAttribute(String inputString, Element element) {
		Matcher matcher = findAttribute.matcher(inputString);
		List<Attribute> attributeList = new ArrayList<Attribute>();
		while (matcher.find()) {
			attributeList.add(createAttribute(matcher.group()));
		}
		element.setAttributeList(attributeList);
	}

	private Attribute createAttribute(String attribute) {
		String nameAttribute = attribute.substring(0, attribute.indexOf("="));
		String valueAttribute = attribute.substring(
				attribute.indexOf("\"") + 1, attribute.length() - 1);
		return new Attribute(nameAttribute, valueAttribute);
	}

	public Document getDocument() {
		return document;
	}
}
