/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * leaderboard class holds the score counting and death counting elements of the game
 * @author Catherine Lin
 * @since 2026-01-18
 */
public class Leaderboard {
    //Attributes 
    private File currplay; //File of the current player
    private int deaths; // Amount times that the player has died
    private int score; // score of the current player
    
    /**
     * Constructor for the leaderboard class
     * @param current holds the file of the current user
     */
    public Leaderboard (File current){
        //assign attributes 
        this.currplay = current;
    }
    
    /**
     * Loads the current player depending on their choices throughout the game
     * @return a 1D string array of their choices though out the game
     */
    public String [] loadCurrentPlayer(){
        //number to count the number of lines in the file
        int num = 0;
        //Count the lines in the file
        try{
            //Open the file for reading
            Scanner read = new Scanner(currplay);
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
        
        //String array to hold the details of the user
        String [] playerdetails = new String [num];
        //counter to loop through each indice
        int counter = 0;
        
        //Write the file into the array
        try{
            //Open the file for reading
            Scanner read = new Scanner(currplay);
            //Loop until there are no more lines in the file
            while(read.hasNext()){
                String details = read.nextLine();
                playerdetails[counter] = details; //Write the line to the array
                //Add one to the counter to move to the next indice
                counter++;
            } 
            
        read.close();//Close scanner
        
        }catch(FileNotFoundException e){ //If the file is not found print out an error
            System.out.println("Sorry! File not Found.");
        }
        //Return the finished array
        return playerdetails;
        
    }
    
    /**
     * Counts the number of times the user has died
     * @param currentPlayer a 1D string array of the user's choices  
     */
    
    public void countDeaths(String [] currentPlayer){
        for (int i = 0; i < currentPlayer.length; i++){
            if (currentPlayer[i].equalsIgnoreCase("died"))
                deaths++;
        }
    }
    
    /**
     * Gets the amount of deaths the user has had
     * @return the number of times the user has died
     */
    public int getDeaths(){
        return deaths;
    }
    
    /**
     * Get the user's score 
     * @return the user's score
     */
    public int getScore(){
        return score;
    }
    
    /**
     * Calculates the player's score depending on their choices through out the game
     * @param currentPlayer a 1D string array of the user's choices 
     */
    public void calculateScore(String[] currentPlayer) {
        if (currentPlayer.length >= 1) {
            score += Integer.parseInt(currentPlayer[1]); // Add the number of monkeys spoken to, to the score
        }
        if (currentPlayer.length >= 2) {
            score += Integer.parseInt(currentPlayer[2]); // Add the number of trees spoken to, to the score
        }
        if (currentPlayer.length >= 3 && currentPlayer[3].equalsIgnoreCase("true")) {
            score += 20; // If the player spared the boss add 20 to the score
        }
}

    
    
    /**
     * runs the methods for the score calculator and death counter
     */
    public void runLogic(){
            String[] data = loadCurrentPlayer();
            countDeaths(data);
            calculateScore(data);
    }
    
}
