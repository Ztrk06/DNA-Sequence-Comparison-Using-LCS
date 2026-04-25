import java.util.Random;
public class LongestCommonString {

    static Random random = new Random();

    /* TASK 3 */
    public static int[][] LCS_len(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();

        int[][] length = new int[len1][len2];

        for(int i = 0; i < len1; i++)length[i][0]= 0;
        for(int j = 0; j < len2; j++)length[0][j] = 0;

        for(int i = 1; i < len1; i++) {
            for(int j = 1; j < len2; j++) {
                if(s1.charAt(i) == s2.charAt(j)) length[i][j] = length[i-1][j-1] + 1;
                else if(length[i-1][j] > length[i][j-1]) length[i][j] = length[i-1][j];
                else length[i][j] = length[i][j-1];
            }
        }
       return length;
    }

    public static char[][] LCS_str(String s1, String s2, int[][] len) {
        int len1 = s1.length();
        int len2 = s2.length();

        char[][] str = new char[len1][len2];

        for(int i = 1; i < len1; i++){
            for(int j = 1; j < len2; j++){
                if(s1.charAt(i) == s2.charAt(j)) str[i][j] = 'D';
                else if(len[i-1][j] > len[i][j-1]) str[i][j] = 'U';
                else str[i][j] = 'L';
            }
        }
        return str;
    }

    public static void printLCS(char[][] LCS, String X, int i, int j) {

        if(i==0 && j==0) return;
        if(LCS[i][j]=='D'){
            printLCS(LCS,X,i-1,j-1);
            System.out.print(X.charAt(i));
        }
        else if(LCS[i][j]=='U') printLCS(LCS,X,i-1,j);
        else if(LCS[i][j]=='L')printLCS(LCS,X,i,j-1);

    }

    /* TASK 5 */
    public static void print5Subsequence(char[][] LCS, String X) {
        int i = X.length();
        int j = LCS[0].length;
        for(int k = 0; k < 5; k++){
            int randomnum = random.nextInt(500);
            int randomnum2 = random.nextInt(500);
            printLCS(LCS,X,i-randomnum,j-randomnum2);
            System.out.println();
        }

    }



}
