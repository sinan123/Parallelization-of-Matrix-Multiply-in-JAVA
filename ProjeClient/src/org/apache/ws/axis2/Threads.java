package org.apache.ws.axis2;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.axis2.AxisFault;
import org.apache.ws.axis2.CarpServerStub.Carp;
import  org.apache.ws.axis2.CarpServerStub.CarpResponse;
 

 

public class Threads extends JFrame implements Runnable {
	public String endPoint1;
	public Threads(String endPoint){
		
		endPoint1=endPoint;
		
	}
	
	
	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		
		double[][] carptýr = new double[2][MatrisleriOlustur.Boyut];
		int x=-1,y=-1,calýsmaSayýsý=0;
		while(MatrisleriOlustur.sayac<(MatrisleriOlustur.Boyut*MatrisleriOlustur.Boyut))
		{
		System.out.println(this.getName());
			
		   if(MatrisleriOlustur.key2==false)
		     {
			   
			   calýsmaSayýsý++;
			   MatrisleriOlustur.key2=true;
			   x=MatrisleriOlustur.sat;
			   y=MatrisleriOlustur.sut;
			   
			   carptýr=MatrisleriOlustur.satsutistek();
			   MatrisleriOlustur.key2=false;
			   
			  
			   CarpServerStub stub = null;
			try 
			{
				stub = new CarpServerStub(endPoint1);
			}
			catch (AxisFault e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   Carp atn = new Carp();
			   atn.setGelendizi1(carptýr[0]);
			   atn.setGelendizi2(carptýr[1]);
		
			   CarpResponse res = null;
			try
			{
				res = stub.carp(atn);
			}
			catch (RemoteException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
			   MatrisleriOlustur.Sonuc[x][y]=res.get_return();
			   
		     }
		}
		MatrisleriOlustur.yazma++;
		
	 
	}
 
	

	 
}
