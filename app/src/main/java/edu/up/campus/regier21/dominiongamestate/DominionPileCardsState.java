package edu.up.campus.regier21.dominiongamestate;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

import static android.content.ContentValues.TAG;

public class DominionPileCardsState extends DominionCardsAbstract {
    public DominionPileCardsState(int resourceID, String cardSet) {
        this(-1, resourceID, cardSet); //-1 populates cards with all card piles from the set
    }

    public DominionPileCardsState(int uniqueCardPiles, int resourceID, String cardSet) {
        cards = generateCards(uniqueCardPiles, resourceID);
    }

    private ArrayList<DominionCardState> generateCards(int uniqueCardPiles, int resourceID) {
        try (InputStream ins = MainActivity.context.getResources().openRawResource(resourceID)) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            Type arrayType = new TypeToken<ArrayList<DominionCardState>>(){}.getType();
            gsonBuilder.registerTypeAdapter(arrayType, new GsonDeserializer());
            Gson gsonParser = gsonBuilder.create();

            ArrayList<DominionCardState> cardPiles = gsonParser.fromJson(new InputStreamReader(ins, "UTF-8"), arrayType);
            return (uniqueCardPiles > 0) ? selectCards(cardPiles, uniqueCardPiles) : cardPiles;
        }
        catch (IOException e) {
            Log.e(TAG, "Error while generating card pile: " + e);
            return null;
        }
    }

    private ArrayList<DominionCardState> selectCards(ArrayList<DominionCardState> cardPiles, int uniqueCardPiles) {
        if (cardPiles.size() > uniqueCardPiles) {
            Collections.shuffle(cardPiles);
            return ((ArrayList<DominionCardState>) cardPiles.subList(0, uniqueCardPiles));
        }
        return cardPiles;
    }



}
