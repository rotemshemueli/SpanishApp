package com.rosol.spanishapp;

public class Word {
    private String mDefaultWord;
    private String mSpanishWord;

    //it is the position for the sound
    private int mAudioResourceId;
    //it starts from default of no image. soon as this variable update to something else,
    //we know that the Word has an image.
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    //this is a constant variable, -1 it is out of the range of all possible valid resource id.
    private static final int NO_IMAGE_PROVIDED = -1;

    public Word(String mDefaultWord, String mSpanishWord, int audioResourceId) {
        this.mDefaultWord = mDefaultWord;
        this.mSpanishWord = mSpanishWord;
        this.mAudioResourceId = audioResourceId;
    }

    public Word(String mDefaultWord, String mSpanishWord, int mImageResourceId, int audioResourceId) {
        this.mDefaultWord = mDefaultWord;
        this.mSpanishWord = mSpanishWord;
        this.mImageResourceId = mImageResourceId;
        this.mAudioResourceId = audioResourceId;

    }

    public String getDefaultTranslation() {
        return mDefaultWord;
    }

    public String getSpanishTranslation() {
        return mSpanishWord;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    //to check if the java class has image to show
    public Boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public int getAudioResourceId() {
        return mAudioResourceId;
    }
}

