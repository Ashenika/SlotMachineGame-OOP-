
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class myThread extends Thread{
 
   int xx = -1;
   JLabel l;
   Reel r;
   boolean suspended = false, stopped = false;
   Symbol[] mySymbol;
   
    public myThread(JLabel l, Reel r){
        
        this.l = l;
        this.r = r;
        //takes the symbol list of each reel
        this.mySymbol = this.r.Spin();
    }

    @Override
    public void run() {
        while(true){
        // when we suspend it it will wait    
        synchronized(this){
        while(this.suspended){
            try {
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(myThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
        // if we stop it it will be terminated
        if(this.stopped){
            break;
        }
        
        this.xx++;
        
        if(this.xx == 6){
        this.xx = 0;
        }
        
        this.l.setIcon(new ImageIcon(getClass().getResource(this.mySymbol[this.xx].getImage().getDescription()))); 
            
        try {
              Thread.sleep(100);
              } catch (InterruptedException e) {
              e.printStackTrace();
              }
        }
     
    }
    
    
    
    synchronized void myStop(){
        this.stopped = true;
        this.suspended = false;
        this.notify();
    }
    
    synchronized void mySuspend(){
        this.suspended = true;
        
    }
    synchronized void myResume(){
        this.suspended = false;
        this.notify();
    }
}
