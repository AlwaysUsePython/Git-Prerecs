
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;

	 
	public class Blob {
	    public Blob(String filePath) throws FileNotFoundException, IOException, NoSuchAlgorithmException
	    {
	    	String fileName = sha1Code(filePath); 
	    	File testFile = new File(fileName); 
	    	PrintWriter write = new PrintWriter(testFile);
	    	write.print(readFile(filePath, StandardCharsets.UTF_8));
	    	write.close();
	    	/*File theDir = new File("/path/directory");
	    	if (!theDir.exists())
	    	{
	    		theDir.mkdirs();
	    	}*/
	    }
	   
	    // Function To Make New File
	    /*public void newFile(String filePath, String myHash)
	    {
	        String strPath = "", strName = "";
	  
	        // Try-catch Block
	        try {
	  
	            // Creating BufferedReadered object
	            BufferedReader br = new BufferedReader(
	                new InputStreamReader(System.in));
	            System.out.println("SHA1 Hash");
	  
	            // Reading File name
	            strName = br.readLine();
	            System.out.println("/Users/avagrace/eclipse-workspace/Git-Prerecs/Object" + "SHA1 Hash");
	  
	            // Reading File Path
	            strPath = br.readLine();
	  
	            // Creating File Object
	            File file1
	                = new File(strPath + "" + strName + ".txt");
	  
	            // Method createNewFile() method creates blank
	            // file.
	            file1.createNewFile();
	        }
	  
	        // Try-Catch Block
	        catch (Exception ex1) {
	        }
	    }*/
	    

	        /**
	         * Generate a file 's sha1 hash code.
	         * @param filePath file path
	         * @return sha1 hash code of this file
	         * @throws IOException if file doesn't or other IOException
	         * @throws NoSuchAlgorithmException
	         */
	        public String sha1Code(String filePath) throws IOException, NoSuchAlgorithmException {
	            FileInputStream fileInputStream = new FileInputStream(filePath);
	            MessageDigest digest = MessageDigest.getInstance("SHA-1");
	            DigestInputStream digestInputStream = new DigestInputStream(fileInputStream, digest);
	            byte[] bytes = new byte[1024];
	            // read all file content
	            while (digestInputStream.read(bytes) > 0);

//	            digest = digestInputStream.getMessageDigest();
	            byte[] resultByteArry = digest.digest();
	            return bytesToHexString(resultByteArry);
	        }

	        /**
	         * Convert a array of byte to hex String. <br/>
	         * Each byte is covert a two character of hex String. That is <br/>
	         * if byte of int is less than 16, then the hex String will append <br/>
	         * a character of '0'.
	         *
	         * @param bytes array of byte
	         * @return hex String represent the array of byte
	         */
	        public static String bytesToHexString(byte[] bytes) {
	            StringBuilder sb = new StringBuilder();
	            for (byte b : bytes) {
	                int value = b & 0xFF;
	                if (value < 16) {
	                    // if value less than 16, then it's hex String will be only
	                    // one character, so we need to append a character of '0'
	                    sb.append("0");
	                }
	                sb.append(Integer.toHexString(value).toUpperCase());
	            }
	            return sb.toString();
	        }
	        
	        public static String readFile(String path, Charset encoding) throws IOException
	        {
	            byte[] encoded = Files.readAllBytes(Paths.get(path));
	            return new String(encoded, encoding);
	        }
		
		/*public static String encryptThisString(String input)
	    {
	        try {
	            // getInstance() method is called with algorithm SHA-1
	            MessageDigest md = MessageDigest.getInstance("SHA-1");
	 
	            // digest() method is called
	            // to calculate message digest of the input string
	            // returned as array of byte
	            byte[] messageDigest = md.digest(input.getBytes());
	 
	            // Convert byte array into signum representation
	            BigInteger no = new BigInteger(1, messageDigest);
	 
	            // Convert message digest into hex value
	            String hashtext = no.toString(16);
	 
	            // Add preceding 0s to make it 32 bit
	            while (hashtext.length() < 32) {
	                hashtext = "0" + hashtext;
	            }
	 
	            // return the HashText
	            return hashtext;
	        }
	 
	        // For specifying wrong message digest algorithms
	        catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException(e);
	        }
	    }*/
	 
	    // Driver code
	    /*public static void main(String args[]) throws
	                                       NoSuchAlgorithmException
	    {
	 
	        System.out.println("HashCode Generated by SHA-1 for: ");
	 
	        String s1 = "GeeksForGeeks";
	        System.out.println("\n" + s1 + " : " + encryptThisString(s1));
	 
	        String s2 = "hello world";
	        System.out.println("\n" + s2 + " : " + encryptThisString(s2));
	    }*/
	}
