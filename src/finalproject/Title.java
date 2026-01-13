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
public class Title {
    private PImage image;
    private PApplet app;
    private button Start;
    private button End;
    
    public Title(String imagePath, PApplet p, button start, button end){
        this.image = app.loadImage(imagePath);
        this.app = p;
        this.Start = start;
        this.End = end; 
        
    }
    
    
    
    
}
