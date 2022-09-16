
import java.util.*;
import java.io.*;
import java.security.*;
import java.math.BigInteger;

public class Tree {
	
	private ArrayList<String> arr;
	private String str;
	
	public Tree(ArrayList<String> list) throws IOException, NoSuchAlgorithmException {
		arr = list;
		str = "";
		for (int i = 0; i < arr.size(); i++)
			str += arr.get(i) + "\n";
		if (str.length() > 0)
			str = str.substring(0, str.length()-1);
		str = getSHA1(str);
	}
	
	private void saveList() throws IOException {
		PrintWriter pw = new PrintWriter(new File("test/objects" + str + "txt"));
		for (String s : arr)
			pw.append(s + "\n");
		pw.close();
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
