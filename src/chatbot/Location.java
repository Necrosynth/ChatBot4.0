package chatbot;

import chatbot.places.Place;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Location {
    public static Process myProcess = null;
    private static String browserLocation = "";
    private static String discordLocation = "";
    private static String ytLiveURL = "";
    private static Place currentPlace = Place.NONE;

    //Whether or not a program opened from here is still running
    private static boolean programIsOpen = false;

    public static void setBrowserLocation(String newBrowserLocation){
        browserLocation = newBrowserLocation;
    }

    public static void setDiscordLocation(String newDiscordLocation) {
        discordLocation = newDiscordLocation;
    }
    public static String getDiscordLocation() {
        return discordLocation;
    }

    public static void setYTLiveURL(String ytLiveURL) {
        Location.ytLiveURL = ytLiveURL;
    }
    public static String getYTLiveURL() {
        return ytLiveURL;
    }

    //Checks if Program is Open
    public static boolean isProgramOpen() {
        return programIsOpen;
    }

    //Set Program is Open to Yes/No
    public static void setProgramIsOpen(boolean isProgramOpen){
        programIsOpen = isProgramOpen;
    }

    public static Place getCurrentPlace() {
        return currentPlace;
    }

    //Set up the Place enum based on user input
    public static void convertStringToPlace(String placeToChat){
        switch (placeToChat){
            case "":
                currentPlace = Place.NONE;
                break;
            case "Youtube":
                currentPlace = Place.YOUTUBE;
                break;
            case "Discord":
                currentPlace = Place.DISCORD;
                break;
            case "CB":
                currentPlace = Place.CB;
                break;
            default:
                System.out.println("Invalid place...");
        }
    }

    //Robot Class
    private static final Robot robot;
    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    //Private Robot helper methods
    //
    //Set text to Clipboard
    private static void clip(String text) {
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, stringSelection);
    }

    //Paste msg to Chat
    public static void pasteToChat(String msg, int delay){
        clip(msg);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(delay);
    }

    //Make program go fullscreen
    ////////////////

    //Select the Address Bar
    private static void selectAddressBar(){
        //Set delay variables
        final int DELAY1 = 1000;

        robot.delay(DELAY1);
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_D);
        robot.delay(DELAY1);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_D);
    }

    //Paste URL to Address Bar
    private static void urlToAddress(String url){
        final int DELAY1 = 1000;

        //Paste URL to address bar
        robot.delay(DELAY1);
        //clip(TPTURL);
        clip(url);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        //Press Enter
        robot.keyPress(KeyEvent.VK_ENTER);
    }

    //Click Chat Text Box
    public static void clickTextBox(int x, int y, int fastDelay, int slowDelay){
        //Click on the text box
        robot.delay(slowDelay);
        robot.mouseMove(x,y);
        robot.delay(fastDelay);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(fastDelay);
    }

    //Switch Open Program
    public static void switchOpenProgram(){
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.delay(1000);
    }
    //

    //Navigate to the URL and click the Chat Box
    public static void openURL1AndClick(String URL, int xToClick, int yToClick, int fastDelay, int slowDelay) throws IOException {
        //Runtime needed for opening a program
        final Runtime RUNTIME = Runtime.getRuntime();

        //Run Browser from Location of Browser.
        String BROWSER_LOCATION = new String(browserLocation);



        //////////////////////////////////// TESTING !!!!!!!!!!!!!!!  /////
        // (myProcess) TESTING: Save Browser Process, TO KILL LATER...!!!!!!!!
        //TRY TO GRAB THE BROWSER PROCESS
        myProcess = RUNTIME.exec(BROWSER_LOCATION);
        //////////////////////////////////////////////////////////////



        selectAddressBar();
        urlToAddress(URL);
        clickTextBox(xToClick, yToClick, fastDelay, slowDelay);
    }

    //Opens a Program and clicks on the Chat Box
    public static void openProgramAndClick(String programLocation,int openProgramDelay, int x1, int y1, int x2, int y2, int x3, int y3,
                                           int fastDelay, int slowDelay) throws IOException {
        //Runtime needed for opening a program
        final Runtime RUNTIME = Runtime.getRuntime();

        //Run Program
        RUNTIME.exec(programLocation);

        robot.delay(openProgramDelay);
        clickTextBox(x1, y1, fastDelay, fastDelay);
        clickTextBox(x2, y2, fastDelay, fastDelay);
        clickTextBox(x3, y3, fastDelay, fastDelay);
    }
}