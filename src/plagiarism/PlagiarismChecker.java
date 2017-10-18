package plagiarism;

public class PlagiarismChecker {
	public static void main(String[] args) {
		String[] documents = { 
				"original.txt",
				"replace2.txt",
				"replace4.txt",
				"replace6.txt",
				"rearrange.txt",
				"paraphrase.txt",
				"unrelated.txt",
				"intersect0.txt"
		};
		String folder = "assets\\";
		for (int i=0; i<documents.length; i++)
			documents[i] = folder + documents[i];
		
		int kSize = 1;
		for (int i=0; i<documents.length; i++) {
			for (int w=0; w<5; w++) {
				Fingerprint original = new Fingerprint(documents[0], kSize, kSize+w);
				Fingerprint other = new Fingerprint(documents[i], kSize, kSize+w);
				System.out.println(i + " " + w + " " + original.compare(other));				
			}
		}
	}
}
