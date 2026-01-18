/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Dialogue class holds the dialogue in the game
 * @author Catherine Lin
 * @since 2026-01-18
 */

public class Dialogue {
    //Attributes 
    protected PApplet app; //holds PApplet
    protected PImage box; //Holds the dialogue box
    protected File dia; //Holds the file that the dialogue is in
    private int words; //holds the number of words in the text file
    
    //Array to hold the dialogue along with the person that is speaking
    protected String [][] lines;
    //Holds the current line that the dialogue is at
    protected int currentLine = 0;
    
    //Checks if the dialogue has finished
    public boolean finished = false;
    
    /**
     * Constructor for dialogue objects
     * @param p holds PApplet
     * @param boxpath holds the imagepath of the dialogue box
     * @param d holds the dialogue file
     * @param words holds the number of lines in the file
     */
    public Dialogue(PApplet p, String boxpath, File d, int words){
        //Assign attributes 
        this.app = p;
        this.box = app.loadImage(boxpath);
        this.dia = d;
        this.words = words;
        
    }
    
    /**
     * Reads a file and returns the contents of it in a 2D string array
     * @param file that holds the dialogue
     * @return a 2D string array with the speaker and dialogue line 
     */
    public String[][] readDialogue(File file){
        //Create a 2D array to hold the dialogue
        String [][] lines  = new String [words][2];
        //num acts as the counter to cycle through indices 
        int num = 0; 
        //Read the file and write to the 2D array
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
                    lines[num][i] = data[i].trim(); //Write data to the file
                }
            //go to the next row 
            num++;
            } 
            read.close(); //Close the scanner
            
        }catch(FileNotFoundException e){ //If the file is not found print out an error
            System.out.println("Sorry! File not Found.");
        }
        //Return the array
        return lines;
    }
    
    /**
     * Display the dialogue box
     */
    public void display(){
        app.image(box, 450, 560);
    }
    
    /**
     * Load the lines using the method created above
     */
    public void load(){
       this.lines = readDialogue(dia);
    }
    
    /**
     * Go to the next line of dialogue
     */
    public void goNext(){
        //Move onto the next line until you get to the last line in which set 
        //Finished to true since all dialogue has been read
        if (currentLine < lines.length-1){
            currentLine +=1 ;
        }else if (currentLine == lines.length - 1){
            finished = true;
        }
    }
    
}
