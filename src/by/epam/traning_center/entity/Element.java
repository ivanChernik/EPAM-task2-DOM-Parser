package by.epam.traning_center.entity;

import java.util.ArrayList;
import java.util.List;

public class Element extends Node {
	private String tagName;
	private List<Node> nodeList;
	private List<Attribute> attributeList;


	public Element(String tagName) {
		this.tagName = tagName;
		nodeList = new ArrayList<Node>();
		attributeList = new ArrayList<Attribute>();
	}
	
	public List<Attribute> getAttributeList() {
		return attributeList;
	}


	public void setAttributeList(List<Attribute> attributeList) {
		this.attributeList = attributeList;
	}


	public void addNode(Node inputNode) {
		nodeList.add(inputNode);
	}

	public List<Node> getNodeList() {
		return nodeList;
	}
	
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTagName() {
		return tagName;
	}
}
