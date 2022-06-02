package com.SK.filedownloader;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText url;
    private EditText ext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    url = findViewById(R.id.url);
    ext=findViewById(R.id.extension);
    Button download = findViewById(R.id.download);

    download.setOnClickListener(new View.OnClickListener(){

        @Override
        public void onClick(View v) {
         String geturl=url.getText().toString();
         String ext1=ext.getText().toString();

            DownloadManager.Request req= new DownloadManager.Request(Uri.parse(geturl));
            String title= URLUtil.guessFileName((geturl),null,null);
            req.setTitle(title);
            req.setDescription("Downloading File...");
            String cookie= CookieManager.getInstance().getCookie(geturl);
            req.addRequestHeader("cookie",cookie);

            req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            req.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,title+ext1);

            DownloadManager dm= (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
            dm.enqueue(req);
            Toast.makeText(MainActivity.this,"Download Started ",Toast.LENGTH_LONG).show();

            long downloadID = dm.enqueue(req);// enqueue puts the download request in the queue.
            
        }

    });


}


}