package com.rosol.spanishapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.spanishapp.R;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    private Context context;
    private ArrayList<Word> words;

    //Resource id for the background of this list of words
    private int mColorResourceId;


    public WordAdapter( Context context,  ArrayList<Word> words,int colorResourceId) {
        super(context, 0, words);
        mColorResourceId=colorResourceId;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder;

        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            holder = new Holder();
            holder.defWord = listItemView.findViewById(R.id.default_word);
            holder.spanWord = listItemView.findViewById(R.id.spanish_word);
            holder.imageRes=listItemView.findViewById(R.id.image);
            listItemView.setTag(holder);
        }else {
            holder=(Holder)listItemView.getTag();
        }

        Word currentWord = getItem(position);


        holder.defWord.setText(currentWord.getDefaultTranslation());
        holder.spanWord.setText(currentWord.getSpanishTranslation());

        //if there is an image, show it
        if (currentWord.hasImage()){
            holder.imageRes.setImageResource(currentWord.getImageResourceId());
            //make sure that the image is shown
            holder.imageRes.setVisibility(View.VISIBLE);

            //otherwise, make sure that it is hidden
        }else {
            holder.imageRes.setVisibility(View.GONE);
        }

        //set the theme color for the list item
        View textContainer=listItemView.findViewById(R.id.text_container);
        //find the color that the resource Id maps to
        int color= ContextCompat.getColor(getContext(),mColorResourceId);
        //set the background color of the text container view
        textContainer.setBackgroundColor(color);

        return listItemView;

    }


    public static class Holder {
        TextView defWord;
        TextView spanWord;
        ImageView imageRes;
    }

    
}
