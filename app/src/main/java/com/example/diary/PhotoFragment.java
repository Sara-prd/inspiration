package com.example.diary;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.documentfile.provider.DocumentFile;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PhotoFragment extends Fragment {

private final int PICK_IMAGE_MULTIPLE=1;
private List<Uri> imageUriList= new ArrayList<>();
private Album album;
private AppDB appDB;
private AlbumDAO albumDAO;
FloatingActionButton addButton;
EditText mainText;
String inputMainText;
EditText title;
String inputTitle;
SlideAdapter adapter;
ViewPager2 pager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.album=getArguments().getParcelable("key");
        Log.i("photoFragment", album.getTitle());
        appDB=AppDB.getInstance(getContext());
        albumDAO=appDB.getAlbumDAO();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_photo,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pager=view.findViewById(R.id.pager);
        adapter= new SlideAdapter(this, album);
        pager.setAdapter(adapter);

        TabLayout tabLayout= view.findViewById(R.id.tab_layout);
        TabLayoutMediator mediator= new TabLayoutMediator(tabLayout, pager, new TabLayoutMediator.TabConfigurationStrategy() {
        @Override
         public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {}
          });
         mediator.attach();

        mainText=view.findViewById(R.id.txt_mainText);
        mainText.setText(album.getMainText());
        mainText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                inputMainText= charSequence.toString();
                album.setMainText(inputMainText);
                albumDAO.updateAlbum(album);
                Log.i("PhotoFragment","main text is updated");
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        title=view.findViewById(R.id.txt_postTitle);
        title.setText(album.getTitle());
        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                inputTitle=charSequence.toString();
                album.setTitle(inputTitle);
                albumDAO.updateAlbum(album);
                Log.i("PhotoFragment","title is updated");}

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        addButton = view.findViewById(R.id.btn_add_photo);
        addButton.setOnClickListener(view1 -> {
            Intent selectPhotoIntent=new Intent(Intent.ACTION_GET_CONTENT);
            selectPhotoIntent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI,"image/*");
            selectPhotoIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            startActivityForResult(Intent.createChooser(selectPhotoIntent, "Select Picture"),PICK_IMAGE_MULTIPLE);

        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            switch (requestCode) {
                case PICK_IMAGE_MULTIPLE:
                    if (resultCode == RESULT_OK & data != null) {
                        if (data.getClipData() != null) {
                            int count = data.getClipData().getItemCount();
                            for (int i = 0; i < count; i++) {
                                imageUriList.add(data.getClipData().getItemAt(i).getUri());
                            }
                            Log.i("selectPhoto", "some photos are selected: "+imageUriList.toString());
                        } else if (data.getData() != null) {
                            Log.i("selectPhoto", "one photo is selected and data uri is: " + data.getData().toString());
                            imageUriList.add(data.getData());


//                            DocumentFile document= DocumentFile.fromSingleUri(getContext(),data.getData());
//                            String name=document.getName();
//                            String imagePath= "file:/"+Environment.getExternalStorageDirectory()+"/"+name; ;
//                            imagePathList.add(imagePath);
//                            String newType=data.getData().getScheme();

//                            Uri selectedImage = data.getData();
//                            String[] filePathColumn = { MediaStore.Images.Media.DATA };
//                            Cursor cursor = getContext().getContentResolver().query(selectedImage,filePathColumn, null, null, null);
//                            cursor.moveToFirst();
//                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                            String picturePath = cursor.getString(columnIndex);
//                            cursor.close();

                        }
                        album.setPhotoUri(imageUriList.get(0).toString());
                        albumDAO.updateAlbum(album);
                        Log.i("PhotoFragment", "photo is updated");
//                        adapter.addNewPhoto(album.getPhotoUri());
//                        pager.setCurrentItem();
//                        adapter.notifyDataSetChanged();


                    } else {
                        Toast.makeText(getContext(), "you have not selected image.", Toast.LENGTH_LONG).show();
                        Log.i("selectPhoto", "No photo is selected ");
                    }

            }
        } catch (Exception e) {
            Toast.makeText(getContext(), "something went wrong.", Toast.LENGTH_LONG).show();
            Log.i("selectPhoto", "There is an error.");
        }

    }
}
