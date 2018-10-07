package edu.up.campus.regier21.dominiongamestate;

public enum DominionCardType {
    TREASURE,
    VICTORY,
    ACTION,
    ATTACK,
    REACTION;

    public static DominionCardType getTypeFromString(String typeName){
        for (DominionCardType type : values()){
            if (type.name().equals(typeName)){
                return type;
            }
        }
        return null;
    }
}
