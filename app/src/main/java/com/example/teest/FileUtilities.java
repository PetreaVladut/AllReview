package com.example.teest;

import android.content.Context;
import android.content.res.AssetManager;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class FileUtilities extends AppCompatActivity {
    static final int READ_BLOCK_SIZE = 100;
    AssetManager assetManager;
    InputStream inputStream;
    OutputStream outputStream;
    BufferedReader reader;
    int charRead;

    Context context;
    public String read_file() throws IOException {
        String s="";
        char[] inputBuffer= new char[READ_BLOCK_SIZE];
        while ((charRead=reader.read(inputBuffer))>0) {
            String readstring=String.copyValueOf(inputBuffer,0,charRead);
            s +=readstring;
        }
        return s;
    }
    public FileUtilities(String f,String type, Context c)
    {
        context=c;
        try {
            if(type.equals("r")) {
                assetManager = c.getAssets();
                inputStream = assetManager.open(f);
                reader = new BufferedReader(new InputStreamReader(inputStream));
            }
            else if(type.equals("w")){
                //write file implementation
                //outputStream = assetManager.open(f);
                //BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            }
            }
         catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void append_file(String s)
    {

    }
    protected void finalize()
    {
        try {
            reader.close();
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
