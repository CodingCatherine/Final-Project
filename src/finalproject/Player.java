/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;
//import PApplet and PImage
import processing.core.PApplet;
import processing.core.PImage;

/**
 *Player Class to create and hold player objects(who the user will control)
 * @author Catherine
 * @since 2025-12-21
 * @version 1
 */
public class Player {
    //Attributes
    public int x, y;
    private String name;
    private PApplet app;
    private PImage image;
    private int width, height;
    
    /**
     * Constructor for the player class
     * @param p holds the PApplet used
     * @param x holds the x value/coordinate of the player
     * @param y holds the y value/coordinate of the player
     * @param name holds the name of the player
     * @param imagePath holds the current image of the player (animations)
     */
    public Player(PApplet p, int x, int y, String name, String imagePath){
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
     * changes the players x and y values to move them across the screen
     * @param dx holds the value of the change in x
     * @param dy holds the value of the change in y
     */
    public void move (int dx, int dy){
         x+= dx; //change the x
         y+= dy; //change the y
    }
    /**
     * draws the specified object + it's animations to the screen
     */
    public void draw(){
        app.image(image, x, y); //draw the object
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
        p.text("name: " + name, x, y - 20);
    }
    
    
    
    
}
