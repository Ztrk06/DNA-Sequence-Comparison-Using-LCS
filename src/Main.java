public class Main {

    public static void main(String[] args) {

        System.out.println(FileOperations.loadTxtAndMerge("homosapiens.txt"));
        System.out.println(FileOperations.loadTxtAndMerge("chimpanzee.txt"));

        String homosapiens = FileOperations.loadTxtAndMerge("homosapiens.txt");
        String chimpanzee = FileOperations.loadTxtAndMerge("chimpanzee.txt");
        int[][] arr = LongestCommonString.LCS_len(homosapiens, chimpanzee);
        int rowlen = arr.length;
        int collen = arr[0].length;
        System.out.println(arr[rowlen-1][collen-1]);
        LongestCommonString.printLCS(LongestCommonString.LCS_str(homosapiens,chimpanzee,arr),homosapiens,rowlen-1,collen-1);


    }
}
