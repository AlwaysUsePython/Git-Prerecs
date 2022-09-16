
import java.util.*;
import java.io.*;
import java.security.*;
import java.math.BigInteger;

public class Tree {
	
	private HashMap<String, String> map;

	
	public Tree() throws IOException {
		
	}
	
	private void saveList() throws IOException {
		
	}
	
	private static String getSHA1(String input) throws IOException, NoSuchAlgorithmException {
		 FileInputStream fileInputStream = new FileInputStream(input);
         MessageDigest digest = MessageDigest.getInstance("SHA-1");
         DigestInputStream digestInputStream = new DigestInputStream(fileInputStream, digest);
         byte[] bytes = new byte[1024];

         while (digestInputStream.read(bytes) > 0);

         byte[] resultByteArry = digest.digest();
         return bytesToHexString(resultByteArry);
	}
	
	public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            int value = b & 0xFF;
            if (value < 16)
                sb.append("0");
            sb.append(Integer.toHexString(value));
        }
        return sb.toString();
    }
}
