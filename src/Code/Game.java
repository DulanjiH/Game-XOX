package Code;

import GUI.TicTacToe;
import java.util.stream.IntStream;
import sun.reflect.generics.tree.Tree;

/**
 *
 * @author Dulanji
 */
public class Game {
    

    private static int[][] winningCombinations = {
        {0, 1, 2},
        {3, 4, 5},
        {6, 7, 8},
        {0, 3, 6},
        {1, 4, 7},
        {2, 5, 6},
        {0, 4, 8},
        {6, 4, 2}
    };

    private boolean isOnGoing;

    private boolean isXturn;

    private final String[] squares;

    private final TicTacToe ticTacToe;

    public Game(TicTacToe ticTacToe) {
        this.ticTacToe = ticTacToe;
        isOnGoing = true;
        isXturn = true;
        squares = new String[9];
        IntStream.range(0, 9).forEach(index -> squares[index] = "-");
    }

    public void mark(int index) {
         int finalindex=0;
        if (squares[index].equals("-")) {
            squares[index] = isXturn ? "x" : "o";
             finalindex=index;
            System.out.println(squares[index]);
            if (calculateVictory()) {
                isOnGoing = !isOnGoing;
                ticTacToe.announceVictory(isXturn ? "x" : "o");
                 ticTacToe.updateMove(finalindex,isXturn ? "x" : "o");
            } else {
                if (calculateDraw()) {
                    isOnGoing = !isOnGoing;
                    ticTacToe.announceDraw();
                } else {
                    ticTacToe.updateMove(index, isXturn ? "x" : "o");
                    isXturn = !isXturn;
                   
                }
            }
        }

    }

    private boolean calculateVictory() {
        long count = IntStream.range(0, winningCombinations.length).filter(index -> {
            int[] winningPosibility = winningCombinations[index];
            return squares[winningPosibility[0]].equals(squares[winningPosibility[1]]) && squares[winningPosibility[1]].equals(squares[winningPosibility[2]]) && !squares[winningPosibility[2]].equals("-");
        }).count();
        //  System.out.println("Count "+count);
        return count > 0;
    }

    private boolean calculateDraw() {
        return IntStream.range(0, squares.length).filter(index -> squares[index].equals("-")).count() == 0;
    }
}
