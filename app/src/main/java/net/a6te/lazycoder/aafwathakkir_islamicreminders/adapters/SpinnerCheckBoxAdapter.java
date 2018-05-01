package net.a6te.lazycoder.aafwathakkir_islamicreminders.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import net.a6te.lazycoder.aafwathakkir_islamicreminders.R;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.model.SpinnerWithCheckBoxItem;

import java.util.ArrayList;
import java.util.List;


public class SpinnerCheckBoxAdapter extends ArrayAdapter {
    private Context mContext;
    private List<String > languages;
    private Boolean[] selectedLanguage;
    private boolean isFromView = false;

    public SpinnerCheckBoxAdapter(Context context, List<String> languages, Boolean[] selectedLanguage) {
        super(context, 0, languages);
        this.mContext = context;
        this.languages = languages;
        this.selectedLanguage = selectedLanguage;

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

        holder.mTextView.setText(languages.get(position));

        // To check weather checked event fire from getview() or user input
//        isFromView = true;
        holder.mCheckBox.setChecked(selectedLanguage[position]);
//        isFromView = false;

//        if ((position == 0)) {
//            holder.mCheckBox.setVisibility(View.INVISIBLE);
//        } else {
//            holder.mCheckBox.setVisibility(View.VISIBLE);
//        }
        holder.mCheckBox.setTag(position);

//        holder.mCheckBox.setChecked();
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int getPosition = (Integer) buttonView.getTag();
                if (isChecked){
                    selectedLanguage[position] = true;
                }else {
                    selectedLanguage[position] = false;

                    Toast.makeText(mContext,languages.get(position),Toast.LENGTH_SHORT).show();
                }
            }
        });

        return convertView;
    }
}
