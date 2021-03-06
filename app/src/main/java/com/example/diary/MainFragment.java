package com.example.diary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.List;

public class MainFragment extends Fragment {

    private RecyclerView albumRV;
    private List<Album> albumList;
    private AppDB appDB;
    private AlbumDAO albumDAO;
    AlertDialog alertDialog;
    AlbumAdapter albumAdapter;
    long id;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appDB= AppDB.getInstance(getContext());
        albumDAO= appDB.getAlbumDAO();
        albumList=albumDAO.getAllAlbums();

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
        albumAdapter = new AlbumAdapter(albumList, new AlbumClickListener() {
            @Override
            public void onAlbumClick(Album album) {
                Bundle bundle=new Bundle();
                bundle.putParcelable("key", album);
                Navigation.findNavController(getView() ).navigate(R.id.action_mainFragment_to_photoFragment, bundle);
            }

            @Override
            public void onAlbumDelete(Album album, int position) {


                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                    alertDialogBuilder.setMessage("?????? ?????????? ?????? ????????");
                    alertDialogBuilder.setPositiveButton("??????",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface arg0, int arg1) {
                                            albumDAO.deleteAlbum(album);
                                            albumList.remove(album);
                                            albumAdapter.notifyItemRemoved(position);
                                        }
                                    });

                    alertDialogBuilder.setNegativeButton("??????",null);

                    alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
            }
        }) ;
        albumRV.setAdapter(albumAdapter);

        view.findViewById(R.id.mp_btn_add_album).setOnClickListener(view1 -> {

            Album newAlbum= new Album();
            newAlbum.setTitle("?????????? ????????");
            newAlbum.setPhotoUri("android.resource://com.example.diary/drawable/empty");
            newAlbum.setMainText("??????????????");

            id=albumDAO.addAlbum(newAlbum);
            newAlbum.setId(id);
            albumList.add(newAlbum);

            Bundle bundle=new Bundle();
            bundle.putParcelable("key", newAlbum);
            Navigation.findNavController(getView() ).navigate(R.id.action_mainFragment_to_photoFragment, bundle);

        });
    }
}
