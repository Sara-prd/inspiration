package com.example.diary;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

public class PhotoFragment extends Fragment {

private Album album;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        album=getArguments().getParcelable("key");
        Log.i("album", album.toString());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_photo,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager2 pager=view.findViewById(R.id.pager);
        SlideAdapter adapter= new SlideAdapter(this, album);
        pager.setAdapter(adapter);

//        TabLayout tabLayout= findViewById(R.id.tab_layout);
//        TabLayoutMediator mediator= new TabLayoutMediator(tabLayout, pager, new TabLayoutMediator.TabConfigurationStrategy() {
//        @Override
//         public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
//                    };
//          });
//         mediator.attach();
    }
}
