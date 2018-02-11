package org.koreader.send2ebook.android.util;


import android.content.Context;

import com.github.mwoz123.send2ebook.storage.ftp.FtpConnection;

import java.io.IOException;

public class FtpConnectionFromProperty {


    public static FtpConnection getConnection(Context context) throws IOException {

        String host = PropertyUtil.getProperty("host", context);
        String user = PropertyUtil.getProperty("user", context);
        String passwd = PropertyUtil.getProperty("password", context);


        FtpConnection connection = new FtpConnection(host, user, passwd);

        String folder = PropertyUtil.getProperty("folder", context);
        if (folder != null)
            connection.setFolder(folder);

        String port = PropertyUtil.getProperty("port", context);
        if (port !=null)
            connection.setPort(Integer.valueOf(port));

        return connection;
    }
}
