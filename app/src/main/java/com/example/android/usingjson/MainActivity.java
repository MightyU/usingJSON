package com.example.android.usingjson;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    String json_string;
    private ConnectivityManager networkCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        networkCheck = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public void getJson (View view){

        final String TAG = "Refresh";
        String imagePath = "https://www.govtrack.us/api/v2/role?current=true";

        if (networkCheck != null) {
            NetworkInfo info = networkCheck.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                new DownloadTask().execute(imagePath);
            }
            else {
                Toast.makeText(this, "Network Not Available", Toast.LENGTH_SHORT).show();
            }

        }

    }

    private class DownloadTask extends AsyncTask <String,Void,String> {

        @Override
        protected String doInBackground(String... urls) {
            return showList(urls[0]);
        }

        @Override
        protected void onProgressUpdate (Void... values){
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute (String result){

            json_string = result;
            TextView textJsonData = (TextView) findViewById(R.id.text_json);
            textJsonData.setText(result);

        }
    }

    public String showList (String path){

        final String TAG = "Download Task";
        InputStream inStream;
        String JSON_STRING = "";
        StringBuffer stringBuilder = new StringBuffer ();

        try {
            URL url = new URL(path);
            HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
            urlConn.setConnectTimeout(5000);
            urlConn.setReadTimeout(2500);
            urlConn.setRequestMethod("GET");
            urlConn.setDoInput(true);
            urlConn.connect();
            inStream = urlConn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));

            while ((JSON_STRING = reader.readLine()) != null)
            {
                stringBuilder.append(JSON_STRING+"\n");
            }



            reader.close();
            inStream.close();
            urlConn.disconnect();

            return stringBuilder.toString().trim();



        }catch (MalformedURLException e){

            Log.e(TAG, "URL error : " + e.getMessage());
        }catch (IOException e){

            Log.e(TAG, "Download failed : " + e.getMessage());
        }

        return null;
    }

    public void display (View view){

        if(json_string==null){

            Toast.makeText(getApplicationContext(),"Click on Get JASON Button",Toast.LENGTH_LONG).show();
        }

        else{
            Intent display = new Intent(this, DisplayListView.class);
            display.putExtra("jason_data",json_string);
            startActivity(display);
        }

    }
}
