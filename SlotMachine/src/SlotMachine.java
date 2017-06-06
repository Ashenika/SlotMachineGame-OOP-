import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Anestis Sakerlis
 */
public class SlotMachine extends JFrame{
    private JButton saveStats = new JButton("Save Stats");
    private boolean spinned = false,betMaxUsed= false,resetReady= false,r1Stopped= false,r2Stopped= false,r3Stopped= false;//****
    private JLabel l1 = new JLabel();
    private JLabel l2 = new JLabel();
    private JLabel l3 = new JLabel();
    private Reel reel1 = new Reel(l1);//***********
    private Reel reel2 = new Reel(l2);
    private Reel reel3 = new Reel(l3);
    private double creditsAverage =0;//****
    private int wins =0, loses =0,totalValue = 0,credits = 10,bet = 0; //***
    private String creditText = "Your credit: ";
    private JButton addCoin = new JButton("Add Coin: ");
    private JLabel creditArea = new JLabel(creditText+credits);//******
    private JButton betOne = new JButton("Bet One");
    private String bettingText = "You are Betting: ";
    private JLabel betting = new JLabel(bettingText+bet);
    private JButton betMax = new JButton("Bet Max");
    private JButton spinThis = new JButton("SPINNNNN THISSSSSS!");
    private JButton reset = new JButton("Reset");
    private JButton stats = new JButton("Statistics");
    private int creditsNetted =0,creditsLost =0,totalGames =0,creditsWon = 0;//*****
    private NumberFormat formatter1 = new DecimalFormat("#0.00");
    private String status;
 
