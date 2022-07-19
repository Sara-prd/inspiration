package com.example.diary;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class PhotoFragment extends Fragment {

private final int PICK_IMAGE_MULTIPLE=1;
private List<Uri> imageURIList= new ArrayList<Uri>();
private Album album;
FloatingActionButton addButton;
EditText mainText;
EditText title;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.album=getArguments().getParcelable("key");
        Log.i("photoFragment", album.getTitle());
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

        TabLayout tabLayout= view.findViewById(R.id.tab_layout);
        TabLayoutMediator mediator= new TabLayoutMediator(tabLayout, pager, new TabLayoutMediator.TabConfigurationStrategy() {
        @Override
         public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {}
          });
         mediator.attach();

        addButton = view.findViewById(R.id.btn_add_photo);
        addButton.setOnClickListener(view1 -> {
            Intent selectPhotoIntent=new Intent(Intent.ACTION_GET_CONTENT);
            selectPhotoIntent.setType("image/*");
            selectPhotoIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            startActivityForResult(Intent.createChooser(selectPhotoIntent, "Select Picture"),PICK_IMAGE_MULTIPLE);
        });

        mainText=view.findViewById(R.id.txt_mainText);
        mainText.setOnClickListener(view1 -> {
            album.setMainText(mainText.getText().toString());
        });

        title=view.findViewById(R.id.txt_postTitle);
        title.setOnClickListener(view1 -> {
            album.setTitle(title.getText().toString());
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case PICK_IMAGE_MULTIPLE:
                if(resultCode == RESULT_OK){
                    Log.i("selectPhoto", "some photos is selected "
                            + "Data is: "+String.valueOf(data.getData())+" clipdata is "
                            +String.valueOf(data.getClipData()));
                    if(data.getClipData() != null) {
                        int count = data.getClipData().getItemCount();
                        for(int i = 0; i < count; i++) {
                            imageURIList.add(data.getClipData().getItemAt(i).getUri());
                        }
                        Log.i("selectPhoto",imageURIList.toString());
                    }
                } else if(data.getData() != null) {
                    imageURIList.add(data.getData());

                }
                album.setPhotoUri(imageURIList.get(0).toString());
        }

    }
}
