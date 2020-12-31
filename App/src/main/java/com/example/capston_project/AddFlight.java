package com.example.capston_project;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


public class AddTrip extends Activity {
    String day, flight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtrip);
        Intent intent = getIntent();
        int yy = intent.getExtras().getInt("yy");
        int mm = intent.getExtras().getInt("mm");
        int dd = intent.getExtras().getInt("dd");
        MakeDay(yy, mm, dd);
        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
        jsoupAsyncTask.execute();
    }

    private void MakeDay(int yy, int mm, int dd) {
        day = Integer.toString(yy);
        if (mm < 10)
            day = day + "0" + Integer.toString(mm);
        else
            day = day + Integer.toString(mm);
        if (dd < 10)
            day = day + "0" + Integer.toString(dd);
        else
            day = day + Integer.toString(dd);
    }

    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            String url = "http://www.flightview.com/TravelTools/FlightTrackerQueryResults.asp";
            flight = "OZ211";
            try {
                Document doc = Jsoup.connect(url)
                        .header("Accept-Encoding", "gzip, deflate")
                        .header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
                        .header("Cache-Control", "max-age=0")
                        .header("Connection", "keep-alive")
                        .header("Content-Length", "123")
                        .header("Cookie", "__utmz=35767543.1590558578.1.1.utmccn=(referral)|utmcsr=google.com|utmcct=/|utmcmd=referral; __utmc=35767543; _hjid=8c5d7050-0ff9-4d1e-b552-de026304c519; _hjIncludedInSample=1; ApplicationGatewayAffinity=721656b12fed4f0e097cefb465f7dabd; ASPSESSIONIDQQRDBBRR=IBGHHKIDGJAPEPDLICGIPCDM; fcspersistslider1=1; __utma=35767543.1728073493.1590558578.1590634117.1590652387.7; __utmb=35767543; _hjAbsoluteSessionInProgress=1")
                        .header("Content-Type", "application/x-www-form-urlencoded")
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36")
                        .header("Host", "www.flightview.com")
                        .ignoreContentType(true)
                        .data("qtype", "sfi")
                        .data("sfw", "/FV/Home/Main")
                        .data("whenArrDep", "dep")
                        .data("namal", "Enter name or code")
                        .data("al", "")
                        .data("fn", flight)
                        .data("whenDate", day)
                        .data("input", "Track Flight")
                        .post();
                System.out.println(doc.select("div.search-results-table"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
