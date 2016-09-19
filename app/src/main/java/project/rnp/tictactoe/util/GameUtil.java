package project.rnp.tictactoe.util;

import java.util.ArrayList;
import java.util.List;

import project.rnp.tictactoe.model.Position;

/**
 * Created by lokex on 9/18/16.
 */
public class GameUtil {


    private List<Position> positionsToMark;
    private List<Position> positionsList;

    private Position.State currentPlayer;
    public boolean gameOver = false;


    public GameUtil(){}


    public void checkGameOver(List<Position> positionsList,int pos, Position.State currentPlayer){


        this.currentPlayer = currentPlayer;
        this.positionsList = positionsList;
        gameOver = false;

        positionsToMark = new ArrayList<>();

        switch (pos){

            case 0:
                checkIndex(0,1,2);//first row
                if(!gameOver) checkIndex(0,3,6);//first column
                if(!gameOver) checkIndex(0,4,8);//diagonal leftbottom-righttop
                break;

            case 1:
                checkIndex(1,4,7);//2nd column
                if(!gameOver) checkIndex(0,1,2);//first row
                break;

            case 2:
                checkIndex(0,1,2);//first row
                if(!gameOver) checkIndex(2,5,8);//third column
                if(!gameOver) checkIndex(2,4,6);//diagonal right bottom- left top

                break;

            case 3:
                checkIndex(3,4,5);//second row
                if(!gameOver) checkIndex(0,3,6);//first column
                break;

            case 4:
                checkIndex(3,4,5);//second row
                if(!gameOver) checkIndex(1,4,7);//2nd column
                if(!gameOver) checkIndex(0,4,8);//diagonal leftbottom-righttop
                if(!gameOver) checkIndex(2,4,6);//diagonal right bottom- left top
                break;

            case 5:
                checkIndex(3,4,5);//second row
                if(!gameOver) checkIndex(2,5,8);//third column
                break;

            case 6:
                checkIndex(6,7,8);//third row
                if(!gameOver) checkIndex(0,3,6);//first column
                if(!gameOver) checkIndex(2,4,6);//diagonal right bottom- left top
                break;

            case 7:
                checkIndex(6,7,8);//third row
                if(!gameOver) checkIndex(1,4,7);//2nd column
                break;

            case 8:
                checkIndex(6,7,8);//third row
                if(!gameOver) checkIndex(2,5,8);//third column
                if(!gameOver) checkIndex(0,4,8);//diagonal leftbottom-righttop
                break;
        }


    }

    public void checkIndex(int i,int j, int k){
        if(positionsList.get(i).getState().equals(currentPlayer) &&
                positionsList.get(j).getState().equals(currentPlayer) &&
                positionsList.get(k).getState().equals(currentPlayer)){

            gameOver = true;
            disableAllCells();

            positionsToMark.add(positionsList.get(i));
            positionsToMark.add(positionsList.get(j));
            positionsToMark.add(positionsList.get(k));

        }
    }

    public List<Position> getPositionsToMark() {
        return positionsToMark;
    }

    public void disableAllCells(){
        for (Position position:positionsList) {
            position.setEnabled(false);
        }
    }


}
