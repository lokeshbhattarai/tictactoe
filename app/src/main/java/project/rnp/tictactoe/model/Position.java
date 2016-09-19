package project.rnp.tictactoe.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

import project.rnp.tictactoe.BR;

/**
 * Created by lokex on 9/18/16.
 *
 * Class represents each cell in the matrix
 */
public class Position extends BaseObservable implements Parcelable{

    public static final String COLOR_NORMAL = "#FF050505";
    public static final String COLOR_WIN = "#FFCE1515";

    protected Position(Parcel in) {
        textColor = in.readInt();
        mark = in.readString();
        enabled = in.readByte() != 0;
    }

    public static final Creator<Position> CREATOR = new Creator<Position>() {
        @Override
        public Position createFromParcel(Parcel in) {
            return new Position(in);
        }

        @Override
        public Position[] newArray(int size) {
            return new Position[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(textColor);
        parcel.writeString(mark);
        parcel.writeByte((byte) (enabled ? 1 : 0));
    }

    public enum State{

        Unmarked(0),
        Player1(1),
        Player2(2);

        private int value;

        private State(int state){
            this.value = state;

        }

        public int getValue(){ return value;}

    }

    private int textColor;

    private State state;

    /*** text to show in marked position */
    private String mark;

    /*** flag to control clicking of box */
    private boolean enabled;


    public Position(){
        recycle();

    }

    public void recycle(){
        setState(State.Unmarked);
        setMark("");
        setTextColor(Color.parseColor(COLOR_NORMAL));
        setEnabled(true);
    }

    @Bindable
    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        notifyPropertyChanged(BR.textColor);
    }

    @Bindable
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        setEnabled(false);
        setMark(String.valueOf(state.getValue()));
        notifyPropertyChanged(BR.state);

    }

    @Bindable
    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
        notifyPropertyChanged(BR.mark);
    }

    @Bindable
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        notifyPropertyChanged(BR.enabled);
    }
}
