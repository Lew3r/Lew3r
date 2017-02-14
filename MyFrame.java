import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;
import java.lang.Math;

public class MyFrame extends JFrame  implements ActionListener
{
	JTextField prima;
	JButton caricafile;
	JButton salvafile;
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
		creastazioni = new JButton("Creare Stazioni, prima inserire numero stazioni sul box sopra e premere salva se si vuole salvare su file");
		prima = new JTextField(15);
		areaCentrale.setLayout(new BoxLayout(areaCentrale, BoxLayout.Y_AXIS));
		areaCentrale.add(prima);
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
 		String path = "D:/Downloads/stazioni.txt";
 		String com = e.getActionCommand();
   		int salva=0;
 		if (com == pulsantec)
		{	
			MyFrame2 mainFrame2 = new MyFrame2();
			mainFrame2.show();
			mainFrame2.leggere(path,arraytemp);
		 	mainFrame2.leggeredafile();
		}
		else
		if (com==pulsantecs)
		{
			String text = prima.getText();
			int ns = Integer.parseInt (text);
	       	MyFrame2 mainFrame2 = new MyFrame2();
			mainFrame2.show();
			mainFrame2.impostans(ns);
        }
			
	
   	}
	
	
	
	
	

   	
}
