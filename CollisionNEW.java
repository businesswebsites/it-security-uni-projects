package Kollision;

import java.math.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;

public class CollisionNEW  {
	static int count =0;
	
	
	public static String byteToHex(byte num) {
		    char[] hexDigits = new char[2];
		    hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
		    hexDigits[1] = Character.forDigit((num & 0xF), 16);
		    return new String(hexDigits);
	}
	
	public static String gethash(String myKey) {
		String s = "";
		try {
			MessageDigest sha3 = MessageDigest.getInstance("SHA3-512");
			byte[] bytes = sha3.digest(myKey.getBytes());
			for(int i=0; i<bytes.length;i++) {
				s = s + (byteToHex(bytes[i]));
			}
			//return sha3.digest(bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return s;
	}
	
	
	
	public static long toLong(String s) {
		String temp = s.substring(0, 9);
		long value = new BigInteger(temp,16).longValue();
		return value;
	}
	
	public static long binSearch(HashMap<Integer,Long> hm, long suchwert) {
		long left = 0;
		long right = hm.size() - 1;
		long not_found = -1;
		Object[] keyset = hm.keySet().toArray();
		while(left <= right) {
			long middle = ((right + left)/2);
			int z = (int) middle;
			if(hm.get(keyset[z]) == suchwert) {
				
				return hm.get(keyset[z]);
				
			}else{
				if(hm.get(keyset[z]) > suchwert){
					right = middle - 1;
				}else {
					left = middle + 1;
				}
			}
		}
		count++;
		System.out.println("not found: " + count);
		//System.out.println("not found");
		return not_found;
	}

	public static void main(String[] args) {
		BigInteger prime = new BigInteger(
        		"FFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B80DC1CD129024E088A67CC74020BBEA63B139B22514A08798E3404DDEF9519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245E485B576625E7EC6F44C42E9A637ED6B0BFF5CB6F406B7EDEE386BFB5A899FA5AE9F24117C4B1FE649286651ECE45B3DC2007CB8A163BF0598DA48361C55D39A69163FA8FD24CF5F83655D23DCA3AD961C62F356208552BB9ED529077096966D670C354E4ABC9804F1746C08CA18217C32905E462E36CE3BE39E772C180E86039B2783A2EC07A28FB5C55DF06F4C52C9DE2BCBF6955817183995497CEA956AE515D2261898FA051015728E5A8AACAA68FFFFFFFFFFFFFFFF", 16);
        
		BigInteger gen = new BigInteger("2",16);
        
        //A = public key
        BigInteger A = new BigInteger(args[0],16);
		        
        //B = public key
        BigInteger B = new BigInteger(args[1],16);

       //HahsMap
        HashMap hm = new HashMap();
        Map<Integer, String> hmNew = new HashMap<>();
        
        Map<Integer, Long> hmLong = new HashMap<>();
                
        
        int length = 1000000;
        
        
        
        for(int i = 1; i < length; i++) {
        	
        	//q2 = private key
        	long s = i;
        	BigInteger q2 = BigInteger.valueOf(s);
 	        BigInteger K2 = B.modPow(q2, prime);
 	        
 	       String myk2 = K2.toString(16);
 	       //byte[] bytes = K2.toByteArray();
	        String hashk2 = gethash(myk2);
	        
	       /* long value = 0;
	        for(int k = 0; k<6; k++) {
	        	value = (value <<8) + (hashk2[k] & 0xff);
	        }*/
	        
	        long erg = toLong(hashk2);
	        
	       // hm.put(q2, hashk2);
	        
	       // hmNew.put(i, hashk2);
	        hmLong.put(i, erg);
	        
	        
        }
        System.out.println("list saved");
        
        //Map<Integer, String> sortedHM = hmNew.entrySet().stream().sorted(comparingByValue()).collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        HashMap<Integer, Long> longHM = hmLong.entrySet().stream().sorted(comparingByValue()).collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        for(int i = 0; i < Integer.MAX_VALUE; i++) {
        	long l = i;
        	//String o = Integer.toHexString(i);
       
        	BigInteger q1 = BigInteger.valueOf(l);
        	 BigInteger K1 = A.modPow(q1,prime);
        	 String myk1 = K1.toString(16);
        	 //byte[] bytes = K1.toByteArray();
        	 String hashk1 = gethash(myk1);
        	 
        	 long erg1 = toLong(hashk1);
        	 int ergN = (int) erg1;
        	/* long value = 0;
 	        for(int k = 0; k<6; k++) {
 	        	value = (value <<8) + (hashk1[] << 0xff);
 	        }*/
 	        
        	 
        	 
        	 long ret= binSearch(longHM, erg1);
        	 //Object [] arr = longHM.keySet().toArray();
        	// long[] ar = new long[arr.length];
        	 //System.arraycopy(arr, 0, ar, 0, arr.length);
        	 
        	 //Long[] longArray = new Long[arr.length];
        	 /*for(int y = 0; y <arr.length;y++) {
        		 longArray[y] = (Long)arr[y];
        	 }
        	 */
        	 //long ret = Arrays.binarySearch(arr, ergN);
        	//long ret = Arrays.binarySearch(longArray, erg1);
        	 
        	 if(ret != -1) {
        		 //System.out.print("yay");
        		 for(Entry<Integer, Long> entry : longHM.entrySet()) {
        			 if(entry.getValue().equals(ret)) {
        				 System.out.println("Gefundener Key in der HashMap ist: " + entry.getKey());
        				 String hash = Long.toHexString(entry.getValue());
        				 System.out.println("Der zugehoerige Hash ist: " + hash);
        			 }
        			
        		 }
        		 System.exit(1);
        	 }
        }
        
        
	}

}