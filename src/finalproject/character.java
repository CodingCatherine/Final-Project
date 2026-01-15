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
public class character {
    //Attributes
    public int x, y;
    protected String name;
    protected final String defname = "Character";
    protected PApplet app;
    protected PImage image;
    protected int width, height;
    protected PImage textImage;
    
    
    public character(PApplet p, int x, int y, String name, String imagePath){
        this.app = p;
        this.x = x;
        this.y = y;
        this.name = name;
        this.image = app.loadImage(imagePath);
        this.width = image.width;
        this.height = image.height;
    }
    
    public character(PApplet p, int x, int y, String imagePath){
        this.app = p;
        this.x = x;
        this.y = y;
        this.name = defname;
        this.image = app.loadImage(imagePath);
        this.width = image.width;
        this.height = image.height;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * gets the width of the specified player
     * @return the width of the specified player
     */
    public int getWidth(){
        return width;
    }
    
    /**
     * gets the height of the specified player
     * @return the height of the specified player
     */
    public int getHeight(){
        return height;
    }
    
    /**
     * Checks if the current player is colliding with another player
     * @param other another player that you want to check if the current is colliding with
     * @return true if they are colliding false if they are not
     */
    public boolean isCollidingWith (Player other){
        boolean isLeftOfOtherRight = x < other.x + other.width;
        boolean isRightOfOtherLeft = x + width > other.x;
        boolean isAboveOtherBottom = y < other.y + other.height;
        boolean isBelowOtherTop = y + height > other.y;
        
        return isLeftOfOtherRight &&  isRightOfOtherLeft
        && isAboveOtherBottom && isBelowOtherTop;
        
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
    
    /**
     * Displays the information of the player that the user has clicked
     * @param p the PApplet of the selected player
     */
    public void displayInfo(PApplet p){
        p.fill(0);
        p.text("Name: " + name, x, y - 20);
    }
    
    public void draw(){
        app.image(image, x, y);
    }
    
    
    
    
}
