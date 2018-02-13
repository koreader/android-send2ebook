package org.koreader.send2ebook.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import koreader.org.send2ebook.android.R;

public class LicenseActivity extends AppCompatActivity {

    private EditText license;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license);

        license = (EditText) findViewById(R.id.license);
        String licences = "Licenses:\n\n\n- Apache Commons IO - Apache License, Version 2.0" +
                "\n\n- Apache Commons Net - Apache License, Version 2.0" +
                "\n\n- Apache Log4j2 - Apache License, Version 2.0" +
                "\n\n- Jsoup - MIT license" +
                "\n\n- Epublib - GNU Lesser General Public License. " ;

        license.setText(licences);
    }
}
