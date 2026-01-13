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
        this.app = p;
        this.image = app.loadImage(imagePath);
        this.Start = start;
        this.End = end; 
        
    }
    
    public void displayTitle(){
        app.image(image, 0, 0);
        Start.display();
        End.display();
    }
    
    
    
    
    
    
    
    
    
}
