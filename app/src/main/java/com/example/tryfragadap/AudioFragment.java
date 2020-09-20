package com.example.tryfragadap;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class AudioFragment extends Fragment {

    private MediaPlayer mMediaPlayer;

    private AudioManager mAudioManager;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                int length = mMediaPlayer.getCurrentPosition();
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(length);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
        };

    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {

            releaseMediaPlayer();
        }
    };

   public AudioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        // Inflate the layout for this fragment
      View rootView = inflater.inflate(R.layout.fragment_audio, container, false);

        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word(R.string.AllGood,R.drawable.baseline_play_arrow_black_18dp, R.raw.allthegoodgirlsgotohell));
        words.add(new Word(R.string.BadGuy,R.drawable.baseline_play_arrow_black_18dp,R.raw.badguy));
        words.add(new Word(R.string.Bury,R.drawable.baseline_play_arrow_black_18dp, R.raw.buryafriend));
        words.add(new Word(R.string.ILU,R.drawable.baseline_play_arrow_black_18dp,R.raw.iloveyou));
        words.add(new Word(R.string.Idk,R.drawable.baseline_play_arrow_black_18dp, R.raw.idontwannabeyouanymore));
        words.add(new Word(R.string.Lovely,R.drawable.baseline_play_arrow_black_18dp, R.raw.lovely));
        words.add(new Word(R.string.When,R.drawable.baseline_play_arrow_black_18dp, R.raw.whenthepartysover));

        CustomArrayAdapter adapter = new CustomArrayAdapter(getActivity(),words);

        ListView listView = (ListView) rootView.findViewById(R.id.list_view);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();

                Word word = words.get(position);

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer = MediaPlayer.create(getActivity(), word.getmAudioId());

                    mMediaPlayer.start();

                    mMediaPlayer.setOnCompletionListener(mOnCompletionListener);

                }
            }
        });

      return rootView;
   }

   public void releaseMediaPlayer() {
       if(mMediaPlayer != null) {
           mMediaPlayer.release();
           mMediaPlayer = null;
           mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
       }
    }

    @Override
    public void onStop() {
        super.onStop();

        releaseMediaPlayer();
    }
}