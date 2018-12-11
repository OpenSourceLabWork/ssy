package org.ssy;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class DataLoader {

	public void getDataX() {

		// File xmlFile = new File("students.xml");
		// DocumentBuilderFactory factory =
		// DocumentBuilderFactory.newInstance();
		// DocumentBuilder builder = factory.newDocumentBuilder();
		// Document doc = builder.parse(xmlFile);
		//
		// // Build the xpath expression
		// XPath xpathExpression = XPath.newInstance("//*[@id]");
		//
		// // Apply xpath and fetch all matching nodes
		// ArrayList<Element> userIds = (ArrayList<Element>)
		// xpathExpression.selectNodes(doc);
		//
		// // Iterate over naodes and print the value
		// for (int i = 0; i < userIds.size(); i++) {
		// System.out.println((userIds.get(i)).getAttributeValue("id").trim());
		// }
	}

	public static List<String> loadXmlData(String filename) {

		List<String> nodes = null;
		try {
			Document document = getDocument(filename);
			String xpathExpression = "//Event//@resource";
		// System.out.println( evaluateXPath(document, xpathExpression) );
			nodes = evaluateXPath(document, xpathExpression);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return nodes;
	}

	private static List<String> evaluateXPath(Document document, String xpathExpression) throws Exception {
		// Create XPathFactory object
		XPathFactory xpathFactory = XPathFactory.newInstance();

		// Create XPath object
		XPath xpath = xpathFactory.newXPath();

		List<String> values = new ArrayList<>();
		try {
			// Create XPathExpression object
			XPathExpression expr = xpath.compile(xpathExpression);

			// Evaluate expression result on XML document
			NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);

			for (int i = 0; i < nodes.getLength(); i++) {
				values.add(nodes.item(i).getNodeValue());
			}

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return values;
	}

	private static Document getDocument(String fileName) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(fileName);
		return doc;
	}

}