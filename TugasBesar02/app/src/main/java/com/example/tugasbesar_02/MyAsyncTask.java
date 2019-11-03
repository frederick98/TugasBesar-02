package com.example.tugasbesar_02;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyAsyncTask extends AsyncTask<String,String,String> {
    protected MainActivity mainActivity;

    public MyAsyncTask(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected String doInBackground(String... params) {
        String stringInput = params[0];
        String stringUrl = params[1];
        String result="";

        for (int i=0 ; i<stringInput.length() ; i++){
            char temp = stringInput.charAt(i);
            result += temp;
        }

        URL url;
        StringBuilder score= new StringBuilder("Score = ");
        try {
            url = new URL(stringUrl+result);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String curLine = bufferedReader.readLine();
            while (curLine != null){
                score.append(curLine);
                curLine = bufferedReader.readLine();
            }
            //Log.d("coba", text.toString());
            connection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            score.append(e);
        }
        return score.toString();
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
        this.mainActivity.tvScore.setText(result);
    }
}
