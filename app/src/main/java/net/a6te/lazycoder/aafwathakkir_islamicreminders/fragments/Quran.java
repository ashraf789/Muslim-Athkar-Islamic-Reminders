package net.a6te.lazycoder.aafwathakkir_islamicreminders.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.shockwave.pdfium.PdfDocument;

import net.a6te.lazycoder.aafwathakkir_islamicreminders.MVP.MVPPresenter;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.MVP.MVPView;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.MVP.QuranPresenter;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.MainActivity;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.R;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.adapters.CustomSuggestionsAdapter;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.interfaces.OnSearchItemClick;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.model.Surah;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Quran extends Fragment implements MVPView.QuranView, OnPageChangeListener,OnLoadCompleteListener,OnSearchItemClick {

    private View view;
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String SAMPLE_FILE = "quraan.pdf";
    PDFView pdfView;
    String pdfFileName;
    private ArrayList<Surah> surahs;
    private LayoutInflater inflater;

    private MaterialSearchBar searchBar;
    private CustomSuggestionsAdapter customSuggestionsAdapter;
    private MVPPresenter.QuranPresenter presenter;

    public Quran() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_quran, container, false);
        initializeAll();
        return view;
    }

    private void initializeAll() {
        pdfView = view.findViewById(R.id.pdfView);
        surahs = new ArrayList<>();

        inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        searchBar = view.findViewById(R.id.searchBar);

        presenter = new QuranPresenter(this);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        displayFromAsset(SAMPLE_FILE,0);//default pdf will be start from 0 page
        try {
            searchBar.setMaxSuggestionCount(2);
            searchBar.setHint("Find surah..");

            searchBar.addTextChangeListener(textWatcher);

        }catch (Exception e){

        }


    }

    private void displayFromAsset(String assetFileName, int pageNumber) {
        pdfFileName = assetFileName;

        pdfView.fromAsset(SAMPLE_FILE)
                .defaultPage(pageNumber)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this.getContext()))
                .load();

    }


    @Override
    public void onPageChanged(int page, int pageCount) {
        getActivity().setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
    }


    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        printBookmarksTree(pdfView.getTableOfContents(), "-");


    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

//            Log.e("TEST", String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));
//            Log.d(TAG, "sep: : "+sep);
//            Log.d(TAG, "Title : "+b.getTitle());
//            Log.d(TAG, "getPageIndex : "+b.getPageIdx());

            surahs.add(new Surah(b.getTitle(),b.getPageIdx()));
            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }


        presenter.prepareSearchAdapter(inflater,surahs);


    }



    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            try {
                // send the entered text to our filter and let it manage everything
                customSuggestionsAdapter.getFilter().filter(searchBar.getText());
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    public void initializeSearchView(CustomSuggestionsAdapter adapter) {
        if (adapter != null) {
            try {
                searchBar.setCustomSuggestionAdapter(adapter);
                this.customSuggestionsAdapter = adapter;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onSearchItemClick(String indexNo) {
//        displayFromAsset(SAMPLE_FILE,Integer.valueOf(indexNo));//default pdf will be start from 0 page
//        onPageChanged(60,500);

//        searchBar.disableSearch();
        pdfView.jumpTo(Integer.parseInt(indexNo));


    }

    @Override
    public void onPause() {
        super.onPause();

    }
}
