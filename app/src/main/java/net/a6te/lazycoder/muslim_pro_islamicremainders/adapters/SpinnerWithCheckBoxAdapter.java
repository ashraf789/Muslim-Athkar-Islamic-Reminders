package net.a6te.lazycoder.muslim_pro_islamicremainders.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import net.a6te.lazycoder.muslim_pro_islamicremainders.R;
import net.a6te.lazycoder.muslim_pro_islamicremainders.model.SpinnerWithCheckBoxItem;

import java.util.ArrayList;
import java.util.List;


public class SpinnerWithCheckBoxAdapter extends ArrayAdapter<SpinnerWithCheckBoxItem> {
    private Context mContext;
    private ArrayList<SpinnerWithCheckBoxItem> listState;
    private SpinnerWithCheckBoxAdapter myAdapter;
    private boolean isFromView = false;

    public SpinnerWithCheckBoxAdapter(Context context, List<SpinnerWithCheckBoxItem> objects) {
        super(context, 0, objects);
        this.mContext = context;
        this.listState = (ArrayList<SpinnerWithCheckBoxItem>) objects;
        this.myAdapter = this;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }


    private class ViewHolder {
        private TextView mTextView;
        private CheckBox mCheckBox;
    }

    public View getCustomView(final int position, View convertView,
                              ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.spiner_with_checkbox, null);
            holder = new ViewHolder();
            holder.mTextView = (TextView) convertView
                    .findViewById(R.id.text);
            holder.mCheckBox = (CheckBox) convertView
                    .findViewById(R.id.checkbox);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTextView.setText(listState.get(position).getTitle());

        // To check weather checked event fire from getview() or user input
        isFromView = true;
        holder.mCheckBox.setChecked(listState.get(position).isSelected());
        isFromView = false;

//        if ((position == 0)) {
//            holder.mCheckBox.setVisibility(View.INVISIBLE);
//        } else {
//            holder.mCheckBox.setVisibility(View.VISIBLE);
//        }
        holder.mCheckBox.setTag(position);
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int getPosition = (Integer) buttonView.getTag();

            }
        });
        return convertView;
    }
}
