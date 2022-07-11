package com.example.diary;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Album implements Parcelable {

    private  int id;
    private  String title;
    private  List<Integer> photos = new ArrayList<>();
    private int photoCoverAddress;
    private  Uri photoUri;

    public Album() {
    }

    protected Album(Parcel in) {
        title = in.readString();
    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Integer> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Integer> photos) {
        this.photos = photos;
    }


    public int getPhotoCoverAddress() {
        return photoCoverAddress;
    }

    public void setPhotoCoverAddress(int photoCoverAddress) {
        this.photoCoverAddress = photoCoverAddress;
    }

    public Uri getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(Uri photoUri) {
        this.photoUri = photoUri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Album{" +
                "title='" + title + '\'' +
                ", photos=" + photos +
                '}';
    }



    //    public void clearAlbum(){
    //        title="";
    //        photos.clear();
    //    }
}
