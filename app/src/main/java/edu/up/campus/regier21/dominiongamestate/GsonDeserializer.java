package edu.up.campus.regier21.dominiongamestate;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * A custom deserializer implementation purposed to convert rawJSON data to an ArrayList of
 * DominionCardState objects
 * @author Julian Donovan
 */
public class GsonDeserializer implements JsonDeserializer<ArrayList<DominionShopPileState>> {

    String expansionSet;

    public GsonDeserializer(String expansionSet) {
        this.expansionSet = expansionSet;
    }

    /**
     * Parses the JSON element in question, yielding a DominionCardState object ArrayList
     * @param el Describes the JSON element being streamed
     * @param type The type of object to deserialize to
     * @param jsonContext The surrounding context as to the JSON element
     * @return A DominionCardState ArrayList as populated by JSON data
     * @throws JsonParseException
     */
    @Override
    public ArrayList<DominionShopPileState> deserialize(JsonElement el, Type type, JsonDeserializationContext jsonContext)
        throws JsonParseException {

        //Interprets the JsonElement as a JSON array
        JsonObject jsonCardsObj = el.getAsJsonObject();
        JsonArray jsonCards = jsonCardsObj.getAsJsonArray(expansionSet);

        //Iterates over the JSON array, extracting card data to populate DominionCardState objects
        ArrayList<DominionShopPileState> cardPiles = new ArrayList<>(10);
        jsonCards.forEach(cards -> {
            JsonObject card = cards.getAsJsonObject();
            cardPiles.add(
                new DominionShopPileState(
                        new DominionCardState(
                                card.getAsJsonObject().get("title").getAsString(),
                                card.get("photoStringID").getAsString(),
                                card.get("text").getAsString(),
                                card.get("cost").getAsInt(),
                                card.get("type").getAsString(),
                                card.get("action").getAsString()
                        ),
                        card.get("amount").getAsInt()
                )
            );
        });

        return cardPiles;
    }

}
