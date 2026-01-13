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
public class button {
    //Attributes
    public int x, y;
    private PImage image;
    private PApplet app;
    
    public button(String ImagePath, PApplet p, int x, int y){
        this.image = app.loadImage(ImagePath);
        this.app = p;
        
        this.x = x;
        this.y = y; 
    }
    
    /**
     * Check if the mouse has been clicked
     * @param mouseX get the x value of the mouse
     * @param mouseY get the y value of the mouse
     * @return true if the mouse has clicked the player false if it has not
     */
    public boolean isClicked(int mouseX, int mouseY){
        return mouseX > x && mouseX < x + image.pixelWidth && 
                mouseY > y && mouseY < y + image.pixelHeight; 
        
    }
}
