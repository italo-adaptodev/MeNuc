package prototipo.italoluis.com.menuc.BloggerStructure;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import prototipo.italoluis.com.menuc.R;

public class DetalhesPosts extends AppCompatActivity {

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
    webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
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
        Toast.makeText(DetalhesPosts.this,"Carregando tela", Toast.LENGTH_SHORT).show();
      }

      @Override
      public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        progressBar.setVisibility(View.GONE);
        webView.setVisibility(View.VISIBLE);
        Toast.makeText(DetalhesPosts.this, "Tela carregada com sucesso", Toast.LENGTH_SHORT).show();

      }

      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url)
      {
        if ("nuclearmedifba.blogspot.com".equals(Uri.parse(url).getHost())) {
          return false;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
        return true;
      }
    });
    webView.loadUrl(getIntent().getStringExtra("url"));
  }

}