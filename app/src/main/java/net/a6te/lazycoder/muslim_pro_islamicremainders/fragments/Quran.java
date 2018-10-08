package net.a6te.lazycoder.muslim_pro_islamicremainders.fragments;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.shockwave.pdfium.PdfDocument;

import net.a6te.lazycoder.muslim_pro_islamicremainders.MVP.MVPPresenter;
import net.a6te.lazycoder.muslim_pro_islamicremainders.MVP.MVPView;
import net.a6te.lazycoder.muslim_pro_islamicremainders.MVP.QuranPresenter;
import net.a6te.lazycoder.muslim_pro_islamicremainders.MainActivity;
import net.a6te.lazycoder.muslim_pro_islamicremainders.R;
import net.a6te.lazycoder.muslim_pro_islamicremainders.adapters.CustomSuggestionsAdapter;
import net.a6te.lazycoder.muslim_pro_islamicremainders.interfaces.OnSearchItemClick;
import net.a6te.lazycoder.muslim_pro_islamicremainders.model.Surah;

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
    private MediaPlayer ring;


    public Quran() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_quran, container, false);
        initializeAll();
        playSound();//it will play allhu akbar sound

        return view;
    }

    private void initializeAll() {
        ring= MediaPlayer.create(getContext(),R.raw.prayer_allahu_akbar);
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

        /*
        * search bar default settings*/
        try {
            searchBar.setMaxSuggestionCount(2);
            searchBar.setHint(getString(R.string.find_surah));

            searchBar.addTextChangeListener(textWatcher);

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    /*
    * initialize PDF view [PDF is located on asset folder]
    * */
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

        searchBar.disableSearch();
        pdfView.jumpTo(Integer.parseInt(indexNo));


    }


    public void playSound(){
        if (!ring.isPlaying()) {
            ring.start();
        }
    }
    @Override
    public void onPause() {
        ring.stop();
        super.onPause();
    }
}
