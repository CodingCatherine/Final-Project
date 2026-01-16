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
public class spawnChars {
    private PApplet app;
    private character[] characters;
    private Background background;
    
    
    public spawnChars(PApplet p, character[] characters, Background background){
        this.app = p;
        this.characters = characters;
        this.background = background;
    }
    
    public void displayCharacter (character[] characters){
        for (int i = 0; i < characters.length; i ++){
             if (characters[i] instanceof Monkey){
                 Monkey monkey = (Monkey) characters[i];
                 if (monkey.getScene() == background.getIndex()){
                     app.image(monkey.image, monkey.x, monkey.y);
                 }
             }
             else if (characters[i] instanceof Tree){
                 Tree tree = (Tree) characters[i];
                 if (tree.getScene() == background.getIndex()){
                     app.image(tree.image, tree.x, tree.y);
                 }
             }
        }
    }
    public void draw(){
        displayCharacter(characters);
    }
}
    
    
   
