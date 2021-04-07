package com.hfad.sdfsdf.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hfad.sdfsdf.MainAdapter;
import com.hfad.sdfsdf.R;
import com.hfad.sdfsdf.SecondAdapter;

import java.io.File;
import java.io.FilenameFilter;

public class BlankFragment2 extends Fragment {
    RecyclerView recyclerView;
    String[] arrayNamesLile;
    String[] files ;
    Context context;
    View view;
    SecondAdapter adapter;
    public BlankFragment2(Context context) {
        this.context = context;
    }

    @Override
    public void onStart() {
        arrayNamesLile = search();
        recyclerView = view.findViewById(R.id.recycler_view1);
        adapter = new SecondAdapter(context,arrayNamesLile,view);
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        recyclerView.setAdapter(adapter);
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_layout_2, container, false);
        return view;
    }


  /*  public void refr(Context ctt, View view3) {
        Context cont = ctt;
        RecyclerView ecyclerView2 = view3.findViewById(R.id.recycler_view);
        if( search() != null ) {arrayNamesLile = search();
            SecondAdapter  myAdapter1 = new SecondAdapter(cont,arrayNamesLile, view3);
            myAdapter1.notifyDataSetChanged();
            ecyclerView2.setAdapter(myAdapter1);}
        else {
            Log.e("TAG", "нет файлов");}
    }*/


    //Ищем наши любимые картинки
    public String[] search(){
        String path1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File directory = new File(path1);
        files = directory.list(new FilenameFilter() {
            public boolean accept(File directory, String fileName) {
                return fileName.endsWith(".jpg");
            }
        });
//Это не правильно, но по другому пока не знаю как сделать,
        // т.е. при первом запуске когда папка с "Like" images пустая, я не имею права передавать null array в адаптер
        // это происходит в момент запуска когда user еще не дал разрешения, но отобразить данные хочется
        if(files == null) {
            String[] f = new String[1];
            return f;
        }else {}
        return files;}
}
