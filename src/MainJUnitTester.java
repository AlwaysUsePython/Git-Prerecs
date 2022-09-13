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
//		Utils.writeStringToFile("junit.txt", "JUnit Data1");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
//		Utils.deleteFile("junit.txt");
	}

	@Test
	void testInit() {
		Index i = new Index();
		i.initialize();
		
		File file = new File("index.txt");
		assertTrue(file.exists());
		
		Path path = Paths.get("test/objects");
		assertTrue(Files.exists(path));
	}
	
	@Test
	void testBlob() throws Exception {
		Blob b = new Blob("testFile.txt");
		
		File file = new File("test/objects/");
		assertTrue(file.exists());
	}
	
	@Test
	void testAdd() throws Exception {
		
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
        
		Index i = new Index();
		i.add("something.txt");
		i.add("another.txt");
		
		File file = new File("test/objects/");
		assertTrue(file.exists());
	}

	@Test
	void testRemove() throws IOException {
		Index i = new Index();
		i.remove("something.txt");
		
		File file = new File("test/objects/");
		assertTrue(!file.exists());
	}
}
