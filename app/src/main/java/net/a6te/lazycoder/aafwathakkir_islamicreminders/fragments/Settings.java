package net.a6te.lazycoder.aafwathakkir_islamicreminders.fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.thomashaertel.widget.MultiSpinner;

import net.a6te.lazycoder.aafwathakkir_islamicreminders.MVP.MVPPresenter;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.MVP.MVPView;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.MVP.SettingsPresenter;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.MainActivity;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.R;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.SavedData;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class Settings extends Fragment implements MVPView.SettingsView{

    private View view;
    private Spinner frequencySp, prayerTimeCalculationSp, juristicSp;
    private Spinner languageSp;
    private MVPPresenter.SettingsPresenter presenter;

    private TextView appLanguageTv, frequencyTv,calculationMethodTv,juristicMethodTv;
    private boolean isFirstTime = true;

    private MultiSpinner remainderSp;

    boolean[] selectedLanguage;

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

        appLanguageTv = view.findViewById(R.id.appLanguageTv);
        frequencyTv = view.findViewById(R.id.frequencyTv);
        calculationMethodTv = view.findViewById(R.id.calculationMethodTv);
        juristicMethodTv = view.findViewById(R.id.juristicMethodTv);


    }



    @Override
    public void initializeLanguageSpinner(ArrayAdapter adapter, String selectedName, int position) {
        appLanguageTv.setText(selectedName);
        languageSp.setAdapter(adapter);

        languageSp.setSelection(position);
        languageSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                /*
                * when we open setting page first time this listener automatically(without click language spinner
                * so we by pass this by this technique)
                * its most important for setting page
                * */
                if (!isFirstTime) {
                    appLanguageTv.setText(languageSp.getSelectedItem().toString());
                    presenter.saveAppLanguageId(position);
                }else {
                    isFirstTime = false;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

    }

    @Override
    public void initializeFrequencySpinner(ArrayAdapter adapter, String selectedName, int position) {

        frequencyTv.setText(selectedName);
        frequencySp.setAdapter(adapter);

        frequencySp.setSelection(position);
        frequencySp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                frequencyTv.setText(frequencySp.getSelectedItem().toString());
                presenter.saveFrequencyId(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

    }



    @Override
    public void initializePrayerTimeCalculationSpinner(ArrayAdapter adapter, String selectedName, int position) {
        calculationMethodTv.setText(selectedName);
        prayerTimeCalculationSp.setAdapter(adapter);

        prayerTimeCalculationSp.setSelection(position);
        prayerTimeCalculationSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                calculationMethodTv.setText(prayerTimeCalculationSp.getSelectedItem().toString());
                presenter.saveCalculationMethodId(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

    }

    @Override
    public void initializeJuristicSpinner(ArrayAdapter adapter, String selectedName, int position) {
        juristicMethodTv.setText(selectedName);
        juristicSp.setAdapter(adapter);

        juristicSp.setSelection(position);
        juristicSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                juristicMethodTv.setText(juristicSp.getSelectedItem().toString());
                presenter.saveJuristicMethodId(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    @Override
    public void initializeRemainderLanguage(ArrayAdapter adapter, boolean[] selectedLanguage){
        remainderSp.setAdapter(adapter,false,onSelectedListener);
        remainderSp.setSelected(selectedLanguage);

        this.selectedLanguage = selectedLanguage;
    }

    private MultiSpinner.MultiSpinnerListener onSelectedListener = new MultiSpinner.MultiSpinnerListener() {
        public void onItemsSelected(boolean[] selected) {
            // Do something here with the selected items
            selectedLanguage = selected;
            presenter.saveSelectedLanguage(selectedLanguage);
        }
    };

}
