package prototipo.adapto.com.menuc.BloggerStructure;

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

import androidx.appcompat.app.AppCompatActivity;

import prototipo.adapto.com.menuc.R;

public class DetalharPostagem extends AppCompatActivity {

  public static final String MENUCEDUCADIONAL_BLOGSPOT_COM = "menuceducadional.blogspot.com";
  public static final String QUESTIONARIOIFBA_BLOGSPOT_COM = "menucquestionarios.blogspot.com";
  public static final String FORMS_GLE = "forms.gle";
  public static final String DOCS_GOOGLE = "docs.google.com";
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
      }

      @Override
      public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        progressBar.setVisibility(View.GONE);
        webView.setVisibility(View.VISIBLE);
      }

      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url)
      {
        String host = Uri.parse(url).getHost();

        if (MENUCEDUCADIONAL_BLOGSPOT_COM.equals(host) || QUESTIONARIOIFBA_BLOGSPOT_COM.equals(host) || FORMS_GLE.equals(host) || DOCS_GOOGLE.equals(host)){
          if(url.contains("docs.google"))
            loadQuestionario(url);
          return false;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
        return true;
      }
    });
    webView.loadUrl(getIntent().getStringExtra("url"));
  }

  private void loadQuestionario(String url) {
    String url_questionario = url.substring(url.lastIndexOf("https"), url.lastIndexOf("viewform")+8);
    webView.loadUrl(url_questionario);
  }

}