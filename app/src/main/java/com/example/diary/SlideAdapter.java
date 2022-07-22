package com.example.diary;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class SlideAdapter extends FragmentStateAdapter {
    private Album album;
//    private int[] photoAddress= new int[] {
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

    public SlideAdapter(@NonNull Fragment fragment, Album album) {
        super(fragment);
        this.album=album;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        SlideFragment slideFragment =new SlideFragment();
        Bundle bundle= new Bundle();
//        bundle.putint("data",album.getPhoto().get(position));
        bundle.putString("data",album.getPhotoUri());
        slideFragment.setArguments(bundle);
        return slideFragment;
    }

    @Override
    public int getItemCount() {

//        return album.getPhotos().size();
//        return album.getPhotoUri().size();
        return 1;
    }

    public void addNewPhoto (String photoUri){
        album.setPhotoUri(photoUri);
//        notifyDataSetChanged();

    };
}
