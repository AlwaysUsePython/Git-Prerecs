import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class CommitTester {

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		Commit test = new Commit("i love trees", "summary", "ava weinrot", null); 
		String returned = Commit.readFile("objects/68c226fbb0df86dabfcb5067ba40d8534229d27e");
		System.out.println(returned); 
	}

}
