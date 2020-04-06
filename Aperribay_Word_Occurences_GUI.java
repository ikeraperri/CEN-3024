/**
Author Name: Iker Aperribay
Date: 3/8/20
Program Name: Aperribay_Word_Ocurrences_GUI
Purpose: adding a GUI to my text analyzer*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.net.*;
import java.io.*;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Aperribay_Word_Occurences_GUI implements ActionListener
{
	public static void main(String[] args) throws Exception
	{
		/////////////////////////////////////////////////////////////////////////////////////////
		
		/**Sets Frame for GUI
		 */
		JFrame frame = new JFrame("Word Occurences GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,400);
		        
		/**creates a container object for the content pane and sets the layout            	
		 */
		Container cp = frame.getContentPane();
		        
		/**creates labels, buttons, and panels for the GUI
		 */
		JLabel outcome = new JLabel("");
		        
		JButton findWords = new JButton("Find top 20 words");
	      
		JPanel newpannel = new JPanel();
		newpannel.add(findWords);
		newpannel.add(outcome);
		        
		cp.add(newpannel);
		frame.setVisible(true);
		
		//////////////////////////////////////////////////////////////////////////////////////////
		
		java.io.File file = new java.io.File("macbeth-1.txt");
		Scanner fileScanner = new Scanner(file);
		
		/*URL shakespeare = new URL("http://shakespeare.mit.edu/macbeth/full.html");
		InputStream in = shakespeare.openStream();
		Scanner fileScanner = new Scanner(in);*/
		
		Map <String,Boolean> wordMap = new HashMap<>();
		Map <Integer,Aperribay_Word_Object> frequencyMap = new HashMap<>();
		ArrayList <String> allWords = new ArrayList<>();
		ArrayList <String> wordList = new ArrayList<>();		
		
		String notLetters="[\\[\\] ]";
		int count=32;
		int insertIndex=4;
		for(int i=0; i<2;i++)
		{
			count++;
			i--;
			if (count==91||count==93)
			{
				count++;
			}
			
			notLetters = notLetters.substring(0,insertIndex)+((char)(count))+
					notLetters.substring(notLetters.length()-1,notLetters.length());
			insertIndex++;
					
			if(count==64)
			{
				count=90;		
			}
			else if(count==96)
			{
				count=122;
			}
			else if(count==126)
			{
				i=2;
			}
		}
		
			wordMap.put("",true);
		
			while(fileScanner.hasNext())
			{
				String temp = fileScanner.next().replaceAll(notLetters,"");
				temp=temp.toLowerCase();
			
				allWords.add(temp);
			
				if (!wordMap.containsKey(temp))
				{
					wordMap.put(temp,true);
					wordList.add(temp);
				}
		
			}
			fileScanner.close();
	
			for(int i =0; i<wordList.size(); i++)
			{
				String currentWord = new String(wordList.get(i));
				int wordCount = 0;
				for(int j =0; j<allWords.size(); j++)
				{
					if (allWords.get(j).equals(currentWord))
					{
						wordCount++;
					}
				}
				
				if(currentWord!="")
				{
					Aperribay_Word_Object word = new Aperribay_Word_Object (wordCount,currentWord);
					frequencyMap.put(i,word);
				}	
			}
			
			/**adds action to the button in GUI
			 */
			ActionListener aL = new ActionListener() {
			    public void actionPerformed(ActionEvent actionEvent) {
			    	
			    	bubbleSort(frequencyMap);
					
					for (int i=0; i<20; i++)
					{
						outcome.setText(outcome.getText()+"<html><br>"+frequencyMap.get(i));
					}
			            	
			    }
			  };
			  
			findWords.addActionListener(aL);
	}
	
	public static void bubbleSort(Map<Integer,Aperribay_Word_Object> map) 
	{
		boolean needNextPass = true;
		
		for(int k=1; k<map.size() && needNextPass; k++)
		{
			needNextPass = false;
			for(int i=0; i<map.size()-k; i++)
			{
				if(map.get(i).frequency < map.get(i+1).frequency)
				{
					Aperribay_Word_Object temp = map.get(i);
					map.put(i,map.get(i+1));
					map.put(i+1,temp);
					
					needNextPass = true;
				}
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}

