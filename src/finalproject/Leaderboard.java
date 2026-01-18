/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 *
 * @author Notak
 */
public class Leaderboard {
    private File currplay;
    private int deaths;
    private int score;
    
    public Leaderboard (File current){
        this.currplay = current;
    }
    
    public String [] loadCurrentPlayer(){
        //number to count the number of lines in the file
        int num = 0;
        try{
            //Open the file for reading
            Scanner read = new Scanner(currplay);
            //Loop until there are no more lines in the file
            while(read.hasNext()){
                read.nextLine();
                num++;
            } 
        read.close();
        }catch(FileNotFoundException e){ //If the file is not found print out an error
            System.out.println("Sorry! File not Found.");
        }
        
        String [] playerdetails = new String [num];
        int counter = 0;
        
        try{
            //Open the file for reading
            Scanner read = new Scanner(currplay);
            //Loop until there are no more lines in the file
            while(read.hasNext()){
                String details = read.nextLine();
                playerdetails[counter] = details;
                counter++;
            } 
        read.close();
        }catch(FileNotFoundException e){ //If the file is not found print out an error
            System.out.println("Sorry! File not Found.");
        }
        return playerdetails;
        
    }
    
    public void countDeaths(String [] currentPlayer){
        for (int i = 0; i < currentPlayer.length; i++){
            if (currentPlayer[i].equalsIgnoreCase("died"))
                deaths++;
        }
    }
    
    public int getDeaths(){
        return deaths;
    }
    
    public int getScore(){
        return score;
    }
    
    public void calculateScore(String[] currentPlayer) {
        if (currentPlayer.length >= 1) {
            score += Integer.parseInt(currentPlayer[1]);
        }
        if (currentPlayer.length >= 2) {
            score += Integer.parseInt(currentPlayer[2]);
        }
        if (currentPlayer.length >= 3 && currentPlayer[3].equalsIgnoreCase("true")) {
            score += 10;
        }
}

    
    
    
    public void runLogic(){
            String[] data = loadCurrentPlayer();
            countDeaths(data);
            calculateScore(data);
    }
    
}
