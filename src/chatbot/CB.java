package chatbot;

import java.awt.*;
import java.util.ArrayList;

public class CB {

    private static int cbXToClick = 1200;

    private static int cbYToClick = 845;

    private static int cbClickDelay = 450;

    private static int cbSlowMsgDelay = 3500;

    private static int cbFastMsgDelay = 550;
    public static void clickChatBox(){
        Location.clickTextBox(cbXToClick, cbYToClick, cbClickDelay, cbClickDelay);
    }

    public static int getCbXToClick() {
        return cbXToClick;
    }

    public static int getCbYToClick() {
        return cbYToClick;
    }

    public static int getCbClickDelay() {
        return cbClickDelay;
    }

    public static int getCbSlowMsgDelay() {
        return cbSlowMsgDelay;
    }

    public static int getCbFastMsgDelay(){
        return cbFastMsgDelay;
    }

    public static void newCBChatTopic(String topicName) throws AWTException {
        //Robot robot = new Robot();
        //robot.delay(5000);
        ArrayList<String> msgs = Conversation.getTopicDictionary().get(topicName);

        if (msgs.size() < 3){
            for (String msg : msgs) {
                Location.pasteToChat(msg, getCbFastMsgDelay());
            }
        }
        else if (msgs.size() >= 3) {
            for (String msg : msgs) {
                //Location.pasteToChat(msgs.get(i), CB.getCbSlowMsgDelay());
                Location.pasteToChat(msg, getCbSlowMsgDelay());
            }
        }
    }
}