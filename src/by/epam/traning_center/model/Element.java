package by.epam.traning_center.model;

import java.util.ArrayList;
import java.util.List;

public class Element extends Node {
	private String tagName;
	private List<Node> nodeList;

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTagName() {
		return tagName;
	}

	public Element(String tagName) {
		this.tagName = tagName;
		nodeList = new ArrayList<Node>();
	}

	public void addNode(Node inputNode) {
		nodeList.add(inputNode);
	}

	public List<Node> getList() {
		return nodeList;
	}
}
