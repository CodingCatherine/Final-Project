/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;
//import PApplet and PImage
import processing.core.PApplet;
import processing.core.PImage;

//Enumeration to hold the states of the boss
enum BGStates{
    IDLE,
    HIT,
    ATTACK,
    }

/**
 * BadGuy Class to create and hold BadGuy objects
 * @author Catherine
 * @since 2026-01-18
 */
public class BadGuy extends character{
    //Attributes 
    //For animations we also require a state so the program knows which animation we need
    private BGStates state = BGStates.IDLE; //Current State of the boss
    private int health; //current health of the player 
    private final int startingHealth; //the starting health of the user
    
    private float dropSpeed = 10; //The speed of which the boss drops 
    private int originalY = 220; // The starting y of the boss
    private int originalX = 680; // The starting x of the boss
    
    
    //Animation 
    //Arrays to hold the images required for each animation
    
    private PImage[] idleFrames;
    private PImage[] hitFrames;
    
    private int currentFrame = 0; // Holds the index of the current frame
    private int frameCounter = 0; // Holds the amount of time/frames the current frame has been drawn onto the screen
    private int frameSpeed = 10; // Holds the amount of time/frames each frame in the animation should last
    

    
    public BadGuy(PApplet p, int x, int y, String name, String imagePath, int health){
        //Call on parent and assign attributes 
        super(p,x,y,name,imagePath);
        
        //Assign Attributes 
        this.health = health;
        this.startingHealth = health;
        
        idleFrames = new PImage[16];
        for(int i = 1; i < 17; i++){
            idleFrames[i-1] = p.loadImage("images/badguy/idle/Idle"+i+".png");
        }
        hitFrames = new PImage[8];
        for(int i = 1; i < 9; i++){
            hitFrames[i-1] = p.loadImage("images/badguy/hit/Hit"+i+".png");
        }
        
    }
    
    public void updateAnimation(){
        //increase the frame counter every frame
        frameCounter++;
        
        switch(state){
            
            case HIT:
                if (frameCounter >= frameSpeed) {
                    int previousFrame = currentFrame;
                    //Displays the next frame if the previous frame has been displayed for long enough
                    //If the program reaches the last frame it will loop to the beginning again
                    currentFrame = (currentFrame + 1) % hitFrames.length;
                    //Set frame counter to 0 since the new frame has just been displayed
                    frameCounter = 0;
                    
                    //reset to idle once the animation has finished
                    if (currentFrame == 0 && previousFrame == hitFrames.length -1){
                        state = BGStates.IDLE;
                    }
                }
                break;
                
            case IDLE:
                if (frameCounter >= frameSpeed) {
                    //Displays the next frame if the previous frame has been displayed for long enough
                    //If the program reaches the last frame it will loop to the beginning again
                    currentFrame = (currentFrame + 1) % idleFrames.length;
                    //Set frame counter to 0 since the new frame has just been displayed
                    frameCounter = 0;
                }
                break;
                
                
            case ATTACK:
                //Drops the boss from the top of the screen
                y+= dropSpeed;
                
                //once boss has dropped past the screen reset it's position and set it back to it's idle animation
                if (y > app.height){
                    y = originalY;
                    x = originalX;
                    state = BGStates.IDLE;
                }
                break;
                
        }
    }
    
    /**
     * Reset the health of the boss (when player dies)
     */
    public void resetHealth(){
        health = startingHealth;
    }
    
    /**
     * When the boss is attacking change the state 
     * teleport the boss to a random x position at the top of the screen 
     */
    public void Attack(){
        state = BGStates.ATTACK;
        x = (int) app.random(0, 1000);
        y = -200;
    }

    
    /**
     * When the boss has been hit by the user change the state. reset animation frames 
     * and subtract 1 from the health
     */
    public void isHit(){
        if (state != BGStates.HIT){
            state = BGStates.HIT;
            currentFrame = 0;
            frameCounter = 0;
            health --;
        }
    }
    
    /** 
     * Checks if the boss has < = 0 health
     * @return true if the boss has < = 0 health  and false if it has > 0 health
     */
    public boolean isDead(){
        boolean dead = false;
        if (health <= 0){
            dead = true;
        }
        return dead;
    }
    
    /**
     * get the current frame of the boss animation
     * @return the current frame of the boss' animation
     */
    public int getCurrentFrame(){
        return currentFrame;
    }
    
    /**
     * Draw the boss to the screen depending on what state the boss is in
     */
    @Override
    public void draw(){
        updateAnimation();
        
        switch(state){          
            case HIT:
                app.image(hitFrames[currentFrame], x, y);
                break; 
                
            case IDLE:
                app.image(idleFrames[currentFrame], x, y);
                break;
                
            case ATTACK:
                //Static image of the boss when it is attacking
                app.image(idleFrames[0], x, y);
                
        }
    }
    
    /**
     * Display the information of the boss
     * @param p holds PApplet
     */
    @Override
    public void displayInfo(PApplet p){
        p.fill(0);
        p.text("Name: " + name, x, y - 20);
        p.text("Health: " + health, x, y);
    }
}
