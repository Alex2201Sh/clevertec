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

public class UserDOMBuilder extends AbstractUserBuilder{
//    private static final Logger LOGGER = LogManager.getLogger(TradesDOMBuilder.class);

    private DocumentBuilder docBuilder;

    public UserDOMBuilder() {
        this.users = new HashSet<>();
        // создание DOM-анализатора
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        Schema schema = ValidationService.loadSchema("data.xsd");
        factory.setNamespaceAware(true);
        factory.setValidating(false);
//        factory.setSchema(schema);
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
//            LOGGER.log(Level.TRACE, String.format("Ошибка конфигурации парсера: %s", e));
        }
    }

    @Override
    public void buildSetUsers(String myFilename) {
        Document doc;
        try {
            // parsing XML-документа и создание древовидной структуры
            doc = docBuilder.parse(myFilename);
            documentToUsers(doc);
        } catch (IOException e) {
//            LOGGER.log(Level.ERROR, String.format("File error or I/O error: %s", e));
        } catch (SAXException e) {
//            LOGGER.log(Level.ERROR, String.format("Parsing failure: %s", e));
        }
    }

    private void documentToUsers(Document doc) {
        Element root = doc.getDocumentElement();
        // получение списка дочерних элементов <user>
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

        user.setId(Integer.parseInt(getElementTextContent(userElement,"id")));
        user.setName(getElementTextContent(userElement,"name"));
        user.setPassword(getElementTextContent(userElement,"password"));
        user.setEmail(getElementTextContent(userElement,"email"));

        return user;
    }

    // получение текстового содержимого тега
    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}
