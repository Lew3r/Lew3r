import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;
import java.lang.Math;

public class MyFrame4 extends JFrame  implements ActionListener
{	
	JButton  inviaindirizzo;
	public static String path;
	public static JTextField indirizzo;
	public static int scrittura=0;
	public MyFrame4()
	{	
		super("Iniziale");
		setSize(300,300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Container areaCentrale = getContentPane();
		inviaindirizzo= new JButton("Salva indirizzo");
		indirizzo= new JTextField(100);
		areaCentrale.setLayout(new BoxLayout(areaCentrale, BoxLayout.Y_AXIS));
		areaCentrale.add(indirizzo);
		areaCentrale.add(inviaindirizzo);
		inviaindirizzo.addActionListener(this);	
		
		

	}
	public void actionPerformed(ActionEvent e)
 	{	
 		int[] arraytemp = new int[1000];
 		path = indirizzo.getText()+"stazioni.txt";
 		String com = e.getActionCommand();
   		if(scrittura==0)
   		{	
   			if (MyFrame.returnleggerefile()==1)
			{	
				MyFrame2 mainFrame2 = new MyFrame2();
				mainFrame2.invisible();
				try 
	    		{
	        		File file = new File(path);
	        		FileReader fr = new FileReader(file);
	        	    fr.close();	
	        	    mainFrame2.show();
	        	    dispose();
	       		}
	     		catch(IOException error) 
	   			{ 
	       			mainFrame2.dispose();;
	        	}
				mainFrame2.leggere(path,arraytemp);
			 	mainFrame2.leggeredafile();
			}
			else
			{
		    	MyFrame2 mainFrame2 = new MyFrame2();
		    	mainFrame2.show();
		    	dispose();
			}
		}
		else
		{	
			int ns=MyFrame2.ns();
			String path = MyFrame4.returnpath();
    		try
    		{

    			for(int i=0;i<ns;i++)
    				for(int j=0;j<ns;j++)
    					Integer.parseInt(MyFrame3.ritorno(i,j));
    			salvataggiostazioni(path,ns);
    			 dispose();
    		}
    		catch(Exception error)
    		{
    			JOptionPane.showMessageDialog(null,"valori non numeri, salvataggio impedito");
    		}
 		}	
 	}
      
  	public static void scrivere()
   	{
   		scrittura=1;
   	}
   	public static void scrivere0()
   	{
   		scrittura=0;
   	}
   	public static String returnpath()
   	{
   		return path;   		
   	}
   	public static void salvataggiostazioni(String path, int ns)
  	{ 
	    String stazioni="";
	    for(int i=0;i<ns;i++)
	        for(int j=0;j<ns;j++)
	          stazioni = stazioni+MyFrame3.ritorno(i,j)+"a";
	    try 
	    {
	        File file = new File(path);
	         if (file.exists())
	        	JOptionPane.showMessageDialog(null,"Il file " + path + " gia esiste ");
	     	else 
	        	if (file.createNewFile())
	          	JOptionPane.showMessageDialog(null,"Il file " + path + " è stato creato ");
	        	else
	           	JOptionPane.showMessageDialog(null,"Il file " + path + " non può essere creato ");
	    } 
	    catch (IOException e) 
	    {
	    	e.printStackTrace();
	    }
	    try
	    {
	        File file = new File(path);
	        FileWriter fw = new FileWriter(file);
	        fw.write(stazioni);
	        fw.flush();
	        fw.close();
	        JOptionPane.showMessageDialog(null,"I file stazioni verranno salvati");

	    }
	   	catch(IOException e)
	    {
	        e.printStackTrace();
	    }

	}


	

   	
}
