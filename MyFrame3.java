import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;
import java.lang.Math;


public class MyFrame3 extends JFrame implements ActionListener
{	
  public static JTextField partenza  = new JTextField(5);
  public static JTextField arrivo = new JTextField(5);
	public static int ns;
	public final static String invia = "puls1";
  public final static String pulsantes = "puls2A";
	public final static JTextField[][] casellamatrice= new JTextField[100][100];
	public MyFrame3()
	{	

		super("Iniziale");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setVisible(true);
    Container pane= getContentPane();
    JButton  inviavalori = new JButton("invia valori");
    JButton salvafile = new JButton("Salva le stazioni nel file Stazioni");
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
      setta();
      pane.add(partenza);
    	pane.add(arrivo);
    	pane.add(inviavalori);
      pane.add(salvafile);
    	MyFrame2 listener = new MyFrame2();
      salvafile.addActionListener(this);  
      salvafile.setActionCommand(this.pulsantes);
    	inviavalori.addActionListener(listener);
   		inviavalori.setActionCommand(this.invia);
  }
  public void actionPerformed(ActionEvent e)
  { 
    String path = "D:/Downloads/stazioni.txt";
    String com = e.getActionCommand();
    if(com==pulsantes)
      salvataggiostazioni(path,ns);
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
  public static void salvataggiostazioni(String path, int ns)
  { 
    String stazioni="";
    for(int i=0;i<ns;i++)
        for(int j=0;j<ns;j++)
          stazioni = stazioni+ritorno(i,j)+"a";
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