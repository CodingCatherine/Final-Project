/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

/**Required imports 
 * File and FileNotFound to use files
 * PApplet for the graphics
 * PrintWriter, FileWriter, Scanner and IO exception for reading and writing to flat file
 */

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
 * @since 2026-01-18
 * @version 4
 */

public class MySketch extends PApplet {
    
    //Set the game State of the beginning of the game
    gameState State = gameState.Title;
    
    /**
     * Files required for this game
     */
    
    private File opfile = new File ("openingDialogue.txt"); //Opening Dialogue
    private File currPlay = new File ("currentPlayer.txt"); //Current Player's Information
    private File scores = new File ("scores.txt"); //Scores of the game
    
    /**
     * Character Declarations
     */
    //Player 
    private Player player; //Create Player Object
    private String playerName = ""; //Holds Player Name
    private int damageCooldown = 100; // Holds the cooldown for the player damage
    //Don't show the player information until the user clicks it
    private boolean playershowInfo = false;
    
    //Character Array to hold other characters
    private spawnChars tut; //Spawns Characters 
    private File tutchars = new File ("charList1.txt"); //File that holds the characters
    private character[] tutorchars; //Character array of characters
    
    //Monkey
    private int currentMonkey = 0; // Holds the current monkey clicked
    private boolean monkshowInfo = false; //Checks if a monkey is showing info
    private boolean clickedaMonkey = false; // Checks if user has clicked a monkey
    public int monkeysClicked = 0; //Holds the number of monkeys clicked
    private monkeySpeech monksp; //Holds the dialogue of the monkeys
    
    //Tree
    private int currentTree = 0; // Holds the current tree clicked
    private boolean treeshowInfo = false; //Checks if a tree is showing info
    private boolean clickedaTree = false; //Checks if user has clicked a tree
    public int treesClicked = 0; // Holds the number of trees clicked
    
    
    //Boss
    private BadGuy bad;//Create Boss Object
    private int attackCooldown = 0; //Attack Cooldown for the boss
    private boolean bossdeath = false; //Checks if boss has been defeated
    private int bossCooldown = 100; //Cooldown for the boss' attacks
    private bossSpeech bosssp; //Holds the dialogue of the boss
    
    /**
     * Backgrounds and dialogue 
     */
    
    private Title title; //Holds the title Screen 
    private Background controls; //Holds the controls screen
    private Background charcreate; // Holds the character creation screen
    private opening Op; //Holds Opening Sequence
    private letter Letter; //Holds Wukong's Letter
    private Background tutback; //Holds the background of the monkey forest
    private Background batbackdia1; //Holds the background dialogue prebattle
    private Background batbackdia2; //Holds the background dialogue prebattle
    private Background batback; //Holds the battle background
    private Background death; //Holds the death screen
    private Background goodEnding; //Holds the good ending scene
    private Background badEnding; //Holds the bad ending scene
    private Background ending; //Holds the ending screen

    //Create a Dialogue object for the opening dialogue
    private Dialogue text;
    //Holds the counter for the prebattle dialogue
    private int batcounter = 0;
    //Holds the leaderboard/summary of the user's points at the end
    private Leaderboard lead;
    //Checks for highest score 
    private compare comp;
    
    /**
     * Buttons and Flags
     */
    
    //Title
    private button startbutton; 
    private button endbutton;
    
    //Controls
    private button contbut;
    
    //Letter
    private button continuebutton;
    
    //Monkey Forest
    private button arrow;
    
    //Player Death
    private button moveon;
    
    //Choose Boss
    private button spare;
    private button nospare;
    
    //End
    private button exit;
    
    private boolean finished; //Checks if dialogue has finished
    private boolean spared; //Checks if user has spared the boss
    public boolean finishedop = false; //Checks if the opening has finished
    
    /**
     * An enumeration to hold all of the game states of the game (all of the levels)
     */
    enum gameState{
        Title,
        Controls,
        CharacterCreation,
        Opening,
        Mountain,
        Tutorial,
        cutBat,
        Battle,
        Death,
        ChooseBoss,
        BadEnding,
        GoodEnding,
        End,
        
    }

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
        
