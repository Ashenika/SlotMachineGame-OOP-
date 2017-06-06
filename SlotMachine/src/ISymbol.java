
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author w1563188
 */
public interface ISymbol {
 
    public ImageIcon getImage();
    public void setImage(ImageIcon image);
    public void setValue(int value);
    public int getValue();
    public int compareTo(Symbol s);
    
    
}
