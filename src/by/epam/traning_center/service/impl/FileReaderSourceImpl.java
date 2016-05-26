package by.epam.traning_center.service.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epam.traning_center.service.FileReaderSource;

public class FileReaderSourceImpl implements FileReaderSource {
	private static final Pattern findTagConstruction = Pattern
			.compile("<[^>]+>[^<]+</[^>]+>|<[^>]+>[\\s]*<");
	private static final Pattern findText = Pattern.compile(">[^<]+</");
	private static final Pattern findOneTag = Pattern.compile("<[^>]+>");
	private List<String> allStringList;

	public FileReaderSourceImpl() {
		allStringList = new ArrayList<String>();
	}

	public List<String> readFile(String fileName) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String readedLine = null;
		String buffer = null;
		try {
			readedLine = reader.readLine();
			while ((readedLine = reader.readLine()) != null) {
				buffer += readedLine;
				buffer = takeContent(buffer);
			}
			allStringList.add(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return allStringList;
	}

	private String takeContent(String parsingString) {
		Matcher matcher = findTagConstruction.matcher(parsingString);
		while (matcher.find()) {
			String resultTag = matcher.group();
			Matcher matcherText = findText.matcher(resultTag);
			if (matcherText.find()) {
				parseThreeElements(resultTag, matcherText);
				parsingString = "";
			} else {

				Matcher matcherOneTag = findOneTag.matcher(resultTag.trim());
				if (matcherOneTag.find()) {
					allStringList.add(matcherOneTag.group().trim());
					return parsingString.substring(matcherOneTag.end(),
							parsingString.length());

				} else {
					return parsingString;
				}
			}
		}
		return parsingString;
	}

	private void parseThreeElements(String resultTag, Matcher matcherText) {
		allStringList.add(resultTag.substring(0, matcherText.start() + 1));
		allStringList.add(resultTag.substring(matcherText.start() + 1,
				matcherText.end() - 2).trim());
		allStringList.add(resultTag.substring(matcherText.end() - 2,
				resultTag.length()));
	}

}
