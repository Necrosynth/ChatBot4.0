package chatbot;

import data.Settings;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;
/*
    ToDo:
    Rename functions to be more accurate
    Consider if user doesn't have certain data in the Settings

    Consider an interface for Places for Place Classes
    Kill Open Program after leaving Topic Menu
 */

//Client for ChatBot
public class ChatBotClient {
    //Loads all prerequisite data
    //(Initializes all the data that is needed for program to function)
    private static void loadData() throws FileNotFoundException {/// IS THIS A GOOD IDEA???
        Settings.loadCustomSettings();//Run all the init in one command??ss
        Conversation.buildListOfTopicsAndMsgs();//All init in one command???
        Conversation.buildTopicDictionary();// all init in one command???
        Conversation.buildListOfTopicNames();// all init in one command???
    }

    //Allows user to choose a Topic
    private static void chooseATopic() throws IOException, AWTException {
        //Choose a Topic Menu
        String userTopic = "Waiting";
        do {
            Conversation.printOutTopicNames();

            //Get input
            System.out.print("Your Name? (Press Enter to go back a Menu): ");
            Scanner console = new Scanner(System.in);
            userTopic = console.nextLine();

            sendMsgToPlace(userTopic);

        } while (!userTopic.equals(""));
    }

    //Sends msg to a given Place
    private static void sendMsgToPlace(String userTopic) throws IOException, AWTException {
        List<String> tempTopicNames = Conversation.getTopicNamesList();
        //If the chosen Topic was a valid name
        if (tempTopicNames.contains(userTopic)) {
            Place currentPlace = Location.getCurrentPlace();

            //Generate Topic Messages based on user choice
            //Handle sending the message to the place that was chosen
            if(!Location.isProgramOpen()) {
                chooseAPlaceForMsg(userTopic, currentPlace);
            } else {
                Location.switchOpenProgram();
                if(currentPlace.equals(Place.YOUTUBE)) {
                    YouTube.clickYTChatBox();
                    YouTube.newYTBotTopic(userTopic);
                } else if (currentPlace.equals(Place.DISCORD)){
                    Discord.newDiscordBotTopic(userTopic);
                }
            }

            Location.switchOpenProgram();
            System.out.println();

        } else if (!userTopic.equals("")){
            System.out.println();
            System.out.println("Invalid Command for Topic Name...");
            System.out.println();

        } else {
            System.out.println();
            Location.setProgramIsOpen(false);
            /////////////////KILL PROGRAM TOO
        }
    }

    //Decides which Place to go based on user picked
    private static void chooseAPlaceForMsg(String userTopic, Place currentPlace) throws IOException, AWTException {
        //Do these things if placeToGo is "Youtube"
        if(currentPlace.equals(Place.YOUTUBE)) {
            Location.openURL1AndClick(Location.getYTLiveURL(), YouTube.getYTXToClick(), YouTube.getYTYToClick(),
                    YouTube.getYtFastClickDelay(), YouTube.getYtSlowClickDelay());
            YouTube.newYTBotTopic(userTopic);
        }

        //Do these things if placeToGo is "CB"
        else if (currentPlace.equals(Place.CB)){
            Location.switchOpenProgram();
            Location.clickTextBox(CB.getCbXToClick(), CB.getCbYToClick(),
                    CB.getCbClickDelay(), CB.getCbClickDelay());
            CB.newCBChatTopic(userTopic);

            //Do these things if placeToGo is "Discord"
        } else if (currentPlace.equals(Place.DISCORD)){
            sendMsgToDiscord(userTopic);
        }

        Location.setProgramIsOpen(true);
    }

    //Sends msg to Discord
    private static void sendMsgToDiscord(String userTopic) throws IOException {
        int openDelay = Discord.getOpenDiscordDelay();
        int x1 = Discord.getDirectMsgsX();
        int y1 = Discord.getDirectMsgsY();
        int x2 = Discord.getTopDirectMsgX();
        int y2 = Discord.getTopDirectMsgY();
        int x3 = Discord.getDirectMsgChatX();
        int y3 = Discord.getDirectMsgChatY();
        int fastDelay = Discord.getFastClickDelay();

        Location.openProgramAndClick(Location.getDiscordLocation(), openDelay, x1, y1, x2, y2, x3, y3, fastDelay, fastDelay);
        Discord.newDiscordBotTopic(userTopic);
    }

    //Start main
    public static void main(String[] args) throws IOException, AWTException {
        //Initialize data that is needed for program to function
        loadData();

        System.out.println("Welcome to the Chat Bot.");
        Scanner console = new Scanner(System.in);
        //Place to Chat Menu
        String placeToChat = "Waiting";
        do {
            System.out.println("Where do you want to chat?");
            System.out.println("Places to chat: [Youtube, Discord]");
            System.out.print("Choose a Place (Enter to Exit): ");
            placeToChat = console.nextLine();
            System.out.println();

            Location.convertStringToPlace(placeToChat);
            switch (Location.getCurrentPlace()) {
                case NONE:
                    break;

                case YOUTUBE:
                    chooseATopic();
                    break;

                case CB:
                    chooseATopic();
                    break;

                case DISCORD:
                    chooseATopic();
                    break;

                default:
                    System.out.println("Invalid Command for Place to Chat");
                    System.out.println();
                    break;
            }

        } while (!placeToChat.equals(""));
    }
}