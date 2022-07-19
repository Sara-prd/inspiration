package com.example.diary;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>{

    private List<Album> albumList =new ArrayList<>();
    private AlbumClickListener albumClickListener;


    public AlbumAdapter(List<Album> albumList, AlbumClickListener albumClickListener) {

        this.albumList = albumList;
        this.albumClickListener=albumClickListener;

    };

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new AlbumViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        holder.bindContent(albumList.get(position));

    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

//    public void addNewAlbum (int photoAddress, String albumTitle){
//        this.albumTitles.add(albumTitle);
//        this.photoAddress.add(photoAddress);
//        notifyItemInserted(0);
//    };
//
//    public void updateAlbum(int photoAddress, String albumTitle, int position){
//        this.photoAddress.set(position, photoAddress);
//        this.albumTitles.set(position,albumTitle);
//        notifyItemChanged(position);
//    };

    public class AlbumViewHolder extends RecyclerView.ViewHolder{

        private ImageView photo;
        private TextView title;

        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            photo=itemView.findViewById(R.id.mp_imageView);
            title=itemView.findViewById(R.id.mp_albumTitle);

        }
        public void bindContent (final Album album){
            title.setText(album.getTitle());
//            photo.setImageResource(album.getPhotos().get(0));
//            photo.setImageResource(album.getPhotoCoverAddress());
            photo.setImageURI(Uri.parse(album.getPhotoUri()));
            Log.i("album","the title became: "+title.getText());
            itemView.setOnClickListener(view -> {
                albumClickListener.onAlbumClick(album);
                Log.i("album", "album"+album.getTitle() +"is clicked");
            });

        }
    }
}
