package com.serveroverload.recorder.util;

import android.content.Context;
import android.os.Environment;
import android.os.Vibrator;

import java.io.File;
import java.util.ArrayList;

public class Helper {

    public static final String RECORDING_PATH = Environment
            .getExternalStorageDirectory() + "/Recordings";
    public static final String LOAD_RECORDINGS = "Load Records";
    private static Helper helperInstance;

    private Helper() {

    }

    public static Helper getHelperInstance() {

        if (null == helperInstance) {
            helperInstance = new Helper();
        }
        return helperInstance;

    }

    public void makeHepticFeedback(Context context) {

        ((Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE))
                .vibrate(100);
    }

    public ArrayList<String> getAllFileInDirectory(File directory) {

        final File[] files = directory.listFiles();
        ArrayList<String> listOfRecordings = new ArrayList<String>();

        if (files != null) {
            for (File file : files) {
                if (file != null) {
                    if (file.isDirectory()) { // it is a folder...
                        getAllFileInDirectory(file);
                    } else { // it is a file..
                        listOfRecordings.add(file.getAbsolutePath());
                    }
                }
            }
        }
        return listOfRecordings;
    }

    public ArrayList<String> getAllRecordings() {
        return getAllFileInDirectory(new File(RECORDING_PATH));

    }

    public boolean createRecordingFolder() {

        if (!new File(RECORDING_PATH).exists()) {

            return new File(RECORDING_PATH).mkdir();
        } else {
            return true;
        }

    }

}
