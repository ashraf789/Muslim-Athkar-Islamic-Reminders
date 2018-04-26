package net.a6te.lazycoder.aafwathakkir_islamicreminders.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import net.a6te.lazycoder.aafwathakkir_islamicreminders.MVP.MVPPresenter;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.MVP.MVPView;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.MVP.SettingsPresenter;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.R;

import org.angmarch.views.NiceSpinner;

import java.util.List;


public class Settings extends Fragment implements MVPView.SettingsView{

    private View view;
    private NiceSpinner languageSp, frequencySp, prayerTimeCalculationSp, juristicSp;
    private Spinner remainderSp;
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
    public void initializeLanguageSpinner(List<String> dataSet) {
        languageSp.attachDataSource(dataSet);
    }

    @Override
    public void initializeFrequencySpinner(List<String> dataSet) {
        frequencySp.attachDataSource(dataSet);
    }

    @Override
    public void initializePrayerTimeCalculationSpinner(List<String> dataSet) {
        prayerTimeCalculationSp.attachDataSource(dataSet);
    }

    @Override
    public void initializeJuristicSpinner(List<String> dataSet) {
        juristicSp.attachDataSource(dataSet);
    }

    @Override
    public void initializeRemainderLanguage(ArrayAdapter adapter){
        remainderSp.setAdapter(adapter);
    }


}
