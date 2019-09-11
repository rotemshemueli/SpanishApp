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

public class PhrasesActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_phrases);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Hello", "Hola", R.raw.phrases_hello));
        words.add(new Word("My name is...", "Me llamo ", R.raw.phrases_mynameis));
        words.add(new Word("What is your name?", "Cómo te llamas?", R.raw.phrases_whatisyourname));
        words.add(new Word("How are you feeling?", "Cómo estás?", R.raw.phrases_howareyoufeeling));
        words.add(new Word("I’m feeling good, thanks.", "Muy bien, gracias.", R.raw.phrases_goodthanks));
        words.add(new Word("Thank you", "Gracias", R.raw.phrases_thanks));
        words.add(new Word("I’m coming.", "Ya voy", R.raw.phrases_imcoming));
        words.add(new Word("Let’s go", "Vamonos", R.raw.phrases_letsgo));
        words.add(new Word("Yes", "Sí", R.raw.phrases_yes));
        words.add(new Word("No", "No", R.raw.phrases_no));
        words.add(new Word("Sorry", "Perdon", R.raw.phrases_sorry));
        words.add(new Word("I'm sorry", "Lo siento", R.raw.phrases_imsorry));
        words.add(new Word("GoodBye", "Adiós", R.raw.phrases_goodbye));
        words.add(new Word("Good morning", "Buenos días", R.raw.phrases_goodmorning));
        words.add(new Word("Goodnight", "Buenas noches", R.raw.phrases_goodnight));
        words.add(new Word("I don't understand", "No entiendo", R.raw.phrases_idontunderstand));
        words.add(new Word("What", "Qué?", R.raw.phrases_what));
        words.add(new Word("Today", "Hoy", R.raw.phrases_today));
        words.add(new Word("Now", "Ahora", R.raw.phrases_now));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);

                //release the media player if it currently exists because we are about to play
                //a different sound file.
                releaseMediaPlayer();

                 mediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getAudioResourceId());

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
