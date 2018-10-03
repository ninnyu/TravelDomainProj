package com.example.potatopaloozac.traveldomainproj.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wang_.busbook.R;

import java.util.List;

public class BusSeatAdapter extends RecyclerView.Adapter<BusSeatAdapter.MyViewHolder>{

    List<BusSeat> seatlist;
    SeatOnClickListener listener;
    int[] image_array = {R.drawable.seat_available, R.drawable.seat_unavailable, R.drawable.seat_selected, R.drawable.aisle};

    public BusSeatAdapter(List<BusSeat> seatlist, SeatOnClickListener listener) {
        this.seatlist = seatlist;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.seat_layout, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {

        BusSeat busSeat = seatlist.get(position);
        int type = busSeat.getType();
        holder.imageView_seat.setImageResource(image_array[type]);
        if( position% 5 !=2) {
            int num = busSeat.getSeatNum();
            holder.textView_num.setText("" + num);

        }
        holder.bind(busSeat, listener);

    }

    @Override
    public int getItemCount() {
        return seatlist.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView_seat;
        TextView textView_num;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView_seat =itemView.findViewById(R.id.imageView_seat);
            textView_num = itemView.findViewById(R.id.textView_seatnum);
        }

        public void bind(final BusSeat busSeat, final SeatOnClickListener listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onitemclick(busSeat);
                }
            });

        };
    }

    public interface SeatOnClickListener{
        void onitemclick(BusSeat busSeat);
    }

}
