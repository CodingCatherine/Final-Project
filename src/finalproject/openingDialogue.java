/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;
import processing.core.PApplet;
import processing.core.PImage;
import java.io.File;
/**
 * openingDialogue class displays the opening dialogue of the game
 * @author Catherine Lin
 * @since 2026-01-18
 */
public class openingDialogue extends Dialogue {
    //Attributes 
    private PImage player; //holds player icon
    private PImage Wukong; //holds wukong icon
    
    /**
     * Constructor for the openingDialogue class
     * @param p holds the PApplet
     * @param boxpath holds the imagepath of the dialogue box
     * @param d holds the file that the dialogue is from
     * @param words holds the number of sentences in the file
     * @param playerPath holds the imagepath of the player's dialogue box
     * @param wukongPath holds the imagepath of wukong's dialogue box
     */
    public openingDialogue(PApplet p, String boxpath, File d, int words,  String playerPath, String wukongPath){
        //Call on parent and assign attributes 
        super(p, boxpath, d, words);
        //Assign attributes 
        this.player = app.loadImage(playerPath);
        this.Wukong = app.loadImage(wukongPath);

    }
    /**
     * Displays the Dialogue and changes the current speaker icon depending on who
     * is talking 
     */
    @Override
    public void display(){
        app.image(box, 450, 560);
        
        String speaker = lines[currentLine][0];
        String text = lines[currentLine][1];
        
        if (speaker.equalsIgnoreCase("player")){
            app.image(player, 350, 570);
        }else if (speaker.equalsIgnoreCase("wukong")){
            app.image(Wukong, 350, 570);
        }
        
        app.fill(0);
        app.textSize(20);
        app.text(text, 480, 600);
        app.text("Click 'f' to continue", 800, 680);

    }
    
    
    
    
    
    
}
