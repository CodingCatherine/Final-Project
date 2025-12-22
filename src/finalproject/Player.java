/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;
//import PApplet and PImage
import processing.core.PApplet;
import processing.core.PImage;

//Special type of class
//Enumeration defines the player states of the game and stores it as constants
enum playerState{
    IDLE,
    WALKUP,
    WALKDOWN,
    WALKRIGHT,
    WALKLEFT
}
/**
 *Player Class to create and hold player objects(who the user will control)
 * @author Catherine
 * @since 2025-12-21
 * @version 1
 */
public class Player {
    //Attributes
    public int x, y;
    private String name;
    private PApplet app;
    private PImage image;
    private int width, height;
    //For animations we also require a state so the program knows which animation we need
    private playerState state = playerState.IDLE; // Set the current player state to idle (default) 
    
    //Animation 
    //Arrays to hold the images required for each animation
    private PImage[] idleFrames;
    private PImage[] walkdownFrames;
    private PImage[] walkupFrames;
    private PImage[] walkrightFrames;
    private PImage[] walkleftFrames;
    
    private int currentFrame = 0; // Holds the index of the current frame
    private int frameCounter = 0; // Holds the amount of time/frames the current frame has been drawn onto the screen
    private int frameSpeed = 6; // Holds the amount of time/frames each frame in the animation should last
    private boolean isMoving = false; //Detects if the player is currently moving
    
    
    /**
     * Constructor for the player class
     * @param p holds the PApplet used
     * @param x holds the x value/coordinate of the player
     * @param y holds the y value/coordinate of the player
     * @param name holds the name of the player
     * @param imagePath holds the current image of the player (animations)
     */
    public Player(PApplet p, int x, int y, String name, String imagePath){
        //Assign attributes
        this.app = p;
        this.x = x;
        this.y = y;
        this.name = name;
        this.image = app.loadImage(imagePath);
        this.width = image.width;
        this.height = image.height;
        
        //Animation
        //Import the required images and put them into their respective arrays
        
        //Idle Animation 
        idleFrames = new PImage[12]; //12 images for the animation
        for (int i = 1; i < 7; i++){
            idleFrames[i-1] = p.loadImage("images/idle/player"+i+".png");
        }
        for (int i = 7; i < 13; i++){
            idleFrames[i-1] = p.loadImage("images/idle/player"+(i-6)+".png");
        }
        
        //Walking Down Animation
        walkdownFrames = new PImage[12];
        for (int i = 1; i < 13; i++){
            walkdownFrames[i-1] = p.loadImage("images/down/playerdown"+i+".png");
        }
        
        //Walking Up Animation
        walkupFrames = new PImage[12];
        for (int i = 1; i < 13; i++){
            walkupFrames[i-1] = p.loadImage("images/up/playerup"+i+".png");
        }
        
        //Walking right Animation
        walkrightFrames = new PImage[12];
        for (int i = 1; i < 13; i++){
            walkrightFrames[i-1] = p.loadImage("images/left/playerleft"+i+".png");
        }
        
        //Walking Left Animation
        walkleftFrames = new PImage [12];
        for (int i = 1; i < 13; i++){
            walkleftFrames[i-1] = p.loadImage("images/right/playerright"+i+".png");
        }

    }
    /**
     * Updates the current image depending on the player's state so that it looks
     * like the image is moving (animating)
     */
    private void updateAnimation(){
        frameCounter++; //Add one to the time
        
        switch(state){
            //If Player is walking down
            case WALKDOWN:
                //Check if the current frame has been displayed for long enough
                if (frameCounter >= frameSpeed) {
                    //Displays the next frame if the previous frame has been displayed for long enough
                    //If the program reaches the last frame it will loop to the beginning again
                    currentFrame = (currentFrame + 1) % walkdownFrames.length;
                    //Set frame counter to 0 since the new frame has just been displayed
                    frameCounter = 0;
                }
                break;
            //If player is walking up
            case WALKUP:
                //Check if the current frame has been displayed for long enough
                if (frameCounter >= frameSpeed) {
                    //Displays the next frame if the previous frame has been displayed for long enough
                    //If the program reaches the last frame it will loop to the beginning again
                    currentFrame = (currentFrame + 1) % walkupFrames.length;
                    //Set frame counter to 0 since the new frame has just been displayed
                    frameCounter = 0;
                }
                break;
            //If player is walking left
            case WALKLEFT:
                //Check if the current frame has been displayed for long enough
                if (frameCounter >= frameSpeed) {
                    //Displays the next frame if the previous frame has been displayed for long enough
                    //If the program reaches the last frame it will loop to the beginning again
                    currentFrame = (currentFrame + 1) % walkleftFrames.length;
                    //Set frame counter to 0 since the new frame has just been displayed
                    frameCounter = 0;
                }
                break;
            //If player is walking right
            case WALKRIGHT:
                //Check if the current frame has been displayed for long enough
                if (frameCounter >= frameSpeed) {
                    //Displays the next frame if the previous frame has been displayed for long enough
                    //If the program reaches the last frame it will loop to the beginning again
                    currentFrame = (currentFrame + 1) % walkrightFrames.length;
                    //Set frame counter to 0 since the new frame has just been displayed
                    frameCounter = 0;
                }
                break;   
            //If player is idle (default)
            case IDLE:
                //Check if the current frame has been displayed for long enough
                if (frameCounter >= frameSpeed * 2) { //Display at a slower rate (it is repeated a lot)                    //Displays the next frame if the previous frame has been displayed for long enough
                    //If the program reaches the last frame it will loop to the beginning again
                    currentFrame = (currentFrame + 1) % idleFrames.length;
                    //Set frame counter to 0 since the new frame has just been displayed
                    frameCounter = 0;
                }
                break;
                
        }
                
    }
    /**
     * gets the width of the specified player
     * @return the width of the specified player
     */
    public int getWidth(){
        return width;
    }
    /**
     * gets the height of the specified player
     * @return the height of the specified player
     */
    public int getHeight(){
        return height;
    }
    
