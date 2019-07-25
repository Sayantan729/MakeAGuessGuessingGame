package com.example.makeaguess;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import io.realm.RealmResults;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ItemViewHolder> {
    private RealmResults<Player> realmResults;
    private Context context;
    private int current;
    public ScoreAdapter(RealmResults<Player> realmResults1, Context context1,int cpos)
    {
        realmResults=realmResults1;
        context=context1;
        current=cpos;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.scorerow,parent,false);
        return new ItemViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Player player=realmResults.get(position);
        holder.rank.setText(""+(position+1));
        holder.scorename.setText(player.getName());
        holder.scoretext.setText(player.getScore()+"");

        if(position==0)
            holder.itemView.findViewById(R.id.row).setBackgroundColor(Color.rgb(255,179,00));
        else if (position==1)
            holder.itemView.findViewById(R.id.row).setBackgroundColor(Color.rgb(158,158,158));
        else if (position==2)
            holder.itemView.findViewById(R.id.row).setBackgroundColor(Color.rgb(141,110,99));
        else
            holder.itemView.findViewById(R.id.row).setBackgroundColor(Color.rgb(0,69,92));
        if(position==current)
            holder.itemView.setBackgroundColor(Color.rgb(198,255,0));
    }


    @Override
    public int getItemCount() {
        return realmResults.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder
    {
        private TextView scorename,scoretext,rank;
        public ItemViewHolder(@NonNull View itemview)
        {
            super(itemview);
            rank=itemview.findViewById(R.id.rank);
            scorename=itemview.findViewById(R.id.playername);
            scoretext=itemview.findViewById(R.id.score);

        }

    }

}

