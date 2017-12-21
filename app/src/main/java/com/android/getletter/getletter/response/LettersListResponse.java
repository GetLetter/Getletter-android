package com.android.getletter.getletter.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by adrienzaganelli on 21/12/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class LettersListResponse implements Parcelable {
    List items;

    public LettersListResponse() {

    }

    protected LettersListResponse(Parcel in) {
    }

    public static final Creator<LettersListResponse> CREATOR = new Creator<LettersListResponse>() {
        @Override
        public LettersListResponse createFromParcel(Parcel in) {
            return new LettersListResponse(in);
        }

        @Override
        public LettersListResponse[] newArray(int size) {
            return new LettersListResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }
}
