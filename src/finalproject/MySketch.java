/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;
//import PApplet
import java.io.File;
import processing.core.PApplet;

/**
 * MySketch class allows for us to add graphics and draw to the screen
 * @author Catherine Lin
 * @since 2026-01-06
 * @version 2
 */

public class MySketch extends PApplet {
    //Initialize a player object
    private Player player;
    private opening Op;
    private Dialogue text;
    private File file = new File ("openingDialogue.txt");
    gameState State = gameState.Opening;
    //Don't show the player/boss information until the user clicks it
    private boolean showInfo = false;
    
    /**
     * Allows us to change the settings of the PApplet
     */
    @Override
    public void settings(){
        //Changing the size of the window
        size(1500, 700);
    }
    
    /**
     * Allows us to set up the game and the defaults 
     */
    @Override
    public void setup(){
        //Set background colour
        background(255);
        //Instantiate a player object
        player = new Player(this, 0, 0, "Player", "images/idle/player1.png");
        text = new openingDialogue(this, "images/textbox/talkbox.png", file, "images/textbox/playericon.png", "images/textbox/wukongicon.png");
        Op = new opening (this, "images/opening/Opening24.png", text);
    }
    
    /**
     * Allows for us to check if the mouse has been pressed
     */
    @Override
    public void mousePressed(){
        if(player.isClicked(mouseX,mouseY)){
            showInfo = !showInfo;
        }else{
            showInfo = false;
        }
    }
    
    public void movement(){
        int dx = 0;
        int dy = 0;
        
        if (keyPressed){
            switch (key) {
                case 'a':
                    dx = -3;
                    break;
                case 'd':
                    dx = 3;
                    break;
                case 'w':
                    dy = -3;
                    break;
                case 's':
                    dy = 3;
                    break;
                default:
                    break;
            }
        }
        
        player.move(dx,dy); // Move player
        player.draw(); // Draw player
        
        //Collide Checker
        noFill();
        stroke(255,0,0);
        rect(player.x,player.y, player.getWidth(), player.getHeight());
    }
    
    public void Skip(){
        if (keyPressed){
            switch(key){
                case 'f':
                case 'F':
                    text.goNext();
                    break;
                    
            }
        }
        
    }
    
    enum gameState{
        Opening,
        CharacterCreation,
        Tutorial,
        StageTwo,
        StageThree,
        StageFour,
        FreeingWukong, 
        End,
        
    }
    
    /**
     * Draws to the screen
     */
    public void draw(){
        switch(State){
            case Opening:
                Op.display();
                Skip();
                break;
                
            case Tutorial:
                background(255);
                movement(); // call on movement method
                break;
                
            case StageTwo:
                break;
                
            case StageThree:
                break;
                
            case StageFour:
                break;
                
            case FreeingWukong:
                break;
                
            case End:
                break;
                  
        }        
        //Show info if the flag is tripped
        if (showInfo){
            player.displayInfo(this);
        }
    }
    
}
