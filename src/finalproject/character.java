/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;
//import PApplet and PImage
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Character class holds all characters in the game
 * @author Catherine Lin
 * @since 2026-01-18
 */
public class character {
    //Attributes
    public int x, y; //X and Y value of the character
    protected String name; //Name of the character
    protected final String defname = "Character"; //Default name for the character
    protected PApplet app; //PApplet 
    protected PImage image; //The image of the character
    protected int width, height; //The width and height of the character
    
    
    /**
     * Constructor for the character object
     * @param p holds PApplet
     * @param x holds the x value of the character
     * @param y holds the y value of the character
     * @param name holds the name of the character
     * @param imagePath holds the imagepath for the image of the character
     */
    public character(PApplet p, int x, int y, String name, String imagePath){
        //Assign attributes 
        this.app = p;
        this.x = x;
        this.y = y;
        this.name = name;
        this.image = app.loadImage(imagePath);
        this.width = image.width;
        this.height = image.height;
    }
    
    /**
     * Constructor for the character object
     * @param p holds PApplet
     * @param x holds the x value of the character
     * @param y holds the y value of the character
     * @param imagePath holds the imagepath for the image of the character
     */
    public character(PApplet p, int x, int y, String imagePath){
        //Assign Attributes 
        this.app = p;
        this.x = x;
        this.y = y;
        this.name = defname;
        this.image = app.loadImage(imagePath);
        this.width = image.width;
        this.height = image.height;
    }
    
    /**
     * Sets the name of the specified character
     * @param name the new name for the character
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * gets the width of the specified character
     * @return the width of the specified character
     */
    public int getWidth(){
        return width;
    }
    
    /**
     * gets the height of the specified character
     * @return the height of the specified character
     */
    public int getHeight(){
        return height;
    }
    
    /**
     * Gets the name of the specified character
     * @return the name of the character
     */
    public String getName(){
        return name;
    }
    
    /**
     * Checks if the current character is colliding with another character
     * @param other another character that you want to check if the current is colliding with
     * @return true if they are colliding false if they are not
     */
    public boolean isCollidingWith (character other){
        boolean isLeftOfOtherRight = x < other.x + other.width;
        boolean isRightOfOtherLeft = x + width > other.x;
        boolean isAboveOtherBottom = y < other.y + other.height;
        boolean isBelowOtherTop = y + height > other.y;
        
        return isLeftOfOtherRight &&  isRightOfOtherLeft
        && isAboveOtherBottom && isBelowOtherTop;
        
    }
    
    /**
     * Check if the character has been clicked
     * @param mouseX get the x value of the mouse
     * @param mouseY get the y value of the mouse
     * @return true if the mouse has clicked the character false if it has not
     */
    public boolean isClicked(int mouseX, int mouseY){
        return mouseX > x && mouseX < x + image.pixelWidth && 
                mouseY > y && mouseY < y + image.pixelHeight; 
        
    }
    
    /**
     * Draw the character to the screen
     */
    public void draw(){
        app.image(image, x, y);
    }
    
    /**
     * Displays the information of the specified character
     * @param p holds the PApplet
     */

    public void displayInfo(PApplet p){
        p.fill(0);
        p.text("Name: " + name, x, y - 20);
    }
    
}
