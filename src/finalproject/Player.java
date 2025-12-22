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
public class Player {
    public int x, y;
    private String name;
    private PApplet app;
    private PImage image;
    private int width, height;
    
    public Player(PApplet p, int x, int y, String name, String imagePath){
        this.app = p;
        this.x = x;
        this.y = y;
        this.name = name;
        this.image = app.loadImage(imagePath);
        this.width = image.width;
        this.height = image.height;

    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public void move (int dx, int dy){
         x+= dx;
         y+= dy;
    }
    public void draw(){
        app.image(image, x, y);
    }
    
    public boolean isCollidingWith (Player other){
        boolean isLeftOfOtherRight = x < other.x + other.width;
        boolean isRightOfOtherLeft = x + width > other.x;
        boolean isAboveOtherBottom = y < other.y + other.height;
        boolean isBelowOtherTop = y + height > other.y;
        
        return isLeftOfOtherRight &&  isRightOfOtherLeft
        && isAboveOtherBottom && isBelowOtherTop;
        
    }
    
    public boolean isClicked(int mouseX, int mouseY){
        return mouseX > x && mouseX < x + image.pixelWidth && 
                mouseY > y && mouseY < y + image.pixelHeight; 
        
    }
    
    public void displayInfo(PApplet p){
        p.fill(0);
        p.text("name: " + name, x, y - 20);
    }
    
    
    
    
}
