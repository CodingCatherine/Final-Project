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
public class monkeySpeech{
    private PImage monkeyIcon1;
    private PImage monkeyIcon2;
    private PApplet app;
    private character [] characters;
    private character player;
    private String currentDialogue = "";
    private Background background;
    private PImage dia;

    
    public monkeySpeech(PApplet p, String monkeyPath, character [] characters, character player, Background background, String boxPath){
        this.app = p;
        this.monkeyIcon1 = p.loadImage(monkeyPath);
        this.monkeyIcon2 = p.loadImage("images/trial 1/heheIcon.png");
        this.characters = characters;
        this.player = player;
        this.background = background;
        
        dia = app.loadImage(boxPath); 
    }
    
    public void display(){
        for (int i = 0; i < characters.length; i++){
            if (characters[i] instanceof Monkey){
                Monkey monkey = (Monkey) characters[i];
                if (monkey.getScene() == background.getIndex()){
                    if (player.isCollidingWith(monkey)){
                        currentDialogue = monkey.getText();
                        if (monkey.getName().equalsIgnoreCase("helen")){
                            app.image(monkeyIcon2, 350, 570);
                        }
                        else{
                            app.image(monkeyIcon1, 350, 570);
                        }
                        app.image(dia, 450, 560);
                        app.fill(0);
                        app.textSize(20);
                        app.text(currentDialogue, 465 ,600);
                    }
                }
                
            }
        }
        
        
    }
    
    
}
