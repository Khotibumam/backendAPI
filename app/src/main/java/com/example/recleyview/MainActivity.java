package com.example.recleyview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvplayer;
    private playerAdapter adapter;
    private ArrayList<Player>players;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvplayer= findViewById(R.id.rv_player);
        adapter =new playerAdapter(this);
        players=new ArrayList<>();
        gson=new Gson();
        ambilData();

        LinearLayoutManager lm =new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false); //Tampilan Scorll ke samping

        GridLayoutManager gr =new GridLayoutManager(this,3); //tampilan grid tergantungan jumlah grid

        StaggeredGridLayoutManager lm2 =new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL); //tampilan 2 jajr tidak beraturan

        DividerItemDecoration divider =new DividerItemDecoration(this,lm.getOrientation());
        rvplayer.setLayoutManager(lm);
        rvplayer.setAdapter(adapter);
        rvplayer.addItemDecoration(divider);

        adapter.setListener(new OnClickListener() {
            @Override
            public void aksiklik(int position) {
                Intent intent=new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra("idPlayer",players.get(position).getIdPlyaer());
                startActivity(intent);
            }
        });
    }
    public void ambilData(){
        //meminta request dengan volley
        //jika request berhasil,tampilkan ke dalam rcelceyview
        RequestQueue queue= Volley.newRequestQueue(this);
        String url ="https://www.thesportsdb.com/api/v1/json/1/searchplayers.php?t=Tottenham";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            //ambil data dari response -> json ->al
                PlayerResult result =gson.fromJson(response,PlayerResult.class);

                players =result.getPlayer();
                //tampilkan data dari adapter
                adapter.setPlayers(players);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
            }
            );
        queue.add(stringRequest);
    }
}
