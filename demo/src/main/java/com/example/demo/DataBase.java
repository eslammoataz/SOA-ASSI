package com.example.demo;

import org.springframework.stereotype.Component;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class DataBase {
    private String filePath = "E:\\Projects\\demo\\demo\\src\\main\\java\\com\\example\\demo\\University.xml";

    public List<StudentModel> loadData() {
        List<StudentModel> studentsList = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            File file = new File(filePath);
            if (file.exists()) {
                // If the file exists, proceed with reading
                Document doc = builder.parse(file);

                //normalize xml structure
                doc.getDocumentElement().normalize();

                //get all elements by tagName
                NodeList students = doc.getElementsByTagName("Student");

                for (int i = 0; i < students.getLength(); i++) {
                    Node node = students.item(i);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element elem = (Element) node;

                        // Get the value of the ID attribute.
                        String ID = node.getAttributes().getNamedItem("ID").getNodeValue();

                        // Get the value of all sub-elements.
                        String firstName = elem.getElementsByTagName("FirstName")
                                .item(0).getChildNodes().item(0).getNodeValue();

                        String lastName = elem.getElementsByTagName("LastName").item(0)
                                .getChildNodes().item(0).getNodeValue();

                        String gender = elem.getElementsByTagName("Gender").item(0)
                                .getChildNodes().item(0).getNodeValue();

                        double gpa = Double.parseDouble(elem.getElementsByTagName("GPA")
                                .item(0).getChildNodes().item(0).getNodeValue());

                        int level = Integer.parseInt(elem.getElementsByTagName("Level")
                                .item(0).getChildNodes().item(0).getNodeValue());

                        String address = elem.getElementsByTagName("Address").item(0)
                                .getChildNodes().item(0).getNodeValue();

                        // Create a new StudentModel and add it to the list
                        StudentModel student = new StudentModel(ID, firstName, lastName, gender, gpa, level, address);
                        studentsList.add(student);

//                        System.out.println("ID: " + ID);
//                        System.out.println("FirstName: " + firstName);
//                        System.out.println("LastName: " + lastName);
//                        System.out.println("Gender: " + gender);
//                        System.out.println("GPA: " + gpa);
//                        System.out.println("Level: " + level);
//                        System.out.println("Address: " + address);
//                        System.out.println("--------------------");
                    }
                }
            }

        } catch (ParserConfigurationException | IOException | org.xml.sax.SAXException e) {
            throw new RuntimeException(e);
        }
        return studentsList;
    }

    public List<StudentModel> loadAllStudents(){
        return loadData();
    }

    public void appendStudentsToXml(List<StudentModel> students) {
        try {
            List<StudentModel> studentsInput = new ArrayList<>();
            studentsInput.addAll(students);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.newDocument();
            Element universityElement = doc.createElement("University");
            doc.appendChild(universityElement);

            // Create student elements for each StudentModel instance and append them to the "University" element
            for (StudentModel student : studentsInput) {
                Element studentElement = createStudentElement(doc, student);
                universityElement.appendChild(studentElement);
            }

            // Write the updated content back to the XML file with indentation
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            // Add indentation properties
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2"); // You can adjust the indentation level
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");

            DOMSource source = new DOMSource(doc);


            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                StreamResult result = new StreamResult(outputStream);
                transformer.transform(source, result);
            }

            System.out.println("XML data written successfully to " + filePath);
        } catch (ParserConfigurationException | IOException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private Element createStudentElement(Document doc, StudentModel student) {
        Element studentElement = doc.createElement("Student");
        studentElement.setAttribute("ID", student.getID());

        Element firstNameElement = doc.createElement("FirstName");
        Text firstNameText = doc.createTextNode(student.getFirstname());
        firstNameElement.appendChild(firstNameText);

        Element lastNameElement = doc.createElement("LastName");
        Text lastNameText = doc.createTextNode(student.getLastname());
        lastNameElement.appendChild(lastNameText);

        Element genderElement = doc.createElement("Gender");
        Text genderText = doc.createTextNode(student.getGender());
        genderElement.appendChild(genderText);

        Element gpaElement = doc.createElement("GPA");
        Text gpaText = doc.createTextNode(String.valueOf(student.getGPA()));
        gpaElement.appendChild(gpaText);

        Element levelElement = doc.createElement("Level");
        Text levelText = doc.createTextNode(String.valueOf(student.getLevel()));
        levelElement.appendChild(levelText);

        Element addressElement = doc.createElement("Address");
        Text addressText = doc.createTextNode(student.getAddress());
        addressElement.appendChild(addressText);

        studentElement.appendChild(firstNameElement);
        studentElement.appendChild(lastNameElement);
        studentElement.appendChild(genderElement);
        studentElement.appendChild(gpaElement);
        studentElement.appendChild(levelElement);
        studentElement.appendChild(addressElement);

        return studentElement;
    }

    public List<StudentModel> searchByFirstName(String name) {
        List<StudentModel> students = loadData();
        List<StudentModel> response = new ArrayList<>();
        for (var x : students) {
            if (x.getFirstname().equals(name)) {
                response.add(x);
            }
        }
        return response;
    }

    public List<StudentModel> searchByGpa(double gpa) {
        List<StudentModel> students = loadData();
        List<StudentModel> response = new ArrayList<>();
        for (var x : students) {
            if (x.getGPA() == gpa) {
                response.add(x);
            }
        }
        return response;
    }

    public boolean deleteById(String id) {
        List<StudentModel> students = loadData();
        for (var x : students) {
            Iterator<StudentModel> iterator = students.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().getID().equals(id)) {
                    iterator.remove();
                    appendStudentsToXml(students);
                    return true;

                }
            }
        }
        return false;

    }

}
