package com.android.getletter.getletter;

import android.view.View;

import com.android.getletter.getletter.LetterViewHolder;
import com.android.getletter.getletter.response.LetterResponse;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

/**
 * Created by adrienzaganelli on 20/12/2017.
 */

public class LetterItem extends AbstractItem<LetterItem, LetterViewHolder> {
    public LetterResponse letter;


    @Override
    public LetterViewHolder getViewHolder(View v) {
        return new LetterViewHolder(v);
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public int getLayoutRes() {
        return 0;
    }

    @Override
    public void bindView(LetterViewHolder holder, List payloads) {
        super.bindView(holder, payloads);

        // holder.fill(letter);
    }
}
