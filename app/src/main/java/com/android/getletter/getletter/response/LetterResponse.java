package com.android.getletter.getletter.response;


import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by adrienzaganelli on 20/12/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class LetterResponse implements Parcelable {
    private String receiver_first_name;
    private String receiver_last_name;
    private String img;


    public LetterResponse() {}

    protected LetterResponse(Parcel in) {
        receiver_first_name = in.readString();
        receiver_last_name = in.readString();
        img = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(receiver_first_name);
        dest.writeString(receiver_last_name);
        dest.writeString(img);
    }

    public String getReceiver_first_name() {
        return receiver_first_name;
    }

    public void setReceiver_first_name(String receiver_first_name) {
        this.receiver_first_name = receiver_first_name;
    }

    public String getReceiver_last_name() {
        return receiver_last_name;
    }

    public void setReceiver_last_name(String receiver_last_name) {
        this.receiver_last_name = receiver_last_name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
