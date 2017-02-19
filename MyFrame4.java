import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;
import java.lang.Math;

public class MyFrame4 extends JFrame  implements ActionListener
{	
	JLabel   labelindirizzo;
	JButton  inviaindirizzo;
	public static String path;
	public static JTextField indirizzo;
	public static int scrittura=0;
	public MyFrame4()
	{	
		super("Iniziale");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Container areaCentrale = getContentPane();
		inviaindirizzo= new JButton("Salva indirizzo");
		labelindirizzo= new JLabel("Inserisci path del file");
		indirizzo= new JTextField(100);
		areaCentrale.setLayout(new BoxLayout(areaCentrale, BoxLayout.Y_AXIS));
		pack();
		areaCentrale.add(labelindirizzo);
		areaCentrale.add(indirizzo);
		areaCentrale.add(inviaindirizzo);
		pack();
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
		{	int temp=0,indice=0;
			int ns=MyFrame2.ns();
			String path = returnpath();
			int dim=path.length();	
			System.out.println(path.charAt(dim-1));	
				if(path.charAt(dim-12)=='s'&& path.charAt(dim-11)=='t'&& path.charAt(dim-10)=='a'&& path.charAt(dim-9)=='z'&&
				   path.charAt(dim-8)=='i'&& path.charAt(dim-7)=='o'&& path.charAt(dim-6)=='n'&& path.charAt(dim-5)=='i'&& 
				   path.charAt(dim-4)=='.'&& path.charAt(dim-3)=='t'&& path.charAt(dim-2)=='x'&& path.charAt(dim-1)=='t')
				{
					for(int j=0;j<path.length();j++)
						if(path.charAt(j)=='\\')
						{
							temp=1;
						}
					if(temp==0)
						path= path.substring(dim-12);
								
				}
	  		try
    		{

    			for(int i=0;i<ns;i++)
    				for(int j=0;j<ns;j++)
    					Integer.parseInt(MyFrame3.ritorno(i,j));
    			salvataggiostazioni(path,ns,temp);
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
   	public static void scrivipath(String pathpassato)
   	{
   		path=pathpassato;
   	}
   	public static String returnpath()
   	{
   		return path;   		
   	}
   	public static void salvataggiostazioni(String path, int ns,int temp)
  	{ 
	    String stazioni="";
	    for(int i=0;i<ns;i++)
	        for(int j=0;j<ns;j++)
	          stazioni = stazioni+MyFrame3.ritorno(i,j)+"a";
	    try 
	    {
	        File file = new File(path);
	        if(temp==0)
	        	JOptionPane.showMessageDialog(null,"Il path è stato modificato con il path del programma");
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
	    	JOptionPane.showMessageDialog(null,path + " non valido");
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
	        JOptionPane.showMessageDialog(null, path + " inserire path corretto");
	    }

	}   	
}
