package com.projects.oleg.seniorproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.projects.oleg.seniorproject.Rendering.MGlSurfaceView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MGlSurfaceView(this));
    }
}
