package by.epam.traning_center.view;

import java.util.List;

import by.epam.traning_center.domain.Attribute;
import by.epam.traning_center.domain.Element;
import by.epam.traning_center.domain.Node;
import by.epam.traning_center.domain.Text;

public class PrintIndividualDOMParserMenu {
	private Element rootElement;

	public PrintIndividualDOMParserMenu() {
		
	}

	public void printResult(Element rootElement) {
		this.rootElement = rootElement;
		System.out.println(rootElement.getTagName());
		printAttributes(rootElement);
		List<Node> nodeList = rootElement.getNodeList();
		parseRootKindDish(rootElement);
	}

	private void parseRootKindDish(Element root) {
		List<Node> kindDishList = root.getNodeList();
		for (Node iterableKindNode : kindDishList) {
			Element iterableKindDish = (Element) iterableKindNode;
			List<Node> disheshList = iterableKindDish.getNodeList();
			for (Node iterableDishNode : disheshList) {
				Element iterableDish = (Element) iterableDishNode;
				printAttributes(iterableDish);
				System.out.println(iterableDish.getTagName());
				parseParticularDish(iterableDish);
			}

		}
	}

	private void parseParticularDish(Element iterableDish) {
		List<Node> listPartDescriptionDish = iterableDish.getNodeList();
		for (int indexPartDish = 0; indexPartDish < listPartDescriptionDish
				.size(); indexPartDish++) {
			Element partDescriptonDish = (Element) listPartDescriptionDish
					.get(indexPartDish);
			System.out.println(partDescriptonDish.getTagName());
			printSingleChild(partDescriptonDish.getNodeList());
		}

	}

	private void printSingleChild(List<Node> list) {
		for (Node nodePartDish : list) {
			Text textPartDish = (Text) nodePartDish;
			if (textPartDish != null) {
				System.out.println(textPartDish.getContent());
			}
		}
	}

	private void printAttributes(Element element) {
		List<Attribute> attributeList = element.getAttributeList();
		for (Attribute iterable : attributeList) {
			System.out.println(iterable.getName() + " = \""
					+ iterable.getValue() + "\"");
		}
	}

}
