package by.epam.traning_center.xerce;

import java.io.IOException;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XerceParserMenu {
	public void parseFile(String filename) {
		DOMParser parser = new DOMParser();
		try {
			parser.parse(filename);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document document = parser.getDocument();
		Element root = document.getDocumentElement();
		System.out.println("-----cold-appetizers-------");
		parseParticularDish(parseRootKindDish(root, "cold-appetizers",
				"cold-appetizer"));
		System.out.println("-----hot-appetizers-------");
		parseParticularDish(parseRootKindDish(root, "hot-appetizers",
				"hot-appetizer"));
		System.out.println("-----breakfasts-------");
		parseParticularDish(parseRootKindDish(root, "breakfasts", "breakfast"));
	}

	private NodeList parseRootKindDish(Element root, String nameKindDish,
			String nameParticularDish) {
		NodeList kindDishList = root.getElementsByTagName(nameKindDish);
		Element kindDish = (Element) kindDishList.item(0);
		NodeList particularDishesList = kindDish
				.getElementsByTagName(nameParticularDish);
		return particularDishesList;
	}

	private void parseParticularDish(NodeList inputDishesList) {
		for (int indexDish = 0; indexDish < inputDishesList.getLength(); indexDish++) {
			Element dish = (Element) inputDishesList.item(indexDish);
			printElemet(getSingleChild(dish, "name"));
			printElemet(getSingleChild(dish, "description"));
			printElemet(getSingleChild(dish, "portion"));
			printElemet(getSingleChild(dish, "price"));
			System.out.println("------------");
		}

	}

	private Element getSingleChild(Element element, String childName) {
		NodeList nodesList = element.getElementsByTagName(childName);
		Element child = (Element) nodesList.item(0);
		return child;
	}

	private void printElemet(Element inputElement) {
		if (inputElement != null) {
			System.out.println(inputElement.getTextContent());
		}
	}

}
