

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author w1563188
 */
public class Symbol implements ISymbol{
   
   protected int value;
    protected ImageIcon image;
     
    public Symbol(ImageIcon image){
        
        this.setImage(image);
        
        if(this.image.getDescription() == ("/cherry.png")){
            this.setValue(2);
        }else if(this.image.getDescription() == ("/watermelon.png")){
            this.setValue(5);
        }else if(this.image.getDescription() == ("/plum.png")){
          this.setValue(4);
        }else if(this.image.getDescription() == ("/lemon.png")){
          this.setValue(3);
        }else if(this.image.getDescription() == ("/bell.png")){
          this.setValue(6);
        }else if(this.image.getDescription() == ("/redseven.png")){
          this.setValue(7);
        }
        
    }
    
    @Override
    public ImageIcon getImage() {
        
        return this.image;
    }

    @Override
    public void setImage(ImageIcon image) {
        this.image = image;
    }

    @Override
    public void setValue(int value) {
       
       this.value = value;
        
    }

    @Override
    public int getValue() {
       
        return this.value;
    }
    
    @Override
    public int compareTo(Symbol s){
        
       int x=1;
       
       if(this.getValue() > s.getValue()){
           x=1;
       }else if(this.getValue() < s.getValue()){
           x=-1;
       }else x=0;
       
       return x;
      
        
    }
    
    
    
}
