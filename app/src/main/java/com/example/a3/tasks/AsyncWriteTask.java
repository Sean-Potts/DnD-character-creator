/*
File: AsyncWriteTask.java
Project: MAD-A2
Programmer: Isaiah Bartlett
Date: 3/17/2023
Description: async task for writing a file
 */


package com.example.a3.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;
/*
 *  Class : AsyncWriteTask
 *  Description : Writes to file asynchronously
 */
public class AsyncWriteTask extends AsyncTask<String, Void, Boolean> {
    public Context context;

    @Override
    protected Boolean doInBackground(String... strings) {
        Boolean canWriteFile = true;

        try { // look in /data/data/com.example.a2/files
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(strings[0] + ".txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(strings[1]);
            outputStreamWriter.close();
        } catch (IOException e) {
            canWriteFile = false;
        }

        return canWriteFile;
    }

    @Override
    protected void onPostExecute(Boolean isSuccessful) {
        if (isSuccessful) {
            Toast.makeText(context, "Character file exported", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Unable to export character", Toast.LENGTH_SHORT).show();
        }
    }
}
