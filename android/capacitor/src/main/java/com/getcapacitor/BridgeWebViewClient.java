package com.getcapacitor;

import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.List;

public class BridgeWebViewClient extends WebViewClient {

    private Bridge bridge;

    public BridgeWebViewClient(Bridge bridge) {
        this.bridge = bridge;
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        return bridge.getLocalServer().shouldInterceptRequest(request);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        Uri url = request.getUrl();
        return bridge.launchIntent(url);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return bridge.launchIntent(Uri.parse(url));
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        List<WebViewListener> webViewListeners = bridge.getWebViewListeners();

        if (webViewListeners != null && view.getProgress() == 100) {
            for (WebViewListener listener : bridge.getWebViewListeners()) {
                listener.onPageLoaded(view, url);
            }
        }
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);

        List<WebViewListener> webViewListeners = bridge.getWebViewListeners();
        if (webViewListeners != null) {
            for (WebViewListener listener : bridge.getWebViewListeners()) {
                listener.onReceivedError(view, request, error);
            }
        }
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        Bridge.inject(view, bridge.getJSInjector());
        bridge.reset();
        List<WebViewListener> webViewListeners = bridge.getWebViewListeners();

        if (webViewListeners != null) {
            for (WebViewListener listener : bridge.getWebViewListeners()) {
                listener.onPageStarted(view);
            }
        }
    }

    @Override
    public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
        if (isReload) {
            Bridge.inject(view, bridge.getJSInjector());
        }
        super.doUpdateVisitedHistory(view, url, isReload);
    }

    @Override
    public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
        super.onReceivedHttpError(view, request, errorResponse);

        List<WebViewListener> webViewListeners = bridge.getWebViewListeners();
        if (webViewListeners != null) {
            for (WebViewListener listener : bridge.getWebViewListeners()) {
                listener.onReceivedHttpError(view, request, errorResponse);
            }
        }
    }
}
