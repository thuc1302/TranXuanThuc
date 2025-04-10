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

public class XMLFileManager extends JFrame {
    private JTextArea textArea;
    private JTextField filePathField;

    public XMLFileManager() {
        setTitle("XML File Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        filePathField = new JTextField("example.xml");
        JButton readButton = new JButton("Read XML");
        JButton writeButton = new JButton("Write XML");
        JButton deleteButton = new JButton("Delete XML");

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 4));
        topPanel.add(filePathField);
        topPanel.add(readButton);
        topPanel.add(writeButton);
        topPanel.add(deleteButton);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);

        readButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                readXML(filePathField.getText());
            }
        });

        writeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                writeXML(filePathField.getText());
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteXML(filePathField.getText());
            }
        });
    }

    private void readXML(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                textArea.setText("File not found!");
                return;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            textArea.setText("Root Element: " + doc.getDocumentElement().getNodeName() + "\n");
            NodeList nodeList = doc.getDocumentElement().getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    textArea.append("Element: " + element.getNodeName() + "\n");
                }
            }
        } catch (Exception e) {
            textArea.setText("Error reading XML: " + e.getMessage());
        }
    }

    private void writeXML(String filePath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement("Company");
            doc.appendChild(root);

            Element employee = doc.createElement("Employee");
            employee.setAttribute("id", "101");

            Element name = doc.createElement("Name");
            name.appendChild(doc.createTextNode("John Doe"));
            employee.appendChild(name);

            Element age = doc.createElement("Age");
            age.appendChild(doc.createTextNode("30"));
            employee.appendChild(age);

            root.appendChild(employee);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);

            textArea.setText("XML File Created Successfully!");
        } catch (Exception e) {
            textArea.setText("Error writing XML: " + e.getMessage());
        }
    }

    private void deleteXML(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) {
                textArea.setText("XML File Deleted Successfully!");
            } else {
                textArea.setText("Failed to delete file.");
            }
        } else {
            textArea.setText("File not found!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new XMLFileManager().setVisible(true));
    }
}
