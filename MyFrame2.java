import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;
import java.lang.Math;


public class MyFrame2 extends JFrame  implements ActionListener
{	
	JLabel titolonstazioni;
	public static JTextField numerostazioni;
	public static int ns;
	public int leggeredafile;
	public final static String coll = "coll";
	public static int[][] matrice=new int[50][50];
	JButton creacollegamenti = new JButton("crea collegamento");
	public MyFrame2( )
	{
		super("Collegamenti");
		setSize(300,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container areaCentrale = getContentPane();
		creacollegamenti = new JButton("crea collegamento");
		numerostazioni=new JTextField(10);
		titolonstazioni=new JLabel("Inserisci numero stazioni");
		matrice=new int[50][50];
		areaCentrale.setLayout(new BoxLayout(areaCentrale, BoxLayout.Y_AXIS));
		areaCentrale.add(creacollegamenti);
		areaCentrale.add(titolonstazioni);
		areaCentrale.add(numerostazioni);
		creacollegamenti.addActionListener(this);	
		creacollegamenti.setActionCommand(this.coll);
	}
 	public static String matrice(int i,int j)
 	{
 		if(i!=j)
 			return Integer.toString(matrice[i][j]);
 		else
 			return Integer.toString(0);
 	}

	public static void impostans(int nspassato)
	{
		ns=nspassato;
	}
	public void leggeredafile()
	{
		leggeredafile=1;
	}
	public static  int ns()
	{
		return ns;
	}
	public void impostamatrice(int[][] matricepassata)
	{	for(int i=0;i<ns;i++)
			for(int j=0;j<ns;j++)
					matrice[i][j]=matricepassata[i][j];
	}
	public void invisible()
	{
		numerostazioni.setVisible(false);
		titolonstazioni.setVisible(false);
	}
	public void actionPerformed(ActionEvent e)
 	{	

 		String com = e.getActionCommand();
        String path = "D:/Downloads/stazioni.txt";
        int[] arraytemp = new int[1000];
  		if(com==coll)
 		{	
 			
 		    if(leggeredafile==1)
 		    {	
 		        leggere(path,arraytemp);
 	  			int[][] matrice = new int [ns][ns];
				int[] distanze = new int[ns];
        		int[] provenienze = new int[ns];
        		boolean[] visitato = new boolean[ns]; 	
        		creamatrice(distanze,provenienze,visitato,matrice,arraytemp,ns);
 		    	impostamatrice(matrice);
 		    	MyFrame3.setta();
 		    	enable();

 		    }
 		    else
 		    {
 		    	ns=Integer.parseInt(numerostazioni.getText());
 				int[][] matrice = new int [ns][ns];
				int[] distanze = new int[ns];
        		int[] provenienze = new int[ns];
        		boolean[] visitato = new boolean[ns]; 					    	
 		    }
 		    MyFrame3 tabella = new MyFrame3();
 		    creacollegamenti.setEnabled(false);
 		    tabella.setSize(1000,1000);
 

        }

        if (com == MyFrame3.invia())
 		{		
 			int ns1=MyFrame3.ns();
 			int[][] matrice = new int [ns][ns];
			int[] distanze = new int[ns];
        	int[] provenienze = new int[ns];
        	boolean[] visitato = new boolean[ns];
 				for(int i=0; i<ns1;i++)
 					for(int j=0;j<ns1;j++)
 						matrice[i][j]=Integer.parseInt(MyFrame3.ritorno(i,j));
 			String partenzastring = MyFrame3.partenza();
			int partenzaint = Integer.parseInt (partenzastring);
			String arrivostring = MyFrame3.arrivo();
			int arrivoint = Integer.parseInt (arrivostring);
        	calcolopercorso(partenzaint,arrivoint,ns1,distanze,provenienze,visitato,matrice);
   		}    
    }
   	public static void calcolopercorso(int partenza,int arrivo,int ns,int[] distanze,int[] provenienze,boolean[] visitato,int[][] matrice)
	{	String risultato="percorso a ritroso:";
		int i,j;
			if (partenza>=ns|| arrivo>=ns)
				JOptionPane.showMessageDialog(null,"partenza o arrivo non valido riprova,le stazioni partono da 0 a "+ (ns-1) );
			else
			{	
				if(partenza!=arrivo)
				{	for (i=0; i<ns; i++)
			    	{	
			    		distanze[i] = 100;
			    		provenienze[i] = -1;
			    		visitato[i] = false;
			    	}
			    	int nodoAttuale, minDistanza;
			 		nodoAttuale = partenza;
					distanze[partenza] = minDistanza = 0;
					while (nodoAttuale != arrivo && minDistanza != 100) 
					{	
			    		minDistanza = 100;
			   			for (j=0; j<ns; j++)
			   				if (!visitato[j] && distanze[j] < minDistanza) 
			     			{
			        			minDistanza =  distanze[j];
			        			nodoAttuale = j;        		     		
			     			}   		    	
			   			visitato[nodoAttuale] = true; 
			   			for (j=0; j<ns; j++)
			   			{
			   				if (matrice[nodoAttuale][j] != 100 && distanze[j] > distanze[nodoAttuale] + matrice[nodoAttuale][j])
			   				{
			   					distanze[j] = distanze[nodoAttuale] + matrice[nodoAttuale][j];
			   					provenienze[j] = nodoAttuale;
			               	}
			   			}
					}
			        if(!visitato[arrivo])
			        	JOptionPane.showMessageDialog(null,"Non esiste collegamento");
			         else
			        {
			        	
			         	i = arrivo;
			       		while (i != partenza) 
			      			{	
			      				risultato=risultato+" Stazione toccata: " +i;
			   	           		i = provenienze[i];
			      			}
			      			risultato=risultato + "Stazione toccata: "+ i;
						JOptionPane.showMessageDialog(null,"Ci mette " + distanze[arrivo]+ "minuti " + risultato);
			        }
			    }
			    else
			    	JOptionPane.showMessageDialog(null,"stazione di partenza = stazione di arrivo");
			}

	}
 	public static void leggere(String path, int[] arraytemp)
	{	
		int k=0,i=0,j=0;
		int prob=1;
		char[] testo = new char[1000];
    	int size = 0;
    	String stringa="";
    	String s="a";
       	char a = s.charAt(0);
       	try 
    	{
        	File file = new File(path);
        	FileReader fr = new FileReader(file);
        	size = fr.read(testo);
         	for(i=0; i<size; i++)
            {
               	StringBuilder sb = new StringBuilder();
        		for(j=i;testo[j]!=a;j++)
        		{	   
        			sb.append(testo[j]);
        			stringa = sb.toString();
           		}
           		i=j;
           		int val = Integer.parseInt(stringa);
           		arraytemp[k]=val;
           		k++;
        	}
        	fr.close();	
        }
     	catch(IOException e) 
   		{ 
       		JOptionPane.showMessageDialog(null,"Il file non esiste");;
        }
    	int radice = (int)Math.sqrt(k);
    	impostans(radice);
    }
 	public static void creamatrice(int[] distanze,int[] provenienze,boolean[] visitato,int[][] matrice,int[] arraytemp,int ns1)
	{	

		int t=0;
    	for(int i=0;i<ns1;i++)
    		for(int j=0;j<ns1;j++)
    		{	
    			matrice[i][j]=arraytemp[t];
       			t++;
    		} 		
    }
}