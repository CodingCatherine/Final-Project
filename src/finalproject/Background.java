/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

import processing.core.PApplet;
import processing.core.PImage;

/**
 *
 * @author Notak
 */
public class Background {
    private PApplet app;
    private PImage [] images;
    private int size;
    private int imageIndex = 0;
    private PImage image;
    
    private int currentFrame = 0; // Holds the index of the current frame
    private int frameCounter = 0; // Holds the amount of time/frames the current frame has been drawn onto the screen
    private int frameSpeed = 10; // Holds the amount of time/frames each frame in the animation should last
    
    
    public Background(PApplet p, int size, String title, String Scene){
        this.app = p; 
        this.size = size;
        
        images = new PImage[size];
        
        for (int i = 1; i <= images.length; i++){
            images[i-1] = p.loadImage("images/"+Scene+"/" + title + i + ".png");
        }
    }
    
    public Background(PApplet p, String imagePath){
        this.app = p;
        this.image = p.loadImage(imagePath);
    }
    
    public void displayarr(){
        app.image(images[imageIndex], 0, 0);
    }
    
    public void animate(){
        if (currentFrame < images.length - 1){
            frameCounter ++;
            
            if (frameCounter >= frameSpeed){
                currentFrame ++;
                frameCounter = 0; 
            }
        }else{
            currentFrame = 49;
        }
    }
    
    public void displayEnding(){
        animate();
        app.image(images[currentFrame], 0, 0);
    }
    
    public void displayone(){
        app.image(image, 0, 0);
    }
    
    public int getIndex(){
       return imageIndex;
    }
    
    public void setIndex(int index){
        this.imageIndex = index;
    }
    
    public int getSize(){
        return images.length;
    }
    public void goNext(){
        imageIndex++; 
    }
    public void goBack(){
        imageIndex--;
    }
    
    
    
    
    
    
}
