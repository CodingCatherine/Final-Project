/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

/**
 *
 * @author Notak
 */
import processing.core.PApplet;

public class MySketch extends PApplet {

    private Player player;
    private boolean showInfo = false;
    
    public void settings(){
        size(1500, 800);
    }
    
    public void setup(){
        background(255);
        player = new Player(this, 0, 0, "Player", "images/idle/player1.png");
    }
    
    public void mousePressed(){
        if(player.isClicked(mouseX,mouseY)){
            showInfo = !showInfo;
        }else{
            showInfo = false;
        }
    }
    
    public void draw(){
        background(255);
        int dx = 0;
        int dy = 0;
        
        if (keyPressed){
            if (key == 'a') dx = -3;
            else if (key == 'd') dx = 3;
            else if (key == 'w') dy = -3;
            else if (key == 's') dy = 3;
        }
        
        player.move(dx,dy);
        player.draw();
        
        //Collide Checker
        noFill();
        stroke(255,0,0);
        rect(player.x,player.y, player.getWidth(), player.getHeight());
        
        if (showInfo){
            player.displayInfo(this);
        }
    }
    
}
