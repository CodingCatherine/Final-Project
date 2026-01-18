/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Letter class holds the letter level
 * @author Catherine Lin
 * @since 2026-01-18
 */
public class letter {
    //Attributes 
    private PApplet app; //PApplet
    private PImage image; //The image of the letter
    private button cont; //Continue button
    
    /**
     * Letter constructor
     * @param p holds PApplet
     * @param ImagePath holds the image path of the letter
     * @param cont holds the continue button
     */
    
    public letter (PApplet p, String ImagePath, button cont){
        //Assign attributes 
        this.app = p;
        this.image = app.loadImage(ImagePath);
        this.cont = cont;
    }
    
    /**
     * Display the letter alongside the continue button so the user can choose to move onto the next level
     */
    public void display(){
        app.image(image, 0,0);
        cont.display();
    }   
}
