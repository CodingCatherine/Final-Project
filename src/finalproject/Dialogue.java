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
 *
 * @author Notak
 */

public class Dialogue {
    protected PApplet app;
    protected PImage box;
    protected File dia;
    private int words;
    
    protected String [][] lines;
    protected int currentLine = 0;
    
    public boolean finished = false;
    
    public Dialogue(PApplet p, String boxpath, File d, int words){
        this.app = p;
        this.box = app.loadImage(boxpath);
        this.dia = d;
        this.words = words;
        
    }
    
    public String[][] readDialogue(File file){
        String [][] lines  = new String [words][2];
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
                    lines[num][i] = data[i].trim();
                }
            //go to the next row 
            num++;
            } 
            read.close();
        }catch(FileNotFoundException e){ //If the file is not found print out an error
            System.out.println("Sorry! File not Found.");
        }
        return lines;
    }
    
    public void display(){
        app.image(box, 50, 400);
    }
    
    public void load(){
       this.lines = readDialogue(dia);
    }
    
    public void goNext(){
        if (currentLine < lines.length-1){
            currentLine +=1 ;
        }else if (currentLine == lines.length - 1){
            finished = true;
        }
    }
    
}
