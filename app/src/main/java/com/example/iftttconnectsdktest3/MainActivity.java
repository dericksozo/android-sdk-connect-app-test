package com.example.iftttconnectsdktest3;

import android.net.Credentials;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.ifttt.connect.ui.ConnectButton;
import com.ifttt.connect.ui.ConnectResult;
import com.ifttt.connect.ui.CredentialsProvider;

import android.content.Intent;

import android.util.Log;

import android.net.Uri;

public class MainActivity extends AppCompatActivity {

    private ConnectButton connectButton;
    private CredentialsProvider provider;
    private ConnectResult connectResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        connectButton = findViewById(R.id.connect_button);
        provider = new CredentialsProvider() {

            @Override
            public String getOAuthCode() {
                return "user_oauth_code";
            }

            @Override
            public String getUserToken() {
                return "joe@hello.com";
            }
        };

        ConnectButton.Configuration configuration = ConnectButton.Configuration.Builder.withConnectionId(
                "y8R53tZG",
                "joe@hello.com",
                provider,
                Uri.parse("connecttest://callback")
        ).build();

        connectButton.setup(configuration);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setContentView(R.layout.activity_main);

        connectResult = ConnectResult.fromIntent(intent);

        connectButton.setConnectResult(connectResult);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
