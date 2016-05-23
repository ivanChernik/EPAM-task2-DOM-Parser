package by.epam.traning_center.test;

import by.epam.traning_center.entity.Document;
import by.epam.traning_center.service.IndividualDOMParser;
import by.epam.traning_center.view.PrintIndividualDOMParserMenu;


public class TestDOMParser {

	public static void main(String[] args) {
		
		IndividualDOMParser personalParser = new IndividualDOMParser();
		personalParser.parseFile("src/by/epam/traning_center/xml/menu.xml");
		Document document = personalParser.getDocument();
		PrintIndividualDOMParserMenu printParsedMenu = new PrintIndividualDOMParserMenu(document.getRootElement());
		printParsedMenu.printResult();
	}


}
