/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rosol.spanishapp;
import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.spanishapp.R;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    private AudioManager mAudioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);


        //Create an ArrayList of Word objects
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("One", "Uno", R.drawable.number_one, R.raw.one1));
        words.add(new Word("Two", "Dos", R.drawable.number_two, R.raw.two));
        words.add(new Word("Three", "Tres", R.drawable.number_three, R.raw.three));
        words.add(new Word("Four", "Cuatro", R.drawable.number_four, R.raw.four));
        words.add(new Word("Five", "Cinco", R.drawable.number_five, R.raw.five));
        words.add(new Word("Six", "Seis", R.drawable.number_six, R.raw.six));
        words.add(new Word("Seven", "Siete", R.drawable.number_seven, R.raw.seven));
        words.add(new Word("Eight", "Ocho", R.drawable.number_eight, R.raw.eight));
        words.add(new Word("Nine", "Nueve", R.drawable.number_nine, R.raw.nine));
        words.add(new Word("Ten", "diez", R.drawable.number_ten, R.raw.ten));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);
                //release the media player if it currently exists because we are about to play
                //a different sound file.
                releaseMediaPlayer();

                mediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());

                //start the audio file
                mediaPlayer.start();
                //setup a listener on the media player, so that we can stop and release tha
                // media player once the sounds has finish playing.
                mediaPlayer.setOnCompletionListener(completionListener);


            }
        });


    }


    @Override
    protected void onPause() {
        super.onPause();
        //when the activity is stopped, release the media player resources because we wont
        //be playing any more sounds.
        releaseMediaPlayer();
    }

    //clean up the media player by releasing its resources.
    private void releaseMediaPlayer() {
        //if the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            //regardless of the current state of the media player, release its resources
            //because we no longer need it.
            mediaPlayer.release();

            //set the media player back to null. for our code, we've decided that setting
            //the media player to null is an easy way to tell that the media player
            //is not configured to play an audio file at the moment.
            mediaPlayer = null;

        }
    }
}
