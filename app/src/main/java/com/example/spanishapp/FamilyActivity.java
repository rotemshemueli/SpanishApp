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

public class FamilyActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_family);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Father", "Padre", R.drawable.family_father,R.raw.family_father1));
        words.add(new Word("Father", "Papá", R.drawable.family_father,R.raw.family_father2));
        words.add(new Word("Mother", "Madre", R.drawable.family_mother,R.raw.family_mother1));
        words.add(new Word("Mother", "Mamá", R.drawable.family_mother,R.raw.family_mother2));
        words.add(new Word("Brother", "Hermano", R.drawable.family_older_brother,R.raw.family_brother));
        words.add(new Word("Sister", "Hermana", R.drawable.family_older_sister,R.raw.family_sister));
        words.add(new Word("Nephew", "Sobrino", R.drawable.family_younger_brother,R.raw.family_nephew));
        words.add(new Word("Niece", "Sobrina", R.drawable.family_younger_sister,R.raw.famiy_niece));
        words.add(new Word("Cousin", "Primo", R.drawable.family_older_brother,R.raw.family_cousin1));
        words.add(new Word("Cousin", "Prima", R.drawable.family_older_sister,R.raw.family_cousin2));
        words.add(new Word("Husband", "Esposo", R.drawable.family_father,R.raw.family_husband));
        words.add(new Word("Wife", "Esposa", R.drawable.family_mother,R.raw.family_wife));
        words.add(new Word("Son", "Hijo", R.drawable.family_son,R.raw.family_son));
        words.add(new Word("Daughter", "Hija", R.drawable.family_daughter,R.raw.family_daughter));
        words.add(new Word("Uncle", "Tío", R.drawable.family_father,R.raw.family_uncle));
        words.add(new Word("Aunt", "Tía", R.drawable.family_mother,R.raw.famiy_aunt));
        words.add(new Word("Grandchild", "Nieto", R.drawable.family_son,R.raw.family_grandchild));
        words.add(new Word("Granddaughter", "Nieta", R.drawable.family_daughter,R.raw.family_grandaughter));
        words.add(new Word("Grandfather", "Abuelo", R.drawable.family_grandfather,R.raw.family_granfather));
        words.add(new Word("Grandmother", "Abuela", R.drawable.family_grandmother,R.raw.family_granmother));
        words.add(new Word("Baby", "Bebé", R.drawable.family_younger_brother,R.raw.family_baby));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_family);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word=words.get(position);
                //release the media player if it currently exists because we are about to play
                //a different sound file.
                releaseMediaPlayer();

                mediaPlayer=MediaPlayer.create(FamilyActivity.this,word.getAudioResourceId());

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
