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
public class Monkey extends character {
    private String text;
    private final String deftext = "...";
    private static int numMonkeys;
    private int scene;
    
    
    public Monkey(PApplet p, int x, int y, String name, String imagePath, String text, int scene){
        super(p,x,y,name,imagePath);
        this.text = text;
        this.scene = scene;
        numMonkeys ++;
    }
    
    public Monkey(PApplet p, int x, int y, String name, String imagePath, int scene){
        super(p,x,y,name,imagePath);
        this.text = deftext;
        this.scene = scene;
        numMonkeys ++;
    }
    
}
