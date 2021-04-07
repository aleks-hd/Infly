package com.hfad.sdfsdf;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hfad.sdfsdf.ui.main.BlankFragment2;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class SecondAdapter  extends RecyclerView.Adapter<SecondAdapter.MyViewHoder1>  {

    private ArrayList<MainData> dataArrayList;
    private Activity activity;

    Context context;
    private String[] nameFiles;
    private Animation animation;
    View view;
    View view2;
    View view3;


    public SecondAdapter(Context ct, String[] array){
        this.context =ct;
        this.nameFiles = array;

    }

    public SecondAdapter(Context ct, String[] array, View view){
        this.context =ct;
        this.nameFiles = array;
        this.view = view;
    }

    @NonNull
    @Override
    public MyViewHoder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_main,parent,false);
        return new MyViewHoder1(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHoder1 holder, int position) {

        File f = new File("/storage/self/primary/Download/" + nameFiles[position]);

        Picasso.get().load(f).into(holder.imageView);
        /*holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteLikeImage(f);
                holder.imageButton.startAnimation(animation);
                Toast.makeText(context,"Удалили", Toast.LENGTH_SHORT).show();
                //new BlankFragment2(context).refr(context, view3);

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return nameFiles.length;
    }

    public class MyViewHoder1 extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        ImageButton imageButton;
        public MyViewHoder1(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
           // textView = itemView.findViewById(R.id.likeBtn);
            //imageButton.setImageResource(R.drawable.imgdeletelittle);
          //  animation = AnimationUtils.loadAnimation(context, R.anim.rotate);


        }
    }

    //Удаляем любимые картинки
    private void deleteLikeImage(File path){
        File file = new File(path.toString());
        if (file.exists()) {
            file.delete();
        }
    }

}
