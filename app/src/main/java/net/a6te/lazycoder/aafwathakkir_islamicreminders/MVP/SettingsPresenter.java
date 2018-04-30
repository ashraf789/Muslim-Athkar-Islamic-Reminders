package net.a6te.lazycoder.aafwathakkir_islamicreminders.MVP;

import android.support.v4.app.Fragment;

import net.a6te.lazycoder.aafwathakkir_islamicreminders.R;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.adapters.SpinnerAdapter;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.adapters.SpinnerWithCheckBoxAdapter;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.model.SpinnerWithCheckBoxItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingsPresenter implements MVPPresenter.SettingsPresenter{

    private Fragment fragment;
    private List<String > juristicMethods;
    private List<String > prayerTimeCalculationMethods;
    private List<String > frequencies;
    private List<String> languages;
    private List<SpinnerWithCheckBoxItem> remainderLanguages;

    private MVPView.SettingsView mvpView;



    public SettingsPresenter(Fragment fragment) {
        this.fragment = fragment;
        mvpView = (MVPView.SettingsView) fragment;
    }

    @Override
    public void prepareAdapters(){
        languageAdapter();
        frequencyAdapter();
        prayerTimeCalculationMethod();
        juristicMethod();
        remainderLanguageAdapter();
    }

    private void remainderLanguageAdapter() {
        remainderLanguages = new ArrayList<>();

        List<String> temp = Arrays.asList(fragment.getContext().getResources().getStringArray(R.array.remainder_language));
        int size = temp.size();

        // we are taking data from sting_array and prepare our custom adapter
        for (int i = 0; i< size; i++){
            remainderLanguages.add(new SpinnerWithCheckBoxItem(temp.get(i),false));
        }

        SpinnerWithCheckBoxAdapter adapter = new SpinnerWithCheckBoxAdapter(fragment.getContext(),remainderLanguages);
        mvpView.initializeRemainderLanguage(adapter);
    }


    private void juristicMethod() {
        juristicMethods = null;
        juristicMethods = Arrays.asList(fragment.getContext().getResources().getStringArray(R.array.juristic_method));

        SpinnerAdapter adapter = new SpinnerAdapter(fragment.getContext(), juristicMethods,0);
        mvpView.initializeJuristicSpinner(adapter);
    }

    private void prayerTimeCalculationMethod() {
        prayerTimeCalculationMethods = null;
        prayerTimeCalculationMethods = Arrays.asList(fragment.getContext().getResources().getStringArray(R.array.prayer_calculation_method));

        SpinnerAdapter adapter = new SpinnerAdapter(fragment.getContext(), prayerTimeCalculationMethods,0);
        mvpView.initializePrayerTimeCalculationSpinner(adapter);
    }

    private void frequencyAdapter() {
        frequencies = null;
        frequencies = Arrays.asList(fragment.getContext().getResources().getStringArray(R.array.frequency));

        SpinnerAdapter adapter = new SpinnerAdapter(fragment.getContext(), frequencies,0);
        mvpView.initializeFrequencySpinner(adapter);
    }

    private void languageAdapter() {
        languages = null;
        languages = Arrays.asList(fragment.getContext().getResources().getStringArray(R.array.app_languages));

        String temp = languages.get(0);
        languages.set(0,languages.get(2));
        languages.set(2,temp);


        SpinnerAdapter adapter = new SpinnerAdapter(fragment.getContext(), languages,1);
        mvpView.initializeLanguageSpinner(adapter);
    }
}
