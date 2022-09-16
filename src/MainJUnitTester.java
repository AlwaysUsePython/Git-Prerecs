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
	void testInit() {
		Index i = new Index();
		i.initialize();
		
		File file = new File("index.txt");
		assertTrue(file.exists());
		
		Path path = Paths.get("objects");
		assertTrue(Files.exists(path));
	}
	
	@Test
	void testBlob() throws Exception {
		Blob b = new Blob("something.txt");
		
		File file = new File("objects/1af17e73721dbe0c40011b82ed4bb1a7dbe3ce29");
		assertTrue(file.exists());
	}
	
	@Test
	void testAdd() throws Exception {
		Index i = new Index();
		i.initialize();
		i.add("another.txt");
		i.add("onemore.txt");
		
		File file1 = new File("objects/b7c8ffb8fbc67c171328e0e8f643694e8e61b3359");
//		assertTrue(file1.exists());
//		File file2 = new File("objects/815b3ec88ab222fd849e02e19613a69ab52f5bae");
//		assertTrue(file2.exists());
	}

	@Test
	void testRemove() throws IOException {
		Index i = new Index();
		i.initialize();
		i.remove("another.txt");
		
		File file = new File("objects/b7c8ffb8fbc67c171328e0e8f643694e8e61b335");
		assertTrue(file.exists());
	}
	
	@Test 
	void testTree() throws NoSuchAlgorithmException, IOException {
		File f = new File("objects");
		f.mkdir();
		
		ArrayList<String> stuff = new ArrayList<String>();
		stuff.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f");
		stuff.add("blob : 01d82591292494afd1602d175e165f94992f6f5f");
		stuff.add("blob: f1d82236ab908c86ed095023b1d2e6ddf78a6d83");
		stuff.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
		stuff.add("tree: e7d79898d3342fd15daf6ec36f4cb095b52fd976");
		
		Tree test = new Tree(stuff);
		test.writePAirs();
		File file1 = new File("objects/827fdb56e44ab6415f0a684d6bc85e16b8bc9c1f");
		assertTrue(file1.exists());
	}
}
