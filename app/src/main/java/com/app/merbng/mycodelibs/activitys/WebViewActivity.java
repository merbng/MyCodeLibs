package com.app.merbng.mycodelibs.activitys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.utils.TimeUtils;

/**
 * 打开网页
 */
public class WebViewActivity extends BaseActivity implements View.OnClickListener {
    //    private RelativeLayout titleBar;
    private WebView webView;
    private ProgressBar progress;
    public String webUrl;
    public String title;
    //    private LinearLayout bottomBar;//底部bar（前进，后退，刷新，复制链接）
    public static final String WEBURL = "url"; //传过来的链接key
    public static final String WEBTITLE = "title"; //传过来的标题key
    private TextView webTitleView;
    private TextView webCloseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fvb();
        init();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    finish();
                }
                return true;
            case R.id.menu_refresh:
                webView.reload();
                return true;
            case R.id.menu_copylink:
                TimeUtils.copyLinks(mContext, webUrl);
                return true;
            case R.id.menu_intentblower:
                Uri content_url = Uri.parse(webUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW, content_url);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void fvb() {
        Intent intent = getIntent();
        webUrl = intent.getStringExtra(WEBURL);
        title = intent.getStringExtra(WEBTITLE);
        webTitleView = (TextView) findViewById(R.id.toobar_title);
        webCloseBtn = (TextView) findViewById(R.id.toolbar_close);
        webView = (WebView) findViewById(R.id.webPlane);
        progress = (ProgressBar) findViewById(R.id.progress);
    }

    public void init() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            webTitleView.setText(title);
        }
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setBlockNetworkImage(false);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(final String downloadUrl, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                // TODO: 2017/2/28 需要换dialog
               /* DialogUtils.confirmDownload(ProtocolActivity.this, "确定要下载" + downloadUrl + "吗？", "这可能产生安全风险", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (downloadUrl != null && downloadUrl.startsWith("http://")) {
                            DownloadManager downloadManager;
                            downloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
                            Uri uri = Uri.parse(downloadUrl);
                            DownloadManager.Request request = new DownloadManager.Request(uri);
                            MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton(); // 获取文件类型实例
                            String mimeString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(downloadUrl)); // 获取文件类型
                            request.setMimeType(mimeString); // 制定下载文件类型
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                            request.setDestinationInExternalFilesDir(mContext, Environment.DIRECTORY_DOWNLOADS, mContext.getString(R.string.app_name));
                            downloadManager.enqueue(request);
                            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
                        }
                    }
                }).show();*/

            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                webTitleView.setText(title);
            }

        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                progress.setVisibility(View.GONE);
                if (webView.canGoBack()) {
                    webCloseBtn.setVisibility(View.VISIBLE);
                } else {
                    webCloseBtn.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progress.setVisibility(View.VISIBLE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("http")) {
                    webView.loadUrl(url);
                    webCloseBtn.setVisibility(View.VISIBLE);
                }

                return true;

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    String url = request.getUrl().toString();
                    if (url != null && url.startsWith("http")) {
                        webView.loadUrl(url);
                        webCloseBtn.setVisibility(View.VISIBLE);
                    }

                }
                return true;
            }

        });
        webView.loadUrl(webUrl);
        webCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();
            } else {
                finish();
            }
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }

    @Override
    public void onDestroy() {
        webView.destroy();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

    }
}
