package com.vsts.willl.demovsts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;
    private Button my_button;
    private Button my_button_2;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = findViewById(R.id.web_my);
        my_button = findViewById(R.id.my_button);
        my_button_2 = findViewById(R.id.my_button_2);
        editText = findViewById(R.id.edit_text);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        webView.loadUrl("file:///android_asset/Test.html");

    }

    public void click(View view){
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("javascript:showWithNoParam()");
            }
        });
    }

    public String[] getStringArray(){
        final String[] arr = new String[2];
        String string = editText.getText().toString();
        if(string.length()>=2){
            arr[0] = string+"_1";
            arr[1] = string+"_2";
        }
        return arr;
    }

    public void click1(View view){
        final String[] array = getStringArray();
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("javascript:showWithParams('"+array[0] +"','"+ array[1]+"')");
            }
        });
    }

    public void click2(View view){
        String[] array = getStringArray();
        webView.evaluateJavascript("javascript:showWithParams('" + array[0] + "','" + array[1] + "')"
                , new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        Toast.makeText(getApplication(),value,Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
