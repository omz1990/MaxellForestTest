package com.omar.maxellforesttest.Common.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.omar.maxellforesttest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by omz on 28/5/18
 */
public class WebViewFragment extends BaseFragment {

    @BindView(R.id.webView) protected WebView webView;
    @BindView(R.id.progressBar) protected ProgressBar progressBar;

    String url;

    /**
     * This class has intentionally been kept separate in the Common package as it can be used for any other purpose
     */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Keep data saved while screen orientation changes
        setRetainInstance(true);

        // Repopulate previously loaded data to handle not losing data on screen orientation if there is any available
        if (savedInstanceState != null) {
            repopulateData(savedInstanceState.getString("url"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web_view, container, false);
        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        if (bundle != null) {
            url = bundle.getString("url");
        }

        setupWebView();

        return view;
    }

    public void repopulateData(String url) {
        this.url = url;
        loadUrl();
    }

    private void loadUrl() {
        webView.loadUrl(url);
    }

    private void setupWebView() {

        // Set the required properties for our webview
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(false);
        settings.setDisplayZoomControls(false);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });
        loadUrl();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently loaded data into the bundle for reloading
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("url", url);
    }
}