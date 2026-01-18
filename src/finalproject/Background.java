/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Background Class holds the background of the current stage
 * @author Catherine Lin
 * @since 2026-01-18
 */
public class Background {
    /**
     * Attributes
     */
    private PApplet app; //Holds PApplet
    
    //For animated backgrounds
    private PImage [] images; //Holds the images needed for animation 
    private int size; //Holds the number of images in the animation
    private int imageIndex = 0;//holds the index of the current background in the array
    
    //For static backgrounds 
    private PImage image; //Holds the background image
    
    private int currentFrame = 0; // Holds the index of the current frame
    private int frameCounter = 0; // Holds the amount of time/frames the current frame has been drawn onto the screen
    private int frameSpeed = 10; // Holds the amount of time/frames each frame in the animation should last
    
    /**
     * Background constructor for animated backgrounds 
     * @param p holds the PApplet
     * @param size hold the number of images in the animation
     * @param title holds the title of the image
     * @param Scene holds the file that the image is located in
     */
    public Background(PApplet p, int size, String title, String Scene){
        //assign attributes
        this.app = p; 
        this.size = size;
        
        //Create a new image array to hold the background animation
        images = new PImage[size];
        
        //load in all of the images needed
        for (int i = 1; i <= images.length; i++){
            images[i-1] = p.loadImage("images/"+Scene+"/" + title + i + ".png");
        }
    }
    
    /**
     * Background constructor for static backgrounds 
     * @param p holds PApplet
     * @param imagePath holds the image path of the background image
     */
    public Background(PApplet p, String imagePath){
        //Assign attributes 
        this.app = p;
        this.image = p.loadImage(imagePath);
    }
    
    /**
     * For Backgrounds that have multiple scenes
     */
    
    
    /**
     * Draw the current background to the screen
     */
    public void displayarr(){
        app.image(images[imageIndex], 0, 0);
    }
    
    /**
     * Get the current index of the background 
     * @return the current index of the background
     */
     public int getIndex(){
       return imageIndex;
    }
    
     /**
      * Change the current index of the background 
      * @param index the new index of the background 
      */
    public void setIndex(int index){
        this.imageIndex = index;
    }
    
    /**
     * Get the amount of backgrounds in the array
     * @return the size of the backgrounds array 
     */
    public int getSize(){
        return images.length;
    }
    
    /**
     * Go to the next background in the array
     */
    public void goNext(){
        imageIndex++; 
    }
    /**
     * Go to the previous background in the array
     */
    public void goBack(){
        imageIndex--;
    }
    
    /**
     * For Animated Backgrounds 
     */
    
    /**
     * Get the current frame of the animated background 
     * @return the index of the current frame in the animated background array
     */
    public int getCurrentFrame(){
        return currentFrame;
    }
    
    /**
     * Animate the background 
     * As long as the current frame is less than the length of the array count
     * frames until it equals the frame speed in then it switches to the next frame 
     * and resets the counter
     */
    public void animate(){
        if (currentFrame < images.length - 1){
            frameCounter ++;
            
            if (frameCounter >= frameSpeed){
                currentFrame ++;
                frameCounter = 0; 
            }
        }
    }
    
    /**
     * Animates the background and displays it to the screen
     */
    public void displayEnding(){
        animate();
        app.image(images[currentFrame], 0, 0);
    }
    
    
    /**
     * For Static backgrounds 
     */
    
    /**
     * Display the static image
     */
    public void displayone(){
        app.image(image, 0, 0);
    }
    
}
