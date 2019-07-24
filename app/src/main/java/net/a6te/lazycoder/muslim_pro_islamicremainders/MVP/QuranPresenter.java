package net.a6te.lazycoder.muslim_pro_islamicremainders.MVP;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import net.a6te.lazycoder.muslim_pro_islamicremainders.adapters.CustomSuggestionsAdapter;
import net.a6te.lazycoder.muslim_pro_islamicremainders.model.Surah;

import java.util.ArrayList;


public class QuranPresenter implements MVPPresenter.QuranPresenter {
    private ArrayList<Surah> suggestions;
    private CustomSuggestionsAdapter customSuggestionsAdapter;
    private MVPView.QuranView quranView;
    private Fragment fragment;

    public QuranPresenter(Fragment fragment) {
        quranView = (MVPView.QuranView) fragment;
        this.fragment = fragment;
    }

    @Override
    public void prepareSearchAdapter(LayoutInflater inflater, ArrayList<Surah> surahs) {
        suggestions = surahs;

        try {
            if (suggestions == null){
                customSuggestionsAdapter = null;

            }else {
                customSuggestionsAdapter = new CustomSuggestionsAdapter(inflater,fragment);
                customSuggestionsAdapter.setSuggestions(suggestions);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        quranView.initializeSearchView(customSuggestionsAdapter);
    }
}
