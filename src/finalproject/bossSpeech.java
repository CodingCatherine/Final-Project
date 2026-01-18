/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * bossSpeech class to hold the speech of the boss in the game
 * @author Catherine
 * @since 2026-01-18
 */
public class bossSpeech {
    //Attributes 
    private PImage bossIcon; //Holds the icon of the boss
    private PApplet app; //Holds PApplet
    private String [] words = new String []{
        "It seems that I have underestimated you.",
        "You are stronger than I thought.",
        "You are also nothing like your master.",
        "I admit that I was wrong.", 
        "Could you spare me this one time?"
    }; //Holds the dialogue of the boss
    
    private String currentDialogue = ""; //Holds the current Dialogue of the boss
    private PImage dia; //holds the dialogue box
    private int currentLine = 0; //holds the current line of dialogue that the boss is on
    private boolean finished = false; //checks if the dialogue has finished
    
    /**
     * Constructor for bossSpeech 
     * @param p holds PApplet
     */
    public bossSpeech (PApplet p){
        //Assigns attributes 
        this.app = p;
        this.bossIcon = p.loadImage("images/badguy/icon.png");
        this.dia = p.loadImage("images/textbox/talkbox.png");
    }
    
    /**
     * Goes to the next line of dialogue
     */
    public void goNext(){
        if (currentLine < words.length-1){
            currentLine +=1 ;
        }else if (currentLine == words.length - 1){
            finished = true;
        }
    }
    
    //Displays the current dialogue of the boss and draws the dialogue box along with it's icon
    public void display(){
        app.image(dia, 450, 560);
        String text = words[currentLine];
        
        app.image(bossIcon, 350, 570);
        app.fill(0);
        app.textSize(20);
        app.text(text, 480, 600);
        app.text("Click 'f' to continue", 800, 680);
    }
    
    /**
     * Checks if the dialogue has finished
     * @return true if dialogue has finished, false if it has not 
     */
    public boolean isFinished(){
        return finished;
    }

}

