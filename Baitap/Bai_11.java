package org.example;

import org.w3c.dom.*;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

class XMLCrudGUI {
    private JFrame frame;
    private JTextField tagNameField, tagValueField;
    private JTextArea xmlDisplay;
    private File xmlFile = new File("data.xml");

    public XMLCrudGUI() {
        frame = new JFrame("XML CRUD Application");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        tagNameField = new JTextField(10);
        tagValueField = new JTextField(10);
        JButton addButton = new JButton("Add Tag");
        JButton deleteButton = new JButton("Delete Tag");
        JButton displayButton = new JButton("Display XML");
        xmlDisplay = new JTextArea(15, 40);

        frame.add(new JLabel("Tag Name:"));
        frame.add(tagNameField);
        frame.add(new JLabel("Tag Value:"));
        frame.add(tagValueField);
        frame.add(addButton);
        frame.add(deleteButton);
        frame.add(displayButton);
        frame.add(new JScrollPane(xmlDisplay));

        addButton.addActionListener(e -> addTag(tagNameField.getText(), tagValueField.getText()));
        deleteButton.addActionListener(e -> deleteTag(tagNameField.getText()));
        displayButton.addActionListener(e -> displayXML());

        frame.setVisible(true);
    }

    private void addTag(String tagName, String tagValue) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc;
            Element root;

            if (xmlFile.exists()) {
                doc = builder.parse(xmlFile);
                root = doc.getDocumentElement();
            } else {
                doc = builder.newDocument();
                root = doc.createElement("root");
                doc.appendChild(root);
            }

            Element newElement = doc.createElement(tagName);
            newElement.appendChild(doc.createTextNode(tagValue));
            root.appendChild(newElement);

            saveXML(doc);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void deleteTag(String tagName) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            NodeList nodes = doc.getElementsByTagName(tagName);
            if (nodes.getLength() > 0) {
                Node node = nodes.item(0);
                node.getParentNode().removeChild(node);
                saveXML(doc);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void displayXML() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("temp.xml"));
            transformer.transform(source, result);

            xmlDisplay.setText(new String(java.nio.file.Files.readAllBytes(new File("temp.xml").toPath())));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void saveXML(Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source, result);
    }

    public static void main(String[] args) {
        new XMLCrudGUI();
    }
}
