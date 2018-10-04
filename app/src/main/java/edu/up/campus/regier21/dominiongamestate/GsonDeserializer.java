package edu.up.campus.regier21.dominiongamestate;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class GsonDeserializer implements JsonDeserializer<ArrayList<DominionCardState>> {

    @Override
    public ArrayList<DominionCardState> deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
        throws JsonParseException {
        JsonArray jsonCards = je.getAsJsonArray();

        ArrayList<DominionCardState> cardPiles = new ArrayList<>(10);
        jsonCards.forEach(cards -> {
            JsonObject card = cards.getAsJsonObject();
            Log.i(TAG, "deserialize: " + card.get("photoStringID").getAsString());
            cardPiles.add(
                    new DominionCardState(
                        card.getAsJsonObject().get("title").getAsString(),
                        card.get("photoStringID").getAsString(),
                        card.get("text").getAsString(),
                        card.get("cost").getAsInt(),
                        card.get("type").getAsString(),
                        card.get("amount").getAsInt(),
                        card.get("action").getAsString()
                    )
            );
        });

        return cardPiles;
    }

}
