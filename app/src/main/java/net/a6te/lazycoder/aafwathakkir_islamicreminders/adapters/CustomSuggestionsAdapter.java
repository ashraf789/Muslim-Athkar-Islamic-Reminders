package net.a6te.lazycoder.aafwathakkir_islamicreminders.adapters;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mancj.materialsearchbar.adapter.SuggestionsAdapter;

import net.a6te.lazycoder.aafwathakkir_islamicreminders.R;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.interfaces.OnSearchItemClick;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.model.Surah;

import java.util.ArrayList;

public class CustomSuggestionsAdapter extends SuggestionsAdapter<Surah, CustomSuggestionsAdapter.SuggestionHolder> {
    private OnSearchItemClick onSearchItemClick;

    public CustomSuggestionsAdapter(LayoutInflater inflater, Fragment fragment) {
        super(inflater);
        onSearchItemClick = (OnSearchItemClick) fragment;
    }

    @Override
    public int getSingleViewHeight() {
        return 100;
    }

    @Override
    public SuggestionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.item_custom_suggestion, parent, false);
        return new SuggestionHolder(view);
    }

    @Override
    public void onBindSuggestionHolder(final Surah suggestion, SuggestionHolder holder, int position) {
        holder.title.setText(suggestion.getName());
        holder.subtitle.setText("Page "+String.valueOf(suggestion.getIndexNo()+1));


        holder.layoutSuggestionLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchItemClick.onSearchItemClick(String.valueOf(suggestion.getIndexNo()));
            }
        });
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                String term = constraint.toString();
                if (term.isEmpty())
                    suggestions = suggestions_clone;
                else {
                    suggestions = new ArrayList<>();
                    for (Surah item : suggestions_clone) {

                        if (item.getName().toLowerCase().contains(term.toLowerCase())) {
                            suggestions.add(item);
                        } else if (String.valueOf(item.getIndexNo()).contains(term)) {
                            suggestions.add(item);
                        }
                    }
                }
                results.values = suggestions;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                suggestions = (ArrayList<Surah>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    static class SuggestionHolder extends RecyclerView.ViewHolder {
        protected TextView title;
        protected TextView subtitle;
        protected LinearLayout layoutSuggestionLl;

        public SuggestionHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            subtitle = (TextView) itemView.findViewById(R.id.subtitle);
            layoutSuggestionLl = itemView.findViewById(R.id.layoutSuggestionLl);
        }
    }
}