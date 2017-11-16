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
				"intersect0.txt",
				"similar.txt"
		};
		String folder = "assets\\test2\\";
		for (int i=0; i<documents.length; i++)
			documents[i] = folder + documents[i];
		
		int domainSize = 7;
		double[][] results = new double[documents.length][domainSize];
		int window = 3;
		for (int i=0; i<documents.length; i++) {
			for (int kSize=1; kSize<domainSize+1; kSize++) {
				Fingerprint original = new Fingerprint(documents[0], kSize, kSize+window);
				Fingerprint other = new Fingerprint(documents[i], kSize, kSize+window);
				results[i][kSize-1] = original.compare(other);				
			}
		}
		
//		int kSize = 3;
//		for (int i=0; i<documents.length; i++) {
//			for (int w=0; w<domainSize; w++) {
//				Fingerprint original = new Fingerprint(documents[0], kSize, kSize+w);
//				Fingerprint other = new Fingerprint(documents[i], kSize, kSize+w);
//				results[i][w] = original.compare(other);				
//			}
//		}
		for (double[] row : results) {
			for(double d : row)
				System.out.print(d + "\t");
			System.out.println();
		}
	}
}
