/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;
//import PApplet
import java.io.File;
import java.io.FileNotFoundException;
import processing.core.PApplet;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * MySketch class allows for us to add graphics and draw to the screen
 * @author Catherine Lin
 * @since 2026-01-06
 * @version 2
 */

public class MySketch extends PApplet {
    //Initialize a player object
    private Player player;
    private String playerName = "";
    
    //Create an opening object to play the opening sequence
    private opening Op;
    //Create a Dialogue object for the opening dialogue
    private Dialogue text;
    //Import the opening dialogue as a file
    private File opfile = new File ("openingDialogue.txt");
    
    private Title title; 
    private button startbutton;
    private button endbutton;
    
    private button continuebutton;
    private letter Letter;
    private Background tutback;
    
    private Background charcreate;
    private File currPlay = new File ("currentPlayer.txt");
    
    
    private spawnChars tut;
    private File tutchars = new File ("charList1.txt");
    private character[] tutorchars;
    
    //Set the game State of the beginning of the game
    gameState State = gameState.Tutorial;
    
    //Don't show the player/boss information until the user clicks it
    private boolean playershowInfo = false;
   
    private button arrow;
    private int currentMonkey = 0;
    private boolean monkshowInfo = false;
    private boolean clickedaMonkey = false;
    public int monkeysClicked = 0;
    
    private int currentTree = 0;
    private boolean treeshowInfo = false;
    private boolean clickedaTree = false;
    public int treesClicked = 0;
    private monkeySpeech monksp;
    
    private Background batback;
    private BadGuy bad;
    
    //Flag for if the opening dialogue has finished (set false as it hasn't finished yet)
    public boolean finishedop = false;
    
   
    private Background threeBack;
    

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
        //Set Text size
        textSize(20);
        //Instantiate a player object
        player = new Player(this, 0, 0, "Player", "images/idle/player1.png");
        //Instantiate a opening dialogue for the opening dialogue
        text = new openingDialogue(this, "images/textbox/talkbox.png", opfile, 5, "images/textbox/playericon.png", "images/textbox/wukongicon.png");
        //Instantiate the opening animations
        Op = new opening (this, "images/opening/Opening24.png", text);
        startbutton = new button("images/title/start.png", this, 150, 450);
        endbutton = new button("images/title/exit.png", this, 900, 450); 
        title = new Title("images/title/titleScreen.png", this, startbutton, endbutton);
        
        continuebutton = new button ("images/title/continue.png", this, 1000, 550);
        Letter = new letter(this, "images/title/letter.png", continuebutton);
        tutback = new Background(this, 4, "titleBackground","trial 1");
        
        arrow = new button("images/trial 1/arrow.png", this, 1100, 350);
        charcreate = new Background(this, "images/title/creation.png"); 
        tutorchars = loadCharacters(tutchars, 15, 5);
        tut = new spawnChars(this, tutorchars, tutback);
        monksp = new monkeySpeech(this, "images/trial 1/monkeyIcon.png", tutorchars, player, tutback, "images/textbox/talkbox.png");
        
        batback = new Background(this, "images/battle/battleback.png");
        bad = new BadGuy(this, 680, 220, "Boss", "images/badguy/idle/Idle1.png");
        
