package by.shumilov.clevertec.builder;

import by.shumilov.clevertec.bean.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;

/**
 * Class UserDOMBuilder creates DOM model from xml file
 * and transforms it into list.
 */
public class UserDOMBuilder extends AbstractUserBuilder {
    private final DocumentBuilder docBuilder;

    public UserDOMBuilder() {
        this.users = new HashSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setValidating(false);
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method creates List of User objects
     * and writes it into
     *
     * @param myFilename - path to xml file with represents of User objects.
     */
    @Override
    public void buildSetUsers(String myFilename) {
        Document doc;
        try {
            doc = docBuilder.parse(myFilename);
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }
        documentToUsers(doc);
    }

    private void documentToUsers(Document doc) {
        Element root = doc.getDocumentElement();
        NodeList userList = root.getElementsByTagName("user");
        nodeListToUsers(userList);
    }

    private void nodeListToUsers(NodeList usersPartsList) {
        for (int i = 0; i < usersPartsList.getLength(); i++) {
            Element userElement = (Element) usersPartsList.item(i);
            User user = buildUser(userElement);
            users.add(user);
        }
    }

    private User buildUser(Element userElement) {
        User user = new User();
        user.setId(Integer.parseInt(getElementTextContent(userElement, "id")));
        user.setName(getElementTextContent(userElement, "name"));
        user.setPassword(getElementTextContent(userElement, "password"));
        user.setEmail(getElementTextContent(userElement, "email"));
        return user;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}
