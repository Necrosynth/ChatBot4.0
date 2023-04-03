package chatbot;

import chatbot.places.CB;
import chatbot.places.Discord;
import chatbot.places.Place;
import chatbot.places.YouTube;
import config.Settings;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;
/*
    ToDo:

    How to keep Config Files outside of the jar when compiling
    How to compile this into a Jar File
    Names of topics are out of order..

    WHERE to define the Initialize Data function

    Consider different Topic files for particular different Subjects/Places
    Consider if user doesn't have certain data in the Settings file. Situations: Empty Tag or incompatible String
    Consider if user messes up Topics file

    Think of different modes bot can run in.

    Kill Open Program after leaving Topic Menu
 */

//Client for ChatBot
public class ChatBotClient {
    //Loads all prerequisite data
    //(Initializes all the data that is needed for program to function)
    private static void loadData() throws FileNotFoundException {
        Settings.loadCustomSettings();
        Conversation.buildTopicDictionary();
    }

    //Main menu system for bot
    //Choose a Place to chat, first
    private static void runMainLoop() throws IOException, AWTException {
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
            Place tempCurrentPlace = Location.getCurrentPlace();
            boolean isValidPlace = tempCurrentPlace == Place.YOUTUBE || tempCurrentPlace == Place.CB || tempCurrentPlace == Place.DISCORD;
            if(isValidPlace)
                chooseATopic(); //Let user choose a Topic Name
            else if(tempCurrentPlace.equals(Place.NONE))
                break;
            else
                System.out.println("Error: Invalid Place");

        } while (!placeToChat.equals(""));

        Location.myProcess.destroy(); //Taskkill LOOK UP
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
        List<String> tempTopicNames = Conversation.getTopicNameList();
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
                } else if (currentPlace.equals(Place.CB)){
                    CB.newCBChatTopic(userTopic);
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

    /*
    //Automatic Mode for random bot messages
    private static void autoMode(int chatTimeout){
        List<String> randomTopicList = Conversation.getTopicNameList();
        Random random = new Random();
        int randomTopicIndex = random.nextInt(0, randomTopicList.size());

        for ()
    }
    */

    //Start main
    public static void main(String[] args) throws IOException, AWTException {
        //Initialize data that is needed for program to function
        loadData();

        //Run Main Menu
        runMainLoop();

        //////FIX THIS
        //
        //
        // Location.myProcess.destroy(); //Taskkill LOOK UP
    }
}