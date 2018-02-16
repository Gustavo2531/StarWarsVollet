package com.example.gustavomendez.soccerfragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DosFragment extends Fragment {

    private int posicion;
    public DosFragment() {
        // Required empty public constructor
    }


    /*Argumento de la actividad*/
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }



    private TextView textView;
    private ImageView imageView;

    public void nextVersion(){
        imageView.setImageResource(Data.drawableArray[posicion]);
        textView.setText(Data.androidNames[posicion]);
        posicion++;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dos, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        imageView= (ImageView) view.findViewById(R.id.imageView2);
        textView = (TextView) view.findViewById(R.id.textView3);

    }
}