    public static void main(String args[]) throws IOException{
        SlotMachine myMainGUI = new SlotMachine();
    }
    public SlotMachine(int wins,int loses,int totalGames,double creditsAverage,int creditsWon,int creditsLost){
                if(creditsWon>creditsLost){
                    status = "Average Status: Winning!";
                }else if(creditsLost > creditsWon){
                    status = "Average Status: Lossing!";
                }else status = "Not status yet!";
                
                setSize(1000,500);
                    setDefaultCloseOperation(HIDE_ON_CLOSE);
                    setLocationRelativeTo(null);
                    setVisible(true);
                    MouseAdapter l= new MouseHandler(totalGames,wins,loses,creditsAverage);
                    saveStats.addMouseListener(l);            
        
                    JPanel statss = new JPanel(new BorderLayout());            
                    JPanel stats = new JPanel(new GridLayout(3,2));
                    
                    JLabel totalGames1 = new JLabel("Total Games: ");
                    JLabel totalWins1 = new JLabel("Total Wins: ");
                    JLabel totalLoses1 = new JLabel("Total Loses: ");
                    
//*************************for Statistics
                    JTextField totalgames = new JTextField(totalGames);
                    totalgames.setBackground(Color.BLUE);
                    totalgames.setEditable(false);
                    totalgames.setPreferredSize(new Dimension(totalGames,50));
                    
                    JTextField totalWins = new JTextField(wins);
                    totalWins.setBackground(Color.GREEN);
                    totalWins.setEditable(false);
                    totalWins.setPreferredSize(new Dimension(wins, 50));
                    
                    JTextField totalLoses = new JTextField(loses);
                    totalLoses.setBackground(Color.RED);
                    totalLoses.setEditable(false);
                    totalLoses.setPreferredSize(new Dimension(loses,50));
                    

                    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    /* I decided to put the JComponents which are JTextFields, in FlowLayout managers 
                    because in the other managers i couldnt resize 
                    the textfields according to wins or loses or totalGames via setting the columns as a fixed number
                    */
                    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    
                    JPanel totalG = new JPanel(new FlowLayout());
                    totalG.add(totalGames1);
                    totalG.add(totalgames);
                    totalG.add(new JLabel("(" + Integer.toString(totalGames) + ")"));
                    JPanel totalW = new JPanel(new FlowLayout());
                    totalW.add(totalWins1);
                    totalW.add(totalWins);
                    totalW.add(new JLabel("(" + Integer.toString(wins) + ")"));
                    JPanel totalL = new JPanel(new FlowLayout());
                    totalL.add(totalLoses1);
                    totalL.add(totalLoses);
                    totalL.add(new JLabel("(" + Integer.toString(loses) + ")"));
                    
                    stats.add(totalG);
                    stats.add(totalW);
                    stats.add(totalL);
                    statss.add(stats, BorderLayout.NORTH);
                    
                    JPanel saveStatsPanel = new JPanel(new GridLayout(2,3));
                    saveStatsPanel.add( new JLabel());
                    saveStatsPanel.add(saveStats);
                    for(int x=0; x<4; x++){
                      saveStatsPanel.add( new JLabel());
                    }
                    statss.add(saveStatsPanel, BorderLayout.SOUTH);
                    JPanel stats2 = new JPanel(new GridLayout(2,1));
                   try{
                    stats2.add(new JLabel("Average of credits netted per game: " + formatter1.format(creditsAverage)));
                   }catch(ArithmeticException p){
                        stats2.add(new JLabel("Average of credits netted per game: 0"));
                   }
                   stats2.add(new JLabel(status));
                   statss.add(stats2, BorderLayout.CENTER);
                 
                   
                    add(statss);
                    setTitle("Statistics Page");
                    
    }
    public SlotMachine() throws IOException{
      setTitle("Slot Machine Game");
     setDefaultCloseOperation(EXIT_ON_CLOSE);
     setSize(1438,738);
     setLocationRelativeTo(null);
     setVisible(true);
     setLayout(new BorderLayout());
     ImageIcon myBackground = new ImageIcon(getClass().getResource("/bg.jpg"));
   //**********background
 
 
     setContentPane(new JLabel(myBackground));
    
    
     setLayout(new BorderLayout());
     Font font=new Font("Arial Rounded MT Bold",Font.BOLD,16);
     MouseAdapter l= new MouseHandler();
      
      JPanel p1 = new JPanel();
      p1.setLayout(new GridLayout(5,5,15,15));
      for(int x=0; x<5; x++){
          p1.add(new JLabel());
      }
      p1.add(creditArea);
      creditArea.setForeground(Color.GREEN);
      p1.add(betting);
        
      betting.setForeground(Color.GREEN);
      p1.add(addCoin);
      creditArea.setFont(font);
      betting.setFont(font);
      addCoin.setFont(font);
      betOne.setFont(font);
      addCoin.setForeground(Color.blue);
      betOne.setForeground(Color.red);
      addCoin.addMouseListener(l);
      p1.add(betOne);
      betOne.addMouseListener(l);
      p1.add(betMax);
      betMax.setFont(font);
      betMax.addMouseListener(l);
      for(int x=0; x<15; x++){
            p1.add(new JLabel());
      }
      
     //*********not included***uda tika!! 
      //RPanel
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(1,5,50,50));
        p2.add(new JLabel());
        l1.setIcon(new ImageIcon(getClass().getResource("/redseven.png")));
        l2.setIcon(new ImageIcon(getClass().getResource("/redseven.png")));
        l3.setIcon(new ImageIcon(getClass().getResource("/redseven.png")));
        p2.add(l1);
        p2.add(l2);
        p2.add(l3);
        
        p2.add(new JLabel());
        l1.addMouseListener(l);
        l2.addMouseListener(l);
        l3.addMouseListener(l);
        JPanel p3 = new JPanel();
        p3.setLayout(new GridLayout(4,5,15,15));
        p3.add(new JLabel());
        p3.add(new JLabel());
        p3.add(spinThis);
        spinThis.setFont(font);
        spinThis.addMouseListener(l);
        for(int x=0; x<4; x++){
            p3.add(new JLabel());
        }
        p3.add(stats);
        stats.addMouseListener(l);
        stats.setFont(font);
        for(int x=0; x<4; x++){
            p3.add(new JLabel());
        }
        p3.add(reset);
        reset.addMouseListener(l);
        reset.setFont(font);
        for(int x=0; x<6; x++){
            p3.add(new JLabel());
        }
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(p1,BorderLayout.NORTH);
        p.add(p2, BorderLayout.CENTER);
        p.add(p3,BorderLayout.SOUTH);
        
