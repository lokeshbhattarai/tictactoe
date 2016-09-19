package project.rnp.tictactoe;

import android.os.Build;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import project.rnp.tictactoe.model.GameRunner;
import project.rnp.tictactoe.ui.MainActivity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Created by lokex on 9/18/16.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,sdk= Build.VERSION_CODES.LOLLIPOP)
public class MainActivityTest {

    private MainActivity activity;

    @Before
    public void setUp() throws Exception
    {
        activity = Robolectric.setupActivity( MainActivity.class );
    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull( activity );
    }

    @Test
    public void firstTurnShouldBePlayerOne() throws Exception{

        TextView tv = (TextView) activity.findViewById(R.id.currentStatus);
        assertEquals(tv.getText(), GameRunner.TEXT_TURN_1);
    }

}
