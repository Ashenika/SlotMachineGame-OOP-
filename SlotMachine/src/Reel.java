
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Anestis Sakerlis
 */
public class Reel{
    
   Random generator = new Random();
    int i = generator.nextInt(6);
    protected String[] images={"/bell.png","/cherry.png","/lemon.png","/plum.png","/redseven.png","/watermelon.png"};
    protected ImageIcon[] myImages = new ImageIcon[images.length];
    protected Symbol[] mySymbol = new Symbol[myImages.length];
    protected JLabel l;
    protected myThread r;
    
    public Reel(JLabel l){
        this.l = l;
        r = new myThread(l,this);

// creating the ImageIcon array through the string array which has just the names of the pictures
       for(int x=0; x< images.length; x++){
         
         try{
           myImages[x] = new ImageIcon(images[x]);
         }catch(NullPointerException e){
             System.out.println("Could not load Pictures!");
         }
       } 
    
        
    //Shuffling the array with the names of the pictures   
    Random rnd = ThreadLocalRandom.current();
    for (int i = myImages.length - 1; i > 0; i--)
    {
      int index = rnd.nextInt(i + 1);
      // Simple swap
      ImageIcon a = myImages[index];
      myImages[index] = myImages[i];
      myImages[i] = a;
    }
    
    // create the 6 symbols for each reel
   for(int x=0; x<myImages.length; x++){
       
       mySymbol[x] = new Symbol(myImages[x]);
   }
        
        
    }
    
   public Symbol[] Spin(){

    return this.mySymbol;

   }   
  
   public String getPictureName(int x){
       String a = this.mySymbol[x].image.getDescription();
       String name = a.substring(1,a.length() - 4);
       name = name + "s";
       return name;
   }  
   
  
   
   
   
   
   
  
    
   
    
    
    
    
    
    

  
    

  
    

  
   

   
    
    
}
