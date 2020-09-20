package com.example.tryfragadap;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyFragment extends Fragment {

    public FamilyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_family, container, false);

        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word(R.string.Me, R.drawable.family_son));
        words.add(new Word(R.string.Sis, R.drawable.family_daughter));
        words.add(new Word(R.string.Papa, R.drawable.family_father));
        words.add(new Word(R.string.Maa, R.drawable.family_mother));
        words.add(new Word(R.string.Chachu, R.drawable.family_older_brother));
        words.add(new Word(R.string.Dadu, R.drawable.family_grandfather));
        words.add(new Word(R.string.Dadi, R.drawable.family_grandmother));
        words.add(new Word(R.string.Dholak, R.drawable.family_older_brother));
        words.add(new Word(R.string.Telu, R.drawable.family_younger_brother));

        CustomArrayAdapter adapter = new CustomArrayAdapter(getActivity(),words);

        ListView listView = (ListView) rootView.findViewById(R.id.list_view);

        listView.setAdapter(adapter);

        return rootView;
    }
}