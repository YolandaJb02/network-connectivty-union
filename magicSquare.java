/*Yolanda Exalus
COP3503 */

import java.util.*;

public class Main {
    static Scanner in = new Scanner(System.in);
    private int[][] square;
    private int magicConst;
    private int totalSqs;
    private boolean[] possible;
    private int numSquares;

    public int findMagicConstant(int N) {
        return N * ((N * N) + 1) / 2;
    }

    public Main(int N) {
        if (N < 3) {
            System.out.print("Only give values > 2!");
            System.exit(1);
        }

        square = new int[N][N];
        magicConst = this.findMagicConstant(N);
        totalSqs = N * N;

        //mark all numbers as available

        possible = new boolean[totalSqs];
        for (int i = 0; i < totalSqs; i++)
            possible[i] = true;

        numSquares = 0;
    }


    //check if the current square is a valid magic square
    public boolean validSquare() {

        //check row sums
        for (int i = 0; i < square.length; i++) {
            int rowSum = 0;
            for (int j = 0; j < square[i].length; j++) {
                rowSum += square[i][j];

                //square is not complete
                if (square[i][j] == 0)
                    return false;
            }

            //row does not equal magic constant
            if (rowSum != magicConst)
                return false;
        }


        //check column sums
        for (int c = 0; c < square.length; c++) {
            int colSum = 0;
            for (int r = 0; r < square.length; r++) {
                colSum += square[r][c];
            }
            if (colSum != magicConst)
                return false;
        }



        //Check main diagonal

      
        int diag1Sum = 0;
        for (int r = 0; r < square.length; r++) {
            diag1Sum += square[r][r];
        }
        if (diag1Sum != magicConst)
            return false;


        //Check secondary diagonal
        int diag2Sum = 0;
        for (int r = 0; r < square.length; r++) {
            diag2Sum += square[r][square.length - 1 - r];
        }
        if (diag2Sum != magicConst)
            return false;

        return true;
    }

    public void fill(int row, int col) {
        if (row == square.length) {
            
            if (validSquare()) {
                System.out.println("Done!!");


                for (int[] rws : square)
                    System.out.println(Arrays.toString(rws));

                numSquares++;
            }
            return;
        }

        for (int i = 0; i < totalSqs; i++) {
            if (possible[i]) {
                square[row][col] = i + 1;
                possible[i] = false;

                int nextcol = col + 1;
                int nextrow = row;

                if (nextcol == square.length) {
                    nextrow++;
                    nextcol = 0;
                }

                fill(nextrow, nextcol);

                square[row][col] = 0;
                possible[i] = true;
            }
        }
    }

    public static void main(String[] args) {
        int N = in.nextInt();
        System.out.println("N = " + N);
        Main ms = new Main(N);
        ms.fill(0, 0);
    }
}