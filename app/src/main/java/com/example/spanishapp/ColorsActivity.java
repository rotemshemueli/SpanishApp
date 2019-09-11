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
package com.example.spanishapp;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;

    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Red", "Rojo", R.drawable.color_red,R.raw.color_red));
        words.add(new Word("Green", "Verde", R.drawable.color_green,R.raw.color_green));
        words.add(new Word("Brown", "Marrón", R.drawable.color_brown,R.raw.color_brown));
        words.add(new Word("Gray", "Gris", R.drawable.color_gray,R.raw.color_gray));
        words.add(new Word("Black", "Negro", R.drawable.color_black,R.raw.color_black));
        words.add(new Word("White", "Blanco", R.drawable.color_white,R.raw.color_white));
        words.add(new Word("Yellow", "Amarillo", R.drawable.color_mustard_yellow,R.raw.color_yellow));
        words.add(new Word("Blue", "Azul", R.drawable.color_blue,R.raw.color_blue));
        words.add(new Word("Orange", "Naranja", R.drawable.color_orange,R.raw.color_orange));
        words.add(new Word("Purple", "Morado", R.drawable.color_purple,R.raw.color_purple));
        words.add(new Word("Pink", "Rosa", R.drawable.color_pink,R.raw.color_pink));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_colors);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word=words.get(position);
                //release the media player if it currently exists because we are about to play
                //a different sound file.
                releaseMediaPlayer();

                 mediaPlayer=MediaPlayer.create(ColorsActivity.this,word.getAudioResourceId());

                //start the audio file
                mediaPlayer.start();
                //setup a listener on the media player, so that we can stop and release tha
                // media player once the sounds has finish playing.
                mediaPlayer.setOnCompletionListener(completionListener);

            }
        });

    }
    //clean up the media player by releasing its resources.


    @Override
    protected void onPause() {
        super.onPause();

        //when the activity is stopped, release the media player resources because we wont
        //be playing any more sounds.
        releaseMediaPlayer();
    }

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
