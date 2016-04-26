package leban.nait.ca.pedometer;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Pedometer extends Activity implements SensorEventListener {
    TextView stepsTaken, stepDetect;
    Sensor stepCounter;
    Sensor stepDetector;
    SensorManager sManager;
    boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_pedometer);
        stepsTaken = (TextView) findViewById(R.id.stepstaken);
        stepDetect = (TextView) findViewById(R.id.stepdetect);
        sManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            }


    @Override
    protected void onResume() {
        super.onResume();
        Sensor countSensor = sManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        running = true;
        if(countSensor != null)
        {
            sManager.registerListener(this, stepCounter, SensorManager.SENSOR_DELAY_UI);
        }
        else
        {
            Toast.makeText(this, "Counter not available!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        if(running)
        {
            stepsTaken.setText(String.valueOf(event.values[0]));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}


