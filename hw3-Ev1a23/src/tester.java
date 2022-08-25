import java.util.Arrays;

public class tester {
    public static void main(String[] args) {
        // question 1
        int[] arr1 = {1, 2, 3, 4, 5};
        question01(arr1, -1, 'R');
        int[] arr2 = {1, 2, 3, 4, 5};
        question01(arr2, 1, 'R');
        int[] arr3 = {1, 2, 3, 4, 5};
        question01(arr3, 1, 'r');
        int[] arr4 = {1, 2, 3, 4, 5};
        question01(arr4, -2, 'g');
        int[] arr5 = {1, 2, 3, 4, 5};
        question01(arr5, 3, 'L');
        int[] arr6 = {1, 2, 3, 4, 5};
        question01(arr6, -3, 'L');
        int[] arr7 = {0, 8, 9, 5, 6};
        question01(arr7, 6, 'L');
        int[] arr8 = {};
        question01(arr8, 3, 'R');

        // question 2
        int[][] arr9 = {{0,0,0},{0,0,0},{0,0,0}};
        question02(arr9, 0, 1);
        int[][] arr10 = {{0,0,0},{0,0,0},{0,0,0}};
        question02(arr10, 1, 1);
        int[][] arr11 = {{0,1,0,0},{0,0,1,0},{0,0,0,1},{1,0,0,0}};
        question02(arr11, 0, 2);
        int[][] arr12 = {{0,1,0,1},{0,0,0,1},{0,0,0,0},{0,0,1,0}};
        question02(arr12, 0, 2);
        // Dvir's test
        int[][] arr13 = {{0,1,0}, {1,0,1},{0,0,0}};
        question02(arr13, 0, 2);
        int[][] arr14 = {{0,1,0}, {1,0,1},{0,0,0}};
        question02(arr14, 2, 0);

        // question 3
        question03("to be or not to be");
        question03("my mind is an empty zoo");
        question03("");
        question03("andy bought candy");
        question03("life is not not not fair");
        question03("art act");
        question03("c b a");

        // question 4
        String t11 = "dog";
        String t12 = "god";
        question04(t11, t12);
        String t21 = "x";
        String t22 = "x";
        question04(t21, t22);
        String t31 = "main";
        String t32 = "man";
        question04(t31, t32);
        String t41 = "ab";
        String t42 = "cab";
        question04(t41, t42);
    }

    public static void question01(int[] array, int move, char direction) {
        System.out.println(Arrays.toString(ArrayUtils.shiftArrayCyclic(array, move, direction)));
    }

    public static void question02(int[][] array, int i, int j) {
        System.out.println(ArrayUtils.findShortestPath(array, i, j));
    }

    public static void question03(String a) {
        System.out.println(StringUtils.findSortedSequence(a));
    }

    public static void question04(String a, String b) {
        System.out.println(StringUtils.isEditDistanceOne(a, b));
    }
}
