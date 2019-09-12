package prototipo.italoluis.com.fireprot3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import prototipo.italoluis.com.fireprot3.PostStructure.TelaInicial;


public class WebViewConfig extends AppCompatActivity {
  private Context mContext;
  private Activity mActivity;
  public String url;

  private WebView mWebView;



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_teste_script);




    // Get the application context
    mContext = getApplicationContext();
    mActivity = this;


    mWebView = findViewById(R.id.web_view);

    Intent intent_origin = getIntent();
    url = intent_origin.getStringExtra("url");
    mWebView.loadUrl(url);

    mWebView.getSettings().setJavaScriptEnabled(true);

    final String js = "javascript:document.document.getElementsByName('M2UYVd').click()";

    mWebView.setWebViewClient(new WebViewClient(){
      public void onPageFinished(WebView view, String url){
        if(Build.VERSION.SDK_INT >= 19){
          view.evaluateJavascript(js, new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String s) {
            }
          });
        }
      }
    });

  }



  @Override
  public void onBackPressed() {
    super.onBackPressed();
    Intent intent = new Intent(this, TelaInicial.class);
    startActivity(intent);
  }
}