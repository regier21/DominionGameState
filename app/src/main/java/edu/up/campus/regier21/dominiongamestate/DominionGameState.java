package edu.up.campus.regier21.dominiongamestate;

import android.content.Context;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;

import static android.content.ContentValues.TAG;

public class DominionGameState {
    protected DominionPlayerState mDominionPlayers[]; //Sorted by order of turn
    protected int mCurrentTurn; //-1 when game ended
    Context context;

    public DominionGameState(int numPlayers, Context context) {

        this.mDominionPlayers = new DominionPlayerState[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            this.mDominionPlayers[i] = new DominionPlayerState("Player"+i, context);
        }

        this.mCurrentTurn = 0;
        this.context = context;
    }


    @Override
    protected DominionGameState clone() {
        DominionGameState clone = null;

        try{
            clone = (DominionGameState) super.clone();
            clone.mDominionPlayers = Arrays.copyOf(mDominionPlayers, mDominionPlayers.length);
        }
        catch(CloneNotSupportedException cnse) {
            Log.e(TAG, "Error while cloning DominionGameState: ", cnse);
        }

        return clone;
    }

    //ToDo: Implement method
    @Override
    public String toString() {
        return super.toString();
    }
}
