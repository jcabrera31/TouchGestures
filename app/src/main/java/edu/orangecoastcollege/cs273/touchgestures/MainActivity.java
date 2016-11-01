package edu.orangecoastcollege.cs273.touchgestures;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import static android.R.attr.x;

public class MainActivity extends AppCompatActivity
        implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private ScrollView gesturesScrollView;

    private TextView gesturesLogTextView;
    private TextView singleTapTextView;
    private TextView doubleTapTextView;
    private TextView longPressTextView;
    private TextView scrollTextView;
    private TextView flingTextView;

    private int singleTaps = 0, doubleTaps = 0, longPresses = 0, scrolls = 0, flings = 0;

    // Define a GestureDetector to listen to events on the ScrollView
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gesturesScrollView = (ScrollView) findViewById(R.id.gesturesScrollView);
        gesturesLogTextView = (TextView) findViewById(R.id.gesturesLogTextView);

        // Hooking up the gesture detector to our scroll view
        //scrollview is the context, the listener is the main activity
        // 4 out of 5 gestures handled
        gestureDetector = new GestureDetector(gesturesScrollView.getContext(), this);

        // Special case: double tap
        gestureDetector.setOnDoubleTapListener(this);

        singleTapTextView = (TextView) findViewById(R.id.tvSingleTap);
        doubleTapTextView = (TextView) findViewById(R.id.tvDoubleTap);
        longPressTextView = (TextView) findViewById(R.id.tvLongPress);
        scrollTextView = (TextView) findViewById(R.id.tvScrolls);
        flingTextView = (TextView) findViewById(R.id.tvFlings);

    }


    // Any touch event is now dispatched from Activity to the ScrollView
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);

        return gestureDetector.onTouchEvent(ev);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        ++singleTaps;
        gesturesLogTextView.append("\nonSingleTapConfirmed touch event");
        updateValues();
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        ++doubleTaps;
        gesturesLogTextView.append("\nonDoubleTapConfirmed touch event");
        updateValues();
        return true;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonDown touch event");
        updateValues();
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        ++scrolls;
        gesturesLogTextView.append("\nonScroll: distanceX is " +v + ", distanceY is " +v1);
        updateValues();
        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        ++longPresses;
        gesturesLogTextView.append("\nonLongPress touch event");
        updateValues();
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        ++flings;
        gesturesLogTextView.append("\nonFling: veleocityX is " +x +" velocityY is " +v1);
        updateValues();
        return true;
    }

    public void clearAll(View view){

        singleTaps = 0;
        doubleTaps = 0;
        longPresses = 0;
        scrolls = 0;
        flings = 0;
        gesturesLogTextView.setText("");

        singleTapTextView.setText(String.valueOf(0));
        doubleTapTextView.setText(String.valueOf(0));
        longPressTextView.setText(String.valueOf(0));
        scrollTextView.setText(String.valueOf(0));
        flingTextView.setText(String.valueOf(0));

    }

    private void updateValues(){
        singleTapTextView.setText(String.valueOf(singleTaps));
        doubleTapTextView.setText(String.valueOf(doubleTaps));
        longPressTextView.setText(String.valueOf(longPresses));
        scrollTextView.setText(String.valueOf(scrolls));
        flingTextView.setText(String.valueOf(flings));
    }
}