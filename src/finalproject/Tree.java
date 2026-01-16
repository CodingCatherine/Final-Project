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
public class Tree extends character {
    private static int numTrees;
    private int scene;
    private String type;
    
    
    public Tree(PApplet p, int x, int y, String name, int scene, String type){
        super(p,x,y,name,"images/trial 1/tree.png");
        this.type = type;
        this.scene = scene;
        numTrees ++;
    }

    public int getScene(){
        return scene;
    }
    
    @Override
    public void draw(){
        app.image(image, x, y);
    }
    
    @Override
    public void displayInfo(PApplet p){
        p.fill(0);
        p.text("Name: " + name, x, y - 20);
        p.text("Type of Tree: " + type, x, y-40);
    } 
    
}
