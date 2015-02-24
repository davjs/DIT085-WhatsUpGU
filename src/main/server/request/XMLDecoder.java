package main.server.request;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.mockito.internal.matchers.Null;

import java.io.IOException;
import java.io.StringReader;

public class XMLDecoder {



    public static int getMessageId(Element action){
        return Integer.parseInt(action.getChildren("messageID").get(0).getText());
    }
    public static String getreceiverID(Element action){
        return (action.getChildren("receiverID").get(0).getText());
    }

    public static String getContent(Element action){

        return action.getChildren("content").get(0).getText();
    }


    public static RequestObject decode(String xml, String ID) throws JDOMException, IOException {
        SAXBuilder sb = new SAXBuilder();
        Document doc = sb.build(new StringReader(xml));

        Element action = doc.getRootElement().getChildren().get(0);
        String actionName = action.getName();

       // String content, String senderID, String receiverID
        switch (actionName) {
            case "request":
                return RequestObject.ConnectRequest(action.getText());

            case "replace":
                return RequestObject.ReplaceRequest(
                        getMessageId(action),
                        getContent(action)
                );
            case "delete":
                return RequestObject.DeleteRequest(getMessageId(action));
            case "fetch":
                return RequestObject.FetchRequest(ID);
            case "fetchComplete":
                return RequestObject.FetchComplete(ID);
            case "add":
                return  RequestObject.AddRequest(

                        getContent(action),
                        ID,
                        getreceiverID(action));
            case "connection":
                return RequestObject.ConnectRequest(ID);



             default:
                return null;
        }


        //return new RequestObject(ActionKind.CONNECT);
    }
}
