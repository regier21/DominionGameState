package edu.up.campus.regier21.dominiongamestate;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;

//import com.google.gson.Gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import static android.content.ContentValues.TAG;

public class DominionCardPileState {
    protected ArrayList<Integer> cardPile;
    protected int totalCardPiles;
    Context context;

    public DominionCardPileState(Context context) {
        this.cardPile = new ArrayList<>(6);
        this.totalCardPiles = 6;

        this.context = context;
        //initializeBaseCards();
    }

    public DominionCardPileState(int totalCards, int resourceID, Context context) {
        this.cardPile = new ArrayList<>(totalCards);
        this.totalCardPiles = totalCards;
        this.context = context;
        generatePile(resourceID); //Populates cardStack
    }

    //Credit: https://stackoverflow.com/questions/4307273/how-can-i-create-and-display-an-arraylist-of-random-numbers-in-java
    private void generatePile(int resourceID){
        try (InputStream ins = context.getResources().openRawResource(resourceID)) {
            Gson gson = new Gson();
            Type arrayType = new TypeToken<ArrayList<DominionCardState>>(){}.getType();

            cardPile = gson.fromJson(new InputStreamReader(ins, "UTF-8"), arrayType);
            if(cardPile.size() > totalCardPiles) {
                Collections.shuffle(cardPile);
                cardPile.subList(0, totalCardPiles);
            }
        }
        catch (IOException e) {
            Log.e(TAG, "Error while generating card pile: " + e);
        }
    }
}
