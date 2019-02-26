package com.vsts.willl.demovsts;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.CrashManagerListener;
import net.hockeyapp.android.LoginManager;
import net.hockeyapp.android.UpdateManager;
import net.hockeyapp.android.UpdateManagerListener;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {
    private static String service = "MyIntentService";
    private TextView textView;


    public void goSecond(View view){
        Intent intent = new Intent(this, WebViewActivity.class);
        startActivity(intent);
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.my_test_menu, popup.getMenu());
        popup.show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_menu_list,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.list_1:
                show(item.getTitle().toString());
                break;
            case R.id.list_2:
                show(item.getTitle().toString());
                break;
            case R.id.list_3:
                show(item.getTitle().toString());
                break;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_menu_1:
                show(item.getTitle().toString());
                break;
            case R.id.item_menu_2:
                show(item.getTitle().toString());
                break;
            case R.id.item_menu_3:
                show(item.getTitle().toString());
                break;
            case R.id.item_menu_4:
                show(item.getTitle().toString());
                break;
            case R.id.item_menu_5:
                show(item.getTitle().toString());
                break;
            case R.id.item_menu_6:
                show(item.getTitle().toString());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_test_menu,menu);
        return true;
    }

    public void show(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

    public void service1(View view){
        Intent intent = new Intent("com.vsts.willl.demovsts."+service);
        intent.setPackage("com.vsts.willl.demovsts");
        intent.putExtra("test","service1");
//        byte[] bytes = new byte[1024*5];
//        for (int i = 0; i < 100; i++) {
//            intent.putExtra("TEST"+i,bytes);
//        }

        startService(intent);
    }
    public void service2(View view){
//        Intent intent = new Intent("com.vsts.willl.demovsts."+service);
//        intent.setPackage("com.vsts.willl.demovsts");
//        intent.putExtra("test","service2");
//        startService(intent);
        showPopup(view);
        TestForError testForError = new TestForError();
        String canonicalName = testForError.getClass().getCanonicalName();
        Toast.makeText(this,canonicalName,Toast.LENGTH_LONG).show();
    }
    public void service3(View view){
        Intent intent = new Intent("com.vsts.willl.demovsts."+service);
        intent.setPackage("com.vsts.willl.demovsts");
        intent.putExtra("test","stop");
//        byte[] bytes = new byte[1024*5];
//        for (int i = 0; i < 100; i++) {
//            intent.putExtra("TEST"+i,bytes);
//        }
        startService(intent);
    }

    public void dialog(View v){
        MyDialogFragment myDialogFragment = new MyDialogFragment();
        myDialogFragment.show(getSupportFragmentManager(),"Dialog");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.test_context_menu);
        this.registerForContextMenu(textView);
        LoginManager.register(this,"bd121317134513976a75ff29da9e7034",LoginManager.LOGIN_MODE_ANONYMOUS);
        LoginManager.verifyLogin(this,getIntent());

        init();

        Toast.makeText(this, "android id:"+getAndroidID(),Toast.LENGTH_SHORT).show();
    }
    public String getAndroidID() {
        String id = Settings.Secure.getString(
                this.getContentResolver(),
                Settings.Secure.ANDROID_ID
        );
        return id == null ? "" : id;
    }

    public void init(){
        CrashManager.register(this, new CrashManagerListener() {
            @Override
            public boolean shouldAutoUploadCrashes() {
                return true;
            }

            @Override
            public void onCrashesSent() {
                super.onCrashesSent();
                Log.e("HockeyCrash:","onCrashesSent");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "onCrashesSent", Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onCrashesNotSent() {
                super.onCrashesNotSent();
                Log.e("HockeyCrash:","onCrashesNotSent");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "onCrashesNotSent", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        UpdateManager.register(this, new UpdateManagerListener() {
            @Override
            public void onUpdateAvailable(JSONArray data, String url) {
                super.onUpdateAvailable(data, url);

                PackageManager packageManager = getApplication().getPackageManager();
                PackageInfo packageInfo = null;
                try {
                    packageInfo = packageManager.getPackageInfo(getApplication().getPackageName(), 0);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                String version = String.format("current app version: %s","" + packageInfo.versionCode);
                Log.e("*****Test*****", version);
                Log.e("*****Test*****", data.toString());
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UpdateManager.unregister();
    }

    public void click1(View view){
        TestForError testForError = new TestForError();
        testForError.error1();
        testForError.error2();
        testForError.error3();
    }
}
