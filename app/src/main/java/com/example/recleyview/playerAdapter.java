package com.example.recleyview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class playerAdapter extends RecyclerView.Adapter<playerAdapter.ViewHolder> {

    private Context mcontext;
    private ArrayList<Player>players;
    private OnClickListener listener;

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    public playerAdapter(Context mcontext) {
        this.mcontext = mcontext;
        this.players =new ArrayList<>();
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mcontext);
        View view =inflater.inflate(R.layout.list_player_item,parent,false);

        ViewHolder holder =new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Player currentPlayer =players.get(position);
        holder.textName.setText(currentPlayer.getName());
        Picasso.get().load(currentPlayer.getImagePath()).fit().into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView =itemView.findViewById(R.id.imageView);
            textName =itemView.findViewById(R.id.tv_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener !=null){
                        listener.aksiklik(getAdapterPosition());
                    }
                }
            });
        }
    }
}
