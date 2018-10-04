package com.example.potatopaloozac.traveldomainproj.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.potatopaloozac.traveldomainproj.R;

import java.util.List;


public class GameScheduleAdapter extends RecyclerView.Adapter<GameScheduleAdapter.MyViewHolder> {

    List<GameSchedule> mylist;
    GameScheduleOnClickListener listener;

    public GameScheduleAdapter(List<GameSchedule> mygamelist, GameScheduleOnClickListener listener) {
        mylist = mygamelist;
        this.listener = listener;
    }


    @Override
    public GameScheduleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_item_layout, parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {

        GameSchedule gameSchedule = mylist.get(position);
        holder.textView_time.setText("Game Starts: "+ gameSchedule.getTime());
        holder.textView_home.setText("Home Team: "+gameSchedule.getHome());
        holder.textView_away.setText("Away Team: "+gameSchedule.getAway());
        holder.bind(gameSchedule, listener);
    }


    @Override
    public int getItemCount() {
        return mylist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView_time, textView_home, textView_away;


        public MyViewHolder(View itemView) {
            super(itemView);
            textView_time = itemView.findViewById(R.id.textView_time);
            textView_home = itemView.findViewById(R.id.textView_home);
            textView_away = itemView.findViewById(R.id.textView_away);
        }
        public void bind(final GameSchedule gameSchedule, final GameScheduleOnClickListener listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(gameSchedule);
                }
            });
        }
    }

    public interface GameScheduleOnClickListener{
        void onItemClick(GameSchedule gameSchedule);
    }

}
