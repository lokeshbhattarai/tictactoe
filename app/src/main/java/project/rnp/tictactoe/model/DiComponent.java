package project.rnp.tictactoe.model;


import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by lokex on 7/21/16.
 */
@Singleton
@Component(modules = {GameUtilModule.class})
public interface DiComponent {

    void inject(GameRunner gameRunner);
}
