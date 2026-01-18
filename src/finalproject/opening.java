/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;
//import PApplet and PImage
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Opening class holds everything needed for the opening animation
 * @author Catherine Lin
 * @since 2026-01-18
 */
public class opening {
    //Attributes 
    private PApplet app;//PApplet
    private Dialogue text; //Holds the dialogue in this level
    
    
    private PImage[] openingframes; //Holds the frames in the opening animation
    
    private int currentFrame = 0; // Holds the index of the current frame
    private int frameCounter = 0; // Holds the amount of time/frames the current frame has been drawn onto the screen
    private int frameSpeed = 5; // Holds the amount of time/frames each frame in the animation should last
    
    private boolean talking = false; //Checks if someone is talking 
    
    
    /**
     * Constructor for the opening class
     * @param p holds the PApplet for the opening class
     * @param dia 
     */
    public opening(PApplet p, Dialogue dia){
        //Assign Attributes 
        this.app = p;
        this.text = dia;
        
        //Create the array
        openingframes = new PImage[24];
        //Opening Animation
        //load in all of the images needed
        for (int i = 1; i < 25; i++){
            openingframes[i-1] = p.loadImage("images/opening/Opening"+i+".png");
        }
        
    }
    
    /**
     * Animate the background 
     * As long as the current frame is less than the length of the array count
     * frames until it equals the frame speed in then it switches to the next frame 
     * and resets the counter
     */
    public void updateAnimation(){
        if (currentFrame < openingframes.length - 1){
            frameCounter ++;
            
            if (frameCounter >= frameSpeed){
                currentFrame ++;
                frameCounter = 0; 
            }
        }else{
            //Once animation has finished, freeze it on the last image and start the dialogue
            currentFrame = 23;
            talking = true;
        }
        
        
    }
    
    
    /**
     * Display the animation and dialogue for the opening level of the game
     */
    public void display(){
        updateAnimation();
        app.image(openingframes[currentFrame], 0, 0);
        
        if (talking){
            text.load();
            text.display();
        }
    }
}


