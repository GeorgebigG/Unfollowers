package com.unfollow.instagram.unfollowers.Activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.unfollow.instagram.unfollowers.Instagram.Data;
import com.unfollow.instagram.unfollowers.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Instagram extends AppCompatActivity {

    WebView webView;
    String token;

    ProgressDialog wait;
    JSONObject data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram);

        webView = (WebView) findViewById(R.id.instagram_web_view);
        webView.setWebViewClient(new OAuthWebViewClient());
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(Data.AUTH_URl_STRING);

        wait = new ProgressDialog(this);
        wait.setTitle("Please Wait!");
        wait.setMessage("Loading...");
        wait.setCancelable(false);
        wait.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    private class OAuthWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            System.out.println("Redirecting URL " + url);
            if (url.startsWith(Data.CALLBACKURL)) {
                System.out.println(url);
                String urls[] = url.split("=");
                token = urls[1];
                wait.show();
                new AccesToken().execute(token);
                return true;
            }
            return false;
        }
    }

    public class AccesToken extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... params) {

            HttpsURLConnection connection = null;
            BufferedReader reader = null;
            JSONObject data = null;

            try {
                URL url = new URL(Data.TOKEN_URL_STRING);
                connection = (HttpsURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoInput(true);
                connection.setDoOutput(true);

                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write("client_id="+ Data.CLIENT_ID + "&client_secret="+ Data.CLIENT_SECRET +
                        "&grant_type=authorization_code" + "&redirect_uri=" + Data.CALLBACKURL + "&code=" + params[0]);

                String response = "";

                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line = "";
                while ((line = reader.readLine()) != null)
                    response += line;

                data = new JSONObject(response);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null)
                    connection.disconnect();

                if (reader != null)
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }

            return data;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            wait.dismiss();
            data = jsonObject;
            System.out.println(data.toString());
        }
    }
}