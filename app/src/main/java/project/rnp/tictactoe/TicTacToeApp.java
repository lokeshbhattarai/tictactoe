package project.rnp.tictactoe;

import android.app.Application;

import project.rnp.tictactoe.model.DiComponent;
import project.rnp.tictactoe.model.DaggerDiComponent;

/**
 * Created by lokex on 9/18/16.
 */
public class TicTacToeApp extends Application {

    private DiComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerDiComponent.builder().build();
    }

    public DiComponent getComponent() {
        return component;
    }
}
