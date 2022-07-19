package com.example.diary;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "tbl_album")
public class Album implements Parcelable {

    @PrimaryKey (autoGenerate = true)
    private  int id;

//    @ColumnInfo (defaultValue = "نوزاد")
    private  String title;

//    @ColumnInfo(defaultValue = "android.resource://your.package.here/drawable/empty")
    private  String photoUri;

    private String mainText;

//    @ColumnInfo (defaultValue = "نوزاد")
//    private int photoCoverAddress;

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


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    //    public int getPhotoCoverAddress() {
//        return photoCoverAddress;
//    }
//
//    public void setPhotoCoverAddress(int photoCoverAddress) {
//        this.photoCoverAddress = photoCoverAddress;
//    }

//    public List<Integer> getPhotos() {
//        return photos;
//    }
//
//    public void setPhotos(List<Integer> photos) {
//        this.photos = photos;
//    }
//
//    @Override
//    public String toString() {
//        return "Album{" +
//                "title='" + title + '\'' +
//                ", photos=" + photos +
//                '}';
//    }


}
