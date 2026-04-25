public class Main {

    public static void similarity(String X, String Y) {
        int[][] a = LongestCommonString.LCS_len(X, Y);
        int longest_substring_length = a[a.length-1][a[0].length-1];
        int maxSizedString = Math.max(X.length(), Y.length());
        double percSimilarity = ((double)longest_substring_length / maxSizedString) * 100;
        System.out.println("Human and Chimpanzee DNA Similarity Ratio: " + percSimilarity);
    }

    public static void main(String[] args) {

        /* TASK 1-2 */
        String homosapiens = FileOperations.loadTxtAndMerge("homosapiens.txt");
        String chimpanzee = FileOperations.loadTxtAndMerge("chimpanzee.txt");

        System.out.println("Homosapiens DNA: "+homosapiens);
        System.out.println("-");
        System.out.println("Chimpanzee DNA: "+chimpanzee);
        System.out.println("-");


        /* TASK 4 */
        int[][] a = LongestCommonString.LCS_len(homosapiens, chimpanzee);
        char[][] b = LongestCommonString.LCS_str(homosapiens,chimpanzee,a);
        int rowlen = a.length;
        int collen = a[0].length;
        System.out.println("Longest Common Substring Size: "+a[rowlen-1][collen-1]);
        System.out.println("-");
        System.out.print("Longest Common Substring: ");
        LongestCommonString.printLCS(b,homosapiens,rowlen-1,collen-1);
        System.out.println();
        System.out.println("-");

        /* TASK 5 */
        LongestCommonString.print5Subsequence(b,homosapiens);
        System.out.println();


        similarity(homosapiens,chimpanzee);

    }
}
