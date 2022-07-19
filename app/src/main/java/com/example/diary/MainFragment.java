package com.example.diary;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.room.Room;

import java.util.List;

public class MainFragment extends Fragment {

    private RecyclerView albumRV;
    private List<Album> albumList;
    private AppDB appDB;
    private AlbumDAO albumDAO;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appDB= Room.databaseBuilder(getContext(), AppDB.class, "db_App")
                .allowMainThreadQueries()
                .build();
        albumDAO= appDB.getAlbumDAO();
        this.albumList=albumDAO.getAllAlbums();

//        SQLiteHelper sqLiteHelper = new SQLiteHelper(getContext());
//        this.albumList = sqLiteHelper.getAllAlbums();
//        this.albumList=DataBase.createAlbum();
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
        albumRV.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        AlbumAdapter albumAdapter = new AlbumAdapter(albumList, new AlbumClickListener() {
            @Override
            public void onAlbumClick(Album album) {
                Bundle bundle=new Bundle();
                bundle.putParcelable("key", album);
                Navigation.findNavController(getView() ).navigate(R.id.action_mainFragment_to_photoFragment, bundle);
            }
        }) ;
        albumRV.setAdapter(albumAdapter);

        view.findViewById(R.id.mp_btn_add_album).setOnClickListener(view1 -> {
            Album newAlbum= new Album();
            newAlbum.setTitle("آلبوم جدید");
//            newAlbum.setPhotoUri("android.resource://"+getResources().getResourceTypeName(R.drawable.empty));
            newAlbum.setPhotoUri("android.resource://com.example.diary/drawable/empty");
            newAlbum.setMainText("دلنوشته");
            albumDAO.addAlbum(newAlbum);
            albumList.add(newAlbum);
//            DataBase.addAlbum(newAlbum);
            Bundle bundle=new Bundle();
            bundle.putParcelable("key", newAlbum);
            Navigation.findNavController(getView() ).navigate(R.id.action_mainFragment_to_photoFragment, bundle);

        });
    }
}
