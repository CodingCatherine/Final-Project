/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;
import processing.core.PApplet;
import processing.core.PImage;

/**
 *
 * @author Notak
 */
public class bossSpeech {
    private PImage bossIcon;
    private PApplet app;
    private String [] words = new String []{
        "It seems that I have underestimated you.",
        "You are stronger than I thought",
        "You are also nothing like your master",
        "I admit that I was wrong", 
        "Could you spare me this one time?"
    };
    
    private String currentDialogue = "";
    private PImage dia;
    private int currentLine = 0;
    private boolean finished = false;
    
    public bossSpeech (PApplet p){
        this.app = p;
        this.bossIcon = p.loadImage("images/badguy/icon.png");
        this.dia = p.loadImage("images/textbox/talkbox.png");
    }
    
    public void goNext(){
        if (currentLine < words.length-1){
            currentLine +=1 ;
        }else if (currentLine == words.length - 1){
            finished = true;
        }
    }
    
    public void display(){
        app.image(dia, 450, 560);
        String text = words[currentLine];
        
        app.image(bossIcon, 350, 570);
        app.fill(0);
        app.textSize(20);
        app.text(text, 480, 600);
        app.text("Click 'f' to continue", 800, 680);
    }
    
    public boolean isFinished(){
        return finished;
    }

}

