import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;
import java.lang.Math;


public class MyFrame3 extends JFrame implements ActionListener
{	
	public static JButton salvafile;	
	public static JTextField partenza;
	public static JTextField arrivo;
	public static int ns;
	public final static String invia = "puls1";
	public final static String pulsantes = "puls2A";
	public final static JTextField[][] casellamatrice= new JTextField[50][50];
	JLabel titolo,titolo1;
	public MyFrame3()
	{	

		super("Collegamenti");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	pack();
    	setVisible(true);
    	Container pane= getContentPane();
    	JScrollPane scrollPane = new JScrollPane(pane);
    	setContentPane(scrollPane);
    	partenza  = new JTextField(8);
    	arrivo = new JTextField(8);
    	JButton  inviavalori = new JButton("calc.percorso");
    	salvafile = new JButton("Salva stazioni su file");
    	pane.setLayout(new GridBagLayout());
    	GridBagConstraints c = new GridBagConstraints();
   		c.fill = GridBagConstraints.HORIZONTAL;
   		int j=0;
     	for(int i=0; i< MyFrame2.ns();i++)
    	{
    		for(j=0;j< MyFrame2.ns();j++)
    		{	if(i==0)
    			{
    				titolo = new JLabel("staz.arr. " + j+ " ");
    				c.gridx = j+1;
    				c.gridy = 0;
    				pane.add(titolo,c);
    			}
    			casellamatrice[i][j]  = new JTextField(8);
    			c.fill = GridBagConstraints.HORIZONTAL;
    			c.gridx = j+1;
    			c.gridy = i+1;
      			pane.add(casellamatrice[i][j], c);
      			if(i==j)
      			{
        			casellamatrice[i][j].setEditable(false);
      			}
    		}
    		 titolo = new JLabel("staz.part."+ i);
    		c.gridx = j+1;
    		c.gridy = i+1;
    		pane.add(titolo,c);

       	}
    	ns=MyFrame2.ns();
    	setta();
       	titolo = new JLabel("Staz.Part.");
    	titolo1 = new JLabel("Staz.Arr.");
    	c.gridx = 1;
    	c.gridy = ns+1;
    	pane.add(titolo,c);
    	c.gridy = ns+2;
        pane.add(partenza,c);
        c.gridx = 2;
     	c.gridy = ns+1;
     	pane.add(titolo1,c);
    	c.gridx = 2;
     	c.gridy = ns+2;
    	pane.add(arrivo,c);
    	c.gridx = 1;
    	c.gridy = ns+3;
    	pane.add(inviavalori,c);
    	c.gridx = ns+4;
    	pane.add(salvafile,c);
       	MyFrame2 listener = new MyFrame2();
        salvafile.addActionListener(this);  
      	salvafile.setActionCommand(this.pulsantes);
    	inviavalori.addActionListener(listener);
   		inviavalori.setActionCommand(this.invia);

            
  	}
   	
	 public void actionPerformed(ActionEvent e)
  { 

    
    String com = e.getActionCommand();
    if(com==pulsantes)
    {
       MyFrame4 mainFrame4 = new MyFrame4();
        mainFrame4.scrivere();
        mainFrame4.show();

        String path = MyFrame4.returnpath();

    	try
    	{
    		for(int i=0;i<ns;i++)
    			for(int j=0;j<ns;j++)
    				Integer.parseInt(ritorno(i,j));
    	}
    	catch(Exception error)
    	{
    		JOptionPane.showMessageDialog(null,"valori non numeri, salvataggio impedito");
    	}
    }
      
  }
  public static void setta()
  {
      for(int i=0;i<ns;i++)
        for(int j=0;j<ns;j++)
            casellamatrice[i][j].setText(MyFrame2.matrice(i,j));
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