package com.training.interextstorage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {


    EditText editText1,editText2;
    String s1,s2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1= findViewById(R.id.editTextTextPersonName);
        editText2= findViewById(R.id.editTextTextPersonName2);

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},1);


    }

    public void inter(View view) {
        s1=editText1.getText().toString();
        s2=editText2.getText().toString();
        FileOutputStream  fileOutputStream;
        try {

            fileOutputStream=openFileOutput(s1, Context.MODE_PRIVATE);
            fileOutputStream.write(s2.getBytes());
            fileOutputStream.close();

            Toast.makeText(this, "Success!!", Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readint(View view) {
        s1=editText1.getText().toString();
        StringBuffer  stringBuffer = new StringBuffer();

        try {
            BufferedReader bf =  new BufferedReader(new InputStreamReader(openFileInput(s1)));

            String tempst = "";

            while ((tempst=bf.readLine())!=null){
                stringBuffer.append(tempst+"\n");
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, ""+stringBuffer.toString(), Toast.LENGTH_SHORT).show();
    }


    public void exte(View view) {

        String filename = editText1.getText().toString();
        String data = editText2.getText().toString();

        try {
            File myFile = new File("/sdcard/" + filename+".txt");

            myFile.createNewFile();

            FileOutputStream fOut = new FileOutputStream(myFile);

            OutputStreamWriter myOutWriter = new

                    OutputStreamWriter(fOut);
            myOutWriter.append(data);
            myOutWriter.close();
            myOutWriter.close();
            fOut.close();

            Toast.makeText(getApplicationContext(), filename + " saved to External", Toast.LENGTH_LONG).show();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void readext(View view) {s1 = editText1.getText().toString();

        String aDatarow=""; String aBuffer="";

        try {
            File myFile = new File("/sdcard/"+s1);

            FileInputStream fin = new FileInputStream(myFile);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fin));

            while ((aDatarow= bufferedReader.readLine())!=null){
                aBuffer+=aDatarow+"\n";
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, ""+aBuffer, Toast.LENGTH_SHORT).show(); }
}