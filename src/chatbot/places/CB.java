package chatbot.places;

import chatbot.Conversation;
import chatbot.Location;

import java.awt.*;
import java.util.Dictionary;
import java.util.List;

public class CB {

    private static int cbXToClick = 1152;
    private static int cbYToClick = 845;
    private static int cbClickDelay = 500;

    private static int cbSlowMsgDelay = 5000;
    private static int cbFastMsgDelay = 500;

    public static int getCbXToClick() {
        return cbXToClick;
    }
    public static int getCbYToClick() {
        return cbYToClick;
    }
    public static int getCbClickDelay() {
        return cbClickDelay;
    }


    public static void newCBChatTopic(String topicName) throws AWTException {
        Dictionary<String, List<String>> tempDictionary = Conversation.getTopicDictionary();
        List<String> msgs = tempDictionary.get(topicName);

        for (String msg : msgs) {
            if (msgs.size() < 3) {
                Location.pasteToChat(msg, cbFastMsgDelay);

            } else if (msgs.size() >= 3) {
                Location.pasteToChat(msg, cbSlowMsgDelay);
            }
        }
    }
}