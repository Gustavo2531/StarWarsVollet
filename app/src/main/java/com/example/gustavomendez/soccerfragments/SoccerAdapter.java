package com.example.gustavomendez.soccerfragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gustavomendez on 13/02/18.
 */

public class SoccerAdapter extends ArrayAdapter<Match> {
    private Context context;
    public SoccerAdapter(@NonNull Context context, int resource, @NonNull List<Match> objects) {
        super(context, resource, objects);
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.soccer_matches_layout, parent, false);
        }
        Match match = getItem(position);
        TextView fecha = (TextView) convertView.findViewById(R.id.fecha);
        TextView equipo01 = (TextView) convertView.findViewById(R.id.equipo01);
        fecha.setText(match.nombre);
        equipo01.setText(match.birth_year);

        //convertView.setBackgroundResource(R.color.colorAccent);
        
        return convertView;
    }
}
