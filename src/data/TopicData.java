package data;

import java.util.ArrayList;

//Represents a Topic for Conversation with corresponding data
public class TopicData {
    //Name of the Topic
    private String topicName = "";
    //Messages for the Topic
    private ArrayList<String> topicMessages = null;

    public TopicData(){
        //location = "Nowhere";
        topicName = "Nothing";
        topicMessages = new ArrayList<>();
    }

    public TopicData(String topicName, ArrayList<String> topicMessages){
        //this.location = location;
        this.topicName = topicName;
        this.topicMessages = topicMessages;
    }

    //Get Topic Messages
    public ArrayList<String> getTopicMessages() {
        return topicMessages;
    }

    //Get Topic Name
    public String getTopicName() {
        return topicName;
    }
}