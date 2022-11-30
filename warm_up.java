package warm_up;

import java.security.MessageDigestSpi;
import java.security.*;
import java.lang.*;
import java.util.Base64;



public class warm_up {
	
	

	public static void main(String[] args) {
		
		//einlesen
		String hexa = args[0];
		String hexb = args[1];
		
		//umwandeln in Hex
		int a = Integer.parseInt(hexa, 16);
		int b = Integer.parseInt(hexb, 16);
		
		double g = 2.0;
		
		//primzahlberechnung und umwandlung in hex
		Double p = Math.pow(2.0, 2048.0) - Math.pow(2.0, 1984.0)- 1.0 + (Math.pow(2.0, 64.0)* ((Math.pow(2.0, 1918.0)* Math.PI) + 124476.0) ) ;                               
		int pri = p.intValue();
		String hexPri = Integer.toHexString(pri);
		
		// ebenfalls primzahlberechnung die funktioniert besser
		Double x =  Math.pow(2, 2048);
		int xi = x.intValue();
		Double y = Math.pow(2.0, 1984.0);
		int yi = y.intValue();
		Double w1 = Math.pow(2.0, 64.0);
		int w1i = w1.intValue();
		Double w2 = Math.pow(2.0, 1918.0) * Math.PI;
		int w2i = w2.intValue();
		Double w3 = 124476.0;
		int w3i = w3.intValue();
		double z = w1 * (w2 + w3);
		int zi = (int) z;
		double pp = x - y -1 -z;
		int prime = xi - yi - 1 + zi;
		
		float prim = (float) prime;
		double prI = (double) prime;
		
		//prim in hex
		String hexP = Integer.toHexString(prime);
		
		
		double t = Math.pow(g, a);
		int ti = (int) t;
		String hext = Integer.toHexString(ti); 
		
		//key für A berechnung und in hex
		double kEyA = (Math.pow(g, a) ) % prI;
		int keYA = (int) kEyA;
		String keyA = Integer.toHexString(keYA);
		
		// key für B berechnung und in hex
		double kEyB = (Math.pow(g, b) ) % prI;
		int keYB = (int) kEyB;
		String keyB = Integer.toHexString(keYB);
		
		// shared Key berechnen
		double keyShareA = (Math.pow(kEyB, a)) % prI;
		int keYAS = (int) keyShareA;
		String keyAshare = Integer.toHexString(keYAS);
		byte kA = (byte) keYAS;
		
		//shared key berechnen
		double keyShareB = Math.pow(kEyA, b) % prI;
		int keYBS = (int) keyShareB;
		String keyBshare = Integer.toHexString(keYBS);
		
		
		MessageDigest sha3;
		try {
			sha3 = MessageDigest.getInstance("SHA-512");
			sha3.update(kA);
			byte[] hashValue = sha3.digest();
			System.out.println(hashValue);
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		
		
		//System.out.println(hashValue);
		
		System.out.println("a = " + a);
		System.out.println("b = " + b);		
		System.out.println("A = " + keyA);
		System.out.println("B = " + keyB);
		System.out.println("keyShareA = " + keyAshare);
		System.out.println("keyShareB = " + keyBshare);
		System.out.println("prI = " + prI);

	}

}
