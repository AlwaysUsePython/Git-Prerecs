package Testers;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.io.*;
import java.security.*;

class MainJUnitTester {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		File f = new File("objects");
		f.mkdir();
		
		Path p1 = Paths.get("something.txt");
        try {
            Files.writeString(p1, "something", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
		Path p2 = Paths.get("another.txt");
        try {
            Files.writeString(p2, "another", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Path p3 = Paths.get("onemore.txt");
        try {
            Files.writeString(p3, "onemore", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		File f1 = new File("index.txt");
		f1.delete();
		
		File f2 = new File("something.txt");
		f2.delete();
		
		File f3 = new File("another.txt");
		f3.delete();

		File f4 = new File("onemore.txt");
		f4.delete();
		File f4hash = new File("objects/815b3ec88ab222fd849e02e19613a69ab52f5bae");
		f4hash.delete();
		
//		File f5 = new File("objects");
//		deleteDir(f5);
	}
	
//	static void deleteDir(File file) {
//	    File[] contents = file.listFiles();
//	    if (contents != null) {
//	        for (File f : contents)
//	            deleteDir(f);
//	    }
//	    file.delete();
//	}

	
	@Test
	void testBlob() throws Exception {
		Blob b = new Blob("something.txt");
		
		File file = new File("objects/1af17e73721dbe0c40011b82ed4bb1a7dbe3ce29");
		assertTrue(file.exists());
	}
	
}
