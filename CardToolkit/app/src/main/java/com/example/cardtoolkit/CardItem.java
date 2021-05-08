package com.example.cardtoolkit;

public class CardItem {
    private int mCardImage;
    private String mCardName;

    public CardItem(int cardImage, String cardName){
        mCardImage = cardImage;
        mCardName = cardName;
    }

    public int getCardImage() {
        return mCardImage;
    }

    public String getCardName(){
        return mCardName;
    }
}
