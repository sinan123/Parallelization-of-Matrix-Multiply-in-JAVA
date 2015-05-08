package org.apache.ws.axis2;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;

import javax.swing.JOptionPane;

 public class MatrisleriOlustur 
{
	
	public static double Matris1[][]=null;
	public static double Matris2[][]=null;
	public static double Matris2T[][]=null;
	public static double Sonuc[][]=null;
	public static int Boyut=0,sat=0,sut=0,sayac=0,yazma=0,tsay=0;
	public static boolean key2=false;
	public static  long sure,bassure,bitsure;
	
	public MatrisleriOlustur()
	{
    	 Random rnd = new Random();
		 Boyut = Integer.parseInt(JOptionPane.showInputDialog ("Kare Matrisler için Boyut Giriniz: "));
	     Matris1=new double[Boyut][Boyut];
		 Matris2=new double[Boyut][Boyut];
		 Matris2T=new double[Boyut][Boyut];
		 Sonuc=new double[Boyut][Boyut];
		 
		for(int i=0;i<Boyut;i++)
		{		
			for(int j=0;j<Boyut;j++)
			{
				
				Matris1[i][j]=(rnd.nextDouble());
				Matris2[i][j]=(rnd.nextDouble());
				Matris2T[j][i]=Matris2[i][j];
		    }	
        }
		System.out.println("Matrisler rasgele oluþturuldu");
		
        //////////////////////////Oluþturulan Matrisleri Dosyaya Yaz////////////////////////
		try
		{
			FileWriter outFile = new FileWriter("DosyaYolu1");
			PrintWriter out1 = new PrintWriter(outFile);
			
			for (int i = 0; i < Boyut; i++)
			{
				for(int j=0;j<Boyut;j++)
				{				
				out1.write(Double.toString(Matris1[i][j]));
				out1.write( "\t");
			    }
			out1.println("\n");
			}
			out1.close();
		}
		
		catch (Exception e)
		{
			System.err.println("Hata: " + e.getMessage());
		}
		
		try
		{
			FileWriter outFile = new FileWriter("DosyaYolu2");
			PrintWriter out2 = new PrintWriter(outFile);
			
			for (int i = 0; i < Boyut; i++)
			{
				for(int j=0;j<Boyut;j++)
				{	
				out2.write(Double.toString(Matris2[i][j]));
				out2.write( "\t");
			    }
		    out2.println("\n");
			}
			out2.close();
		}
		
		catch (Exception e)
		{
			System.err.println("Hata: " + e.getMessage());
		}
		
		System.out.println("Oluþturulan matrisler dosyalara yazýldý");
      
    
		
	}/////////constructor///////////////////////////
	
   
  
  public static double[][] satsutistek()
  {
	 double[][] gidensatsut = new double[2][Boyut];
	 gidensatsut[0]= Matris1[sat];
	 gidensatsut[1]= Matris2T[sut];
	 sayac++;

	 if(sayac<(Boyut*Boyut))
	 {
	    if((sut%(Boyut-1))==0 && sut!=0)
	      {
	     	 sat++;
		     sut=0;
	      }
	 
	    else
	    {
		 sut++;
	    }
	 
	 }
	 return gidensatsut;
  }
  
  public static void main(String[] args) throws InterruptedException
  {
	 
	  MatrisleriOlustur s=new MatrisleriOlustur();
	  String endPoint1="http://192.168.1.60:8080/ProjeServer/services/CarpServer.CarpServerHttpSoap11Endpoint/";
	  String endPoint2="http://192.168.1.196:8080/ProjeServer/services/CarpServer.CarpServerHttpSoap11Endpoint/";
	 
	  tsay=0;
	  bassure=System.currentTimeMillis();
	  while(tsay<(s.Boyut*s.Boyut))
	  {
		  tsay++;
		
		  Threads[] TreadDizisi=new Threads[6];
		  Thread[] Tread=new Thread[6];
		  Threads Server;
		  
		  for (int i = 0; i < TreadDizisi.length; i++)
		  {
			  if(i%2==0)
			  {
			    Server=new Threads(endPoint1);
			  }
			  
			  else
			  {
				   Server=new Threads(endPoint2);
			  }
			  
			  TreadDizisi[i]=Server;
			  Thread Server1=new Thread(TreadDizisi[i]);
			  Tread[i]=Server1;
		}
		 
		 
		  for (int i = 0; i < Tread.length; i++)
		  {
			Tread[i].start();
		  }
		  for (int i = 0; i < Tread.length; i++)
		    {
				Tread[i].join();
			}

	  }	  
	 
		 System.out.println("Ýþlemler sonlandý Sonuç Dosyaya yazdýrýldý");
		 MatrisleriOlustur.bitsure=System.currentTimeMillis();
		 MatrisleriOlustur.sure=( MatrisleriOlustur.bitsure- MatrisleriOlustur.bassure)/1000l;
		 JOptionPane.showMessageDialog(null, "Ýþlem süresi= "+Double.toString(MatrisleriOlustur.sure));
		 try
			{
				FileWriter outFile = new FileWriter("DosyaYolu3");
				PrintWriter out1 = new PrintWriter(outFile);
				
				for (int i = 0; i < MatrisleriOlustur.Boyut; i++)
				{
					for(int j=0;j<MatrisleriOlustur.Boyut;j++)
					{				
					out1.write(Double.toString(MatrisleriOlustur.Sonuc[i][j]));
					out1.write( "\t");
				    }
				out1.println("\n");
				}
				out1.close();
			}
			
			catch (Exception e)
			{
				System.err.println("Hata: " + e.getMessage());
			}
		 
	 
  }
	
	
}