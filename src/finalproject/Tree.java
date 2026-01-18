/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

import processing.core.PApplet;

/**
 * Tree Class to create and hold tree objects
 * @author Catherine
 * @since 2026-01-18
 */
public class Tree extends character {
    //Attributes 
    private int scene; //Holds the scene that the tree is supposed to be on
    private String type; // holds the type of tree the tree is 
    
    /**
     * Constructor for the tree class
     * @param p holds PApplet
     * @param x holds the x value of the tree
     * @param y holds the y value of the tree
     * @param name holds the name of the tree
     * @param scene holds the scene of the tree
     * @param type holds the type of the tree
     */
    public Tree(PApplet p, int x, int y, String name, int scene, String type){
        //Call on the parent and assign attributes 
        super(p,x,y,name,"images/trial 1/tree.png");
        this.type = type;
        this.scene = scene;
    }
    
    /**
     * Get the scene of the tree 
     * @return the scene of the tree
     */
    public int getScene(){
        return scene;
    }

    /**
     * Display the info of the tree
     * @param p holds PApplet
     */
    @Override
    public void displayInfo(PApplet p){
        p.fill(0);
        p.text("Name: " + name, x, y - 20);
        p.text("Type of Tree: " + type, x, y-40);
    } 
    
}
