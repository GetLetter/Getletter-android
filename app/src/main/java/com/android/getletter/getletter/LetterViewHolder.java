package com.android.getletter.getletter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.getletter.getletter.response.LetterResponse;

import butterknife.ButterKnife;

/**
 * Created by adrienzaganelli on 20/12/2017.
 */

public class LetterViewHolder extends RecyclerView.ViewHolder {
    public LetterViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void fill(LetterResponse letter) {
        // String full_name = letter.g

       // titleTextView.setText(title);

       // Glide.with(context).load(url).into(imageView);
        Log.d("FILL", ""+letter);
    }
}

