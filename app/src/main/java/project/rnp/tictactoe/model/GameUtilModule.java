package project.rnp.tictactoe.model;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import project.rnp.tictactoe.util.GameUtil;

/**
 * Created by lokex on 9/18/16.
 */
@Module
public class GameUtilModule {
    @Singleton
    @Provides
    public GameUtil provideGameUtil(){ return new GameUtil();}
}
