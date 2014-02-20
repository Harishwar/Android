package com.harishwar.servicesapp;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import android.content.Context;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;

public class DownloadUtil{
    private final static int BUFFERSIZE = 1024;
    
    public static String downloadFile(Context context, String url) {
        try {
            Log.d("Processing", "Retrieving content from URL");
            InputStream in_stream = (InputStream) new URL(url).getContent();
            int nRead;
            byte[] data = new byte[BUFFERSIZE];
            String fileExtenstion = MimeTypeMap.getFileExtensionFromUrl(url);
            String file_name = URLUtil.guessFileName(url, null, fileExtenstion);
            Log.d("Downloading", "Downloading File");
            FileOutputStream out_stream = context.openFileOutput(file_name , Context.MODE_WORLD_READABLE);
            while ((nRead = in_stream.read(data, 0, data.length)) != -1) {
                out_stream.write(data, 0, nRead);
            }
            out_stream.close();
            Log.d("","File " + file_name + " Download Complete");
            return file_name;
        } catch (Exception e) {
            Log.d("Complete", "Download Failed", e);
            return null;
        }
    }
}
