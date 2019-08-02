package prototipo.italoluis.com.fireprot3.postsblog;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import prototipo.italoluis.com.fireprot3.R;

public class postDetalhe extends AppCompatActivity {

  ProgressBar progressBar;

  WebView webView;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_post_detalhe);
    progressBar = findViewById(R.id.progressBar);
    webView = findViewById(R.id.detalhestela);

    webView.setVisibility(View.INVISIBLE);
    webView.getSettings().setJavaScriptEnabled(true);
    webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
    webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    webView.getSettings().setAppCacheEnabled(true);
    webView.getSettings().setDomStorageEnabled(true);
    webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
    webView.getSettings().setUseWideViewPort(true);
    webView.getSettings().setEnableSmoothTransition(true);
    webView.getSettings().setSaveFormData(true);
    webView.getSettings().setSavePassword(true);



    webView.setWebChromeClient(new WebChromeClient());
    webView.setWebViewClient(new WebViewClient(){

      @Override
      public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        Toast.makeText(postDetalhe.this,"Carregando tela", Toast.LENGTH_SHORT).show();
      }

      @Override
      public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        progressBar.setVisibility(View.GONE);
        webView.setVisibility(View.VISIBLE);
        Toast.makeText(postDetalhe.this, "Tela carregada com sucesso", Toast.LENGTH_SHORT).show();

      }
    });
    webView.loadUrl(getIntent().getStringExtra("url"));
  }

}