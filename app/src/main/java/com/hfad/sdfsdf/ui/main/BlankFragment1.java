package com.hfad.sdfsdf.ui.main;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import com.hfad.sdfsdf.MainActivity;
import com.hfad.sdfsdf.MainAdapter;
import com.hfad.sdfsdf.MainData;
import com.hfad.sdfsdf.MainInterface;
import com.hfad.sdfsdf.R;


public class BlankFragment1 extends Fragment {
    RecyclerView recyclerView;
    ArrayList<MainData> dataArrayList = new ArrayList<>();
    MainAdapter adapter;
    Context context;
    View view;
    public BlankFragment1() {

    }
    public BlankFragment1(Context context) {
        this.context = context;
    }


    @Override
    public void onStart() {
        super.onStart();
        recyclerView = view.findViewById(R.id.recycler_view);
        adapter = new MainAdapter(context,dataArrayList);
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        recyclerView.setAdapter(adapter);
        getData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
           view = inflater.inflate(R.layout.fragment_layout_1, container, false);
        return view;
    }
    private void getData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://picsum.photos/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        MainInterface mainInterface = retrofit.create(MainInterface.class);

        Call<String> stringCall = mainInterface.STRING_CALL();
        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null)
                {
                    try {
                        JSONArray jsonArray = new JSONArray(response.body());
                        parseArray(jsonArray);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }

    private void parseArray(JSONArray jsonArray) {
        dataArrayList.clear();
        for(int i=0; i<jsonArray.length(); i++ ){
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                MainData data = new MainData();
                data.setImage(jsonObject.getString("download_url"));
                data.setName(jsonObject.getString("author"));
                dataArrayList.add(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            adapter = new MainAdapter(context,dataArrayList);
            recyclerView.setAdapter(adapter);
        }

    }


}

