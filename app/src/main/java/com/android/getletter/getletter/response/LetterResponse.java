package com.android.getletter.getletter.response;


import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by adrienzaganelli on 20/12/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class LetterResponse implements Parcelable {
    private String name;

    protected LetterResponse(Parcel in) {
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LetterResponse> CREATOR = new Creator<LetterResponse>() {
        @Override
        public LetterResponse createFromParcel(Parcel in) {
            return new LetterResponse(in);
        }

        @Override
        public LetterResponse[] newArray(int size) {
            return new LetterResponse[size];
        }
    };
}
