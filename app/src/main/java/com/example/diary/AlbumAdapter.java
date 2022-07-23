package com.example.diary;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.File;
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
        holder.bindContent(albumList.get(position),position);

    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder{

        private ImageView photo;
        private TextView title;
        private Button deletBTN;

        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            photo=itemView.findViewById(R.id.mp_imageView);
            title=itemView.findViewById(R.id.mp_albumTitle);
            deletBTN=itemView.findViewById(R.id.btn_delete);

        }
        public void bindContent (final Album album, int position){
            title.setText(album.getTitle());
            Picasso.get().setLoggingEnabled(true);
            Picasso.get().load(Uri.parse(album.getPhotoUri())).into(photo);
            itemView.setOnClickListener(view -> {
                albumClickListener.onAlbumClick(album);
                Log.i("album", "album"+album.getTitle() +"is clicked");
            });

            itemView.setOnLongClickListener(view -> {
                deletBTN.setVisibility(View.VISIBLE);
                deletBTN.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        deletBTN.setVisibility(View.INVISIBLE);
                    }
                },5000);
                deletBTN.setOnClickListener(view1 -> {
                    albumClickListener.onAlbumDelete(album,position);
                });
                return true;
            });

        }
    }
}
