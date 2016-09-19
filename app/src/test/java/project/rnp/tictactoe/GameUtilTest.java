package project.rnp.tictactoe;

import android.os.Build;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import project.rnp.tictactoe.model.Position;
import project.rnp.tictactoe.util.GameUtil;

/**
 * Created by lokex on 9/19/16.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,sdk= Build.VERSION_CODES.LOLLIPOP)
public class GameUtilTest {

    GameUtil gameUtil;
    List<Position> positions;

    @Before
    public void setUp() throws Exception{
        gameUtil = new GameUtil();

        positions = new ArrayList<>();

        Position pos0 = new Position();
        Position pos1 = new Position();
        Position pos2 = new Position();
        Position pos3 = new Position();
        Position pos4 = new Position();
        Position pos5 = new Position();
        Position pos6 = new Position();
        Position pos7 = new Position();
        Position pos8 = new Position();


        pos0.setState(Position.State.Player1);
        pos1.setState(Position.State.Player2);
        pos3.setState(Position.State.Player1);
        pos2.setState(Position.State.Player2);


        positions.add(pos0);
        positions.add(pos1);
        positions.add(pos2);
        positions.add(pos3);
        positions.add(pos4);
        positions.add(pos5);
        positions.add(pos6);
        positions.add(pos7);
        positions.add(pos8);



    }


    @Test
    public void testGameOver() throws Exception{

        positions.get(6).setState(Position.State.Player1);

        gameUtil.checkGameOver(positions,6, Position.State.Player1);
        boolean gameOver = gameUtil.gameOver;

        Assert.assertTrue(gameOver);

    }

    @After
    public void destroy() throws Exception{
        positions = null;
        gameUtil = null;
    }

}
