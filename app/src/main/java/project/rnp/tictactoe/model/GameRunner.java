package project.rnp.tictactoe.model;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import project.rnp.tictactoe.BR;
import project.rnp.tictactoe.TicTacToeApp;
import project.rnp.tictactoe.util.GameUtil;

/**
 * Created by lokex on 9/18/16.
 */
public class GameRunner extends BaseObservable implements Parcelable{

    public static final String TEXT_TURN_1 = "Turn: Player 1";
    public static final String TEXT_TURN_2 = "Turn: Player 2";
    public static final String TEXT_WINNER_1 = "WINNER: Player 1";
    public static final String TEXT_WINNER_2 = "WINNER: Player 2";
    public static final String TEXT_UNDECIDED = "GAME UNDECIDED";


    private  List<Position> positionsList  = new ArrayList<>();

    //either player one or player two
    private Position.State currentPlayer;

    private String currentStatus;

    private int positionLeftToFill;

    private boolean allowReload=false;

    @Inject
    GameUtil gameUtil;

    private Context context;

    public GameRunner(Context context){
        this.context = context;
        positionsList = new ArrayList<>();
        for(int i=0;i<9;i++){
            positionsList.add(new Position());
        }
        init();
    }

    protected GameRunner(Parcel in) {
        positionsList = in.createTypedArrayList(Position.CREATOR);
        currentStatus = in.readString();
        positionLeftToFill = in.readInt();
        allowReload = in.readByte() != 0;
    }

    public static final Creator<GameRunner> CREATOR = new Creator<GameRunner>() {
        @Override
        public GameRunner createFromParcel(Parcel in) {
            return new GameRunner(in);
        }

        @Override
        public GameRunner[] newArray(int size) {
            return new GameRunner[size];
        }
    };

    public void init(){
        setAllowReload(false);
        setCurrentStatus(TEXT_TURN_1);
        positionLeftToFill = 9;
        currentPlayer = Position.State.Player1;

        ((TicTacToeApp)context.getApplicationContext()).getComponent().inject(this);

    }

    public List<Position> getPositionsList() {
        return positionsList;
    }

    public void setPositionsList(List<Position> positionsList) {
        this.positionsList = positionsList;
    }

    public List<Position> getPositions() {
        return positionsList;
    }

    public void onPositionMarked(View view, int pos){

        --positionLeftToFill;

        Position markedPosition = positionsList.get(pos);
        markedPosition.setState(currentPlayer);//mark position filled by current player

        //check game over
        gameUtil.checkGameOver(positionsList,pos,currentPlayer);

        if(gameUtil.gameOver){
            //notify game over
            List<Position> positions = gameUtil.getPositionsToMark();
            for (Position position:positions) {
                position.setState(currentPlayer);
                position.setTextColor(Color.parseColor(Position.COLOR_WIN));
            }
            setCurrentStatus(currentPlayer == Position.State.Player1 ? TEXT_WINNER_1 : TEXT_WINNER_2);
            setAllowReload(true);
        }else{

            if(positionLeftToFill>0){
                switchPlayer();
            }else{
                //notify undecided
                setCurrentStatus(TEXT_UNDECIDED);
                setAllowReload(true);
            }
        }


    }


    public void switchPlayer(){

        currentPlayer = (currentPlayer == Position.State.Player1 ? Position.State.Player2 : Position.State.Player1);
        setCurrentStatus(currentPlayer == Position.State.Player1 ? TEXT_TURN_1 : TEXT_TURN_2);
    }


    public void resetGame(){

        for (Position position:positionsList) {
            position.recycle();
        }
        init();

    }

    @Bindable
    public String getCurrentStatus() {return currentStatus; }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
        notifyPropertyChanged(BR.currentStatus);
    }

    @Bindable
    public boolean isAllowReload() {
        return allowReload;
    }

    public void setAllowReload(boolean allowReload) {
        this.allowReload = allowReload;
        notifyPropertyChanged(BR.allowReload);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(positionsList);
        parcel.writeString(currentStatus);
        parcel.writeInt(positionLeftToFill);
        parcel.writeByte((byte) (allowReload ? 1 : 0));
    }
}
