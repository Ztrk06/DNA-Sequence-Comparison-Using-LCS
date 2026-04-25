public class LongestCommonString {

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
}