        add(p);
        p.setOpaque(false);
        p1.setOpaque(false);
        p2.setOpaque(false);
        p3.setOpaque(false);
        setVisible(true);
    }
    
   public class MouseHandler extends MouseAdapter{
        private int totalGamess=0,winss = 0,losess = 0;
        private double creditsAveragee=0;
        // mouse Handler for the Stats window
        public MouseHandler(int totalGames, int wins, int loses,double creditsAverage){
            this.totalGamess = totalGames;
            this.winss = wins;
            this.losess = loses;
            this.creditsAveragee = creditsAverage;
        }
        // mouse Handler for the slotMachine window
        public MouseHandler(){
        }
        public void mouseClicked(MouseEvent e) {
                if(e.getSource() == addCoin){
                    addCoin();
                }
                if(e.getSource() == betOne){
                    betOne();
                }
                if(e.getSource() == betMax){
                    betMax();
                }
                // if its not already spinning
                if(e.getSource() == spinThis && !spinned){
                   // the first time obviously the threads will .start();
                   if(totalGames ==0){
                   if(bet >0){
                   JOptionPane.showMessageDialog(null,"You currently have " + credits + " credits and betting " + bet + " credits! ~~~~~~~GOOD LUCK~~~~~~");
                   //start the threads
                   reel1.r.start();
                   reel2.r.start();
                   reel3.r.start();
                   // set the "switch" true
                   spinned = true;
                   }else JOptionPane.showMessageDialog(null,"Sorry but you must bet first! If you don't have credits, help yourself and add! :)");
                    // after the first time they .myResume();
                    }else{
                       if(bet > 0){
                       JOptionPane.showMessageDialog(null,"You currently have " + credits + " credits and betting " + bet + " credits! ~~~~~~~GOOD LUCK~~~~~~");
                       reel1.r.myResume();
                       reel2.r.myResume();
                       reel3.r.myResume();
                       spinned = true;
                       }else JOptionPane.showMessageDialog(null,"Sorry but you must bet first! If you don't have credits, help yourself and add! :)");
                   }
                }//l1 kynne labels
                if((e.getSource() == l1 || e.getSource()== l2 || e.getSource()== l3 ) && spinned){
                   /* Only if it is spinning it will suspend the threads.
                   Otherwise 1) its not correct and 
                   2) in the initial reels if you press on them it will count as you have played without spinning   
                   */
                   // call mySuspend method for each thread in order to wait.
                   reel1.r.mySuspend();
                   reel2.r.mySuspend();
                   reel3.r.mySuspend();
                   
                   // switches true for control
                   r1Stopped= true;
                   r2Stopped = true;
                   r3Stopped = true;
                }
                //when all of them are stopped lets calculate
                if(r1Stopped && r2Stopped && r3Stopped){
                    checkResults();
                }
                if(e.getSource() == reset){//**************
                    resetButton();
                }
                if(e.getSource() == stats){
                    if(totalGames>0){
                    creditsAverage = (double) creditsNetted / totalGames;
                    }else {
                        creditsAverage = 0;
                    }
                    SlotMachine myFrameSs = new SlotMachine(wins,loses,totalGames,creditsAverage,creditsWon,creditsLost);
                }
                if(e.getSource() == saveStats){
                 saveStats(totalGamess, winss, losess, creditsAveragee,status);
                }
    }//********************
}
   
   
    public void betMax(){
        if(!betMaxUsed && !spinned){
                    if(credits >0){
                        if(credits >= 3){
                            bet = bet + 3;
                            credits = credits - 3;
                            betMaxUsed = true;
                        }else if(credits < 3){
                            bet = bet + credits;
                            credits = 0;
                            betMaxUsed = true;
                        }
                    betting.setText(bettingText + bet);
                    creditArea.setText(creditText+credits);
                    }else {
                        JOptionPane.showMessageDialog(null,"Sorry but you dont have more credits");
                    }
                    }else if((betMaxUsed && spinned) || (!betMaxUsed && spinned)){
                        JOptionPane.showMessageDialog(null,"The game has already started! You cannot bet coins while the reels are spinning. Please try again later!");
                        
                    }else if(betMaxUsed && credits <=0){
                        JOptionPane.showMessageDialog(null,"Sorry but you dont have more credits");
                        
                    }else if(betMaxUsed){
                       JOptionPane.showMessageDialog(null,"Sorry but you cannot bet max more than once! You have to bet by betting one !");
                    }
    }
    public void betOne(){
    
            if(spinned){
                JOptionPane.showMessageDialog(null,"The game has already started! You cannot bet coins while the reels are spinning. Please try again later!");
            }else if(credits == 0){
                JOptionPane.showMessageDialog(null,"Sorry but you dont have more credits");
            }else if(credits > 0 && !spinned){
             credits = credits - 1;
             creditArea.setText(creditText + credits);
             bet = bet + 1;
             betting.setText(bettingText + bet);
            }
    }
    public void addCoin(){
        if(!spinned){
        credits = credits+1;
        creditArea.setText(creditText+credits);
        }else JOptionPane.showMessageDialog(null,"The game has already started! You cannot add coins while the reels are spinning. Please try again later!");
    }
    public void resetButton(){
        // Reset button conditions in order not to reset when the reels are moving!
        if((bet > 0 && wins == 0 && loses == 0 && !spinned) || (totalGames>0 && bet>0 && !spinned)){
            credits = credits+ bet;
            bet = 0;
            betting.setText(bettingText + bet);
            creditArea.setText(creditText+credits);
            betMaxUsed = false;
            JOptionPane.showMessageDialog(null,"Your betting credits have been added back to your credits succesfully!");
        }else if(spinned || (spinned && totalGames>0)){
             JOptionPane.showMessageDialog(null,"The game has already started! You cannot reset the coins while the reels are spinning. Please try again later!");
        }else if(bet == 0){
            JOptionPane.showMessageDialog(null,"There is nothing to reset from the bet area!");
        }
    }
    public void checkResults(){
                    // just a boolean to display appropriate message if it never becomes true, in other words if you lose!;
                    boolean ReelsEqual = false;
                    // rX is threadX. We check if the reels' symbols match with any other reel's.
                    if(reel1.r.mySymbol[reel1.r.xx].compareTo(reel2.r.mySymbol[reel2.r.xx]) == 0 && reel1.r.mySymbol[reel1.r.xx].compareTo(reel3.r.mySymbol[reel3.r.xx]) == 0){
                        creditsNetted = creditsNetted + bet;
                        wins++;
                        totalValue = reel1.r.mySymbol[reel1.r.xx].getValue()*bet;
                        credits = bet + totalValue + credits;
                        creditsWon = creditsWon + totalValue - bet;
                        bet = 0;
                        betting.setText(bettingText + bet);
                        creditArea.setText(creditText+credits);
                        ReelsEqual = true;
                        JOptionPane.showMessageDialog(null,"Congratulations! You have won " + totalValue + " credits with 3x " + reel1.getPictureName(reel1.r.xx) + " in the 1st,2nd and 3rd reel!"); 
                    }else if(reel1.r.mySymbol[reel1.r.xx].compareTo(reel2.r.mySymbol[reel2.r.xx]) == 0){
                        creditsNetted = creditsNetted + bet;
                        wins++;
                        totalValue = reel1.r.mySymbol[reel1.r.xx].getValue()*bet;
                        credits = bet + totalValue + credits;
                        creditsWon = creditsWon + totalValue - bet;
                        bet = 0;
                        betting.setText(bettingText + bet);
                        creditArea.setText(creditText+credits);
                        ReelsEqual = true;
                        JOptionPane.showMessageDialog(null,"Congratulations! You have won " + totalValue + " credits with 2x " + reel1.getPictureName(reel1.r.xx) + " in the 1st and 2nd reel!"); 
                    }else if(reel2.r.mySymbol[reel2.r.xx].compareTo(reel3.r.mySymbol[reel3.r.xx]) == 0){
                        creditsNetted = creditsNetted + bet;
                        wins++;
                        totalValue = reel2.r.mySymbol[reel2.r.xx].getValue()*bet;
                        credits = bet + totalValue +credits;
                        creditsWon = creditsWon + totalValue - bet;
                        bet = 0;
                        betting.setText(bettingText + bet);
                        creditArea.setText(creditText+credits);
                        ReelsEqual = true;
                        JOptionPane.showMessageDialog(null,"Congratulations! You have won " + totalValue + " credits with 2x " + reel2.getPictureName(reel2.r.xx) + " in the 2nd and 3rd reel!"); 
                    }else if(reel1.r.mySymbol[reel1.r.xx].compareTo(reel3.r.mySymbol[reel3.r.xx]) == 0){
                        creditsNetted = creditsNetted + bet;
                        wins++;
                        totalValue = reel1.r.mySymbol[reel1.r.xx].getValue()*bet;
                        credits = bet + totalValue + credits;
                        creditsWon = creditsWon + totalValue - bet;
                        bet = 0;
                        betting.setText(bettingText + bet);
                        creditArea.setText(creditText+credits);
                        ReelsEqual = true;
                        JOptionPane.showMessageDialog(null,"Congratulations! You have won " + totalValue + " credits with 2x " + reel1.getPictureName(reel1.r.xx) + " in the 1st and 3rd reel!"); 
                    }
                    
                    
                if(!ReelsEqual){
                  loses++;
                  creditsNetted = creditsNetted + bet;
                  creditsLost = creditsLost + bet;
                  JOptionPane.showMessageDialog(null,"Sorry but you did not win! You lost " + bet + " credits!"); 
                  bet = 0;
                  betting.setText(bettingText+bet);
                }
                 // total games ++;   
                 totalGames++;
                 
                 //handling booleans for "Setting" the game ready to run again!
                 betMaxUsed = false;
                
                 r1Stopped = false;
                 r2Stopped = false;
                 r3Stopped = false;
                 resetReady = true;
                 spinned = false;
                 // the xx's in order to access the ImageIcon array;s icons
                 reel1.r.xx = -1;
                 reel2.r.xx = -1;
                 reel3.r.xx = -1;
    }
    public void saveStats(int totalGames, int wins,int loses, double creditsAverage,String status){
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy kk-mm");
                    Calendar cal = Calendar.getInstance();
                    String date = dateFormat.format(cal.getTime());  
                    String filename = date+".txt";

                        try {
                        BufferedWriter outputWriter = null;
                        outputWriter = new BufferedWriter(new FileWriter(filename));
                        outputWriter.write("Total Games: " + totalGames);
                        outputWriter.newLine();
                        outputWriter.write("Total Wins: " + wins);
                        outputWriter.newLine();
                        outputWriter.write("Total Loses: " + loses);
                        outputWriter.newLine();
                        outputWriter.write("Average of credits netted per game: " + this.formatter1.format(creditsAverage));
                        outputWriter.newLine();
                        outputWriter.write(status);
                        
                        
                        outputWriter.flush();
                        outputWriter.close();
                        JOptionPane.showMessageDialog(null,"Your stats have been saved succesfully");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null,"The system could not save your stats!");
                    }
    }
   
    
    
    }
    


