package com.example.diary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class mainFragment extends Fragment {

    private RecyclerView albumRV;
    private List<Album> albumList;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.albumList = DataBase.createAlbum();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_main,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        albumRV =view.findViewById(R.id.mp_recyclerView);
        albumRV.setLayoutManager(new GridLayoutManager(getContext(),2 ));
        AlbumAdapter albumAdapter = new AlbumAdapter(albumList, new AlbumClickListener() {
            @Override
            public void onAlbumClick(Album album) {
                Bundle bundle=new Bundle();
                bundle.putParcelable("key", album);
                Navigation.findNavController(getView() ).navigate(R.id.action_mainFragment_to_photoFragment, bundle);
            }
        }) ;
        albumRV.setAdapter(albumAdapter);
    }
}
