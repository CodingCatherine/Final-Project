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
    //Create an opening object to play the opening sequence
    private opening Op;
    //Create a Dialogue object for the opening dialogue
    private Dialogue text;
    //Import the opening dialogue as a file
    private File opfile = new File ("openingDialogue.txt");
    //Set the game State of the beginning of the game
    gameState State = gameState.Opening;
    //Don't show the player/boss information until the user clicks it
    private boolean showInfo = false;
    //Flag for if the opening dialogue has finished (set false as it hasn't finished yet)
    public boolean finishedop = false;
    
    /**
     * Allows us to change the settings of the PApplet
     */
    @Override
    public void settings(){
        //Changing the size of the window
        size(1300, 700);
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
        //Instantiate a opening dialogue for the opening dialogue
        text = new openingDialogue(this, "images/textbox/talkbox.png", opfile, 5, "images/textbox/playericon.png", "images/textbox/wukongicon.png");
        //Instantiate the opening animations
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
    
    /**
     * Holds the movement controls for the player
     */
    public void movement(){
        //sets an int for the change in x
        int dx = 0;
        
        if (keyPressed){
            switch (key) {
                //If the user chooses to move left "a" dx = -3 (moving left) 
                case 'a':
                    dx = -3;
                    break;
                //If the user chooses to move right "d" dx = 3 (moving right)
                case 'd':
                    dx = 3;
                    break;
            }
        }
        
        player.move(dx); // Move player either left (neg) or right (pos)
        player.draw(); // Draw player after movement
        
        //Collide Checker get rid of this when game is done
        noFill();
        stroke(255,0,0);
        rect(player.x,player.y, player.getWidth(), player.getHeight());
    }
    
    /**
     * Key Pressed checker for dialogue
     */
    public void keyPressed(){
        //Only trigger depending on if the state has dialogue
        switch(State){
            //For Opening Dialogue
            case Opening:
                switch(key){
                    //When F is pressed call on the method to go to the next line
                    case 'f':
                    case 'F':
                        text.goNext();
                        break;
                    }
                break;
        }
    }
    
    /**
     * An enum to hold all of the game states of the game (all of the levels)
     */
    enum gameState{
        Title,
        Opening,
        Mountain,
        Tutorial,
        StageTwo,
        StageThree,
        StageFour,
        FreeingWukong, 
        End,
        
    }
    
    /**
     * Method to change the current state of the game
     * @param newState the new state that we want to change the game to
     */
    private void changeState(gameState newState){
       State = newState;
    }
    
    /**
     * Draws to the screen
     */
    public void draw(){
        //Switch statement holds all of the levels and runs them depending on the level the user is on
        switch(State){
            case Title:
                
                break;
            
            case Opening:
                Op.display();
                if (text.finished){
                    changeState(gameState.Tutorial);
                }
                break;
                
            case Mountain:
                background(255);
                break;
                
            case Tutorial:
                background(255);
                player.setY(300);
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
