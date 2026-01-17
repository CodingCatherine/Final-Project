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
    ATTACK,
    HIT,
    }
public class BadGuy extends character{
    private BGStates state = BGStates.IDLE;
    private int health;
    
    private PImage[] idleFrames;
    private PImage[] attackFrames;
    private PImage[] hitFrames;
    
    private int currentFrame = 0; // Holds the index of the current frame
    private int frameCounter = 0; // Holds the amount of time/frames the current frame has been drawn onto the screen
    private int frameSpeed = 10; // Holds the amount of time/frames each frame in the animation should last
    
    private int attackSpeed = 20;
    private int attackCounter = 0;
    
    public BadGuy(PApplet p, int x, int y, String name, String imagePath, int health){
        super(p,x,y,name,imagePath);
        
        this.health = health;
        
        idleFrames = new PImage[16];
        for(int i = 1; i < 17; i++){
            idleFrames[i-1] = p.loadImage("images/badguy/idle/Idle"+i+".png");
        }
        attackFrames = new PImage[16];
        for(int i = 1; i < 17; i++){
            attackFrames[i-1] = p.loadImage("images/badguy/attack/Attack"+i+".png");
        }
        hitFrames = new PImage[8];
        for(int i = 1; i < 9; i++){
            hitFrames[i-1] = p.loadImage("images/badguy/hit/Hit"+i+".png");
        }
        
    }
    
    public void updateAnimation(){
        frameCounter++;
        
        switch(state){
            case ATTACK:
                if (frameCounter >= frameSpeed) {
                    int previousFrame = currentFrame;
                    //Displays the next frame if the previous frame has been displayed for long enough
                    //If the program reaches the last frame it will loop to the beginning again
                    currentFrame = (currentFrame + 1) % attackFrames.length;
                    //Set frame counter to 0 since the new frame has just been displayed
                    frameCounter = 0;
                    
                    if (currentFrame == 0 && previousFrame == attackFrames.length -1){
                        state = BGStates.IDLE;
                    }
                }
                break;
                
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
                
        }
    }
    
    public void isAttacking(){
        if (state != BGStates.ATTACK){
            state = BGStates.ATTACK;
            currentFrame = 0;
            frameCounter = 0;
        }
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
            System.out.println("dead....");
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
                app.image(attackFrames[currentFrame], x, y);
                break;
        }
    }
}
