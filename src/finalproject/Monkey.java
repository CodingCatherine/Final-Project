/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

import processing.core.PApplet;

/**
 * Monkey class holds all monkeys in the game
 * @author Catherine Lin
 * @since 2026-01-18
 */
public class Monkey extends character {
    //Attributes 
    private String text; //Holds the dialogue of the monkey
    private final String deftext = "..."; //Holds the default dialogue of the monkey
    private int scene; //Holds the scene that the monkey is supposed to appear on 
    public static int numMonkeys = 0; //Number of monkeys created
    
    
    /**
     * Normal monkey constructor
     * @param p holds PApplet
     * @param x holds the x value of the monkey
     * @param y holds the y value of the monkey
     * @param name holds the name of the monkey
     * @param text holds the dialogue of the monkey
     * @param scene holds the scene of the monkey
     */
    public Monkey(PApplet p, int x, int y, String name, String text, int scene){
        //Call on parent and assign the attributes 
        super(p,x,y,name,"images/trial 1/Monkey.png");
        //Assign attributes 
        this.text = text;
        this.scene = scene;
        numMonkeys ++; //Add a monkey to the count
    }
    
    /**
     * Special monkey constructor
     * @param p holds PApplet
     * @param x holds the x value of the monkey
     * @param y holds the y value of the monkey
     * @param name holds the name of the monkey
     * @param scene holds the scene of the monkey
     */
    public Monkey(PApplet p, int x, int y, String name, int scene){
        //Call on parent and assign the attributes 
        super(p,x,y,name,"images/trial 1/hehe.png");
        //Assign attributes 
        this.text = deftext;
        this.scene = scene;
        numMonkeys ++; //Add a monkey to the count
    }
    
    /**
     * Gets the scene of the monkey
     * @return the scene that the monkey is supposed to be on
     */
    public int getScene(){
        return scene;
    }
    
    /**
     * Gets the dialogue of the monkey
     * @return the dialogue of the monkey
     */
    public String getText(){
        return text;
    }
    
}
