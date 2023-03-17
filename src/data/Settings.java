package data;

import chatbot.Location;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Settings {
    //Location on computer of the custom Settings text file
    private static final String SETTINGS_FILE_NAME = "config\\Settings.txt";

    //Handles loading the Custom Settings into the program
    public static void loadCustomSettings() throws FileNotFoundException {
        //Get all data from the Settings file
        Scanner inputData = new Scanner(new File(SETTINGS_FILE_NAME));
        try {
            while (inputData.hasNextLine()) { //Runs loop while there's more data to process
                while(inputData.hasNext()){
                    String nextToken = inputData.next();
                    switch (nextToken){
                        ///// Consider situations where a setting doesn't apply to a user
                        case "BROWSER_LOCATION:": //Start picking out pieces of data from the Settings
                            String browserLocation = inputData.nextLine();
                            Location.setBrowserLocation(browserLocation);
                            break;
                        case "DISCORD_LOCATION:":
                            String discordLocation = inputData.nextLine();
                            Location.setDiscordLocation(discordLocation);
                            break;
                        case "YOUTUBE_LIVESTREAM_URL:":
                            String ytLivestreamURL = inputData.nextLine();
                            Location.setYTLiveURL(ytLivestreamURL);
                            break;
                        default:
                            ///
                    }
                }
            }
        } finally {
            //Close whether or not there's File Error
            inputData.close();
        }
    }

    public static String getSettingsFileName() {
        return SETTINGS_FILE_NAME;
    }
}