package com.getcapacitor;

import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

/**
 * Provides callbacks associated with the {@link BridgeWebViewClient}
 */
public abstract class WebViewListener {

    /**
     * Callback for page load event.
     *
     * @param webView The WebView that is initiating the callback.
     * @param url The url of the page.
     */
    public void onPageLoaded(WebView webView, String url) {
        // Override me to add behavior to the page loaded event
    }

    /**
     * Callback for onReceivedError event.
     *
     * @param webView The WebView that is initiating the callback.
     * @param request The originating request.
     * @param error Information about the error occurred.
     */
    public void onReceivedError(WebView webView, WebResourceRequest request, WebResourceError error) {
        // Override me to add behavior to handle the onReceivedError event
    }

    /**
     * Callback for onReceivedHttpError event.
     *
     * @param webView The WebView that is initiating the callback.
     * @param request The originating request.
     * @param error Information about the error occurred.
     */
    public void onReceivedHttpError(WebView webView, WebResourceRequest request, WebResourceResponse error) {
        // Override me to add behavior to handle the onReceivedHttpError event
    }

    /**
     * Callback for page start event.
     *
     * @param webView The WebView that loaded
     */
    public void onPageStarted(WebView webView) {
        // Override me to add behavior to the page started event
    }
}
