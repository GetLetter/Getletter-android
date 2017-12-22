package com.android.getletter.getletter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;

/**
 * Created by adrienzaganelli on 21/12/2017.
 */

public class LettersAdaptater extends ArrayAdapter {
    public LettersAdaptater(@NonNull Context context, @NonNull List letters) {
        super(context, R.layout.custom_row, letters);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater lettersInflater = LayoutInflater.from(getContext());
        View customView = lettersInflater.inflate(R.layout.custom_row, parent, false);
        //ButterKnife.bind(this, customView);


        Map<String, String> singleLetterItem = (Map<String, String>) getItem(position);
        TextView letterName = (TextView) customView.findViewById(R.id.letter_row_name);
        ImageView letterImg = (ImageView) customView.findViewById(R.id.letter_row_img);
        TextView letterDate = customView.findViewById(R.id.letter_row_date);

        letterName.setText(singleLetterItem.get("name"));
        Log.d("BITE", singleLetterItem+"");
        letterDate.setText(singleLetterItem.get("created_at"));

        Glide
                .with(getContext())
                .load("https://get-letter-api.herokuapp.com" + singleLetterItem.get("picture"))
                .into(letterImg);

        return customView;
    }
}
