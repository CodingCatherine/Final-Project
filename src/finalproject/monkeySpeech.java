/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * monkeySpeech class to hold the speech of the monkeys in the game
 * @author Catherine
 * @since 2026-01-18
 */
public class monkeySpeech{
    //Attributes 
    private PImage monkeyIcon1; //Holds the icon of a normal monkey in the game
    private PImage monkeyIcon2; //holds the icon of special monkeys in the game
    private PApplet app; // holds PApplet
    private character [] characters; //Holds the characters in the section
    private character player; //Holds the current player 
    
    private String currentDialogue = ""; //holds the current dialogue of the monkeys
    private Background background; //Holds the background of the monkey forest
    private PImage dia; //holds the dialogue box

    
    /**
     * Constructor for monkeyspeech
     * @param p holds PApplet
     * @param monkeyPath holds the imagepath of the normal monkeys in the game
     * @param characters holds the character array of characters in this level
     * @param player holds the current player
     * @param background holds the background 
     * @param boxPath holds the imagepath to the dialogue box
     */
    public monkeySpeech(PApplet p, String monkeyPath, character [] characters, character player, Background background, String boxPath){
        //Assign attributes 
        this.app = p;
        this.monkeyIcon1 = p.loadImage(monkeyPath);
        this.monkeyIcon2 = p.loadImage("images/trial 1/heheIcon.png");
        this.characters = characters;
        this.player = player;
        this.background = background;
        
        dia = app.loadImage(boxPath); 
    }
    
    /**
     * Display the monkeys 
     * If the player touches the monkey, display their respective dialogues
     */
    public void display(){
        for (int i = 0; i < characters.length; i++){
            if (characters[i] instanceof Monkey){ //Ensure that the character is a monkey
                Monkey monkey = (Monkey) characters[i]; // downcast
                if (monkey.getScene() == background.getIndex()){ //ensure that it triggers on the correct background 
                    if (player.isCollidingWith(monkey)){ //Check if the player is touching the monkey
                        currentDialogue = monkey.getText(); // Set the current dialogue to the monkey's dialogue
                        //Special. 
                        //If the monkey is called helen display a different icon
                        if (monkey.getName().equalsIgnoreCase("helen")){ 
                            app.image(monkeyIcon2, 350, 570);
                        }
                        //Display the regular icon
                        else{
                            app.image(monkeyIcon1, 350, 570);
                        }
                        //Draw out the dialogue box and draw out the current dialogue
                        app.image(dia, 450, 560);
                        app.fill(0);
                        app.textSize(20);
                        app.text(currentDialogue, 465 ,600);
                    }
                }
                
            }
        }
        
        
    }
    
    
}
