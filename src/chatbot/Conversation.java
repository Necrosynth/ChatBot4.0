package chatbot;

import data.TopicData;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Array;
import java.util.*;
import java.util.List;

public class Conversation {
    //Location on computer of the custom Topics data text file
    private static String topicsFileName = "config\\Topics.txt";
    //List of Topic objects
    private static ArrayList<TopicData> topicsList = new ArrayList<>();

    //List of Topic Names
    private static List<String> topicNamesList = new ArrayList<>();

    //Dictionary for Topic Names (key), and Topic
    private static Dictionary<String, ArrayList<String>> topicDictionary = new Hashtable<>();

    //Creates a list of Topic objects from a text file that is contained in this class
    public static void buildListOfTopicsAndMsgs() throws FileNotFoundException {
        //Get all data from the Topics text file
        Scanner inputData = new Scanner(new File(topicsFileName));
        try {
            while (inputData.hasNextLine()) { //Runs loop while there's more data to process
                //Temporary variables to contain data that will be used to create a Topic object
                String topicName = "";
                ArrayList<String> topicMessageList = new ArrayList<>();
                TopicData tempTopic = null;

                //Temporary variable to contain the next piece of data from all data
                topicName = inputData.nextLine();

                //Build the List of Messages for current Topic
                buildListOfMessages(inputData, topicMessageList);

                //Create new Topic object then add it to the Topics List
                tempTopic = new TopicData(topicName, topicMessageList);
                topicsList.add(tempTopic);
            }
        } finally {
            //Close whether there's File Error
            inputData.close();
        }
    }

    //Builds a list of Msgs
    ////////////////////DOES IT MAKE SENSE TO HAVE THIS HERE???
    private static void buildListOfMessages(Scanner inputData, List<String> topicMessageList) {
        String nextString = "Waiting";
        while (inputData.hasNext()) { //Runs loop until data is an empty line
            nextString = inputData.nextLine();

            //If the next piece of data is empty, it's the end of this Topic (or end of file)
            if (nextString.equals("")) {
                break; //Move on to next Topic
            } else {
                topicMessageList.add(nextString); //Data must be a Topic Message, add to Topic Message List
            }
        }
    }

    //Builds the list of Topic Names within Conversation
    public static void buildListOfTopicNames(){
        List<TopicData> tempTopics = topicsList;
        for (TopicData tempTopic : tempTopics){
            String topicName = tempTopic.getTopicName();
            topicNamesList.add(topicName);
        }
    }

    //Builds our the Topic Dictionary in Conversation
    public static void buildTopicDictionary(){
        for(TopicData topic : topicsList){
            String name = topic.getTopicName();
            ArrayList<String> msgs = topic.getTopicMessages();

            topicDictionary.put(name, msgs);
        }
    }

    //Get list of Topic Names and prints them out for user
    public static void printOutTopicNames(){
        System.out.print("Names of Topics: [" + topicNamesList.get(0) + ", ");
        for (int i = 1; i < topicNamesList.size(); i++) {
            if (i < (topicNamesList.size() - 1))
                System.out.print(topicNamesList.get(i) + ", ");
            else
                System.out.print(topicNamesList.get(i));
        }
        System.out.println("]");
    }

    //Get the list of Topics
    public static ArrayList<TopicData> getTopicsList() { /////////////////////////Ever needed???
        return topicsList;
    }

    //Get the list of Topic Names
    public static List<String> getTopicNamesList() { return topicNamesList; }

    //Get the Dictionary of Topic Names and Topic Messages
    public static Dictionary<String, ArrayList<String>> getTopicDictionary(){ return topicDictionary; }
}