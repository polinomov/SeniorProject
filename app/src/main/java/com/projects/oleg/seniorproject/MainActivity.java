package com.projects.oleg.seniorproject;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.projects.oleg.seniorproject.DebugView.DebugView;
import com.projects.oleg.seniorproject.Rendering.MGlSurfaceView;
import com.projects.oleg.seniorproject.Rendering.ObjParser.ObjLoader;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private MGlSurfaceView surfaceView;
    private View debugView;
    public static float PPI_X;
    public static float PPI_Y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.camera_view_layout);
        debugView = findViewById(R.id.debug_view);
        DebugView.initDebug(debugView);
        //output = (TextView) findViewById(R.id.text_view);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        PPI_X = metrics.xdpi;
        PPI_Y = metrics.ydpi;
    }

    public static void writeOutput(String output){

    }

}
