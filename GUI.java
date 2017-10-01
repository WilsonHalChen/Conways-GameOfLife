package gameEngine;

import java.awt.EventQueue;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.awt.event.ActionEvent;

import java.util.concurrent.*;
import java.lang.Runnable;

public class GUI {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		final ScheduledExecutorService[] executorService = new ScheduledExecutorService[1];
		executorService[0] = Executors.newSingleThreadScheduledExecutor();
		
		frame = new JFrame();
		int[] input = {};
		final Calculator c = new Calculator(20, 20, input);
		frame.setContentPane(c);
		frame.setBounds(100, 100, 960, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

		JButton advance1 = new JButton("Grow x1");
		//advance 1
		advance1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.advance();
				c.validate();
				c.repaint();
			}
		});
		advance1.setBounds(13, 558, 171, 41);
		frame.getContentPane().add(advance1);
		
		class Advance implements Runnable {
			private int delay;
			
			public Advance(){
				delay = 200;
			}
			public Advance(int sleepTime){
				delay = sleepTime;
			}
			
			public void run(){
				c.advance();
				c.validate();
				c.repaint();
				try{
				Thread.sleep(delay);
				}
				catch(Exception e){
					System.out.println("Advance; run(); Thread.sleep threw an Exception");
				}
			}
		}
		
		JButton advance10 = new JButton("Grow x10");
		//advance 10
		advance10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(int i = 0; i < 10; i++){
					executorService[0].submit(new Advance());
				}
			}
		});
		advance10.setBounds(194, 558, 171, 41);
		frame.getContentPane().add(advance10);
		

		JButton advance25 = new JButton("Grow x25");
		//advance 25
		advance25.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < 25; i++){
					executorService[0].submit(new Advance(100));
				}
			}
		});
		advance25.setBounds(372, 558, 171, 41);
		frame.getContentPane().add(advance25);
		
		
		//terminate is an array in order 
		//to make it final so it can be added to an action listener
		final boolean[] terminate = new boolean[1];
		terminate[0] = false;
		
		
		class AdvanceOne implements Runnable {
			public void run(){
				c.advance();
				c.validate();
				c.repaint();
			}
		}
		
		
		JButton advanceC = new JButton("Grow Continually");
		//advance Continually
		advanceC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executorService[0].scheduleAtFixedRate(new AdvanceOne(), 0, 100, TimeUnit.MILLISECONDS);
			}
		});
		advanceC.setBounds(474, 312, 308, 41);
		frame.getContentPane().add(advanceC);
		
		
		JButton stopAdvance = new JButton("Stop Continual Growth");
		//stop Growth
		stopAdvance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executorService[0].shutdown();
				executorService[0] = Executors.newSingleThreadScheduledExecutor();
			}
		});
		stopAdvance.setBounds(474, 368, 308, 41);
		frame.getContentPane().add(stopAdvance);
		
		
		JButton reset = new JButton("Clear");
		//reset
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.reset();
				c.repaint();
			}
		});
		reset.setBounds(555, 558, 171, 41);
		frame.getContentPane().add(reset);
		
		
		textField = new JTextField();
		textField.setBounds(13, 512, 903, 39);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		JLabel lblNewLabel = new JLabel("Ex: to enter initial points (0,0), (1,2), (3,6) enter: \"0 0 1 2 3 6\" (20x20 board)");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 28));
		lblNewLabel.setBounds(13, 472, 872, 33);
		frame.getContentPane().add(lblNewLabel);
		
		
		JButton setupBoard = new JButton("Initialize");
		setupBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HashSet<Integer> values = new HashSet<Integer>();
				String str = textField.getText();
				String[] array = str.split(" +");
				for(int i = 0; i < array.length; i += 2){
					values.add(Integer.parseInt(array[i]) + Integer.parseInt(array[i + 1]) * 20);
				}
				c.reassign(values);
				c.repaint();
			}
		});
		setupBoard.setBounds(731, 558, 171, 41);
		c.add(setupBoard);
	}
}
