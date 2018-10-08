package edu.up.campus.regier21.dominiongamestate;


import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.RawRes;
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

public class CardReader{

    GsonBuilder gsonBuilder;
    Type arrayType;
    Gson gsonParser;

    public CardReader() {
        //cards = generateCards(uniqueCardPiles, resourceID);
        gsonBuilder = new GsonBuilder();
        arrayType = new TypeToken<ArrayList<DominionCardState>>(){}.getType();
        gsonBuilder.registerTypeAdapter(arrayType, new GsonDeserializer());
        gsonParser = gsonBuilder.create();
    }

    public ArrayList<DominionCardState> generateCards(Context context, @RawRes int resourceID){
        return generateCards(context, -1, resourceID);
    }

    public ArrayList<DominionCardState> generateCards(Context context, int uniqueCardPiles, @RawRes int resourceID) {
        try (InputStream ins = context.getResources().openRawResource(resourceID)) {
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
