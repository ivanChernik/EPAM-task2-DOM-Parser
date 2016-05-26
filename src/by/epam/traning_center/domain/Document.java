package by.epam.traning_center.domain;

public class Document {
	private Element rootElement;
	
	public Document(Element rootElement){
		this.rootElement = rootElement;
	}

	public Element getRootElement() {
		return rootElement;
	}

}