        /**
         * Instantiation list:
         * Title
         * Controls
         * Character Creation
         * Opening 
         * Letter
         * Monkey Forest(Tutorial)
         * Cutscene preBattle
         * Battle
         * Death
         * Choose Boss
         * Endings 
         * End
         */
        
        //Title
        title = new Title("images/title/titleScreen.png", this, startbutton, endbutton);
        startbutton = new button("images/title/start.png", this, 150, 450);
        endbutton = new button("images/title/exit.png", this, 900, 450); 
        
        //Controls
        controls = new Background (this, "images/controls.png");
        contbut = new button ("images/title/continue.png", this, 1000, 550);
        
        //Character Creation
        charcreate = new Background(this, "images/title/creation.png"); 
        
        //Opening
        //Instantiate a opening dialogue for the opening dialogue
        text = new openingDialogue(this, "images/textbox/talkbox.png", opfile, 5, "images/textbox/playericon.png", "images/textbox/wukongicon.png");
        //Instantiate the opening animations
        Op = new opening (this, text);
        
        //Letter
        continuebutton = new button ("images/title/continue.png", this, 1000, 550);
        Letter = new letter(this, "images/title/letter.png", continuebutton);
        
        //Monkey Forest
        //Instantiate a player object
        player = new Player(this, 0, 0, "Player", "images/idle/player1.png", 12);
        tutback = new Background(this, 4, "titleBackground","trial 1");
        arrow = new button("images/trial 1/arrow.png", this, 1100, 350);
        tutorchars = loadCharacters(tutchars, 15, 5);
        tut = new spawnChars(this, tutorchars, tutback);
        monksp = new monkeySpeech(this, "images/trial 1/monkeyIcon.png", tutorchars, player, tutback, "images/textbox/talkbox.png");
        
        //preBattle (Choose Boss)
        batbackdia1 = new Background (this, "images/battle/batdia1.png");
        batbackdia2 = new Background (this, "images/battle/batdia2.png");
        
        //Battle
        batback = new Background(this, "images/battle/battleback.png");
        bad = new BadGuy(this, 680, 220, "Boss", "images/badguy/idle/Idle1.png", 10);
        bosssp = new bossSpeech(this);
        spare = new button ("images/badguy/spare.png", this, 100, 200);
        nospare = new button ("images/badguy/nospare.png", this, 1000, 200);

        //Player Death
        death = new Background (this, "images/death.png");
        moveon = new button ("images/title/continue.png", this, 1000, 400);
        
        //Endings
        goodEnding = new Background(this, 40, "goodEnding", "good");
        badEnding = new Background(this, 40, "badEnding", "bad");
        
