package org.koreader.send2ebook.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.koreader.send2ebook.android.share.IntentAndContext;
import org.koreader.send2ebook.android.share.ShareVia;

import koreader.org.send2ebook.android.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ShareVia task = new ShareVia(this);
        task.execute(new IntentAndContext(getIntent(), getBaseContext()));


    }


    public void settinsAction(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void licencesAction(View view) {
        Intent intent = new Intent(this, LicenseActivity.class);
        startActivity(intent);
    }

}
