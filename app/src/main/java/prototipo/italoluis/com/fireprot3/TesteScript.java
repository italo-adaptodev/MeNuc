package prototipo.italoluis.com.fireprot3;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;


public class TesteScript extends AppCompatActivity {
    private Context mContext;
    private Activity mActivity;

    private LinearLayout mRootLayout;
    private WebView mWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_script);

        // Get the application context
        mContext = getApplicationContext();
        mActivity = this;

        // Get the widget reference from xml layout
        mRootLayout = findViewById(R.id.root_layout);
        mWebView = findViewById(R.id.web_view);

        // The target url to surf using web view
        String url = "https://www.blogger.com/u/1/blogger.g?blogID=537701014572510680#basicsettings";

        /*
            WebView
                A View that displays web pages. This class is the basis upon which you can roll
                your own web browser or simply display some online content within your Activity.
                It uses the WebKit rendering engine to display web pages and includes methods
                to navigate forward and backward through a history, zoom in and out,
                perform text searches and more.
        */
        // Load the url in web view
        mWebView.loadUrl(url);

        // Enable java script on web view
        mWebView.getSettings().setJavaScriptEnabled(true);

        // The java script string to execute in web view after page loaded
        // First line put a value in input box
        // Second line submit the form
        final String js = "javascript:document.querySelector('K3JSBVB-mb-a').click()";


        // Set a web view client for web view
        mWebView.setWebViewClient(new WebViewClient(){
            /*
                void onPageFinished (WebView view, String url)
                    Notify the host application that a page has finished loading. This method is
                    called only for main frame. When onPageFinished() is called, the rendering
                    picture may not be updated yet. To get the notification for the new Picture,
                    use onNewPicture(WebView, Picture).

                Parameters
                    view WebView : The WebView that is initiating the callback.
                    url String : The url of the page.
            */
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
}