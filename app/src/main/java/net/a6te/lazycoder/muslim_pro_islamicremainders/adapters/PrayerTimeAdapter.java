package net.a6te.lazycoder.muslim_pro_islamicremainders.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.a6te.lazycoder.muslim_pro_islamicremainders.R;
import net.a6te.lazycoder.muslim_pro_islamicremainders.model.PrayerTimeModel;

import java.util.ArrayList;

public class PrayerTimeAdapter extends RecyclerView.Adapter<PrayerTimeAdapter.ViewHolder> {

    private ArrayList<PrayerTimeModel> prayerTimes;

    public PrayerTimeAdapter(ArrayList<PrayerTimeModel> prayerTimes) {
        this.prayerTimes = prayerTimes;
    }

    @NonNull
    @Override
    public PrayerTimeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.prayer_time_view,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PrayerTimeAdapter.ViewHolder holder, int position) {
        holder.prayerNameTv.setText(prayerTimes.get(position).getPrayerName());
        holder.prayerTimeTv.setText(prayerTimes.get(position).getPrayerTime());
    }

    @Override
    public int getItemCount() {
        return prayerTimes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView prayerNameTv;
        TextView prayerTimeTv;

        public ViewHolder(View itemView) {
            super(itemView);
            prayerNameTv = itemView.findViewById(R.id.prayerNameTv);
            prayerTimeTv = itemView.findViewById(R.id.prayerTimeTv);
        }
    }
}
