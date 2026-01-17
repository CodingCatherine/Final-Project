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
public class Chest extends button{
    private String type;
    
    
    public Chest(String ImagePath, PApplet p, int x, int y, String type){
        super(ImagePath, p, x, y);
        this.type = type;
    }
    
    
    
}