        //End
        ending = new Background(this, "images/ending.png");
        lead = new Leaderboard(currPlay);
        exit = new button ("images/title/exit.png",this, 200, 550);
        comp = new compare (scores, lead.getScore());

    }

    
    /**
     * Allows for us to check if the mouse has been pressed
     */
    @Override
    public void mousePressed(){
        //Changes depending on which level the player is on
        switch (State){
            case Title:
                //When the start button is clicked start the game (go to next level)
                if (startbutton.isClicked(mouseX, mouseY)){
                    changeState(gameState.Controls);
                }
                //If the exit button is clicked exit the program
                else if(endbutton.isClicked(mouseX, mouseY)){
                    System.exit(0);
                }
                
                break;
                
            case Controls:
                //When the continue button is clicked go to the next level
                if (contbut.isClicked(mouseX, mouseY)){
                    changeState(gameState.CharacterCreation);
                }
                break;
                
            case Mountain:
                //When the continue button is clicked go to the next level
                if (continuebutton.isClicked(mouseX, mouseY)){
                    changeState(gameState.Tutorial);
                    //Help Guide the user
                    System.out.println("Click the Monkeys and Trees to learn more about them!");
                }
                break;
                
            case Tutorial:
                //If the player is clicked set showInfo as true so program will draw their info
                if (player.isClicked(mouseX,mouseY)){
                    playershowInfo = !playershowInfo;
                }else{
                    //If they are not clicked do not showInfo as true
                    playershowInfo = false;
                }
                //If the arrow is clicked reset user's position and go to the next level
                if (tutback.getIndex() == 3){ //Make sure it is on the right screen
                    if (arrow.isClicked(mouseX,mouseY)){
                        writeInfo(currPlay);
                        player.setX(300);
                        changeState(gameState.cutBat);
                    }
                }
                //Reset every click so it doesnt get stuck
                clickedaMonkey = false;
                clickedaTree = false;
                
                for (int i = 0; i < tutorchars.length; i ++){
                    //If a monkey is clicked set monkey show info as true so the program will draw it
                    if (tutorchars[i] instanceof Monkey){ //Check if obj is a monkey (its in an array)
                        Monkey monkey = (Monkey) tutorchars[i]; //Downcast
                        if (monkey.getScene() == tutback.getIndex()){ //Make sure the monkey is on the same scene as the background
                            if (monkey.isClicked(mouseX, mouseY)){ 
                                currentMonkey = i; //Set current monkey to it's position in the array
                                monkshowInfo = !monkshowInfo; //Set showInfo to true
                                clickedaMonkey = true; //Set clicked monkey to true
                                monkeysClicked ++; //Since you clicked a monkey add one to the monkeys clicked count
                                
                            }else if (!clickedaMonkey){ //If you didnt click a monkey set showInfo to false
                            monkshowInfo = false;
                            }
                        }
                    }
                    //If a tree is clicked set tree show info as true so the program will draw it
                    else if (tutorchars[i] instanceof Tree){
                        //If a tree is clicked set tree show info as true so the program will draw it
                        Tree tree = (Tree) tutorchars[i]; //Downcast
                        if (tree.getScene() == tutback.getIndex()){ //Make sure the tree is on the same scene as the background
                            if (tree.isClicked(mouseX, mouseY)){
                                currentTree = i; //Set current tree to it's position in the array
                                treeshowInfo = !treeshowInfo; //Set showInfo to true
                                clickedaTree = true; //Set clicked tree to true
                                treesClicked ++; //Since you clicked a tree add one to the trees clicked count
                            }
                            else if (!clickedaTree){ //If you didnt click a tree set showInfo to false
                                treeshowInfo = false;
                            }
                        }
                }
                    
                }
                break; 
            
            case ChooseBoss:
                //If the player chooses to spare the boss go to the next scene (good ending)
                if (spare.isClicked(mouseX, mouseY)){
                    spared = true; //Set spared to true since the user spared the boss
                    writeInfo(currPlay); //Write the player's actions to the flat file
                    player.setX(10); //reset player position
                    changeState(gameState.GoodEnding);
                    
                    
                }
                //if the player didn't spare the boss go to the next scene (bad ending)
                else if (nospare.isClicked(mouseX, mouseY)){
                    spared = false; //Set spared to false since the user didnt spare the boss
                    writeInfo(currPlay); //Write the player's actions to the flat file
                    player.setX(10); //reset player position
                    changeState(gameState.BadEnding);
                }
                break;
                
            case Death:
                //If the player chooses to try again go to the previous level
                if (moveon.isClicked(mouseX, mouseY)){
                    player.setX(10); //reset their position
                    writeInfo(currPlay); //Write the player's actions to the flat file
                    player.resetHealth(); //Reset the players health to it's original value
                    bad.resetHealth(); //Reset the boss' health to it's original value
                    changeState(gameState.Tutorial);
                }
                break;
                
            case End:
                //If the user chooses to exit, exit the program
                if (exit.isClicked(mouseX, mouseY)){
                    System.exit(0);
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
            //If the user chooses to move left "a" dx = -5 (moving left) 
            case 'a':
            dx = -20;
            break;
            //If the user chooses to move right "d" dx = 5 (moving right)
            case 'd':
            dx = 20;
            break;
            }
        }
        
        player.move(dx); // Move player either left (neg) or right (pos)
        player.setY(370); //Limit the user's y since the game is a 2D scroller
        player.draw(); // Draw player after movement
        
    }
    
    /**
     * Checks if the user has pressed down keys
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
                //If the player chooses enter move onto the next scene
                if (keyCode == ENTER){
                   changeState(gameState.Opening);
                   writeInfo(currPlay);  //Write the player's name to the flat file
                }
                //If the player clicks backspace subtract their previous character (probably spelling mistake)
                else if (keyCode == BACKSPACE){
                    //Works only if the user has typed something
                    if (playerName.length() > 0){
                        //Remove the last letter
                        playerName = playerName.substring(0, playerName.length() - 1);
                    }
                }
                //If the key pressed doesnt have a code (means its one of the letters)
                else if (keyCode != CODED){
                //Add it to the player name
                playerName += key;
            }
                break;
            
            case Battle:
                //Only let the player attack when the boss is not attacking and if the attack cooldown is < = 0
                if (bossCooldown >= 100){
                    if (attackCooldown <= 0){
                        //When the g is pressed run the player attacking animation + the boss hit animation and reset the attack cooldown of the player
                        if (key == 'g' || key == 'G'){
                            player.isAttacking();
                            bad.isHit();
                            attackCooldown = 150;
                            }
                        }
                }
                break;
                
                
            case ChooseBoss:
                //Only for the boss dialogue
                switch(key){
                    //When F is pressed call on the method to go to the next line
                    case 'f':
                    case 'F':
                        bosssp.goNext();
                        break;
                    }
                break;    
        }
    }
    
    
    
    /**
     * Method to change the current state of the game
     * @param newState the new state that we want to change the game to
     */
    private void changeState(gameState newState){
       State = newState;
    }
    
    /**
     * The background logic for the game
     * Doesn't let player run off the map and changes background indexes depending on the player's position
     * For background arrays
     * @param background of the current level
     */
    private void cycleBack(Background background){
        background.displayarr(); //Draw the background 
        
        //Prevent player from going too far left
        if (player.x < -25 && background.getIndex() == 0){
            player.setX(-25);
        }
        
        //As long as it is not the first background index and the player is moving left switch scenes and change the player's position
        else if (player.x < -25 && background.getIndex() != 0){
            background.goBack();
            background.displayarr();
            player.setX(1250);
        }
        //As long as it is not the last background index and the player is moving right switch scenes and change the player's position
        else if (player.x > 1250&& background.getIndex() != (background.getSize()-1)){
            background.goNext();
            background.displayarr();
            player.setX(0);
        }
        //Prevent player from going too far right
        else if (player.x > 1250 && background.getIndex() == (background.getSize()-1)){
            player.setX(1250);
        }
    } 
    
    /**
     * Restricts player to the screen 
     * For backgrounds that are only 1 image
     * @param background 
     */
    private void cycleBack1(Background background){
        background.displayone(); //Draw the background
        //Prevent player from going too far left
        if (player.x < -25){
            player.setX(-25);//reset player position
        }
        //Prevent player from going too far right
        else if (player.x > 1250){
            player.setX(1250);//reset player position
        }
    } 
    
    
    
    /**
     * Writes level specific information to a flat file
     * @param file that the information will be written to
     */
    public void writeInfo(File file){
        //Different depending on level
        switch(State){
            case Opening:
                try{
                    FileWriter write1 = new FileWriter(file, false); //Initialize filewriter
                    PrintWriter output = new PrintWriter(write1); //Initialize printwriter
                    output.println(playerName); //Write the player's name
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
                    output.println(monkeysClicked); //Write the number of monkeys clicked
                    output.println(treesClicked); //Write the number of trees clicked
                    output.close(); //close printwriter
                    
                //If exception is thrown print out an error code
                }catch(IOException e){
                    System.out.println("IO Error");
                }
                break;
                
            case ChooseBoss:
                try{
                    FileWriter write1 = new FileWriter(file, true); //Initialize filewriter
                    PrintWriter output = new PrintWriter(write1); //Initialize printwriter
                    output.println(spared); //Writes if the player spared the boss
                    output.close(); //close printwriter
                    
                //If exception is thrown print out an error code
                }catch(IOException e){
                    System.out.println("IO Error");
                }
                break;
                
            case Death:
                try{
                    FileWriter write1 = new FileWriter(file, true); //Initialize filewriter
                    PrintWriter output = new PrintWriter(write1); //Initialize printwriter
                    output.println("Died"); //If the player has died write that to the file
                    output.close(); //close printwriter
                    
                //If exception is thrown print out an error code
                }catch(IOException e){
                    System.out.println("IO Error");
                }
                break;
                
            case BadEnding:
            case GoodEnding:
                try{
                    FileWriter write1 = new FileWriter(file, true); //Initialize filewriter
                    PrintWriter output = new PrintWriter(write1); //Initialize printwriter
                    output.println(lead.getScore()); //Write the player's score to the file
                    output.close(); //close printwriter
                    
                //If exception is thrown print out an error code
                }catch(IOException e){
                    System.out.println("IO Error");
                }
                break;
        }

    }
    
    /**
     * Loads characters from a flat file and instantiates them into objects
     * @param file that will be read
     * @param rows in the flat file
     * @param cols in the flat file
     * @return a character array that holds all the characters needed
     */
    
    public character[] loadCharacters(File file, int rows, int cols){
        //Create a 2D array to load the information from the flat file
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
        //close the scanner
        read.close();

        }catch(FileNotFoundException e){ //If the file is not found print out an error
            System.out.println("Sorry! File not Found.");
        }
        
        //Create a character array that will be returned 
        character [] characters = new character [rows];
        
        //loop through all rows of the 2D array created  
        for (int i = 0; i < num; i++){
            //If the character type is a monkey create monkey objecys
            if (chars[i][0].equalsIgnoreCase("Monkey")){
                //If the monkey has no dialogue create a special monkey
                if (chars [i][2].equalsIgnoreCase("no")){
                    characters[i] = new Monkey(this, Integer.parseInt(chars[i][4]), 440, chars[i][1], Integer.parseInt(chars[i][3]));
                }
                //if not create regular monkey objects
                else{
                    characters[i] = new Monkey(this, Integer.parseInt(chars[i][4]), 440, chars[i][1], chars[i][2], Integer.parseInt(chars[i][3]));
                }
                }
            //If the character type is a tree create tree objects
            else if (chars[i][0].equalsIgnoreCase("Tree")){
                characters[i] = new Tree(this, Integer.parseInt(chars[i][4]), 230, chars[i][1], Integer.parseInt(chars[i][3]), chars[i][2]);
            }
            }
        //return the finished array
        return characters;
    }
    
    /**
     * Draws to the screen
     */
    public void draw(){
        //Switch statement holds all of the levels and runs them depending on the level the user is on
        switch(State){
            case Title:
                //Draw the title
                title.displayTitle();
                break;
                
            case Controls:
                //Draw the background and button
                controls.displayone();
                contbut.display();
                break;
                
                
            case CharacterCreation:
                //Draw the button
                charcreate.displayone();
                //Prompt user to enter their name 
                fill(0);
                textSize(30);
                text("What is your name?", 700, 200);
                text(playerName, 700, 250);
                text("Click Enter to continue", 700, 300);
                //Change the player's name depending on what the user chose
                player.setName(playerName);
                break;
                
            
            case Opening:
                //Display th opening scenes
                Op.display();
                //Go to the next scene once the dialogue finishes
                if (text.finished){
                    changeState(gameState.Mountain);
                }
                break;
                
            case Mountain:
                //Draw the letter
                Letter.display();
                break;

            case Tutorial:
                //Draw the background + the logic
                cycleBack(tutback);
                //Draw the characters 
                tut.draw();
                //Draw player info aslong as flag is tripped
                if (playershowInfo){
                    player.displayInfo(this);
                }
                //If monkey flag is tripped ensure the monkey is on the right scene and then show info
                else if (monkshowInfo){
                    Monkey monkeys = (Monkey)tutorchars[currentMonkey];
                    if (monkeys.getScene() == tutback.getIndex()){
                    monkeys.displayInfo(this); 
                    }
                }
                //If tree flag is tripped ensure the tree is on the right scene and then show info
                else if (treeshowInfo){
                    Tree tree = (Tree)tutorchars[currentTree];
                    if (tree.getScene() == tutback.getIndex()){
                    tree.displayInfo(this);
                    }
                }
                
                movement();// call on movement method so the user can move
                
                //Once on the last background show the arrow so the player can move onto the next level
                if (tutback.getIndex() == 3){
                    arrow.display();
                }
                
                //Display the monkey dialogue
                monksp.display();
                break;

            case cutBat:
                //Show the first dialogue when counter is less than 100
                if (batcounter < 100){
                    batcounter++;
                    batbackdia1.displayone();
                }
                //Show the second dialogue when counter is less than 200
                else if (batcounter < 200){
                    batcounter += 2;
                    batbackdia2.displayone();
                }
                //once counter is > 200 go to the next scene
                else if (batcounter >= 200){
                    System.out.println("Click 'g' to attack"); //reminds user about the controls
                    changeState(gameState.Battle);
                }

                break;
                
            case Battle:
                //count down the cooldown every frame
                damageCooldown --;
                //aslong as the damage counter is less than 0 the user can take damage
                if (damageCooldown <= 0){
                    //when the boss touches the player, player takes damage and cooldown resets
                    if (bad.isCollidingWith(player)){
                        player.hit();
                        damageCooldown = 100;
                    }
                }
                
                //Count up the cooldown every frame
                bossCooldown ++;
                
                //If player's attack cooldown is > 0 count down the cooldown every frame (prevents neg numbers)
                if (attackCooldown > 0){
                    attackCooldown --;
                       
                }
                
                //If the bosses cooldown is 300 they can attack
                if (bossCooldown >= 300){
                    bad.Attack();
                    bossCooldown = 0; //refresh cooldown as soon as they attack
                }
                
                //Draw in the background 
                cycleBack1(batback);
                //draw in the boss
                bad.draw();
                //draw in the user's movement controls
                movement();
                //Check if the boss has been defeated
                bossdeath = bad.isDead();
                //If the boss has been defeated make sure it is the end of their animation and go to the next scene
                if (bossdeath && bad.getCurrentFrame() == 7){
                    changeState(gameState.ChooseBoss);
                }
                
                //If the player dies go to the death scene
                if (player.getHealth() == 0){
                   changeState(gameState.Death);
                }
                
                //Display both the player and bosses health 
                player.displayInfo(this);
                bad.displayInfo(this);

                break;
            
            case ChooseBoss:
                //Display the background
                cycleBack1(batback);
                //Draw the boss
                bad.draw();
                //draw the player
                player.draw();
                //draw the boss' dialogue
                bosssp.display();
                //checks if the boss' dialogue has finished and displays the choices to the user
                finished = bosssp.isFinished();
                if (finished){
                    spare.display();
                    nospare.display();
                }
                break;
                
                
            case Death:
                //Display the background and the button for the user to choose
                death.displayone();
                moveon.display();
                break;
                
            case GoodEnding:
                //Displays the animation
                goodEnding.displayEnding();
                if (goodEnding.getCurrentFrame() >= 39){
                    //Once the animation finished switch to the final level and run the score calculator
                    lead.runLogic(); 
                    writeInfo(scores);//Write the score of the user to the file
                    comp.setScore(lead.getScore()); //Set the final score
                    changeState(gameState.End);
                }
                break;
                
            case BadEnding:
                //Displays the animation
                badEnding.displayEnding();
                if (badEnding.getCurrentFrame() >= 39){
                    //Once the animation finished switch to the final level and run the score calculator
                    lead.runLogic();
                    writeInfo(scores); //Write the score of the user to the file
                    comp.setScore(lead.getScore()); //Set the final score
                    changeState(gameState.End);
                }
                break;
               
            case End:
                //Display the background 
                ending.displayone();
                //Draw the button
                exit.display();
                //Display the user's results 
                fill(0);
                textSize(35);
                text(player.getName(), 45, 160);
                text("You Died: " + lead.getDeaths() + " Times.", 45, 260);
                text("You End Score is: " + lead.getScore(), 45, 360);
                text("Highest Score: " + comp.runCheck(), 600, 160);
                
                //A small comment depending on what the user's end score is 
                if (lead.getScore() >= 100){
                    text("WOW! Your Score is Exceptional!", 45, 460);
                }
                
                else if (lead.getScore() <= 10){
                    text("Your Score is so low. You can play again for a higher one", 35, 460);
                }
                else{
                    text("You did average. Try again for a higher score", 35, 460);
                }
                break;
                  
        }        
    }
    
}
