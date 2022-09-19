import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class CommitTester {

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		System.out.println("\n");
		System.out.println("\n");
		System.out.println("\n");
		System.out.println("\n");
		System.out.println("\n");
		System.out.println("\n");
		System.out.println("\n");
		Commit test = new Commit("i love trees", "summary", "ava weinrot", null); 
		Commit test2 = new Commit("i also love trees", "summary2", "ava weinrot", test); 
	}

}
