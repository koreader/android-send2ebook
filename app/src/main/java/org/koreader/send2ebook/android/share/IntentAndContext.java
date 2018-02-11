package org.koreader.send2ebook.android.share;

import android.content.Context;
import android.content.Intent;

public class IntentAndContext {
    private Intent intent;
    private Context context;

    public IntentAndContext(Intent intent, Context context) {
        this.intent = intent; this.context = context;
    }

    public Intent getIntent() {
        return intent;
    }

    public Context getContext() {
        return context;
    }
}
