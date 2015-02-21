package main.server.request;

import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

public class XMLEncoder {

    public static String encode(RequestObject message) {
        switch (message.kind) {
            case CONNECT:
                if(message.Error != null){
                    return RefusedConnection(message);
                }
                return AcceptedConnection(message);
            case REPLACE:
                if(message.Error != null){
                    return FailedReplacing(message);
                }
                return Replaced(message);
            case REMOVE:
                if(message.Error != null){
                    return FailedDeleting(message);
                }
                return Deleted(message);
            case FETCH:
                if(message.Error != null){
                    return FailedFetching(message);
                }
                return Fetched(message);
            case FETCHCOMPLETE:
                if(message.Error != null){
                    return FailedFetchCompleting(message);
                }
                return FetchCompleted(message);
        }

        return null;
    }

    private static String FailedReplacing(RequestObject message) {
        return null;
    }

    public static String RefusedConnection(RequestObject retmsg) {
        Element root = new Element("connection");
        Element refused = new Element("refused");
        refused .addContent(retmsg.ID);
        root.addContent(refused);

        return new XMLOutputter().outputString(root);
    }

    public static String AcceptedConnection(RequestObject retmsg) {
        Element root = new Element("connection");
        Element accepted = new Element("accepted");
        accepted.addContent(retmsg.ID);
        root.addContent(accepted);

        return new XMLOutputter().outputString(root);
    }

    public static String Added(RequestObject retmsg){
        Element root = new Element("messageActionResponse");
        Element added = new Element("added");
        added.addContent(String.valueOf(retmsg.messageID));
        root.addContent(added);

        return new XMLOutputter().outputString(root);
    }
    public static String Deleted(RequestObject retmsg){
        return "</>";
    }
    public static String Replaced(RequestObject retmsg){
        return "</>";
    }

    public static String Fetched(RequestObject retmsg){
        return "</>";
    }

    public static String FetchCompleted(RequestObject retmsg){
        return "</>";
    }


    /*                  FAILS               */

    public static String FailedFetching(RequestObject retmsg){
        return "</>";
    }
    public static String FailedDeleting(RequestObject retmsg){
        return "</>";
    }
    public static String FailedFetchCompleting(RequestObject retmsg){
        return "</>";
    }
    public static String FailedAdding(RequestObject retmsg){
        return "</>";
    }
}