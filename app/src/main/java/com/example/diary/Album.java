package com.example.diary;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "tbl_album")
public class Album implements Parcelable {

    @PrimaryKey (autoGenerate = true)
    private  Long id;
    private  String title;
    private  String photoUri;
    private String mainText;

//    private  List<Integer> photos = new ArrayList<>();

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


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotoUri() {
        return photoUri;
    }
    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    public String getMainText() {
        return mainText;
    }
    public void setMainText(String mainText) {
        this.mainText = mainText;
    }


//    public List<Integer> getPhotos() {
//        return photos;
//    }
//
//    public void setPhotos(List<Integer> photos) {
//        this.photos = photos;
//    }

}
