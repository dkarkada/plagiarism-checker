package plagiarism;

public class PlagiarismChecker {
	public static void main(String[] args) {
		Fingerprint f1 = new Fingerprint("example2.txt", 3, 5);
		Fingerprint f2 = new Fingerprint("example3.txt", 3, 5);
		System.out.println(f1);
		System.out.println(f2);
		System.out.println(f1.compare(f2));
	}
}
