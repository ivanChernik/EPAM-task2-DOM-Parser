package by.epam.traning_center.parser;

import java.util.List;

import by.epam.traning_center.model.Element;
import by.epam.traning_center.model.Node;
import by.epam.traning_center.model.Text;

public class PrintIndividualDOMParserMenu {
	private Element rootElement;

	public PrintIndividualDOMParserMenu(Element rootElement) {
		this.rootElement = rootElement;
	}

	public void printResult() {
		System.out.println(rootElement.getTagName());
		List<Node> nodeList = rootElement.getList();
		parseRootKindDish(rootElement);
	}

	private void parseRootKindDish(Element root) {
		List<Node> kindDishList = root.getList();
		for (Node iterableKindNode : kindDishList) {
			Element iterableKindDish = (Element) iterableKindNode;
			List<Node> disheshList = iterableKindDish.getList();
			for (Node iterableDishNode : disheshList) {
				Element iterableDish = (Element) iterableDishNode;
				System.out.println(iterableDish.getTagName());
				parseParticularDish(iterableDish);
			}

		}
	}

	private void parseParticularDish(Element iterableDish) {
		List<Node> listPartDescriptionDish = iterableDish.getList();
		for (int indexPartDish = 0; indexPartDish < listPartDescriptionDish
				.size(); indexPartDish++) {
			Element partDescriptonDish = (Element) listPartDescriptionDish
					.get(indexPartDish);
			System.out.println(partDescriptonDish.getTagName());
			printSingleChild(partDescriptonDish.getList());
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

}
