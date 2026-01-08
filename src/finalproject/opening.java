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
public class opening {
    private PApplet app;
    private PImage image;
    private Dialogue text;
    
    
    private PImage[] openingframes;
    private int currentFrame = 0; // Holds the index of the current frame
    private int frameCounter = 0; // Holds the amount of time/frames the current frame has been drawn onto the screen
    private int frameSpeed = 5; // Holds the amount of time/frames each frame in the animation should last
    private boolean talking = false;
    
    public opening(PApplet p, String imagePath, Dialogue dia){
        this.app = p;
        this.image = app.loadImage(imagePath);
        this.text = dia;
        
        openingframes = new PImage[24];
        //Opening Animation
        for (int i = 1; i < 25; i++){
            openingframes[i-1] = p.loadImage("images/opening/Opening"+i+".png");
        }
        
    }
    
    public void updateAnimation(){
        if (currentFrame < openingframes.length - 1){
            frameCounter ++;
            
            if (frameCounter >= frameSpeed){
                currentFrame ++;
                frameCounter = 0; 
            }
        }else{
            currentFrame = 23;
            talking = true;
        }
        
        
    }
    
    
    public void display(){
        updateAnimation();
        app.image(openingframes[currentFrame], 0, 0);
        if (talking){
            text.load();
            text.display();
        }
        
    }
}


