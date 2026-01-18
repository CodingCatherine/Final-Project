/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Compare class compares a int to a int array to find the highest score
 * @author Catherine Lin
 * @since 2026-01-18
 */
public class compare {
    //Attributes 
    private File scores; //File that holds all scores gotten in the game
    private int score; //Score of the user
    
    /**
     * Constructor for the compare class
     * @param scores file that holds all scores
     * @param score the user's score
     */
    public compare(File scores, int score){
        //Assign attributes 
        this.scores = scores; 
        this.score = score;
    }
    
    /**
     * Sets the user's score
     * @param score of the user
     */
    public void setScore(int score){
        this.score = score;
    }
    
    /**
     * Reads a file and returns an integer array of all the scores 
     * @return a 1D integer array of all scores
     */
    public int [] readFile(){
        //number to count the number of lines in the file
        int num = 0;
        //Count the lines in the file
        try{
            //Open the file for reading
            Scanner read = new Scanner(scores);
            //Loop until there are no more lines in the file
            while(read.hasNext()){
                read.nextLine();
                //Add one to the count as long as there is a line
                num++;
            } 
        read.close(); //Close scanner
        
        }catch(FileNotFoundException e){ //If the file is not found print out an error
            System.out.println("Sorry! File not Found.");
        }
        
        //int array to hold the scores 
        int [] pscores = new int [num];
        //counter to loop through each indice
        int counter = 0;
        
        //Write the file into the array
        try{
            //Open the file for reading
            Scanner read = new Scanner(scores);
            //Loop until there are no more lines in the file
            while(read.hasNext()){
                String details = read.nextLine();
                pscores[counter] = Integer.parseInt(details); //Write the line to the array
                //Add one to the counter to move to the next indice
                counter++;
            } 
            
        read.close();//Close scanner
        
        }catch(FileNotFoundException e){ //If the file is not found print out an error
            System.out.println("Sorry! File not Found.");
        }
        //Return the finished array
        return pscores;    
    }
    
    /**
     * Takes an integer array and compares it to the user's score to find the highest
     * @param pscores and integer array to compare the user's score to
     * @return the highest integer
     */
    public int checkScore(int [] pscores){
        int highScore = 0;
        for (int i = 0; i < pscores.length; i ++){
            if (pscores[i] > highScore){
                highScore = pscores[i];
            }
        }
        return highScore;
    }
    
    /**
     * Runs all of the methods in the class
     * @return the highest integer found 
     */
    public int runCheck(){
        return checkScore(readFile());
    }
}




