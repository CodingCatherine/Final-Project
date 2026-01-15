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
    IDLE, //Idle Animation
    WALKRIGHT, // Walk Right Animation
    WALKLEFT // Walk Left Animation
}
/**
 *Player Class to create and hold player objects(who the user will control)
 * @author Catherine
 * @since 2026-01-06
 * @version 2
 */
public class Player extends character{
    
    //For animations we also require a state so the program knows which animation we need
    private playerState state = playerState.IDLE; // Set the current player state to idle (default) 
    
    //Animation 
    //Arrays to hold the images required for each animation
    private PImage[] idleFrames;
    private PImage[] walkrightFrames;
    private PImage[] walkleftFrames;
    
    private int currentFrame = 0; // Holds the index of the current frame
    private int frameCounter = 0; // Holds the amount of time/frames the current frame has been drawn onto the screen
    private int frameSpeed = 5; // Holds the amount of time/frames each frame in the animation should last
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
        super(p,x,y,name,imagePath);
        
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
                if (frameCounter >= frameSpeed * 3) { //Display at a slower rate (it is repeated a lot)                    
                    //Displays the next frame if the previous frame has been displayed for long enough
                    //If the program reaches the last frame it will loop to the beginning again
                    currentFrame = (currentFrame + 1) % idleFrames.length;
                    //Set frame counter to 0 since the new frame has just been displayed
                    frameCounter = 0;
                }
                break;
                
        }
                
    }
    
    /**
     * changes the players x and y values to move them across the screen
     * @param dx holds the value of the change in x
     */
    public void move (int dx){
         x+= dx; //change the x
         
         isMoving = (dx != 0); //Change isMoving to true aslong as x or y is changing
         
         if (dx > 0){
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
     * Change the y of the user
     * @param y the y we want to change the player to
     */
    public void setY(int y){
        this.y = y;
    }
    
    /**
     * Change the x of the user
     * @param x the x that we want to change the player to
     */
    public void setX(int x){
        this.x = x;
    }
    /**
     * draws the specified object + it's animations to the screen
     */
    @Override
    public void draw(){
        updateAnimation();
        
        switch(state){          
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
    
}
