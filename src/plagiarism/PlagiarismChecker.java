package plagiarism;

public class PlagiarismChecker {
	public static void main(String[] args) {
		Fingerprint f = new Fingerprint("example1.txt",4,6);
		System.out.println(f);
	}
}
