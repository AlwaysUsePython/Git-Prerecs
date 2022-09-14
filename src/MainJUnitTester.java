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

class MainJUnitTester {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
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
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
//		File f1 = new File("index.txt");
//		f1.delete();
//		
//		File f2 = new File("something.txt");
//		f2.delete();
//		
//		File f3 = new File("another.txt");
//		f3.delete();
//		
//		File f4 = new File("objects");
//		f4.delete();
	}

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
		Blob b = new Blob("testFile.txt");
		
		File file = new File("objects/be76136a108da89559231f95ebd847b70d78e9e6");
		assertTrue(file.exists());
	}
	
	@Test
	void testAdd() throws Exception {
		Index i = new Index();
		i.add("something.txt");
		i.add("another.txt");
		
		File file = new File("objects/1af17e73721dbe0c40011b82ed4bb1a7dbe3ce29");
		assertTrue(file.exists());
	}

	@Test
	void testRemove() throws IOException {
		Index i = new Index();
		i.remove("something.txt");
		
		File file = new File("objects/1af17e73721dbe0c40011b82ed4bb1a7dbe3ce29");
		assertTrue(!file.exists());
	}
}
