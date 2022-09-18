import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Commit {
	private Commit parent = null; 
	private Commit connected = null; // will this always be null?
	private String commitLocation = "";
	private String pTree; 
	public String summary;
	public String author;
	public String date; 
	public Commit(String value, String summary, String author, Commit parentPointer) throws NoSuchAlgorithmException, IOException
	{
		pTree = value; 
		this.summary = summary;
		this.author = author; 
		parent = parentPointer; 
		commitLocation = getLocation();
	}
	
	public String getDate()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		date = dateFormat.format(cal.getTime()); // should I use the member variable here?
		return date; 
	}
	
	public static String SHA1 (String contents) throws NoSuchAlgorithmException, IOException
	{
		FileInputStream fileInputStream = new FileInputStream(contents);
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        DigestInputStream digestInputStream = new DigestInputStream(fileInputStream, digest);
        byte[] bytes = new byte[1024];
        // read all file content
        while (digestInputStream.read(bytes) > 0);

//        digest = digestInputStream.getMessageDigest();
        byte[] resultByteArry = digest.digest();
        return bytesToHexString(resultByteArry);
	}
	
	public static String bytesToHexString(byte[] bytes)
	{
		 StringBuilder sb = new StringBuilder();
         for (byte b : bytes) {
             int value = b & 0xFF;
             if (value < 16) {
                 // if value less than 16, then it's hex String will be only
                 // one character, so we need to append a character of '0'
                 sb.append("0");
             }
             sb.append(Integer.toHexString(value));
         }
         return sb.toString();
	}
	
	public String getContents() throws NoSuchAlgorithmException, IOException
	{
		String content = ""; 
		content += pTree + "/n"; 
		if (parent != null)
		{
			content += parent.getLocation() + "/n";
		}
		else
		{
			content += "/n";
		}
		if (connected != null)
		{
			content += connected.getLocation() + "/n";
		}
		else
		{
			content += "/n";
		}
		content += author + "/n";
		content += date + "/n";
		content += summary + "/n"; 
		return content;
		
	}
	
	public String getLocation() throws NoSuchAlgorithmException, IOException
	{
		return SHA1(getContents()); 
	}
	
	public void writeFile() throws NoSuchAlgorithmException, IOException
	{
		Path p = Paths.get(SHA1(getContents()));
        try {
            Files.writeString(p, getContents(), StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

}
