//Bela Gupta
//May 19, 2022
//Connect 4 game using X's and O's

import java.util.Scanner;

public class Connect4
{
    public static boolean winner (char[][] arr) {
        int rightConnect = 0;
        int upConnect = 0;
        int leftDiagConnect = 0;
        int rightDiagConnect = 0;
        boolean winner = false;
        for(int i=arr.length-1; i>=0; i--) {
            for(int j=0; j<arr.length; j++) {
                if(arr[i][j]=='2'||arr[i][j]=='1') {
                    rightConnect = 0;
                    upConnect = 0;
                    leftDiagConnect = 0;
                    rightDiagConnect = 0;
                    for(int x=1; x<4; x++) {
                        if(j+3<arr.length&&arr[i][j]==arr[i][j+x])
                                rightConnect++;
                        if(i-3>=0&&arr[i][j]==arr[i-x][j])
                                upConnect++;
                        if(i-3>=0&&j+3<arr.length&&arr[i][j]==arr[i-x][j+x])
                                rightDiagConnect++;
                        if(i-3>=0&&j-3>=0&&arr[i][j]==arr[i-x][j-x])
                                leftDiagConnect++;
                    }
                    if(rightConnect==3||upConnect==3||rightDiagConnect==3||leftDiagConnect==3)
                        winner=true;
                }
            }
        }
        return winner;
    }
    
    public static void place (char[][] arr, int c, char player) {
        boolean placed = false;
        int r=arr.length-1;
        while(!placed&&r>=0) {
            if(arr[r][c]=='-') {
                arr[r][c] = player;
                placed = true;
            }
            r--;
        }
    }
    
    public static void printBoard (char[][] arr) {
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr.length; j++) {
                char letter = ' ';
                if(arr[i][j]=='1') letter = 'X';
                else if(arr[i][j]=='2') letter = 'O';
                else letter = '-';
                System.out.printf("%-2c", letter);
            }
            System.out.println();
        }
    }
    
    public static void prepBoard (char[][] arr) {
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr.length; j++) {
                arr[i][j] = '-';
            }
        }
    }

    public static void main(String[] args)
    {
        Scanner kb = new Scanner(System.in);
        char[][] board = new char[7][7];
        int turn = 0;
        int column = 0;
        
        prepBoard(board);
        printBoard(board);
        
        while(!winner(board)) {
            if(turn%2==0) {
                System.out.print("Player 1's turn. Player 1 chooses column ");
                column = kb.nextInt();
                while(column<1||column>7) {
                    System.out.println("Column "+column+" is not valid. Please choose a differnt column.");
                    column = kb.nextInt();
                }
                place(board, column-1, '1');
            }
            else {
                System.out.print("Player 2's turn. Player 2 chooses column ");
                column = kb.nextInt();
                while(column<1||column>7) {
                    System.out.println("Column "+column+" is not valid. Please choose a differnt column.");
                    column = kb.nextInt();
                }
                place(board, column-1, '2');
            }
            printBoard(board);
            if(winner(board)) {
                if(turn%2==0)
                    System.out.print("Player 1 wins with 4 Xs in a row!");
                else
                    System.out.print("Player 2 wins with 4 Os in a row!");
            }
            turn++;
        }
    }
}