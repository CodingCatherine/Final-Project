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
    
    
    
    public Background(PApplet p, int size, String title, String Scene){
        this.app = p; 
        this.size = size;
        
        images = new PImage[size];
        
        for (int i = 1; i <= images.length; i++){
            images[i-1] = p.loadImage("images/"+Scene+"/" + title + i + ".png");
        }
    }
    
    public void display(){
        app.image(images[imageIndex], 0, 0);
    }
    
    public int getIndex(){
       return imageIndex;
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
