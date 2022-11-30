package diffie;


import java.math.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class diffie {
	
	 public static String byteToHex(byte num) {
		    char[] hexDigits = new char[2];
		    hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
		    hexDigits[1] = Character.forDigit((num & 0xF), 16);
		    return new String(hexDigits);
		}

	public static void main(String[] args) {
		
		//einlesen
		String a = args[0];
		String b = args[1];
		
		//Eingabewerte in BigInt
		BigInteger aA = new BigInteger(a,16);
		BigInteger bB = new BigInteger(b,16);
		BigInteger g = new BigInteger("2",16);
		
		//Primzahlformel in BigDec
		
		BigDecimal basis = new BigDecimal("2.0");
		
		BigDecimal wert1 = new BigDecimal("124476.0");
		BigDecimal wert2 = new BigDecimal("1.0");
		
		BigDecimal res1 = basis.pow(2048);
		BigDecimal res2 = basis.pow(1984);
		BigDecimal res3 = basis.pow(64);
		BigDecimal res4 = basis.pow(1918);
		BigDecimal res5 = new BigDecimal(Math.PI);
		
		BigDecimal res4n = res4.multiply(res5);
		BigDecimal erg1 = res4n.add(wert1);
		BigDecimal erg2 = res3.multiply(erg1);
		BigDecimal erg3 = res1.subtract(res2);
		BigDecimal erg4 = erg3.subtract(wert2);
		BigDecimal p = erg4.add(erg2);
		
		
		
		String hexString = "FFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B80DC1CD129024E088A67CC74020BBEA63B139B22514A08798E3404DDEF9519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245E485B576625E7EC6F44C42E9A637ED6B0BFF5CB6F406B7EDEE386BFB5A899FA5AE9F24117C4B1FE649286651ECE45B3DC2007CB8A163BF0598DA48361C55D39A69163FA8FD24CF5F83655D23DCA3AD961C62F356208552BB9ED529077096966D670C354E4ABC9804F1746C08CA18217C32905E462E36CE3BE39E772C180E86039B2783A2EC07A28FB5C55DF06F4C52C9DE2BCBF6955817183995497CEA956AE515D2261898FA051015728E5A8AACAA68FFFFFFFFFFFFFFFF";
		
		BigInteger bigInt = new BigInteger(hexString, 16);
		
		
		//Key A
		BigInteger keya = g.modPow(aA, bigInt);
		String keyA = keya.toString(16);
		
		
		
		//KeyB
		BigInteger keyB = g.modPow(bB, bigInt);
		String keyBb = keyB.toString(16);
		
		//Shared Key A & B
		
		BigInteger k = keyB.modPow(aA, bigInt);
		String hexKey = k.toString(16);
		
		//Ausgabe
		System.out.println("a: " + a);
		System.out.println("b: " + b);
		System.out.println("A: " + keyA);
		System.out.println("B: " + keyBb);
		System.out.println("K: " + hexKey);
		
		//Hash
		try {
			final MessageDigest digest = MessageDigest.getInstance("SHA3-512");
			final byte[] hashbytes = digest.digest(hexKey.getBytes(StandardCharsets.UTF_8));
			String sha3_512hex = new String(hashbytes);
			
			StringBuffer hexStringBuffer = new StringBuffer();
			for(int i = 0; i < hashbytes.length; i++) {
				hexStringBuffer.append(byteToHex(hashbytes[i]));
			}
			
			
			System.out.println("H: " + hexStringBuffer.toString());
			
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}		
		
		}

}
