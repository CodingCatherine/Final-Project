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
public class letter {
    private PApplet app;
    private PImage image;
    private button cont;
    
    public letter (PApplet p, String ImagePath, button cont){
        this.app = p;
        this.image = app.loadImage(ImagePath);
        this.cont = cont;
    }
    
    public void display(){
        app.image(image, 0,0);
        cont.display();
    }   
}
