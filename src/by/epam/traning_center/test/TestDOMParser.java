package by.epam.traning_center.test;

import by.epam.traning_center.parser.IndividualDOMParser;
import by.epam.traning_center.parser.PrintIndividualDOMParserMenu;
import by.epam.traning_center.xerce.XerceParserMenu;


public class TestDOMParser {

	public static void main(String[] args) {
		XerceParserMenu parser = new XerceParserMenu();
		parser.parseFile("src/by/epam/traning_center/xml/menu.xml");
		
		System.out.println("--------PERSONAL PARSER-------------");
		
		IndividualDOMParser personalParser = new IndividualDOMParser();
		personalParser.readFile("src/by/epam/traning_center/xml/menu.xml");
		PrintIndividualDOMParserMenu printParsedMenu = new PrintIndividualDOMParserMenu(personalParser.getRootElement());
		printParsedMenu.printResult();
	}


}
