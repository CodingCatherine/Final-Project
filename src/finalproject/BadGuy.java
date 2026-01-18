/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;
//import PApplet and PImage
import processing.core.PApplet;
import processing.core.PImage;

/**
 *
 * @author Notak
 */
enum BGStates{
    IDLE,
    HIT,
    ATTACK,
    }
public class BadGuy extends character{
    private BGStates state = BGStates.IDLE;
    private int health;
    private final int startingHealth;
    
    private boolean attacking = false;
    private float dropSpeed = 10;
    private int originalY = 220;
    private int originalX = 680;
    
    private PImage[] idleFrames;
    private PImage[] hitFrames;
    
    private int currentFrame = 0; // Holds the index of the current frame
    private int frameCounter = 0; // Holds the amount of time/frames the current frame has been drawn onto the screen
    private int frameSpeed = 10; // Holds the amount of time/frames each frame in the animation should last
    

    
    public BadGuy(PApplet p, int x, int y, String name, String imagePath, int health){
        super(p,x,y,name,imagePath);
        
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
                y+= dropSpeed;
                if (y > app.height){
                    y = originalY;
                    x = originalX;
                    state = BGStates.IDLE;
                    attacking = false;
                }
                break;
                
        }
    }
    
    public void resetHealth(){
        health = startingHealth;
    }
    
    public void Attack(){
        state = BGStates.ATTACK;
        x = (int) app.random(0, 1000);
        y = -200;
        attacking = true;
    }

    
    public void isHit(){
        if (state != BGStates.HIT){
            state = BGStates.HIT;
            currentFrame = 0;
            frameCounter = 0;
            health --;
        }
    }
    
    public boolean isDead(){
        boolean dead = false;
        if (health <= 0){
            dead = true;
        }
        return dead;
    }
    
    public int getCurrentFrame(){
        return currentFrame;
    }
    
    
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
                app.image(idleFrames[0], x, y);
                
        }
    }
    
    @Override
    public void displayInfo(PApplet p){
        p.fill(0);
        p.text("Name: " + name, x, y - 20);
        p.text("Health: " + health, x, y);
    }
}
