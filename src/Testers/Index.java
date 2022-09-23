package Testers;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Index {
public HashMap<String, String> fileInfo; 

public Index()
{
	fileInfo = new HashMap<String, String>(); 
}
	
public void initialize() 
	{
//		File file = new File("index");
//		boolean fileCreated = false;
		Path p = Paths.get("index");
        try {
            Files.writeString(p, "", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//	    	try {
//	            fileCreated = file.createNewFile();
//	        }
//	        catch (IOException ioe) {
//	            System.out.println("Error while creating empty file: " + ioe);
//	        }
//	 
//	        if (fileCreated) {
//	            System.out.println("Created empty file: " + file.getPath());
//	        }
//	        else {
//	            System.out.println("Failed to create empty file: " + file.getPath());
//	        }
	        
	        File theDir = new File("/path/objects");
			if (!theDir.exists()){
			    theDir.mkdirs();
			}
	  }

public void add(String fileName) throws Exception
{
	Blob newBlob = new Blob(fileName); 
	String hash = newBlob.sha1Code(fileName);
	System.out.println(hash);
	String updated = "objects/" + hash; 
	System.out.println(updated);
	fileInfo.put(fileName, updated);
	clearTheFile();
	readHashContent(); 
}

public void remove(String fileName) throws IOException
{
	if (fileInfo.containsKey(fileName))
	{
		String hash = fileInfo.get(fileName);
		fileInfo.remove(fileName, hash); 
		clearTheFile();
		readHashContent(); 
	}
	else
	{
		return;
	}
}

public static void clearTheFile() throws IOException {
    FileWriter fwOb = new FileWriter("index.txt", false); 
    PrintWriter pwOb = new PrintWriter(fwOb, false);
    pwOb.flush();
    pwOb.close();
    fwOb.close();
}

public void readHashContent()
{

    BufferedWriter bf = null;

    try {

        // create new BufferedWriter for the output file
        bf = new BufferedWriter(new FileWriter("index.txt"));

        // iterate map entries
        for (Map.Entry<String, String> entry :
             fileInfo.entrySet()) {

            // put key and value separated by a colon
            bf.write(entry.getKey() + ":"
                     + entry.getValue());

            // new line
            bf.newLine();
        }

        bf.flush();
    }
    catch (IOException e) {
        e.printStackTrace();
    }
    finally {

        try {

            // always close the writer
            bf.close();
        }
        catch (Exception e) {
        }
    }
}
}
