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
    
    
    public Monkey(PApplet p, int x, int y, String name, String text, int scene){
        super(p,x,y,name,"images/trial 1/Monkey.png");
        this.text = text;
        this.scene = scene;
        numMonkeys ++;
    }
    
    public Monkey(PApplet p, int x, int y, String name, int scene){
        super(p,x,y,name,"images/trial 1/Monkey.png");
        this.text = deftext;
        this.scene = scene;
        numMonkeys ++;
    }
    
    public int getScene(){
        return scene;
    }
    
    
    @Override
    public void draw(){
        app.image(image, x, y);
    }
}
