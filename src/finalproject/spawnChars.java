/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;
import processing.core.PApplet;
/**
 * spawnChars class spawns and draws characters from a character array
 * @author Catherine Lin
 * @since 2026-01-18
 */
public class spawnChars {
    //Attributes
    private PApplet app; //PApplet
    private character[] characters; //holds character array that we are spawning characters from
    private Background background; //Holds the background of the levlel
    
    /**
     * spawnChars constructor
     * @param p holds the PApplet
     * @param characters holds the character array needed
     * @param background holds the background of the level
     */
    public spawnChars(PApplet p, character[] characters, Background background){
        //Assign attributes 
        this.app = p;
        this.characters = characters;
        this.background = background;
    }
    
    /**
     * Displays the characters from a character array depending on what kind of 
     * Character it is 
     * @param characters a character array that holds different characters 
     */
    public void displayCharacter (character[] characters){
        //Loop through all characters in the array
        for (int i = 0; i < characters.length; i ++){
            //Checks if the current character is a monkey
             if (characters[i] instanceof Monkey){
                 //Downcast
                 Monkey monkey = (Monkey) characters[i];
                 //Draw the monkey if it is on the correct background index
                 if (monkey.getScene() == background.getIndex()){
                     app.image(monkey.image, monkey.x, monkey.y);
                 }
             }
             //Checks if current character is a tree
             else if (characters[i] instanceof Tree){ 
                 //Downcast
                 Tree tree = (Tree) characters[i];
                 //Draw the tree if it is on the correct background index
                 if (tree.getScene() == background.getIndex()){
                     app.image(tree.image, tree.x, tree.y);
                 }
             }
        }
    }
    /**
     * Draw the characters in the character array
     */
    public void draw(){
        displayCharacter(characters); //Call on the method
    }
}
    
    
   
