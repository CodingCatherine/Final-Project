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
    WALKLEFT, // Walk Left Animation
    ATTACK, //Attack Animation
}
/**
 *Player Class to create and hold player objects(who the user will control)
 * @author Catherine
 * @since 2026-01-18
 */
public class Player extends character{
    
    //For animations we also require a state so the program knows which animation we need
    private playerState state = playerState.IDLE; // Set the current player state to idle (default) 
    
    private int health; //current health of the player 
    private final int startingHealth; //the starting health of the user
    
    //Animation 
    //Arrays to hold the images required for each animation
    
    private PImage[] idleFrames;
    private PImage[] walkrightFrames;
    private PImage[] walkleftFrames;
    private PImage[] AttackFrames;
    
    private int currentFrame = 0; // Holds the index of the current frame
    private int frameCounter = 0; // Holds the amount of time/frames the current frame has been drawn onto the screen
    private int frameSpeed = 5; // Holds the amount of time/frames each frame in the animation should last
    
    
    
    /**
     * Constructor for the player class
     * @param p holds the PApplet used
     * @param x holds the x value/coordinate of the player
     * @param y holds the y value/coordinate of the player
     * @param name holds the name of the player
     * @param imagePath holds the current image of the player (animations)
     * @param health holds the user's current health
     */
    public Player(PApplet p, int x, int y, String name, String imagePath, int health){
        //Call on parent and assign attributes 
        super(p,x,y,name,imagePath);
        //Set health
        this.health = health;
        this.startingHealth = health;
        
        //Animation
        //Import the required images and put them into their respective arrays
        
        //Idle Animation 
        idleFrames = new PImage[12]; //12 images for the animation
        //Load images 
        for (int i = 1; i < 7; i++){
            idleFrames[i-1] = p.loadImage("images/idle/player"+i+".png");
        }
        for (int i = 7; i < 13; i++){
            idleFrames[i-1] = p.loadImage("images/idle/player"+(i-6)+".png");
        }
        
        //Walking right Animation
        walkrightFrames = new PImage[12]; //12 images for the animation
        //Load images 
        for (int i = 1; i < 13; i++){
            walkrightFrames[i-1] = p.loadImage("images/left/playerleft"+i+".png");
        }
        
        //Walking Left Animation
        walkleftFrames = new PImage [12]; //12 images for the animation
        //Load images 
        for (int i = 1; i < 13; i++){
            walkleftFrames[i-1] = p.loadImage("images/right/playerright"+i+".png");
        }
        
        //Attacking animations 
        AttackFrames = new PImage [12]; //12 images for the animation
        //Load images 
        for (int i = 1; i < 13; i ++){
            AttackFrames[i-1] = p.loadImage("images/attack/attack"+i+".png");
        }

    }
    
    /**
     * Gets the user's current health
     * @return the user's health
     */
    public int getHealth(){
        return health;
    }
    
    /**
     * When the player takes damage from the boss
     */
    public void hit(){
        health--; //Subtract one from their health
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
                
                
            //If player is attacking
            case ATTACK:
                if (frameCounter >= frameSpeed) {
                    int previousFrame = currentFrame;
                    //Displays the next frame if the previous frame has been displayed for long enough
                    //If the program reaches the last frame it will loop to the beginning again
                    currentFrame = (currentFrame + 1) % AttackFrames.length;
                    //Set frame counter to 0 since the new frame has just been displayed
                    frameCounter = 0;
                    
                    if (currentFrame == 0 && previousFrame == AttackFrames.length -1){
                        state = playerState.IDLE;
                    }
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
        
        //If the player is currently attack override any other animations until it finishes
        if (state == playerState.ATTACK){
            return;
        }
        
         x+= dx; //change the x to how fast the player is moving
         
         //If player is moving right change player state to walk right
         if (dx > 0){
             state = playerState.WALKRIGHT;
         }
         
         //If player is moving left change player state to walk left
         else if (dx < 0){
             state = playerState.WALKLEFT;
         }
         
         //If player is not moving set player state as idle
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
     * If the player is attacking change the player state and reset the animation counters
     */
    public void isAttacking(){
        if (state != playerState.ATTACK){
            state = playerState.ATTACK;
            currentFrame = 0;
            frameCounter = 0;
        }
    }
    
    /**
     * Reset the players health to what it was before (after death)
     */
    public void resetHealth(){
        health = startingHealth;
    }
    
    /**
     * Draws the specified object + it's animations to the screen
     */
    @Override
    public void draw(){
        updateAnimation();
        
        //Draw the player depending on what state they are in
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
                
            case ATTACK:
                app.image(AttackFrames[currentFrame], x, y);
                break;
        }
    }
    
    /**
     * Displays the information of the player that the user has clicked
     * @param p the PApplet of the selected player
     */
    @Override
    public void displayInfo(PApplet p){
        p.fill(0);
        p.text("Name: " + name, x, y - 20);
        p.text("Health: " + health, x, y);
    }
    
}
