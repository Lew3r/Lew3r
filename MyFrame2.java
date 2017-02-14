import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;
import java.lang.Math;


public class MyFrame2 extends JFrame  implements ActionListener
{	
	public int salva;
	public static int ns;
	public int leggeredafile;
	public final static String coll = "coll";
	public final static String calc = "calc";
	public static JTextField partenza;
	public static JTextField arrivo;
	JButton calcola = new JButton("calcola percorso");
	JButton creacollegamenti = new JButton("crea collegamento");
	public MyFrame2( )
	{
		super("Collegamenti");
		setSize(300,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container areaCentrale = getContentPane();
		creacollegamenti = new JButton("crea collegamento");
		calcola = new JButton("calcola percorso");
		partenza = new JTextField(15);
		arrivo = new JTextField(15);
		areaCentrale.setLayout(new BoxLayout(areaCentrale, BoxLayout.Y_AXIS));
		areaCentrale.add(creacollegamenti);
		areaCentrale.add(calcola);
		areaCentrale.add(partenza);
		areaCentrale.add(arrivo);
		calcola.addActionListener(this);	
		calcola.setActionCommand(this.calc);
		creacollegamenti.addActionListener(this);	
		creacollegamenti.setActionCommand(this.coll);
	}
	public static String partenza()
	{	
  			return partenza.getText();
	}		
 	public static String arrivo()
    { 
    		return arrivo.getText();
 	} 

	public static void impostans(int nspassato)
	{
		ns=nspassato;
	}
	public void leggeredafile()
	{
		leggeredafile=1;
	}
	public void salva()
	{
		salva=1;
	}
	public static  int ns()
	{
		return ns;
	}
	public void actionPerformed(ActionEvent e)
 	{	
 		String com = e.getActionCommand();
 		int ns1=ns;
 		//int part=1100;
 		//int arr=1000;
 		if(com==coll)
 		{
 		    MyFrame3 tabella = new MyFrame3();
 		   	//part=Integer.parseInt(MyFrame3.partenza());
 		    //arr=Integer.parseInt(MyFrame3.arrivo());
        }
      	Scanner in = new Scanner(System.in);
  	  	int[] arraytemp = new int[1000];
 	  	int[][] matrice = new int [ns][ns];
		int[] distanze = new int[ns];
        int[] provenienze = new int[ns];
        boolean[] visitato = new boolean[ns];
        String path = "D:/Downloads/stazioni.txt";
        if (com == MyFrame3.invia())
 		{	
 			ns1=MyFrame3.ns();
 				for(int i=0; i<ns1;i++)
 					for(int j=0;j<ns1;j++)
 						matrice[i][j]=Integer.parseInt(MyFrame3.ritorno(i,j));

 			String partenzastring = MyFrame3.partenza();
			int partenzaint = Integer.parseInt (partenzastring);
			String arrivostring = MyFrame3.arrivo();
			int arrivoint = Integer.parseInt (arrivostring);
        	calcolopercorso(partenzaint,arrivoint,ns1,distanze,provenienze,visitato,matrice);
   		}


        if(com==calc)
        {
        	if(leggeredafile==1)
        	{	
        		int i=0,j=0,k=0,t=0;
				int prob=0;
				leggere(path,arraytemp);
        		creamatrice(distanze,provenienze,visitato,matrice,arraytemp,ns1);
        		String partenzastring = partenza.getText();
				int partenzaint = Integer.parseInt (partenzastring);
				String arrivostring = arrivo.getText();
				int arrivoint = Integer.parseInt (arrivostring);
        		calcolopercorso(partenzaint,arrivoint,ns1,distanze,provenienze,visitato,matrice);
        	}
           		
        		
        	
        }
        if (salva==1)
        	salvataggiostazioni(matrice,path,ns1);
 	}
 
 	public static void calcolopercorso(int partenza,int arrivo,int ns,int[] distanze,int[] provenienze,boolean[] visitato,int[][] matrice)
	{	String risultato="percorso a ritroso:";
		int i,j;
			if (partenza>=ns|| arrivo>=ns)
				JOptionPane.showMessageDialog(null,"partenza o arrivo non valido riprova,le stazioni partono da 0 a "+ (ns-1) );
			else
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
       		e.printStackTrace();
    	}
    	int radice = (int)Math.sqrt(k);
    	impostans(radice);
    }
 	public static void creamatrice(int[] distanze,int[] provenienze,boolean[] visitato,int[][] matrice,int[] arraytemp,int ns1)
	{	

		int t=0;
    	System.out.println("il numero di stazioni sono "+ ns1);
    	for(int i=0;i<ns1;i++)
    		for(int j=0;j<ns1;j++)
    		{	
    			matrice[i][j]=arraytemp[t];
    			System.out.println("posizione "+i + " "+ j + " valore "+matrice[i][j]);
    			t++;
    		} 		
    }
    public static void stampamatrice(int[][] matrice, int ns)
	{
		for(int i=0;i<ns;i++)
			for(int j=0;j<ns;j++)
				System.out.println("posizione "+i + " "+ j + " valore "+matrice[i][j]);
	}
	public static void salvataggiostazioni(int[][]matrice, String path, int ns)
	{	
		String stazioni="";
		for(int i=0;i<ns;i++)
	    	for(int j=0;j<ns;j++)
	    		stazioni = stazioni+matrice[i][j]+"a";
		try 
		{
		File file = new File(path);
		if (file.exists())
			System.out.println("Il file " + path + " gia esiste ");
		   	else 
		   		if (file.createNewFile())
		       		System.out.println("Il file " + path + " è stato creato ");
		   		else
		      		System.out.println("Il file " + path + " non può essere creato ");
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
		   	System.out.println("Il file stazioni è stato salvato");
	   	}
	   	catch(IOException e)
	   	{
	       	e.printStackTrace();
	   	}
		
	}
	public static void creacollegamentonorandom(int ns, int[][] matrice, Scanner in)
	{
		int collegamento;
		System.out.println("inserisci <0> per assenza di collegamento");
       	for(int i=0;i<ns;i++)
		{
			for(int j=i;j<ns;j++)
			{
				if(i!=j)
				{	System.out.println("inserisci collegamento dalla stazione " + i + " alla stazione " + j );
					collegamento= in.nextInt();
					if(collegamento!=0)
					{
						matrice[i][j]= collegamento;
						matrice[j][i]= collegamento;
					}
					else
					{
						matrice[i][j]=999;
						matrice[j][i]=999;
					}
				}
			}
		}	

	}
 }