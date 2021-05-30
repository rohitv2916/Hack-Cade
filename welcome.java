import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class welcome {
	
	

	private static JFrame frame;
	private static JPanel panel;
	private static JLabel name;
	private static JButton PC;
	private static JButton friend;
	private static ImageIcon logo;
	private static JLabel ask;
	private static JButton flappyBirdbtn;
	private static JLabel tag;
	private static JButton tetris;
	private static JButton pacman;
	private static JButton brick;

	public static void main(String[] args) {
		frame = new JFrame();
		panel = new JPanel();
		logo = new ImageIcon("logo.png");
		
		frame.setSize(640,600);
		frame.setTitle("GAME HUB");
//		frame.setIconImage(logo.getImage());
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.add(panel);
		
		panel.setLayout(null);
		panel.setBackground(new Color(25,25,25));
		
		name = new JLabel();
		name.setBackground(new Color(25,25,25));
		name.setForeground(new Color(25,255,0));
		name.setFont(new Font("Ink Free",Font.BOLD,75));
		name.setBounds(100,70,600,75);
		name.setText("GAME HUB");
		name.setOpaque(true);
		panel.add(name);
		
		tag = new JLabel();
		tag.setBackground(new Color(25,25,25));
		tag.setForeground(new Color(25,255,0));
		tag.setFont(new Font("Ink Free",Font.BOLD,35));
		tag.setBounds(130,180,600,75);
		tag.setText("Recreating old games");
		tag.setOpaque(true);
		panel.add(tag);
		
		
		ask = new JLabel("WHICH GAME DO YOU WANNA PLAY ?");
		ask.setBounds(80,280,600,75);
		ask.setBackground(new Color(25,25,25));
		ask.setForeground(new Color(25,255,0));
		ask.setFont(new Font("Ink Free",Font.BOLD,25));
		ask.setOpaque(true);
		panel.add(ask);
		
		friend = new JButton("TIC TAC TOE");
		friend.setFont(new Font("MV Boli", Font.BOLD, 20));
		friend.setFocusable(false);
		friend.setLayout(null);
		friend.setBounds(330,450,250,50);
		friend.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				new against_friend();
				frame.dispose();
			}
			
		});
		panel.add(friend);
		
		flappyBirdbtn = new JButton("FLAPPY BIRD");
		flappyBirdbtn.setFont(new Font("MV Boli", Font.BOLD, 20));
		flappyBirdbtn.setFocusable(false);
		flappyBirdbtn.setLayout(null);
		flappyBirdbtn.setBounds(30,450,265,50);
		flappyBirdbtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				flappyBird.main(args);
				frame.dispose();
			}
			
		});
		panel.add(flappyBirdbtn);
		
		brick = new JButton("BRICK BREAKER");
		brick.setFont(new Font("MV Boli", Font.BOLD, 20));
		brick.setFocusable(false);
		brick.setLayout(null);
		brick.setBounds(30,380,265,50);
		brick.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Main.main(args);
				frame.dispose();
			}
			
		});
		panel.add(brick);
		
		pacman = new JButton("PACMAN");
		pacman.setFont(new Font("MV Boli", Font.BOLD, 20));
		pacman.setFocusable(false);
		pacman.setLayout(null);
		pacman.setBounds(330,380,250,50);
		pacman.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Pacman.main(args);
				frame.dispose();
			}
			
		});
		panel.add(pacman);
		
		
		frame.setVisible(true);
		
	}
		
		
	

}
