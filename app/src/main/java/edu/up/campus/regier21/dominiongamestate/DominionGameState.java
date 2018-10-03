package edu.up.campus.regier21.dominiongamestate;

import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;

import static android.content.ContentValues.TAG;

public class DominionGameState {
    protected DominionPlayerState mDominionPlayers[]; //Sorted by order of turn
    protected int mCurrentTurn; //-1 when game ended

    protected DominionGameState(int numPlayers) {

        mDominionPlayers = new DominionPlayerState[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            mDominionPlayers[i] = new DominionPlayerState("Player"+i);
        }

        mCurrentTurn = 0;
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
