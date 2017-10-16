package plagiarism;

public class PlagiarismChecker {
	public static void main(String[] args) {
		Fingerprint f = new Fingerprint("example1.txt", 3, 5);
		System.out.println(f);
	}
}
