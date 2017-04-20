package assignment;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import javax.swing.*;
import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;
import javax.swing.JCheckBox;

import assignment.FileManager;

public class Screen extends JFrame implements ActionListener
{
	
	
   JButton button1;
   JButton button2;
   JButton button8;
  
   //Verify Suspicious
   JButton button3;
   
   //Verify Options
   JButton button4;
   JButton button5;
   
   //Edit Post
   JButton button7;
   ProcessBuilder pb;
   
   
   JTextField textfield1;
   JTextField textfield2;
   JTextField textfield3;
   JLabel label1;
   JLabel label2;
   JLabel label3;
   static JLabel label4;
   JLabel label5;
   JRadioButton radiobutton1;
   JCheckBox simple;
   JCheckBox child;
   
   static JTextArea jta;
   static JTextArea jta2;
   static JScrollPane jsp;
   static JScrollPane jsp2;
   int Verify = 0 ; //Verifying suspicious words.
   public static boolean simplecheck = false;
   public static boolean childcheck = false;
   
   JComboBox NameList = new JComboBox();
   JLabel picture;
   
   //Constructor
   Screen(String title)
   {
	   int xsize = 800;
	   int ysize = 500;
	   JFrame window = new JFrame("Language Filter");
	   window.setSize(xsize,ysize);
	   window.setResizable(false);
	   window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
	   JPanel panel = new JPanel();
	   panel.setLayout(null);
	   window.add(panel);
	   
	   
	   
	   //Verifying Suspicious Words.
	   button1 = new JButton("Verify Suspicious Words");
	   button1.setBounds(500,70,200,25);
	   panel.add(button1);
	   button1.addActionListener(this);
	   
	   button4 = new JButton("Abusive");
	   button4.setBounds(450,150,130,25);
	   panel.add(button4);
	   button4.addActionListener(this);
	   
	   button5 = new JButton("Safe");
	   button5.setBounds(609,150,130,25);
	   panel.add(button5);
	   button5.addActionListener(this);
	   
	   
	   button1.setEnabled(false);
	   button4.setEnabled(false);
	   button5.setEnabled(false);
	   
	   jta2 = new JTextArea();
	   JScrollPane scroll2 = new JScrollPane (jta2);
	   scroll2.setBounds(450,100,290,50);
	   jta2.setEditable(false);
	  // textArea.setText();
	   panel.add(scroll2);
	   panel.setVisible (true);
	   
	   //Adding new bad words
	   label3 = new JLabel("Add or Remove Abusive Words");
	   label3.setBounds(470,250,200,25);
	   panel.add(label3);
	   
	   textfield2 = new JTextField (10);
	   textfield2.setBounds(450,275,195,50);
	   panel.add(textfield2);
	   
	   button2 = new JButton("Add");
	   button2.setBounds(650,275,89,20);
	   panel.add(button2);
	   button2.addActionListener(this);
	   
	   button8 = new JButton("Remove");
	   button8.setBounds(650,304,89,20);
	   panel.add(button8);
	   button8.addActionListener(this);
	   
	   label4 = new JLabel("");
	   label4.setBounds(460,320,400,25);
	   panel.add(label4);
	   
	   //Scan social media posts.
	   button3 = new JButton("Scan Posts");
	   button3.setBounds(10,70,200,25);
	   panel.add(button3);
	   button3.addActionListener(this);
	   
	   label5 = new JLabel("Scan posts.");
	   label5.setBounds(150,170,500,25);
	   //panel.add(label5);
	   
	   jta = new JTextArea();
	   JScrollPane scroll = new JScrollPane (jta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	   scroll.setBounds(10,100,380,350);
	   jta.setEditable(false);
	  // textArea.setText();
	   panel.add(scroll);
	   panel.setVisible (true);
	   
	   textfield3 = new JTextField (10);
	   textfield3.setBounds(10,100,380,75);
	   //panel.add(textfield3);
	   
	   //Edit Post
	   button7 = new JButton("Edit Posts");
	   button7.setBounds(145,40,110,25);
	   panel.add(button7);
	   button7.addActionListener(this);
	   
	   //Simplify results.
	   simple = new JCheckBox("Simplify");
	   simple.setBounds(220,70,70,25);
	   panel.add(simple);
	   simple.addActionListener(this);
	   
	   //Child results.
	   child = new JCheckBox("Child");
	   child.setBounds(290,70,70,25);
	   panel.add(child);
	   child.addActionListener(this);
	   
	   //Making it visible now ensures all GUI components load.
	   window.setVisible(true);
	   
	   //Open and close file.
	   FileManager f1 = new FileManager("abuse.txt");
	   FileManager f2 = new FileManager("safe.txt");
	   f1.connectToFile();
	   f1.readFile();
	   f2.connectToFile();
	   f2.readFile2();
	   f1.closeReadFile();
   }
   
	   //Event Handler
   		boolean SuspectButtons = false; //Users can't mark suspicious words without clicking  the "Verify" button.
   		public void actionPerformed(ActionEvent action)
   		{
   			FileManager f3 = new FileManager("suspect.txt");

		   //Verify Suspicious Words.
   			if(action.getSource() == button1)
   			{
   				button1.setEnabled(false); //Disable button while user is verifying.
   				while (FileManager.SuspectWordsEvaluate[Verify] == "")
				{
   					Verify++;
				}
			   SuspectButtons = true;
			   f3.connectToFile();
			   f3.getFileWriter();
			   for(int i = 0; i!= FileManager.Evaluate; i++)
			   {
				   f3.writeLineToFile(FileManager.SuspectWordsEvaluate[i]);
			   }
			   f3.closeWriteFile();
			   
			   Screen.jta2.setText("");
			   Screen.jta2.append(FileManager.SuspectWordsEvaluate[Verify]);
		   }
		   
   		//User marks the word as "Abusive".
		   if(action.getSource() == button4)
		   {
			   if (SuspectButtons == false)
			   {
				   Screen.jta2.setText("Please select verify button above.");
				   return;
			   }
			   else if (Verify >= FileManager.Evaluate)
			   {
				   	VerifyComplete();
			   }

			   else
			   {
				 //Read File
				   FileManager f1 = new FileManager("abuse.txt");
				   f1.connectToFile();
				   f1.readFile();

				   try 
				   {
					   f1.AbuseAppend((FileManager.SuspectWordsEvaluate[Verify]));
				   } 
				   catch (IOException e) 
				   {
					   e.printStackTrace();
				   }
			   
				   finally
				   {
				   		Verify++;
					   	Screen.jta2.setText("");
					   	Screen.jta2.append(FileManager.SuspectWordsEvaluate[Verify]);
				   		f1.readFile();
				   		if (Verify == FileManager.Evaluate)
				   		{
				   			VerifyComplete();
				   		}
				   }
			   }
		   }
		   
		   //User marks the word as "Safe".
		   if(action.getSource() == button5)
		   {
			   if (SuspectButtons == false)
			   {
				   Screen.jta2.setText("Please select verify button above.");
				   return;
			   }
			   else if (Verify >= FileManager.Evaluate)
			   {
				   VerifyComplete();
			   }

			   else
			   {
				   //Read File
				   FileManager f2 = new FileManager("safe.txt");
				   f2.connectToFile();
				   f2.readFile2();
					  
				   try 
				   {
					   f2.SafeAppend((FileManager.SuspectWordsEvaluate[Verify]));
				   } 
				   catch (IOException e) 
				   {
					   e.printStackTrace();
				   }
			   
				   finally
				   {
					   
					   Verify++;
					   	Screen.jta2.setText("");
					   	Screen.jta2.append(FileManager.SuspectWordsEvaluate[Verify]);

				   		f2.readFile2();
				   		if (Verify == FileManager.Evaluate)
				   		{
				   			VerifyComplete();
				   		}
				   }
			   }
		   }
		   
	   
	   //Add new bad word.
	   if(action.getSource() == button2)
	   {
		   System.out.println("Insert word!");
		   
		   String input = textfield2.getText();
		   
		   //Ensures correct user input.
		   while(!input.matches("[a-zA-Z]+"))
		   {
			   	Screen.label4.setText("Invalid syntax. Only characters are permitted.");
			   	return;
		   }
		   
		   FileManager f1 = new FileManager("abuse.txt");
		   f1.connectToFile();
		   f1.readFile();
		   try 
		   {
			   f1.AbuseAppend(input);
		   } 
		   catch (IOException e) 
		   {
			   e.printStackTrace();
		   }
		   finally
		   {
			   if (FileManager.insertion == true)
			   {
       				Screen.label4.setText("Word inserted!");
       				FileManager.insertion = false;
			   }
		   }
		   
		   //Refreshes "BadWords" array.
		   f1.readFile();
	   }
	   
	   //Scan posts in "posts.txt" for abusive content.
	   if(action.getSource() == button3)
	   {

		   Screen.jta2.setText(""); //Clear verify
		   FileManager f1 = new FileManager("posts.txt");
		   
		   //Refresh "safe.txt"
		   FileManager f2 = new FileManager("safe.txt");
		   f2.connectToFile();
		   f2.readFile2();
		   f2.closeReadFile();
		   
		   FileManager.Evaluate = 0;
		   //Clears text.
		   Screen.jta.setText(""); //Clears text.
		   f1.connectToFile();
		   f1.scanPost();
		   f1.closeReadFile();
	   }
	   
	   //Scan posts in "posts.txt" for abusive content.
	   if(action.getSource() == button7)
	   {
		   pb = new ProcessBuilder("notepad.exe", "posts.txt");
		   try 
		   {
			   pb.start();
		   } 
		   catch (IOException e) 
		   {
			   e.printStackTrace();
		   }
	   }
	   
	   //Remove Bad Word
	   if(action.getSource() == button8)
	   {
		   System.out.println("Insert word!");
		   
		   String input = textfield2.getText();
		   
		   //Ensures correct user input.
		   while(!input.matches("[a-zA-Z]+"))
		   {
			   	Screen.label4.setText("Invalid syntax. Only characters are permitted.");
			   	return;
		   }
		   
		   FileManager f1 = new FileManager("abuse.txt");
		   f1.connectToFile();
		   f1.readFile();
		   f1.getFileWriter();
		   //f1.writeLineToFile("What"); //Clears the text file
				   try
				   {
					   f1.AbuseRemove(input);
				   }
				   catch (IOException e) 
				   {
					   e.printStackTrace();
				   }
				   finally
				   {
					   f1.closeWriteFile();
					   //Refreshes "BadWords" array.
					   f1.readFile();
					   if (FileManager.insertion == false)
					   {
						   Screen.label4.setText("Word does not exist!");
					   }
					   else
					   {
						   Screen.label4.setText("Word removed!");
						   FileManager.insertion = false;
					   }
			   }
		  
	   }
	   
	   if (FileManager.Evaluate > 0)
	   {
		   //Enables verification buttons.
		   button1.setEnabled(true);
		   button4.setEnabled(true);
		   button5.setEnabled(true);
		   
		   //Disables adding bad words.
		   button2.setEnabled(false);
		   button8.setEnabled(false);
		   Screen.label4.setText("");
	   }
	   else
	   {
		   //Disables verification buttons.
		   button1.setEnabled(false);
		   button4.setEnabled(false);
		   button5.setEnabled(false);
		   
		   //Enables adding and removing bad words.
		   button2.setEnabled(true);
		   button8.setEnabled(true);
	   }
	   
	   //Scan posts in "posts.txt" for abusive content.
	   if(action.getSource() == simple)
	   {
		   if(simple.isSelected())
		   {
			   simplecheck = true;
			   child.setSelected(false);
			   childcheck = false;
		   }
		   else
		   {
			   simplecheck = false;
		   }
		   Screen.jta.setText("");
	   }
	   
	   if(action.getSource() == child)
	   {
		   if(child.isSelected())
		   {
			   childcheck = true;
			   simple.setSelected(false);
			   simplecheck = false;
		   }
		   else
		   {
			   childcheck = false;
		   }
		   Screen.jta.setText("");
	   	}
   	}


   	void VerifyComplete()
   	{
   			Screen.jta2.setText("Verification complete!\nPlease rescan posts!");
   			FileManager.Evaluate = 0;
 		   button2.setEnabled(true); //Bad Word option is enabled.
   	}
}