public class Main {

    public static void main(String[] args) {

        System.out.println(FileOperations.loadTxtAndMerge("homosapiens.txt"));
        System.out.println(FileOperations.loadTxtAndMerge("chimpanzee.txt"));

        String homosapiens = FileOperations.loadTxtAndMerge("homosapiens.txt");
        String chimpanzee = FileOperations.loadTxtAndMerge("chimpanzee.txt");
        int[][] arr = LongestCommonString.LCS_len(homosapiens, chimpanzee);
        System.out.println(arr[arr.length-1][arr[0].length-1]);


    }
}
