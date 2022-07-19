package com.example.diary;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

    private static List<Album> albumList =new ArrayList<>();
    private static Album album = new Album() ;


    //    private static int[] photos= new int[]{
//            R.drawable.safar,
//            R.drawable.nozad,
//            R.drawable.tabiat,
//            R.drawable.kouh,
//            R.drawable.dustan,
//            R.drawable.dorehami,
//            R.drawable.aroosi,
//            R.drawable.tavalod,
//            R.drawable.honari,
//            R.drawable.decor
//    };
//    private static String[] titles=new String[]{
//            "سفر",
//            "نوزاد",
//            "طبیعت",
//            "کوهنوردی",
//            "دوستان",
//            "دورهمی",
//            "عروسی",
//            "تولد",
//            "هنری",
//            "دکوراسیون",
//            "تولد",
//    };




    public static List<Album> createAlbum() {

//        albumList.clear();
//        Log.d("Album", String.valueOf(albumList.size()));
//        for (int l = 0; l < titles.length - 1; l++) {
//            Log.d("Album", String.valueOf(photos[l])+titles[l]);
//            Album album = new Album() ;
//            album.getPhotos().add(photos[l]);
//            album.setTitle(titles[l]);
//            Log.i("Album", album.toString());
//            albumList.add(album);
//            Log.d("Album", String.valueOf(albumList.size())+albumList.get(0).getTitle()+albumList.get(l).getTitle());
//            Log.i("Album", album.toString());
//
//        }

        Album album1 = new Album() ;
//        album1.getPhotos().add(R.drawable.safar);
//        album1.getPhotos().add(R.drawable.nozad);
//        album1.getPhotos().add(R.drawable.tabiat);
        album1.setTitle("سفر");
        albumList.add(album1);
        Log.i ("Album", "album1 is "+ album1.toString());
        Log.i ("Album", String.valueOf(albumList.size())+"albumLTitle is "+ albumList.get(0).getTitle());

        Album album2 = new Album() ;
//        album2.getPhotos().add(R.drawable.nozad);
//        album2.getPhotos().add(R.drawable.tabiat);
//        album2.getPhotos().add(R.drawable.kouh);
        album2.setTitle("نوزاد");
        albumList.add(album2);
        Log.i ("Album", "album1 is "+ album2.toString());
        Log.i ("Album", String.valueOf(albumList.size())+"albumLTitle is "+ albumList.get(1).getTitle());

        Album album3 = new Album() ;
//        album3.getPhotos().add(R.drawable.tabiat);
//        album3.getPhotos().add(R.drawable.kouh);
        album3.setTitle("طبیعت");
        albumList.add(album3);

        Album album4 = new Album() ;
//        album4.getPhotos().add(R.drawable.kouh);
        album4.setTitle("کوه");
        albumList.add(album4);

        Album album5 = new Album() ;
//        album5.getPhotos().add(R.drawable.dustan);
//        album5.getPhotos().add(R.drawable.tabiat);
//        album5.getPhotos().add(R.drawable.kouh);
        album5.setTitle("دوستان");
        albumList.add(album5);

        Album album6 = new Album() ;
//        album6.getPhotos().add(R.drawable.dorehami);
//        album6.getPhotos().add(R.drawable.tabiat);
//        album6.getPhotos().add(R.drawable.kouh);
        album6.setTitle("دورهمی");
        albumList.add(album6);


        Album album7 = new Album() ;
//        album7.getPhotos().add(R.drawable.tavalod);
//        album7.getPhotos().add(R.drawable.tabiat);
//        album7.getPhotos().add(R.drawable.kouh);
        album7.setTitle("تولد");
        albumList.add(album7);

        Album album8 = new Album() ;
//        album8.getPhotos().add(R.drawable.aroosi);
//        album8.getPhotos().add(R.drawable.tabiat);
//        album8.getPhotos().add(R.drawable.kouh);
        album8.setTitle("عروسی");
        albumList.add(album8);

        Album album9 = new Album() ;
//        album9.getPhotos().add(R.drawable.honari);
//        album9.getPhotos().add(R.drawable.tabiat);
//        album9.getPhotos().add(R.drawable.kouh);
        album9.setTitle("هنری");
        albumList.add(album9);

        Album album10 = new Album() ;
//        album10.getPhotos().add(R.drawable.decor);
//        album10.getPhotos().add(R.drawable.tabiat);
//        album10.getPhotos().add(R.drawable.kouh);
        album10.setTitle("دکوراسیون");
        albumList.add(album10);

        return albumList;
    }

    public static List<Album> getAlbumList() {
        return albumList;
    }

    public static void setAlbumList(List<Album> albumList) {
        DataBase.albumList = albumList;
    }

    public static List<Album> addAlbum(Album album){
        albumList.add(0,album);
        if (album.getTitle() == null) {
            albumList.get(0).setTitle(String.valueOf(R.string.albumTitle));
        }
        Log.i("KEY", "new title is "+albumList.get(0).getTitle());

//        if (album.getPhotos().isEmpty() ) {
            Log.i("KEY", "new album photo is "+albumList.get(0).toString());

//            albumList.get(0).getPhotos().add(R.drawable.empty);}
        return albumList;
    }
}



