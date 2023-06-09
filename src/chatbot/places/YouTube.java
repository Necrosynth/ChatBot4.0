package chatbot.places;

import chatbot.Conversation;
import chatbot.Location;
import math.Numbers;

import java.util.Dictionary;
import java.util.List;

public class YouTube {
    //Youtube Settings Values
    //
    //X value of Chat Box to click on
    private static final int ytXToClick = 1267;

    //Y value of Chat Box to click on
    private static final int ytYToClick = 596;

    //The Youtube fast delay (timeout) in between messages
    private static final int ytFastClickDelay = 1000;

    //The Youtube slow delay (timeout) in between messages
    private static final int ytSlowClickDelay = 10000;

    //Faster delay between messages to start chat with
    private static final int ytFastMsgDelay = 1000;

    //How many fast messages before YouTube Timeout
    private static final int ytChatTimeout = 5;

    //Slower delay between messages after Chat Timeout
    private static final int ytSlowMsgDelay = 5000;
    //

    public static int getYTXToClick() {
        return ytXToClick;
    }

    public static int getYTYToClick() {
        return ytYToClick;
    }

    public static int getYTFastMsgDelay() {
        return ytFastMsgDelay;
    }

    public static int getYTSlowMsgDelay() {
        return ytSlowMsgDelay;
    }

    public static int getYTChatTimeout() {
        return ytChatTimeout;
    }
    //

    public static int getYtSlowClickDelay(){
        return ytSlowClickDelay;
    }

    public static int getYtFastClickDelay(){
        return ytFastClickDelay;
    }

    //Click on the Youtube ChatBox
    public static void clickYTChatBox(){
        Location.clickTextBox(ytXToClick, ytYToClick, ytFastClickDelay, ytFastClickDelay);
    }

    //Sends a given msg to a Youtube Livestream
    public static void newYTBotTopic(String topicName) {
        List<String> msgs = Conversation.getTopicDictionary().get(topicName);

        if (msgs.size() < getYTChatTimeout()) {
            for (int i = 0; i < msgs.size(); i++) {
                Location.pasteToChat(msgs.get(i), YouTube.getYTFastMsgDelay());
            }

            //If the size of total messages is greater than or equal to the YouTube Timeout between messages
        } else if (msgs.size() >= getYTChatTimeout()) {
            //Say messages up to the Youtube Timeout
            for (int i = 0; i < getYTChatTimeout(); i++) {
                Location.pasteToChat(msgs.get(i), YouTube.getYTFastMsgDelay());
            }

            //Then slow down a bit for the Timeout between messages
            for (int i = getYTChatTimeout(); i < msgs.size(); i++) {
                Location.pasteToChat(msgs.get(i), YouTube.getYTSlowMsgDelay());
            }
        }
    }

    public static void newYTPrimeNumbers(int maxValue){
        //PASTE SOME RANGE OF PRIME NUMBERS...
        List<Integer> tempPrimesList = Numbers.generatePrimeNums(maxValue);

        if (tempPrimesList.size() < getYTChatTimeout()) {
            for (int i = 0; i < tempPrimesList.size(); i++) {
                Location.pasteToChat(tempPrimesList.get(i).toString(), YouTube.getYTFastMsgDelay());
            }

            //If the size of total messages is greater than or equal to the YouTube Timeout between messages
        } else if (tempPrimesList.size() >= getYTChatTimeout()) {
            //Say messages up to the Youtube Timeout
            for (int i = 0; i < getYTChatTimeout(); i++) {
                Location.pasteToChat(tempPrimesList.get(i).toString(), YouTube.getYTFastMsgDelay());
            }

            //Then slow down a bit for the Timeout between messages
            for (int i = getYTChatTimeout(); i < tempPrimesList.size(); i++) {
                Location.pasteToChat(tempPrimesList.get(i).toString(), YouTube.getYTSlowMsgDelay());
            }
        }
    }
}