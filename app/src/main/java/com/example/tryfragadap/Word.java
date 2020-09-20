package com.example.tryfragadap;

public class Word {

    private int mTextId;
    private int mImageId;
    private int mAudioId;

    public Word(int text, int imageId, int audioId){
        mImageId = imageId;
        mTextId = text;
        mAudioId = audioId;
    }

    public Word(int text, int imageId){
        mImageId = imageId;
        mTextId = text;
    }

    public int getmText(){
        return mTextId;
    }

    public int getmImageId() {
        return mImageId;
    }

    public int getmAudioId() {
        return mAudioId;
    }
}
