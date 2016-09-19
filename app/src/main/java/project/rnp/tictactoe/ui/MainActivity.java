package project.rnp.tictactoe.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import project.rnp.tictactoe.R;
import project.rnp.tictactoe.databinding.ActivityMainBinding;
import project.rnp.tictactoe.model.GameRunner;

public class MainActivity extends AppCompatActivity {

    private final static String EXTRA_GAME = "game";

    private GameRunner gameRunner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        if(savedInstanceState==null) {
            gameRunner = new GameRunner(this);
            binding.setPositions(gameRunner.getPositions());
            binding.setGamePlay(gameRunner);
        }else{
            gameRunner = savedInstanceState.getParcelable(EXTRA_GAME);
            binding.setPositions(gameRunner.getPositions());
            binding.setGamePlay(gameRunner);
        }


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(EXTRA_GAME, gameRunner);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        gameRunner = savedInstanceState.getParcelable(EXTRA_GAME);
    }

}