    /**
     * changes the players x and y values to move them across the screen
     * @param dx holds the value of the change in x
     * @param dy holds the value of the change in y
     */
    public void move (int dx, int dy){
         x+= dx; //change the x
         y+= dy; //change the y
         
         isMoving = (dx != 0|| dy !=0); //Change isMoving to true aslong as x or y is changing
         
         if (dy > 0){ 
             state = playerState.WALKDOWN;
         }
         else if (dy < 0){
             state = playerState.WALKUP;
         }
         else if (dx > 0){
             state = playerState.WALKRIGHT;
         }
         else if (dx < 0){
             state = playerState.WALKLEFT;
         }
         else{
             state = playerState.IDLE;
         }
                 
         
    }
    /**
     * draws the specified object + it's animations to the screen
     */
    public void draw(){
        updateAnimation();
        
        switch(state){
            case WALKDOWN:
                app.image(walkdownFrames[currentFrame], x, y);
                break;
            
            case WALKUP:
                app.image(walkupFrames[currentFrame], x, y);
                break;
            
            case WALKRIGHT:
                app.image(walkrightFrames[currentFrame], x, y);
                break;
                
            case WALKLEFT:
                app.image(walkleftFrames[currentFrame], x, y);
                break; 
                
            case IDLE:
                app.image(idleFrames[currentFrame], x, y);
                break;
        }
    }
    
    /**
     * Checks if the current player is colliding with another player
     * @param other another player that you want to check if the current is colliding with
     * @return true if they are colliding false if they are not
     */
    public boolean isCollidingWith (Player other){
        boolean isLeftOfOtherRight = x < other.x + other.width;
        boolean isRightOfOtherLeft = x + width > other.x;
        boolean isAboveOtherBottom = y < other.y + other.height;
        boolean isBelowOtherTop = y + height > other.y;
        
        return isLeftOfOtherRight &&  isRightOfOtherLeft
        && isAboveOtherBottom && isBelowOtherTop;
        
    }
    
    /**
     * Check if the mouse has been clicked
     * @param mouseX get the x value of the mouse
     * @param mouseY get the y value of the mouse
     * @return true if the mouse has clicked the player false if it has not
     */
    public boolean isClicked(int mouseX, int mouseY){
        return mouseX > x && mouseX < x + image.pixelWidth && 
                mouseY > y && mouseY < y + image.pixelHeight; 
        
    }
    
    /**
     * Displays the information of the player that the user has clicked
     * @param p the PApplet of the selected player
     */
    public void displayInfo(PApplet p){
        p.fill(0);
        p.text("name: " + name, x, y - 20);
    }
    
    
    
    
}
