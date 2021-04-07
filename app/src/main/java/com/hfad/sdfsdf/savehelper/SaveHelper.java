package com.hfad.sdfsdf.savehelper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import android.os.Environment;
import android.util.Log;
import android.widget.Toast;


import com.hfad.sdfsdf.ui.main.BlankFragment1;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class SaveHelper {
   private Target mTarget;
    Context context;
    public void loadImage(Context context1, String url, int namePosition) {
        String fileName = String.valueOf(namePosition);
        context= context1;

        mTarget = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap,Picasso.LoadedFrom loadedFrom) {
              saveImage(bitmap, fileName); }
            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                Log.d("DEBUG", "onBitmapFailed");
            }
            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                Log.d("DEBUG", "onPrepareLoad");
            }};
        Picasso.get().load(url).into(mTarget);
    }
    //Сохраниние в локальный репозиторий
    public void saveImage(Bitmap imageToSave, String fileName) {
        //Рабочий вариант:
        File folder1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File folder = new File(folder1.toString());
        if(!folder.exists()){folder.mkdir();}
        File file = new File(folder, fileName+".jpg");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 95, fos);
            fos.flush();
            fos.close();
            Toast.makeText(context, "Файл был успешно сохранен", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(context, "Нет разрешения", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

}