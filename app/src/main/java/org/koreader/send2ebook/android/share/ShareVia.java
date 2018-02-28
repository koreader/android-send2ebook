package org.koreader.send2ebook.android.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.mwoz123.send2ebook.creator.Creator;
import com.github.mwoz123.send2ebook.creator.EpubCreator;
import com.github.mwoz123.send2ebook.input.InputProcessor;
import com.github.mwoz123.send2ebook.input.UrlInputProcessor;
import com.github.mwoz123.send2ebook.model.Ebook;
import com.github.mwoz123.send2ebook.model.EbookData;
import com.github.mwoz123.send2ebook.model.epub.EpubEbook;
import com.github.mwoz123.send2ebook.storage.Storage;
import com.github.mwoz123.send2ebook.storage.ftp.FtpConnection;
import com.github.mwoz123.send2ebook.storage.ftp.FtpStorage;

import org.koreader.send2ebook.android.SettingsActivity;
import org.koreader.send2ebook.android.util.FtpConnectionFromProperty;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import koreader.org.send2ebook.android.R;


public class ShareVia extends AsyncTask<IntentAndContext, Void, Void> {
    private final static Logger LOGGER = Logger.getLogger(ShareVia.class.toString());

    private InputProcessor<String> inputProcessor = new UrlInputProcessor();
    private Creator<EpubEbook> creator = new EpubCreator();

    private Storage storage;
    private Activity mActivity;

    public ShareVia(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    protected Void doInBackground(IntentAndContext... intentAndContext) {

        Intent intent = intentAndContext[0].getIntent();

        if (Intent.ACTION_SEND.equals(intent.getAction())) {
            String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);

            if (sharedText != null) {

                try {
                    this.startProgress();
                    showMessage("Processing url:\n" + sharedText);

                    boolean processOnlyText = false;
                    EbookData ebookData = inputProcessor.transformInput(sharedText, processOnlyText);

                    showMessage("Creating Ebook");
                    Ebook ebook = creator.createOutputEbook(ebookData);



                    FtpConnection connection = this.getConnection(intentAndContext[0].getContext());
                    showMessage("Connecting to storage server:\n" + connection.getHost());
                    storage = FtpStorage.getInstance();
                    storage.connect(connection);

                    showMessage("Saving file to server. " );
                    storage.storeFile(ebook);

                    showMessage("Successfully finished processing:\n" + ebook.getTitle());
                    this.stopProgress();


                } catch (IOException e) {
                    LOGGER.log(Level.ALL, "IO Exception occured", e);
                    showMessage("Exception occurred :\n" + e.getMessage());
                    this.stopProgress();
                } finally {
                    if (storage != null) {
                        storage.disconnect();
                    }
                }
            }
        }
        return null;
    }




    private FtpConnection getConnection(Context context) {
        SharedPreferences settings = context.getSharedPreferences(SettingsActivity.STORAGE_NAME, 0);

        String host = settings.getString(SettingsActivity.HOST, "ftp.exmaple_server.com");
        String user = settings.getString(SettingsActivity.USER, "koreader_user");
        String passwd = settings.getString(SettingsActivity.PASSWORD, "");
        String folder = settings.getString(SettingsActivity.FOLDER, "/ebooks/");
        int port = settings.getInt(SettingsActivity.PORT, 21);

        FtpConnection connection = new FtpConnection(host, user, passwd);
        connection.setFolder(folder);
        connection.setPort(port);
        return connection;
    }

    private void showMessage(final String message) {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView centralTextView = (TextView) mActivity.findViewById(R.id.main_text);
                centralTextView.setText(message);
            }
        });
    }

    private void startProgress() {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ProgressBar progressBar = (ProgressBar) mActivity.findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
                progressBar.animate();
            }
        });
    }

    private void stopProgress() {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ProgressBar progressBar = (ProgressBar) mActivity.findViewById(R.id.progressBar);
                progressBar.setVisibility(View.GONE);
                progressBar.clearAnimation();
            }
        });
    }


}