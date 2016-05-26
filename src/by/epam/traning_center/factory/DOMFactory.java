package by.epam.traning_center.factory;

import by.epam.traning_center.service.DOMParser;
import by.epam.traning_center.service.FileReaderSource;
import by.epam.traning_center.service.impl.DOMParserImpl;
import by.epam.traning_center.service.impl.FileReaderSourceImpl;

public class DOMFactory {
	private static final DOMFactory factory = new DOMFactory();
	private final DOMParser parser = new DOMParserImpl();
	private final FileReaderSource fileReader = new FileReaderSourceImpl();
	
	public static DOMFactory getInstanse(){
		return factory;
	}
	
	public DOMParser getParser(){
		return parser;
	}
	
	public FileReaderSource getReader(){
		return fileReader;
	}

}
