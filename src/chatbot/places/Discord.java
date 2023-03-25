package chatbot.places;

import chatbot.Conversation;
import chatbot.Location;

import java.util.List;

public class Discord {
    //Discord values
    //Delay after first opening the program
    private final static int openDiscordDelay = 5000;

    //X/Y value of Direct Msgs Button
    private final static int directMsgsX = 35;
    private final static int  directMsgsY = 45;

    //X/Y value of Top Direct Msg
    private static final int topDirectMsgX = 200;
    private static final int topDirectMsgY = 270;

    //X/Y value of Direct Msg Chat Box
    private static final int directMsgChatX = 500;
    private static final int directMsgChatY = 865;

    //Chat delay (timeout) for between messages
    private static final int fastChatDelay = 1000;
    private static final int slowChatDelay = 1500;

    //Click delays
    private static final int fastClickDelay = 500;
    private static final int slowClickDelay = 1500;

    public static int getOpenDiscordDelay() { return openDiscordDelay; }

    public static int getDirectMsgsX() {
        return directMsgsX;
    }
    public static int getDirectMsgsY() {
        return directMsgsY;
    }

    public static int getTopDirectMsgX() {
        return topDirectMsgX;
    }
    public static int getTopDirectMsgY() {
        return topDirectMsgY;
    }

    public static int getDirectMsgChatX() {
        return directMsgChatX;
    }
    public static int getDirectMsgChatY() {
        return directMsgChatY;
    }

    public static int getFastClickDelay() {
        return fastClickDelay;
    }

    public static void newDiscordBotTopic(String topicName){
        List<String> msgs = Conversation.getTopicDictionary().get(topicName);

        for(String msg : msgs){
            Location.pasteToChat(msg, fastChatDelay);
        }
    }
}

