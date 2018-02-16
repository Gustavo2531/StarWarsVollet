package com.example.gustavomendez.soccerfragments;


import android.app.ListFragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;


import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;



/**
 * A simple {@link ListFragment} subclass.
 */
public class MatchesListFragment extends ListFragment {
    private RequestQueue mQueue;

    public MatchesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_matches_list, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        SoccerAdapter adapter = new SoccerAdapter(
                getActivity(), R.layout.soccer_matches_layout,
                new ArrayList<Match>());
        mQueue= VolleySingleton.getInstance(getContext()).getRequestQueue();
        for(int i=0; i<10;i++) {
            jsonMarvel(getStarWarsString(i), adapter);
        }

        setListAdapter(adapter);

    }
/*
    private SoccerAdapter getAdapter(){
        SoccerAdapter adapter = new SoccerAdapter(
                getActivity(), R.layout.soccer_matches_layout,
                new ArrayList<Match>());
        try {
            JSONObject jsonObject = new JSONObject(getJSON());
            JSONArray jsonArray = jsonObject.getJSONArray("rounds");
            for (int i = 0; i< jsonArray.length(); i++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                JSONArray matches = jsonObject1.getJSONArray("matches");
                for (int j = 0; j< matches.length(); j++){
                    JSONObject unMatch = matches.getJSONObject(j);
                    Match m = new Match();
                    m.fecha = unMatch.getString("date");
                    m.equipo01 = unMatch.getJSONObject("team1").getString("name");
                    m.equipo02 = unMatch.getJSONObject("team2").getString("name");
                    m.marcador01 = unMatch.getInt("score1");
                    m.marcador02 = unMatch.getInt("score1");
                    adapter.add(m);

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return adapter;


    }


    private String getJSON() {
        try {
            InputStream inputStream = getActivity().getAssets().open("lisgaespanola.json");
            int s = inputStream.available();
            byte[] archivo = new byte[s];
            inputStream.read(archivo);
            inputStream.close();
            return new String(archivo);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return "";

    }*/

 private void jsonMarvel(String url, final SoccerAdapter adapter){

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");

                    for(int i = 0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                      //  JSONObject thumbnail = jsonObject.getJSONObject("thumbnail");
                        //String string = thumbnail.getString("path") + "/portrait_medium" + "."+ thumbnail.getString("extension");

                        Match m = new Match();
                        m.nombre = jsonObject.getString("name");
                        m.birth_year = "Birth year: "+jsonObject.getString("birth_year");


                        adapter.add(m);
                    }
                    adapter.notifyDataSetChanged();

                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mQueue.add(request);
    }

    /*
    public static String hexEncode(byte[] bytes) {
        char[] result = new char[bytes.length*2];
        int b;
        for (int i = 0, j = 0; i < bytes.length; i++) {
            b = bytes[i] & 0xff;
            result[j++] = HEXCodes[b >> 4];
            result[j++] = HEXCodes[b & 0xf];
        }
        return new String(result);
    }*/
/*
    private SoccerAdapter getAdapter(){
        SoccerAdapter adapter = new SoccerAdapter(
                getActivity(), R.layout.soccer_matches_layout,
                new ArrayList<Match>());
        try {
            for(int n=1; n<10;n++) {
                JSONObject jsonObject = new JSONObject(getStarWarsString(n));
                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    Match m = new Match();
                    m.fecha = jsonObject1.getString("name");
                    m.equipo01 = jsonObject1.getString("hair_color");
                    m.equipo02 = jsonObject1.getString("name");
                    m.marcador01 = 1;
                    m.marcador02 = 2;
                    adapter.add(m);


                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return adapter;


    }*/

    private String getStarWarsString(int i){

        String h="https://swapi.co/api/people/?page="+i+"&format=json";
        return h;

    }



}
