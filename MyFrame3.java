import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;
import java.lang.Math;


public class MyFrame3 extends JFrame
{	
  public static JTextField partenza  = new JTextField(5);
  public static JTextField arrivo = new JTextField(5);
	public static int ns;
	public final static String invia = "puls1";
	public final static JTextField[][] casellamatrice= new JTextField[100][100];
	public MyFrame3()
	{	

		super("Iniziale");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setVisible(true);
    Container pane= getContentPane();
    JButton  inviavalori = new JButton("invia valori");
    pane.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
   	c.fill = GridBagConstraints.HORIZONTAL;
   	for(int i=0; i< MyFrame2.ns();i++)
    	{
    		for(int j=0;j< MyFrame2.ns();j++)
    		{
    			casellamatrice[i][j]  = new JTextField(5);
    			c.fill = GridBagConstraints.HORIZONTAL;
    			c.gridx = j;
    			c.gridy = i;
      			pane.add(casellamatrice[i][j], c);
    		}

    		ns=MyFrame2.ns();
    	}
       partenza.setText(MyFrame2.partenza());
      arrivo.setText(MyFrame2.arrivo());
      pane.add(partenza);
    	pane.add(arrivo);
    	pane.add(inviavalori);
    	MyFrame2 listener = new MyFrame2();
    	inviavalori.addActionListener(listener);
   		inviavalori.setActionCommand(this.invia);
  }

	public static String ritorno(int i,int j)
	{	
		return casellamatrice[i][j].getText();
	
	}
	public static String partenza()
	{	
  	return partenza.getText();
	}		
  public static String arrivo()
  { 
    return arrivo.getText();
  } 
    public static String invia()
  { 
    return invia;
  }  

	public static int  ns()
	{	
		 return ns;
	}

 }