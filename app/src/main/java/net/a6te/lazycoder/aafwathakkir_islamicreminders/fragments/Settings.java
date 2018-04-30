package net.a6te.lazycoder.aafwathakkir_islamicreminders.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import net.a6te.lazycoder.aafwathakkir_islamicreminders.MVP.MVPPresenter;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.MVP.MVPView;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.MVP.SettingsPresenter;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.R;


import java.util.List;


public class Settings extends Fragment implements MVPView.SettingsView{

    private View view;
    private Spinner frequencySp, prayerTimeCalculationSp, juristicSp;
    private Spinner remainderSp,languageSp;
    private MVPPresenter.SettingsPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_settings, container, false);
        initializeAll();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter.prepareAdapters();
    }

    private void initializeAll() {
        languageSp = view.findViewById(R.id.languageSp);
        frequencySp = view.findViewById(R.id.frequencySp);
        prayerTimeCalculationSp = view.findViewById(R.id.prayerTimeCalculationMethodSp);
        juristicSp = view.findViewById(R.id.juristicMethodSp);

        remainderSp = view.findViewById(R.id.remainderLanguageSp);

        presenter = new SettingsPresenter(this);
    }

    @Override
    public void initializeLanguageSpinner(ArrayAdapter adapter) {
        languageSp.setAdapter(adapter);
    }

    @Override
    public void initializeFrequencySpinner(ArrayAdapter adapter) {
        frequencySp.setAdapter(adapter);

        frequencySp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }



    @Override
    public void initializePrayerTimeCalculationSpinner(ArrayAdapter adapter) {
        prayerTimeCalculationSp.setAdapter(adapter);
    }

    @Override
    public void initializeJuristicSpinner(ArrayAdapter adapter) {
        juristicSp.setAdapter(adapter);
    }

    @Override
    public void initializeRemainderLanguage(ArrayAdapter adapter){
        remainderSp.setAdapter(adapter);
    }
}
