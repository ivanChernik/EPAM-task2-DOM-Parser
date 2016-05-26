package by.epam.traning_center.service;

import java.util.List;

import by.epam.traning_center.domain.Document;

public interface DOMParser {
	
	Document getDocument();
	void handleStringList(List<String> inputStringList);
}
