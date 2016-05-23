package by.epam.traning_center.test;

import by.epam.traning_center.entity.Document;
import by.epam.traning_center.service.IndividualDOMParser;
import by.epam.traning_center.view.PrintIndividualDOMParserMenu;


public class TestDOMParser {
	private static final String PATH="src/by/epam/traning_center/xml/menu.xml";

	public static void main(String[] args) {
		
		IndividualDOMParser personalParser = new IndividualDOMParser();
		personalParser.parseFile(PATH);
		Document document = personalParser.getDocument();
		PrintIndividualDOMParserMenu printParsedMenu = new PrintIndividualDOMParserMenu();
		printParsedMenu.printResult(document.getRootElement());
	}


}
