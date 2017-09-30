package gameEngine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Calculator extends JPanel{

	//stores positions of all alive cells
	private HashSet<Integer> table = new HashSet<Integer>();
	private int lengthX;
	private int lengthY;
	private int generationCounter = 0;
	
	public Calculator(int sizeX, int sizeY, int[] input){
		lengthX = sizeX;
		lengthY = sizeY;
		for(int i : input){
			table.add(new Integer(i));
		}
	}
	
	public void reassign(HashSet<Integer> input){
		table = input;
	}
	
	public void reset(){
		table = new HashSet<Integer>();
		generationCounter = 0;
	}
	
	public void advance(){
		HashSet<Integer> output = new HashSet<Integer>();
		for(Integer i : table){
			ArrayList<Integer> list = doA(i);
				for(Integer number : list)
					output.add(number);
		}
		table = output;
		generationCounter++;
	}
	
	public HashSet<Integer> getTable(){
		return table;
	}
	
	private ArrayList<Integer> doA(Integer num){
		ArrayList<Integer> result = new ArrayList<Integer>();
		int neighbors = 0;
		int x = num % lengthX;
		int y = num / lengthX;
		int dummy = (x + 1) % lengthX + y * lengthX;
		if(table.contains(new Integer(dummy)))
			neighbors++;
		else if(wakeMeUp(dummy))
			result.add(dummy);
		dummy = (x + 1) % lengthX + ((y + 1) % lengthY) * lengthX;
		if(table.contains(new Integer(dummy)))
			neighbors++;
		else if(wakeMeUp(dummy))
			result.add(dummy);
		dummy = x + ((y + 1) % lengthY) * lengthX;
		if(table.contains(new Integer(dummy)))
			neighbors++;
		else if(wakeMeUp(dummy))
			result.add(dummy);
		dummy = (mod(x, lengthX)) + ((y + 1) % lengthY) * lengthX;
		if(table.contains(new Integer(dummy)))
			neighbors++;
		else if(wakeMeUp(dummy))
			result.add(dummy);
		dummy = (mod(x, lengthX)) + y * lengthX;
		if(table.contains(new Integer(dummy)))
			neighbors++;
		else if(wakeMeUp(dummy))
			result.add(dummy);
		dummy = (mod(x, lengthX)) + (mod(y, lengthY)) * lengthX;
		if(table.contains(new Integer(dummy)))
			neighbors++;
		else if(wakeMeUp(dummy))
			result.add(dummy);
		dummy = x + (mod(y, lengthY)) * lengthX;
		if(table.contains(new Integer(dummy)))
			neighbors++;
		else if(wakeMeUp(dummy))
			result.add(dummy);
		dummy = (x + 1) % lengthX + (mod(y, lengthY)) * lengthX;
		if(table.contains(new Integer(dummy)))
			neighbors++;
		else if(wakeMeUp(dummy))
			result.add(dummy);
		if(neighbors == 3 || neighbors == 2)
			result.add(num);
		return result;
		
	}
	
	private boolean wakeMeUp(int num){
		int neighbors = 0;
		int x = num % lengthX;
		int y = num / lengthX;
		int dummy = (x + 1) % lengthX + y * lengthX;
		if(table.contains(new Integer(dummy)))
			neighbors++;
		dummy = (x + 1) % lengthX + ((y + 1) % lengthY) * lengthX;
		if(table.contains(new Integer(dummy)))
			neighbors++;
		dummy = x + ((y + 1) % lengthY) * lengthX;
		if(table.contains(new Integer(dummy)))
			neighbors++;
		dummy = (mod(x, lengthX)) + ((y + 1) % lengthY) * lengthX;
		if(table.contains(new Integer(dummy)))
			neighbors++;
		dummy = (mod(x, lengthX)) + y * lengthX;
		if(table.contains(new Integer(dummy)))
			neighbors++;
		dummy = (mod(x, lengthX)) + (mod(y, lengthY)) * lengthX;
		if(table.contains(new Integer(dummy)))
			neighbors++;
		dummy = x + (mod(y, lengthY)) * lengthX;
		if(table.contains(new Integer(dummy)))
			neighbors++;
		dummy = (x + 1) % lengthX + (mod(y, lengthY)) * lengthX;
		if(table.contains(new Integer(dummy)))
			neighbors++;
		if(neighbors == 3)
			return true;
		return false;
	}
	
	private int mod(int num, int divisor){
		if(num == 0) return divisor - 1;
		return num - 1;
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color gColor = g.getColor();

        g.setFont(new Font("TimesRoman", Font.PLAIN, 28)); 
        g.drawString("Generation: " + generationCounter, 500, 50);
        for (int i = 0; i < lengthY; i++) {
            for (int j = 0; j < lengthX; j++) {
                if (table.contains(new Integer(j + i * lengthX))) {
                    g.setColor(Color.red);
                    g.fillRect(j * 22, 420 - i * 22, 20, 20);
                }
            }
        }
        
		for (int i = 0; i <= lengthY; i++) {
			g.setColor(Color.black);
			g.drawLine(0, i * 22, 440, i * 22);
			for(int j = 0; j < lengthX; j++){
				g.drawLine(i * 22, 0, i * 22, 440);
			}
		}
		
        g.setColor(gColor);
    }
	
	 @Override
	 @Transient
	    public Dimension getPreferredSize() {
	        return new Dimension(460, 460);
	    }
}
