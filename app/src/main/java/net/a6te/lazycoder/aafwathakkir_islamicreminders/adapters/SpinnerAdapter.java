package net.a6te.lazycoder.aafwathakkir_islamicreminders.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.a6te.lazycoder.aafwathakkir_islamicreminders.R;

import java.util.ArrayList;
import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<String> {

    private List<String > items;
    private Context mContext;

    public SpinnerAdapter(@NonNull Context context, @NonNull List<String> objects) {
        super(context, 0, objects);
        items = objects;
        this.mContext = context;
    }

    private class ViewHolder {
        private TextView mTextView;
        private LinearLayout spinnerLl;//spinner layout
    }


    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(final int position, View convertView,
                              ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.spinner_view, null);
            holder = new ViewHolder();
            holder.mTextView = convertView.findViewById(R.id.itemTv);
            holder.spinnerLl = convertView.findViewById(R.id.spinnerLl);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTextView.setText(items.get(position));

//        holder.mTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext,items.get(position),Toast.LENGTH_SHORT).show();
//            }
//        });


//        if ((position == selectedItemIndex  && selectedItemIndex != 0)) {
//            holder.spinnerLl.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));
//        }else{
//            holder.spinnerLl.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimaryDark));
//        }
//
//
        return convertView;
    }
}
