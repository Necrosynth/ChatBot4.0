package chatbot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Conversation {
    //File location for custom Topics
    private static String topicFileName = "config\\Topics.txt";

    //Names of topics and messages
    private static Dictionary<String, List<String>> topicDictionary = new Hashtable<>();
    public static Dictionary<String, List<String>> getTopicDictionary() { return topicDictionary; }
    public static List<String> getTopicNameList(){
        Enumeration<String> tempTopicNamesEnumeration = topicDictionary.keys();
        return Collections.list(tempTopicNamesEnumeration);
    }

    //Build Topic dictionary from Topics file
    public static void buildTopicDictionary() throws FileNotFoundException {
        Scanner inputData = new Scanner(new File(topicFileName));
        try {
            while (inputData.hasNextLine()) { //Runs loop while there's more data to process
                //Temporary variables to contain data that will be used to create a Topic object
                String topicName = "";

                //Temporary variable to contain the first line of data
                topicName = inputData.nextLine();
                List<String> topicMsgs = buildListOfMsgs(inputData);

                topicDictionary.put(topicName, topicMsgs);
            }
        } finally {
            //Close whether there's File Error
            inputData.close();
        }
    }

    //Handles assembling the messages for a given Topic
    private static List<String> buildListOfMsgs(Scanner inputData) {
        List<String> tempTopicMsgList = new ArrayList<>();
        String nextString = "Waiting";
        while (inputData.hasNext()) { //Runs loop until data is an empty line
            nextString = inputData.nextLine();

            //If the next piece of data is empty, it's the end of this Topic (or end of file)
            if (nextString.equals("")) {
                break; //Move on to next Topic
            } else {
                tempTopicMsgList.add(nextString); //Data must be a Topic Message, add to Topic Message List
            }
        }
        return tempTopicMsgList;
    }

    public static void printOutTopicNames(){
        Enumeration<String> tempNamesEnumeration = topicDictionary.keys();
        List<String> tempTopicNamesList = Collections.list(tempNamesEnumeration);
        //tempTopicNamesList.sort(new myCompare());
        System.out.print("Names of Topics: [" + tempTopicNamesList.get(0) + ", ");
        for (int i = 1; i < tempTopicNamesList.size(); i++) {
            if (i < (tempTopicNamesList.size() - 1))
                System.out.print(tempTopicNamesList.get(i) + ", ");
            else
                System.out.print(tempTopicNamesList.get(i));
        }
        System.out.println("]");
    }
}
