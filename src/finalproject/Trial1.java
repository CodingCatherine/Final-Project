/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;
import processing.core.PApplet;

/**
 *
 * @author Notak
 */
public class Trial1 {
    private PApplet app;
    private Background background;
    private character[] characters;
    private Player player;
    
    public Trial1 (PApplet p, Background background, Player player){
        this.app = p;
        this.background = background; 
        this.player = player;
    }
        

    public void display(){
        background.display(); 
        
    }    
            
            
    
}
