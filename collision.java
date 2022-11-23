#! /bin/bash
javac collision.java
java collision $1 $2
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class collision {

	public static void main(String[] args) {
		  	BigInteger prime = new BigInteger(
	        		"FFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B80DC1CD129024E088A67CC74020BBEA63B139B22514A08798E3404DDEF9519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245E485B576625E7EC6F44C42E9A637ED6B0BFF5CB6F406B7EDEE386BFB5A899FA5AE9F24117C4B1FE649286651ECE45B3DC2007CB8A163BF0598DA48361C55D39A69163FA8FD24CF5F83655D23DCA3AD961C62F356208552BB9ED529077096966D670C354E4ABC9804F1746C08CA18217C32905E462E36CE3BE39E772C180E86039B2783A2EC07A28FB5C55DF06F4C52C9DE2BCBF6955817183995497CEA956AE515D2261898FA051015728E5A8AACAA68FFFFFFFFFFFFFFFF", 16);
	        BigInteger gen = new BigInteger("2",16);

	        BigInteger b = new BigInteger(args[1],16);

	        BigInteger B = gen.modPow(b, prime);

	        int listsize = 10000;
	        String[] K2Hashes = new String[listsize];

	        for(int i=1; i<listsize+1; i++) {
	        	String s = Integer.toHexString(i);

	        	BigInteger q2 = new BigInteger(s,16);
	 	        BigInteger K2 = B.modPow(q2, prime);

	 	        String myk2 = K2.toString(16);
	 	        String hashk2 = getHash(myk2);
	 	        K2Hashes[i-1] = hashk2;
	        }


	        BigInteger a = new BigInteger(args[0],16);

	        BigInteger A =  gen.modPow(a, prime);

	        int matchindex =0;
	        String q1String = "";
	        String q2String = "";
	        for(int i=0; i<Integer.MAX_VALUE;i++) {
	        	String s = Integer.toHexString(i);
	        	BigInteger q1 = new BigInteger(s,16);
	 	        BigInteger K1 = A.modPow(q1, prime);

	 	        String myk1 = K1.toString(16);

	 	        String hashk1 = getHash(myk1);

	 	        for(int h=0; h<listsize;h++) {
	 	        	int tmp = compare(hashk1, K2Hashes[h]);
	 	        	if (tmp > 7) {
	 	        		matchindex = h+1;
	 	        		q1String = s;
	 	        		q2String = Integer.toHexString(matchindex);

	 	        		System.out.println(q1String);
		 	        	System.out.println(q2String);
		 	        	return;
	 	        	}
	 	        }
	        }


	}

	private static int compare(String hashk2, String hashk1) {
		int i=0;
		while(hashk2.charAt(i) == hashk1.charAt(i)) {
			if (i >11) {
				return i;
			}
			i++;
		}
		return i;
	}

	private static String getHash(String myk1) {
		String s2 = "";
		try {
			MessageDigest sha3 = MessageDigest.getInstance("SHA3-512");
			byte[] bytes = sha3.digest(myk1.getBytes());
			for(int i=0; i<bytes.length;i++) {
				s2 = s2 + (byteToHex(bytes[i]));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return s2;
	}

	private static String byteToHex(byte b) {
		 char[] hexDigits = new char[2];
		    hexDigits[0] = Character.forDigit((b >> 4) & 0xF, 16);
		    hexDigits[1] = Character.forDigit((b & 0xF), 16);
		    return new String(hexDigits);
	}

}
