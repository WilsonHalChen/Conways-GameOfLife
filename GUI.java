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
		frame = new JFrame();
		int[] input = {};
		final Calculator c = new Calculator(20, 20, input);
		frame.setContentPane(c);
		frame.setBounds(100, 100, 960, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton advance1 = new JButton("Grow x1");
		advance1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.advance();
				c.validate();
				c.repaint();
			}
		});
		advance1.setBounds(13, 558, 171, 41);
		frame.getContentPane().add(advance1);
		
		JButton advance10 = new JButton("Grow x10");
		advance10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(int i = 0; i < 10; i++){
					c.advance();
					c.validate();
					c.repaint();
					
				}
			}
		});
		advance10.setBounds(194, 558, 171, 41);
		frame.getContentPane().add(advance10);
		
		JButton advance25 = new JButton("Grow x25");
		advance25.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < 25; i++){
					c.advance();
					c.repaint();
				}
			}
		});
		advance25.setBounds(372, 558, 171, 41);
		frame.getContentPane().add(advance25);
		
		JButton reset = new JButton("Clear");
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
