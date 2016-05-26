package by.epam.traning_center.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epam.traning_center.domain.Attribute;
import by.epam.traning_center.domain.Document;
import by.epam.traning_center.domain.Element;
import by.epam.traning_center.domain.Node;
import by.epam.traning_center.domain.Text;
import by.epam.traning_center.service.DOMParser;

public class DOMParserImpl implements DOMParser {
	private static final String START_BRACKET = "<";
	private static final String END_BRACKET = "</";
	private static final Pattern findAttribute = Pattern
			.compile("[a-zA-Z]+[:]*[a-zA-Z]+=\"[^\"]+\"");
	private List<String> allElementsList;
	private Document document;

	public DOMParserImpl() {
		
	}


	public  void handleStringList(List<String> inputStringList) {
		allElementsList = inputStringList;
		LinkedList<Node> nodeStack = new LinkedList<Node>();
		deleteSpaces();
		for (String iterableString : allElementsList) {
			if (iterableString.substring(0, 2).equals(END_BRACKET)) {
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
			} else if (iterableString.startsWith(START_BRACKET)) {
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

	private void deleteSpaces() {
		for (int index = 0; index < allElementsList.size(); index++) {
			if (allElementsList.get(index).equals(""))
				allElementsList.remove(index);

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
