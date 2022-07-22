package com.example.diary;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class PhotoFragment extends Fragment {

private final int PICK_IMAGE_MULTIPLE=1;
private List<Uri> imageURIList= new ArrayList<>();
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
//        OnBackPressedCallback callback = new OnBackPressedCallback(true ) {
//            @Override
//            public void handleOnBackPressed() {
//                albumDAO.updateAlbum(album);
////               Bundle bundle = new Bundle();
////               bundle.putParcelable("configuredAlbum",album);
////               Navigation.findNavController(getView() ).navigate(R.id.action_photoFragment_to_mainFragment2,bundle);
////               Navigation.findNavController(getView()).navigate(R.id.action_photoFragment_to_mainFragment2);
//
//            }
//        };
//        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

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
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                inputMainText= charSequence.toString();
                album.setMainText(inputMainText);
                albumDAO.updateAlbum(album);
//                MainFragment.sqLiteHelper.updateAlbum(album);
                Log.i("PhotoFragment","main text is updated");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        title=view.findViewById(R.id.txt_postTitle);
        title.setText(album.getTitle());
        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                inputTitle=charSequence.toString();
                album.setTitle(inputTitle);
                albumDAO.updateAlbum(album);
//                MainFragment.sqLiteHelper.updateAlbum(album);
                Log.i("PhotoFragment","title is updated");}

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        addButton = view.findViewById(R.id.btn_add_photo);
        addButton.setOnClickListener(view1 -> {
            Intent selectPhotoIntent=new Intent(Intent.ACTION_GET_CONTENT);
            selectPhotoIntent.setType("image/*");
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
                        Log.i("selectPhoto", "some photos is selected "
                                + "Data is: " + String.valueOf(data.getData()) + " clipdata is "
                                + String.valueOf(data.getClipData()));
                        if (data.getClipData() != null) {
                            int count = data.getClipData().getItemCount();
                            for (int i = 0; i < count; i++) {
                                imageURIList.add(data.getClipData().getItemAt(i).getUri());
                            }
                            Log.i("selectPhoto", imageURIList.toString());
                        } else if (data.getData() != null) {
                            Log.i("selectPhoto", "one photo is selected and data is: " + data.getData().toString());
                            imageURIList.add(data.getData());

                        }
                        album.setPhotoUri(String.valueOf(imageURIList.get(0)));
                        albumDAO.updateAlbum(album);
//                        MainFragment.sqLiteHelper.updateAlbum(album);
                        Log.i("PhotoFragment", "photo is updated");
//                        adapter.addNewPhoto(album.getPhotoUri());
//                        pager.setCurrentItem();
                        adapter.notifyDataSetChanged();


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
