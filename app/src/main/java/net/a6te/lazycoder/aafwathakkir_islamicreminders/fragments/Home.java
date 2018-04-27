package net.a6te.lazycoder.aafwathakkir_islamicreminders.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.a6te.lazycoder.aafwathakkir_islamicreminders.R;

import me.grantland.widget.AutofitHelper;
import me.grantland.widget.AutofitTextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {


    public Home() {
        // Required empty public constructor
    }

    private View view;
//    private TextView autoSizeTv;
    private TextView autoSizeTv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view =  inflater.inflate(R.layout.fragment_home, container, false);
        initializeAll();
        return view;
    }

    private void initializeAll() {
        autoSizeTv = view.findViewById(R.id.testAutoFill);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        AutofitHelper.create(autoSizeTv);
//        autoSizeTv.setText(getContext().getResources().getString(R.string.maximum_text_limit));

        TextViewCompat.setAutoSizeTextTypeWithDefaults(autoSizeTv, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);

    }
}
