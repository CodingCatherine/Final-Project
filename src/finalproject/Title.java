/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;
//import PApplet and PImage
import processing.core.PApplet;
import processing.core.PImage;
/**
 * Title class holds all needed components for the title level of the game
 * @author Catherine Lin
 * @since 2026-01-18
 */
public class Title {
    //Attributes 
    private PImage image; //Holds the background of the level
    private PApplet app; //PApplet
    private button Start; //Holds the start button
    private button End; //Holds the exit button
    
    /**
     * Constructor for the title class
     * @param imagePath of the background 
     * @param p holds PApplet
     * @param start holds the start button 
     * @param end holds the exit button
     */
    public Title(String imagePath, PApplet p, button start, button end){
        //Assign to attributes 
        this.app = p;
        this.image = app.loadImage(imagePath);
        this.Start = start;
        this.End = end; 
        
    }
    
    /**
     * Display the title and buttons 
     */
    public void displayTitle(){
        app.image(image, 0, 0);
        Start.display();
        End.display();
    }
    
    
    
    
    
    
    
    
    
}