        threeBack = new Background(this, 4, "titleBackground", "trial 1");
        
        
        
        
    }
    
    /**
     * Allows for us to check if the mouse has been pressed
     */
    @Override
    public void mousePressed(){
        switch (State){
            case Title:
                if (startbutton.isClicked(mouseX, mouseY)){
                    changeState(gameState.CharacterCreation);
                }
                else if(endbutton.isClicked(mouseX, mouseY)){
                    System.exit(0);
                }
                
                break;
            case Tutorial:
                if(player.isClicked(mouseX,mouseY)){
                    playershowInfo = !playershowInfo;
                }else{
                    playershowInfo = false;
                }
                if (tutback.getIndex() == 3){
                    if (arrow.isClicked(mouseX,mouseY)){
                        writeInfo(currPlay);
                        player.setX(300);
                        changeState(gameState.Battle);
                    }
                }
                clickedaMonkey = false;
                clickedaTree = false;
                
                for (int i = 0; i < tutorchars.length; i ++){
                    if (tutorchars[i] instanceof Monkey){
                        Monkey monkey = (Monkey) tutorchars[i];
                        if (monkey.getScene() == tutback.getIndex()){
                            if (monkey.isClicked(mouseX, mouseY)){
                                currentMonkey = i;
                                monkshowInfo = !monkshowInfo;
                                clickedaMonkey = true;
                                monkeysClicked ++;
                            
                            }else if (!clickedaMonkey){
                            monkshowInfo = false;
                            }
                        }
                    }
                    else if (tutorchars[i] instanceof Tree){
                        Tree tree = (Tree) tutorchars[i];
                        if (tree.getScene() == tutback.getIndex()){
                            if (tree.isClicked(mouseX, mouseY)){
                                currentTree = i;
                                treeshowInfo = !treeshowInfo;
                                clickedaTree = true;
                                treesClicked ++;
                            }
                            else if (!clickedaTree){
                                treeshowInfo = false;
                            }
                        }
                }
                    
                }
                break; 
                
            case Mountain:
                if (continuebutton.isClicked(mouseX, mouseY)){
                    changeState(gameState.Tutorial);
                }
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
            dx = -11;
            break;
            //If the user chooses to move right "d" dx = 3 (moving right)
            case 'd':
            dx = 11;
            break;
            }
        }
        
        player.move(dx); // Move player either left (neg) or right (pos)
        player.setY(370);
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
                
            case CharacterCreation:
                if (keyCode == ENTER){
                   changeState(gameState.Opening);
                   writeInfo(currPlay);
                }
                else if (keyCode == BACKSPACE){
                    if (playerName.length() > 0){
                        playerName = playerName.substring(0, playerName.length() -1);
                    }
                }
                else if (keyCode != CODED){
                playerName += key;
            }
                break;
            
            case Battle:
                if (key == 'g' || key == 'G'){
                    player.isAttacking();
                    System.out.println("Hit");
                    bad.isHit();
                }    
        }
    }
    
    /**
     * An enum to hold all of the game states of the game (all of the levels)
     */
    enum gameState{
        Title,
        Opening,
        Mountain,
        CharacterCreation,
        Tutorial,
        StageTwo,
        Battle,
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
    
    private void cycleBack(Background background){
        background.displayarr();
        if (player.x < -25 && background.getIndex() == 0){
            player.setX(-25);
        }
        else if (player.x < -25 && background.getIndex() != 0){
            background.goBack();
            background.displayarr();
            player.setX(1250);
        }
        else if (player.x > 1250&& background.getIndex() != (background.getSize()-1)){
            background.goNext();
            background.displayarr();
            player.setX(0);
        }
        else if (player.x > 1250 && background.getIndex() == (background.getSize()-1)){
            player.setX(1250);
        }
    } 
    
    private void cycleBack1(Background background){
        background.displayone();
        if (player.x < -25){
            player.setX(-25);
        }
        else if (player.x > 1250){
            player.setX(1250);
        }
    } 
    
    
    
    public void writeInfo(File file){
        switch(State){
            case Opening:
                try{
                    FileWriter write1 = new FileWriter(file, false); //Initialize filewriter
                    PrintWriter output = new PrintWriter(write1); //Initialize printwriter
                    output.println(playerName); 
                    output.close(); //close printwriter
            
                //If exception is thrown print out an error code
                }catch(IOException e){
                    System.out.println("IO Error");
                }
                break;
                
            case Tutorial:
                try{
                    FileWriter write1 = new FileWriter(file, true); //Initialize filewriter
                    PrintWriter output = new PrintWriter(write1); //Initialize printwriter
                    output.println(monkeysClicked); 
                    output.println(treesClicked);
                    output.close(); //close printwriter
                //If exception is thrown print out an error code
                }catch(IOException e){
                    System.out.println("IO Error");
                }
                break;
        }
    }
    
    public character[] loadCharacters(File file, int rows, int cols){
        String [][] chars = new String [rows][cols];
        //number to count the number of lines in the file
        int num = 0;
        try{
            //Open the file for reading
            Scanner read = new Scanner(file);
            //Loop until there are no more lines in the file
            while(read.hasNext()){
                //Assign the current line to a string
                String line = read.nextLine();
                //Split the data wherever there are commas and put it into an array
                String [] data = line.split(",");
                //For loop to loop through all indices to ensure all information is transferred
                for(int i = 0;i < data.length; i++){
                    chars[num][i] = data[i].trim();
                }
            //go to the next row 
            num++;
            } 
        read.close();
     
            
        }catch(FileNotFoundException e){ //If the file is not found print out an error
            System.out.println("Sorry! File not Found.");
        }
        
        character [] characters = new character [rows];
        
        for (int i = 0; i < num; i++){
            if (chars[i][0].equalsIgnoreCase("Monkey")){
                if (chars [i][2].equalsIgnoreCase("no")){
                    characters[i] = new Monkey(this, Integer.parseInt(chars[i][4]), 440, chars[i][1], Integer.parseInt(chars[i][3]));
                }
                else{
                    characters[i] = new Monkey(this, Integer.parseInt(chars[i][4]), 440, chars[i][1], chars[i][2], Integer.parseInt(chars[i][3]));
                }
                }
            else if (chars[i][0].equalsIgnoreCase("Tree")){
                characters[i] = new Tree(this, Integer.parseInt(chars[i][4]), 230, chars[i][1], Integer.parseInt(chars[i][3]), chars[i][2]);
            }
            }
        return characters;
    }
    
    /**
     * Draws to the screen
     */
    public void draw(){
        //Switch statement holds all of the levels and runs them depending on the level the user is on
        switch(State){
            case Title:
                title.displayTitle();
                break;
            
            case Opening:
                Op.display();
                if (text.finished){
                    changeState(gameState.Mountain);
                }
                break;
                
            case Mountain:
                Letter.display();
                break;
                
            case CharacterCreation:
                charcreate.displayone();
                fill(0);
                textSize(30);
                text("What is your name?", 700, 200);
                text(playerName, 700, 250);
                text("Click Enter to continue", 700, 300);
                player.setName(playerName);
                break;
                
            case Tutorial:
                cycleBack(tutback);
                tut.draw();
                if (playershowInfo){
                    player.displayInfo(this);
                }
                else if (monkshowInfo){
                    Monkey monkeys = (Monkey)tutorchars[currentMonkey];
                    if (monkeys.getScene() == tutback.getIndex()){
                    monkeys.displayInfo(this); 
                    }
                }
                else if (treeshowInfo){
                    Tree tree = (Tree)tutorchars[currentTree];
                    if (tree.getScene() == tutback.getIndex()){
                    tree.displayInfo(this);
                    }
                }
                
                movement();// call on movement method
                if (tutback.getIndex() == 3){
                    arrow.display();
                }
                monksp.display();
                break;
                
            case StageTwo:
                cycleBack(threeBack);
                movement();
                break;
                
            case Battle:
                cycleBack1(batback);
                bad.draw();
                movement();
                break;
               
                
            case FreeingWukong:
                break;
                
            case End:
                break;
                  
        }        
        //Show info if the flag is tripped
    }
    
}
