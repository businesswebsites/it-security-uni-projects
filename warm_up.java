package warm_upA;

import java.security.*;
import java.lang.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Base64;



public class warm_up {
	
	 public static String bytesToHex(byte [] hashbytes) {

	        StringBuilder sb = new StringBuilder();
	        for (byte b : hashbytes) {
	            sb.append(String.format("%02x", b));
	        }
	        return sb.toString();

	    }
	 public static String byteToHex(byte num) {
		    char[] hexDigits = new char[2];
		    hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
		    hexDigits[1] = Character.forDigit((num & 0xF), 16);
		    return new String(hexDigits);
		}
	

	public static void main(String[] args) {
		
		//einlesen
		String hexa = args[0];
		String hexb = args[1];
		
		//umwandeln in Hex
		int a = Integer.parseInt(hexa, 16);
		int b = Integer.parseInt(hexb, 16);
		
		double g = 2.0;
		
		//primzahlberechnung und umwandlung in hex
		Double p = (Math.pow(2.0, 2048.0)) - (Math.pow(2.0, 1984.0))- 1.0 + (((Math.pow(2.0, 64.0))* (((Math.pow(2.0, 1918.0))* Math.PI) + 124476.0) )) ;                               
		long doubleAsLong = Double.doubleToRawLongBits(p);
		String hexPrim = Long.toHexString(doubleAsLong);
		System.out.println("hexP =  " + hexPrim);
		int pri = p.intValue();
		String hexPri = Integer.toHexString(pri);
		
		BigInteger bi = new BigInteger(hexPrim, 16);
		System.out.println("BigInteger = " +  bi);
		
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
		
		BigInteger bigI = new BigInteger(hexP, 16);
		System.out.println("BigInteger 2 = " +  bigI);
		
		
		//key für A berechnung und in hex
		double kEyA = (Math.pow(g, a) ) % prI;
		int keYA = (int) kEyA;
		String keyA = Integer.toHexString(keYA);
		
		// key für B berechnung und in hex
		double kEyB = (Math.pow(g, b) ) % prI;
		System.out.println("Key B in double = "+ kEyB);
		System.out.println("2^256 = " + Math.pow(2, 256) % prI);
		int keYB = (int) kEyB;
		String keyB = Integer.toHexString(keYB);
		
		// shared Key berechnen
		double keyShareA = (Math.pow(kEyB, a)) % prI;
		int keYAS = (int) keyShareA;
		String keyAshare = Integer.toHexString(keYAS);
		
		//shared key berechnen
		double keyShareB = Math.pow(kEyA, b) % prI;
		int keYBS = (int) keyShareB;
		String keyBshare = Integer.toHexString(keYBS);
							
		System.out.println("a = " + hexa);
		System.out.println("b = " + hexb);		
		System.out.println("A = " + keyA);
		System.out.println("B = " + keyB);
		System.out.println("B in int : " + keYB);
		System.out.println("keyShareA = " + keyAshare);
		System.out.println("keyShareB = " + keyBshare);
		System.out.println(prI);
		
		
		try {
			final MessageDigest digest = MessageDigest.getInstance("SHA3-512");
			final byte[] hashbytes = digest.digest(keyBshare.getBytes(StandardCharsets.UTF_8));
			String sha3_512hex = new String(hashbytes);
			
			StringBuffer hexStringBuffer = new StringBuffer();
			for(int i = 0; i < hashbytes.length; i++) {
				hexStringBuffer.append(byteToHex(hashbytes[i]));
			}
			
			
			System.out.println("H = " + hexStringBuffer.toString());
			
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		
		//System.out.println("prI = " + prI);

	}

}