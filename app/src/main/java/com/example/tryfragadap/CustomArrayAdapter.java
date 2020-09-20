package com.example.tryfragadap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomArrayAdapter extends ArrayAdapter<Word> {

    public CustomArrayAdapter(@NonNull Context context, ArrayList<Word> words) {
        super(context, 0, words);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;
        if(listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_items, parent, false);
        }

        Word currentWord = getItem(position);

        TextView mText = (TextView) listView.findViewById(R.id.textView);

        mText.setText(currentWord.getmText());

        ImageView mImageId = (ImageView) listView.findViewById(R.id.imageView);

        mImageId.setImageResource(currentWord.getmImageId());

        return listView;
    }
}
