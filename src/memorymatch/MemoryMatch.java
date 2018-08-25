package memorymatch;

import java.util.Scanner;
import java.util.Random;

public class MemoryMatch 
{

    private static int[][] playerboard;
    private static char[][] uiboard;
    private static int size = 0;
    private static int win = 0;
    
    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
        int row1, col1, row2, col2, temp1, temp2;
        int boardcount = 2;
        
        System.out.println("Welcome to the Memory-Matching Game!");
        System.out.println("Select the board size you would like to play:");
        size = in.nextInt();
        //generate the UI array
        generateBoard();
        //generate the player's option array
        playBoard();
        printUI();
        
        do
        {
        System.out.println("Select the row of your first guess:");
        row1 = in.nextInt();
        System.out.println("Select the column of your first guess:");
        col1 = in.nextInt();
        
        //make sure the input they enter is valid
        while(row1>size||col1>size||row1<0||col1<0||uiboard[row1-1][col1-1] != '*')
        {
            System.out.println("Invalid option, try again!");
            System.out.println("Select the row of your first guess:");
            row1 = in.nextInt();
            System.out.println("Select the column of your first guess:");
            col1 = in.nextInt();
        }
        
        //store the choice in a temp variable for checking later
        temp1 = playerboard[row1-1][col1-1];
        //cast the player's choice as a character
        //in order to store it in our uiboard array of char data type
        uiboard[row1-1][col1-1] = (char) (playerboard[row1-1][col1-1]+48);
        printUI();
        
        System.out.println("Select the row of your second guess:");
        row2 = in.nextInt();
        System.out.println("Select the column of your second guess:");
        col2 = in.nextInt();
        
        while(row2>size||col2>size||row2<0||col2<0||uiboard[row2-1][col2-1] != '*')
        {
            System.out.println("Invalid option, try again!");
            System.out.println("Select the row of your second guess:");
            row2 = in.nextInt();
            System.out.println("Select the column of your second guess:");
            col2 = in.nextInt();
        }
        
        temp2 = playerboard[row2-1][col2-1];
        uiboard[row2-1][col2-1] = (char) (playerboard[row2-1][col2-1]+48);
        printUI();
        
        //check to see if the player's choices match
        if(temp1 != temp2)
        {
            uiboard[row1-1][col1-1] = '*';
            uiboard[row2-1][col2-1] = '*';
            System.out.println("Sorry, they do not match!");
        }
        else
        {
            System.out.println("That's a match!");
            //increment this for win condition
            //player matched -2- options, meaning two spots on the board are covered
            //once all are covered, or boardcount> the number of places on the board
            //win condition is set to 1, and the loop ends
            boardcount += 2;
        }
        
        //check to see if the board is still covered
        if(boardcount > (size*2))
        {
            win = 1;
            System.out.println("Congratulations, you win!");
        }
        printUI();
        }while(win==0);
    }
    
    //print the UI using multiple for loops
    public static void printUI()
    {
        System.out.print("   ");
        for(int i = 0; i < size; i++)
        {
            System.out.print("   "+(i+1));
        }
        System.out.println();
        System.out.print("   ");
        for(int j = 0; j < size; j++)
        {
            System.out.print("----");
        }
        System.out.println();
        for(int k = 0; k < uiboard.length; k++)
        {
            System.out.print((k+1)+" |");
            for(int l = 0; l < uiboard[k].length; l++)
            {
                    System.out.print("   "+uiboard[k][l]);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    //fill the uiboard with * to be displayed in the printUI method
    //and to be changed when a player guesses correctly
    public static void generateBoard()
    {
        uiboard = new char[size][size];
        for(int i = 0; i < uiboard.length; i++)
        {
            for (int j = 0; j < uiboard[i].length; j++)
            {
                uiboard[i][j] = '*';
            }
        }
    }
    
    //generate the player's board that the player will be guessing from
    //randomize it
    public static void playBoard()
    {
        Random rnd = new Random();
        int count1 = 0;
        int count2 = 1;
        
        playerboard = new int[size][size];
        
        //generate the player board
        //since numbers need to be repeated, and since one count variable doesn't
        //work for an oddxodd array, use a second count variable to keep track
        //of where the loop is, so that if, for example, the loop ends at 3 for
        //1 1 2 2 3, it will continue the next row at the next 3, or 3 4 4 5 5
        //resulting in 
        //1 1 2 2 3
        //3 4 4 5 5
        for(int i = 0; i < playerboard.length; i++)
        {
            for (int j = 0; j < playerboard[i].length; j++)
            {
                count2++;
                if(count2%2==0)
                {
                    count1 = count1+1;
                }
                playerboard[i][j] = count1;
            }
        }
        
        //randomize the board
        for (int i = 0; i < playerboard.length; i++) 
        {
            for (int j = 0; j < playerboard[i].length; j++) 
            {
                int a = rnd.nextInt(i + 1);
                int b = rnd.nextInt(j + 1);

                int temp = playerboard[i][j];
                playerboard[i][j] = playerboard[a][b];
                playerboard[a][b] = temp;
            }
        }
        /*
        testing the randomize loop
        for(int i = 0; i < playerboard.length; i++)
        {
            for (int j = 0; j < playerboard[i].length; j++)
            {
                System.out.printf("%3d",playerboard[i][j]);
            }
            System.out.println();
        }
        */
    }
}