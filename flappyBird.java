import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Random;

public class flappyBird implements ActionListener,MouseListener,KeyListener
{
    
    public static flappyBird FlappyBird;
    public final int WIDTH=1200,HEIGHT=650;
    public Renderer renderer;
    public Rectangle bird;
    public Random random=new Random();
    public int ticks,yMotion,score,highScore;
    public boolean gameOver=false,started=false;
    
    public ArrayList<Rectangle>columns;
    
    public flappyBird(){
        JFrame jFrame=new JFrame();
        Timer timer=new Timer(20,this);
        renderer =new Renderer();
        jFrame.add(renderer);
        jFrame.addKeyListener(this);
        jFrame.addMouseListener(this);
        jFrame.setTitle("FlappyBird");
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        jFrame.setSize(WIDTH,HEIGHT);
        jFrame.setResizable(true);
        jFrame.setVisible(true);
        bird=new Rectangle(WIDTH / 2-10,HEIGHT / 2-10,20,20);
        
        columns=new ArrayList<Rectangle>();
        addColumn(true);
        addColumn(true);
        addColumn(true);
        addColumn(true);
        
        timer.start();
    }
    public void addColumn(boolean start){
        int space=300;
        int width=100;
        int height=50+random.nextInt(300);
        if(start){
            columns.add(new Rectangle(WIDTH+width+columns.size()*300,HEIGHT-height-120,width,height));
            columns.add(new Rectangle(WIDTH+width+(columns.size()-1)*300,0,width,HEIGHT-height-space));
        }else{
            columns.add(new Rectangle(columns.get(columns.size()-1).x+600,HEIGHT-height-120,width,height));
            columns.add(new Rectangle(columns.get(columns.size()-1).x,0,width,HEIGHT-height-space));
        }
    }
    
    
    public void paintColumn(Graphics g,Rectangle column){
        g.setColor(Color.green.darker());
        g.fillRect(column.x,column.y,column.width,column.height);
    }
    
    public void jump(){
        if(gameOver){
            bird=new Rectangle(WIDTH /2-10,HEIGHT / 2-10,20,20);
            yMotion=0;
            score=0;
            gameOver=false;
            
            columns.clear();
            addColumn(true);
            addColumn(true);
            addColumn(true);
            addColumn(true);
        }
        if(!started){
            started=true;
        }else if(!gameOver){
            if(yMotion>0)
            yMotion=0;
        }
        yMotion-=10;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        int speed=10;
        ticks++;
        if(started){
            for(int i=0;i<columns.size();i++){
                Rectangle column=columns.get(i);
                column.x-=speed;
            }
            if(ticks%2==0&&yMotion<15){
                yMotion+=2;
            }
            
            for(int i=0;i<columns.size();i++){
                Rectangle column=columns.get(i);
                if(column.x+column.width<0){
                    columns.remove(column);
                    if(column.y==0){
                        addColumn(false);
                    }
                }
            }
            bird.y+=yMotion;
            for(Rectangle column:columns){
                if(column.y==0&&bird.x+bird.width/2>column.x+column.width/2-10&&bird.x+bird.width/2<column.x+column.width/2+10){
                    score ++;
                }
                if(score>highScore)
                highScore=score;
             
                
                if(column.intersects(bird)){
                    gameOver=true;
                    bird.x=column.x-bird.width;
                }
                if(bird.y>HEIGHT-120||bird.y<0){
                    bird.y=HEIGHT-125-bird.height;
                    
                    gameOver=true;
                }
                
                
            }
        }
        
        renderer.repaint();
    }
    
    public void repaint(Graphics g){
        
        g.setColor(Color.cyan);
        g.fillRect(0,0,WIDTH,HEIGHT);
        
        g.setColor(Color.GREEN.darker());
        g.fillRect(0,HEIGHT-120,WIDTH,120);
        
        g.setColor(Color.YELLOW.brighter());
        g.fillRect(1,HEIGHT-123,WIDTH,7);
        
        g.setColor(Color.WHITE);
        g.fillRoundRect(40,95,180,100,180,100);
        g.setColor(Color.WHITE);
        g.fillRoundRect(125,90,180,100,180,100);
        g.setColor(Color.WHITE);
        g.fillRoundRect(80,55,160,100,180,100);
        
        g.fillRoundRect(800,80,180,100,180,100);
        g.setColor(Color.WHITE);
        g.fillRoundRect(920,60,180,100,180,100);
        g.setColor(Color.WHITE);
        g.fillRoundRect(850,30,160,100,180,100);
        
        g.setColor(Color.ORANGE.brighter());
        g.fillOval(40,50,100,100);
        
        g.setColor(Color.orange);
        g.drawArc(41,51,99,99,0,360);
        
        g.setColor(Color.MAGENTA.darker().darker());
        g.fillRect(bird.x,bird.y,bird.width,bird.height);
        
        for(Rectangle column:columns){
            paintColumn(g,column);
        }
        
        g.setColor(Color.darkGray);
        g.setFont(new Font("Comic_Sans",2,100));
        
        if(!started){
            g.drawString("Click To Start",150,HEIGHT/2-50);
        }
        
        if(gameOver){
            g.drawString("Game Over",350,HEIGHT/2-50);
            
        }
        
        if(!gameOver&&started)
        g.drawString(String.valueOf(score),WIDTH/2-25,100);
        
        g.setFont(new Font("Comic_Sans",3,32));
        if(started){
            g.drawString("HighScore:"+highScore,WIDTH-1190,50);
        }
        
        if(score>=highScore&&gameOver&&!started)
        g.drawString("New HighScore:"+highScore,WIDTH/2-25,100);
        
    }
    
    public static void main(String[] args){
        FlappyBird=new flappyBird();
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        jump();
    }
    @Override
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            jump();
        }
        if(gameOver&& e.getKeyCode()==KeyEvent.VK_R){
            score=0;
            highScore=0;
        }
        
        if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
        	welcome.main(null);
        	
        }
            
            
        }
        
    @Override
    public void mousePressed(MouseEvent e){
        
    }
    
    @Override
    public void mouseReleased(MouseEvent e){
        
    }
    
    @Override
    public void mouseEntered(MouseEvent e){
        
    }
    
    @Override
    public void mouseExited(MouseEvent e){
        
    }
    
    @Override
    public void keyTyped(KeyEvent e){
        
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        
    }
}