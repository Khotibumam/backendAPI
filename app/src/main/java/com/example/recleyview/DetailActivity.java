package com.example.recleyview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SyncRequest;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView tvname,tvplace,tvnational,tvbirthday,tvdeskripsi;
    private String idPlayer;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView =findViewById(R.id.imagevieww);
        tvname =findViewById(R.id.tv_nama);
        tvnational =findViewById(R.id.tv_negara);
        tvplace =findViewById(R.id.tv_tempat);
        tvbirthday =findViewById(R.id.tv_tanggal);
        tvdeskripsi =findViewById(R.id.tv_deskripsi);
        gson =new Gson();


        idPlayer =getIntent().getStringExtra("idPlayer");
        String url ="https://www.thesportsdb.com/api/v1/json/1/lookupplayer.php?id="+idPlayer;

        //ambil data dengan volley dan json
        RequestQueue queue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    //ambil nilai dan set ke komponen
                        PlayerResult result=gson.fromJson(response,PlayerResult.class);
                        Player player =result.getPlayer().get(0);
                        tvname.setText(player.getName());
                        tvnational.setText(player.getNegara());
                        tvbirthday.setText(player.getBirthDate());
                        tvplace.setText(player.getBirthPlace());
                        tvdeskripsi.setText(player.getDescription());
                        Picasso.get().load(player.getImagePath()).into(imageView);
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
