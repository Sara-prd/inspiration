package com.example.diary;

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


//    private List<String> albumTitles = new ArrayList<>();
//    private List<Integer> photoAddress = new ArrayList<>();

    public AlbumAdapter(List<Album> albumList, AlbumClickListener albumClickListener) {

        this.albumList = albumList;
        this.albumClickListener=albumClickListener;

//        for (int l=0 ; l<albumCoverList.size()-1;l++){
//            albumTitles.add(albumCoverList.get(l).getTitle() );
//            photoAddress.add(albumCoverList.get(l).getPhotoSources().get(0) );
//        }
//        photoAddress.add(R.drawable.safar);
//        photoAddress.add(R.drawable.nozad);
//        photoAddress.add(R.drawable.tabiat);
//        photoAddress.add(R.drawable.kouh);
//        photoAddress.add(R.drawable.dustan);
//        photoAddress.add(R.drawable.dorehami);
//        photoAddress.add(R.drawable.aroosi);
//        photoAddress.add(R.drawable.tavalod);
//        photoAddress.add(R.drawable.honari);
//        photoAddress.add(R.drawable.decor);
//        albumTitles.add("سفر");
//        albumTitles.add("نوزاد");
//        albumTitles.add("طبیعت");
//        albumTitles.add("کوهنوردی");
//        albumTitles.add("دوستان");
//        albumTitles.add("دورهمی");
//        albumTitles.add("عروسی");
//        albumTitles.add("تولد");
//        albumTitles.add("هنری");
//        albumTitles.add("دکوراسیون");
//        albumTitles.add("تولد");
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
            photo.setImageResource(album.getPhotos().get(0));
            Log.i("album","the title became: "+title.getText());

            itemView.setOnClickListener(view -> {
                albumClickListener.onAlbumClick(album);
                Log.i("album", "album"+album.getTitle() +"is clicked");
            });

        }
    }
}
