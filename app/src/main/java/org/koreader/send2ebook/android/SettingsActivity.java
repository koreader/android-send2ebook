package org.koreader.send2ebook.android;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import koreader.org.send2ebook.android.R;


public class SettingsActivity extends AppCompatActivity {

    public static final String STORAGE_NAME = "Send2EbookStorage";

    public final static String HOST = "host", USER = "user", FOLDER = "folder",
            PASSWORD = "password", PORT = "port";

    private AutoCompleteTextView hostView;
    private EditText mPasswordView;
    private EditText folderView;
    private AutoCompleteTextView userView;

    private EditText portView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        hostView = (AutoCompleteTextView) findViewById(R.id.host);

        userView = (AutoCompleteTextView) findViewById(R.id.user);
        folderView = (EditText) findViewById(R.id.folder);
        portView = (EditText) findViewById(R.id.port);

        mPasswordView = (EditText) findViewById(R.id.password);

        SharedPreferences settings = getSharedPreferences(STORAGE_NAME, 0);

        String host = settings.getString(HOST, "ftp.exmaple_server.com");
        String user = settings.getString(USER, "koreader_user");
        String passwd = settings.getString(PASSWORD, "secret");
        String folder = settings.getString(FOLDER, "/ebooks/");
        int port = settings.getInt(PORT, 21);

        folderView.setText(folder);
        userView.setText(user);
        mPasswordView.setText(passwd);
        hostView.setText(host);
        portView.setText(String.valueOf(port));
    }


    public void saveAction(View view) {

        SharedPreferences settings = getSharedPreferences(STORAGE_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        String folder = folderView.getText().toString();
        String user = userView.getText().toString();
        String password = mPasswordView.getText().toString();
        String host = hostView.getText().toString();
        Integer port = Integer.valueOf(portView.getText().toString());


        editor.putString(HOST, host);
        editor.putString(USER, user);
        editor.putString(PASSWORD, password);
        editor.putString(FOLDER, folder);
        editor.putInt(PORT, port);

        editor.commit();
    }

}


