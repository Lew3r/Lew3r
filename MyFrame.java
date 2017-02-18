import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;
import java.lang.Math;

public class MyFrame extends JFrame  implements ActionListener
{	
	public static int leggerefile=0;
	JButton caricafile;
	JButton creastazioni;
	public final static String pulsantec = "puls1";
	public final static String pulsantecs = "puls2";
	public MyFrame( )
	{
		super("Iniziale");
		setSize(300,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container areaCentrale = getContentPane();
		caricafile = new JButton("Carica Stazioni da file");
		creastazioni = new JButton("Creare Stazioni");
		areaCentrale.setLayout(new BoxLayout(areaCentrale, BoxLayout.Y_AXIS));
		areaCentrale.add(caricafile);
		areaCentrale.add(creastazioni);
		caricafile.addActionListener(this);	
		caricafile.setActionCommand(this.pulsantec);
		creastazioni.addActionListener(this);	
		creastazioni.setActionCommand(this.pulsantecs);

	}
	public void actionPerformed(ActionEvent e)
 	{	
 		int[] arraytemp = new int[1000];
 		String com = e.getActionCommand();
   		int salva=0;
 		if (com == pulsantec)
 		{
			MyFrame4 mainFrame4 = new MyFrame4();
			leggerefile=1;
			mainFrame4.scrivere0();
			mainFrame4.show();
		}
		if (com==pulsantecs)
			{
	    		MyFrame2 mainFrame2 = new MyFrame2();
	    		mainFrame2.show();
        	}
   		
   	}
   	public static int returnleggerefile()
   	{
   		return leggerefile;
   	}
}
