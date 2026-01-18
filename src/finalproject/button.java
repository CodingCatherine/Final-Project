/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;
//import PApplet and PImage
import processing.core.PApplet;
import processing.core.PImage;


/**
 * Button class holds the button objects used in this game
 * @author Catherine Lin
 * @since 2026-01-18
 */
public class button {
    //Attributes
    public int x, y; //x and y values of the button
    private PImage image; //the image of the button
    private PApplet app; //PApplet
    
    /**
     * Button Constructor
     * @param ImagePath holds the imagepath of the button 
     * @param p holds the PApplet
     * @param x holds the x value of the button
     * @param y holds the y value of the button
     */
    public button(String ImagePath, PApplet p, int x, int y){
        //Assign attributes 
        this.app = p;
        this.image = app.loadImage(ImagePath);
        this.x = x;
        this.y = y; 
    }
    
    /**
     * Check if the button has been clicked
     * @param mouseX get the x value of the mouse
     * @param mouseY get the y value of the mouse
     * @return true if the mouse has clicked the button false if it has not
     */
    public boolean isClicked(int mouseX, int mouseY){
        return mouseX > x && mouseX < x + image.pixelWidth && 
                mouseY > y && mouseY < y + image.pixelHeight; 
        
    }
    
    //Displays the button to the screen
    public void display(){
        app.image(image, x, y);
    }
    

}
