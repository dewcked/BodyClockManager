package com.example.capston_project;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.samsung.android.sdk.shealth.Shealth;
import com.samsung.android.sdk.shealth.tracker.TrackerInfo;
import com.samsung.android.sdk.shealth.tracker.TrackerManager;
import com.samsung.android.sdk.shealth.tracker.TrackerTileManager;



public class SurveyActivity extends Activity{

    private static final String LOG_TAG = "SampleService";
    private static final String STORE_URL = "market://details?id=com.sec.android.app.shealth";
    private TrackerManager mTrackerManager = null;

    private TrackerTileManager mTrackerTileManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_main);

        Shealth shealth = new Shealth();
        try {
            shealth.initialize(this);
            if (shealth.isFeatureEnabled(Shealth.FEATURE_TRACKER_TILE, Shealth.FEATURE_TRACKER_LAUNCH_EXTENDED)) {
                mTrackerManager = new TrackerManager(this);
                mTrackerTileManager = new TrackerTileManager(this);
            } else {
                Log.d(LOG_TAG, "SHealth should be upgraded");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(STORE_URL));
                this.startActivity(intent);
                finish();
                return;
            }
        } catch (IllegalStateException e) {
            Log.e(LOG_TAG, e.toString());
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            finish();
            return;
        } catch (IllegalArgumentException e) {
            Log.e(LOG_TAG, e.toString());
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        addWeightMenu();
        addFoodMenu();
    }
    private void addExerciseMenu() {
        TrackerInfo trackerInfo = mTrackerManager.getTrackerInfo(TrackerManager.TrackerId.FOOD);

        if(trackerInfo == null) {
            return;
        }

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mTrackerManager.startActivity((Activity)v.getContext(), TrackerManager.TrackerId.FOOD,
                            TrackerManager.Destination.TRACK);
                } catch (IllegalArgumentException e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                } catch (IllegalStateException e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private void addFoodMenu() {
        TrackerInfo trackerInfo = mTrackerManager.getTrackerInfo(TrackerManager.TrackerId.FOOD);

        if(trackerInfo == null) {
            return;
        }

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mTrackerManager.startActivity((Activity)v.getContext(), TrackerManager.TrackerId.FOOD,
                            TrackerManager.Destination.TRACK);
                } catch (IllegalArgumentException e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                } catch (IllegalStateException e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void addWeightMenu() {
        TrackerInfo trackerInfo = mTrackerManager.getTrackerInfo(TrackerManager.TrackerId.WEIGHT);

        if(trackerInfo == null) {
            return;
        }

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mTrackerManager.startActivity((Activity)v.getContext(), TrackerManager.TrackerId.WEIGHT,
                            TrackerManager.Destination.TRACK);
                } catch (IllegalArgumentException e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                } catch (IllegalStateException e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
