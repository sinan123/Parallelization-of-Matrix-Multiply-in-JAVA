
public class CarpServer {

	public static int sayac=0;
	public double carp(double[] gelenDizi1,double[] gelenDizi2)
	  {
		sayac++;
		double cevap=0.0;
		
		for(int i=0;i<gelenDizi1.length;i++)
		{
			cevap+=gelenDizi1[i]*gelenDizi2[i];
		}
		 
		System.out.println("Client cevaplandý "+sayac);
         return cevap;
       }

}
