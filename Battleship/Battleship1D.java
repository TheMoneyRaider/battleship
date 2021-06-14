import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class Battleship1D implements ActionListener {
    private ArrayList<Battleship>sea;
    public int guessNum=0;
    public int totalSegs=0;
    public boolean ifWon=false;
    Random random=new Random();
        JFrame frame=new JFrame();
        JPanel title_panel=new JPanel();
        JPanel big_panel=new JPanel();
        JPanel reset_panel=new JPanel();
        JPanel button_panel=new JPanel();
        JLabel textfield =new JLabel();
        JLabel winfield =new JLabel();
        JButton resetButton = new JButton();
        int width=10;
        int height=1;
        int totalBattleships=0;
        JButton[] buttons = new JButton[height*width];
   public Battleship1D()
    {
      sea=new ArrayList<Battleship>();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setSize(1500,300);
     frame.getContentPane().setBackground(new Color(50,50,50));
     frame.setLayout(new BorderLayout());
     frame.setVisible(true);
     
     
     
        
     
        
        
        
     textfield.setBackground(new Color(25,25,25));
     textfield.setForeground(new Color(230,0,0));
     textfield.setFont(new Font("Ink Free",Font.BOLD,75));
     //font MS PMincho
     textfield.setHorizontalAlignment(JLabel.CENTER);
     textfield.setText("Battleship");
     textfield.setOpaque(true);
     
     winfield.setBackground(new Color(25,25,25));
     winfield.setForeground(new Color(230,0,0));
     winfield.setFont(new Font("Ink Free",Font.BOLD,75));
     //font MS PMincho
     winfield.setHorizontalAlignment(JLabel.CENTER);
     winfield.setText("Your Score was: ");
     winfield.setOpaque(true);
     
     
     
     title_panel.setLayout(new BorderLayout());
     title_panel.setBounds(0,0,500,100);
     reset_panel.setLayout(new BorderLayout());
     reset_panel.setBounds(500,100,600,100);
     
     resetButton.setFont(new Font("MV Boli",Font.BOLD,50));
     resetButton.addActionListener(this);
     resetButton.setFocusable(false);
     resetButton.setHorizontalAlignment(JButton.RIGHT);
     resetButton.setText("Reset");
     
     
     button_panel.setLayout(new GridLayout(height,width));
     button_panel.setBackground(new Color(150,150,150));
     
     for(int i=0;i<height*width;i++){
         buttons[i]=new JButton();
         button_panel.add(buttons[i]);
         buttons[i].setFont(new Font("MV Boli",Font.BOLD,120));
         // font Imprint MT Shadow
         buttons[i].setFocusable(false);
         buttons[i].addActionListener(this);
     }
     title_panel.add(textfield);
     reset_panel.add(resetButton);
     big_panel.add(textfield);
     big_panel.add(resetButton);
     frame.add(big_panel,BorderLayout.NORTH);
     frame.add(button_panel);
     
     
     Battleship ship = new Battleship(random.nextInt(5)+1);
     this.setShipRan(ship,10);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        for(int i=0;i<height*width;i++){
            if(e.getSource()==buttons[i] && !ifWon){
               if(guess(i,i)){
                  totalBattleships=0;
                  for(int j=0;j<this.getSea().size();j++){
                    if(this.checkLife(j)==true){
                        totalBattleships++;    
                    }
                  }
                  if(totalBattleships==0){
                  }
               }
            }
            if(e.getSource()==resetButton){
                for(int l=0;l<height*width;l++){
                   button_panel.remove(0);
                }
                for(int l=0;l<height*width;l++){
                    buttons[l]=new JButton();
                    button_panel.add(buttons[l]);
                    buttons[l].setFont(new Font("MV Boli",Font.BOLD,120));
                    // font Imprint MT Shadow
                    buttons[l].setFocusable(false);
                    buttons[l].addActionListener(this);
                }
                textfield.setText("Battleship");
                int j=this.getSea().size();
                while(j>0){
                    j--;
                    this.removeShip(0);
                  }
                Battleship ship = new Battleship(random.nextInt(5)+1);
                this.setShipRan(ship,10);
                ifWon=false;
            }
        }
    }
    public boolean guess(int target,int button){
        int negativeTotal=0;
        
        for(int j=0;j<sea.size();j++){
                for(int h = 0;h<sea.get(j).getLength();h++){
                    if(this.sea.get(j).getPos(h)==target){
                       this.sea.get(j).setNPos(h,this.sea.get(j).getPos(h)-(height*width));
                       for(int u = 0;u<sea.get(j).getLength();u++){
                            if(this.sea.get(j).getPos(u)<=-1)
                            negativeTotal++;
                       }
                       if(negativeTotal==sea.get(j).getLength()){
                           this.sea.get(j).die();
                           
                           for(int i: sea.get(j).getSegs()){
                               buttons[i+(height*width)].setText("O");
                               buttons[i+(height*width)].setForeground(new Color(0,230,0));
                           }
                           guessNum++;
                           totalSegs=0;
                           for(int l=0;l<sea.size();l++){
                            totalSegs+=sea.get(l).getLength();
                           }
                           
                           textfield.setText("Your Score was: "+((int)(((double)totalSegs*10000)/guessNum))/100.0+"pts");
                           big_panel.remove(title_panel);
                           big_panel.remove(reset_panel);
                           big_panel.add(reset_panel);
                           frame.add(big_panel,BorderLayout.NORTH);
                           ifWon=true;
                           guessNum=0;
                           return true;
                       }else{
                           buttons[button].setForeground(new Color(230,0,0));
                           buttons[button].setText("[]");
                           guessNum++;
                        }
                       return true;
                    }else if(this.sea.get(j).getPos(h)+(height*width)==target){
                        return true;
                    }
                }
        }
        buttons[button].setForeground(new Color(0,0,230));
        buttons[button].setText("X");
        guessNum++;
        return false;    
    }
    public void setShip(Battleship a,int place){
        a.setPos(place);
        sea.add(a);
    }  
    public void setShipRan(Battleship a,int place){
        a.setPosRan(place);
        sea.add(a);
    } 
    public int getSeaSize(){
        return sea.size();
    }
    public Battleship getShip(int i){
        return sea.get(i);
    }
    public ArrayList getSea(){
        return sea;
    }
    public boolean checkLife(int j){
        return this.sea.get(j).getLife(); 
    }
    public void removeShip(int i){
        sea.remove(i);
    }
    
}