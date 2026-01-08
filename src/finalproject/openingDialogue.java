/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;
import processing.core.PApplet;
import processing.core.PImage;
import java.io.File;
/**
 *
 * @author Notak
 */
public class openingDialogue extends Dialogue {
    private PImage player;
    private PImage Wukong;
    
    public openingDialogue(PApplet p, String boxpath, File d, String playerPath, String wukongPath){
        super(p, boxpath, d);
        this.player = app.loadImage(playerPath);
        this.Wukong = app.loadImage(wukongPath);

    }
    
    @Override
    public void display(){
        app.image(box, 450, 560);
        
        String speaker = lines[currentLine][0];
        String text = lines[currentLine][1];
        
        if (speaker.equalsIgnoreCase("player")){
            app.image(player, 350, 570);
        }else if (speaker.equalsIgnoreCase("wukong")){
            app.image(Wukong, 350, 570);
        }
        
        app.fill(255);
        app.textSize(20);
        app.text(text, 480, 600);
        app.text("Click 'f' to continue", 800, 680);
      
    }
    
    
    
    
}
