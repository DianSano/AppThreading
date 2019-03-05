package id.co.blogspot.diansano.appthreading;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtView1;
    DoCountingTask task;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtView1 = findViewById(R.id.textView1);
    }
    public void startCounter(View view) {
        task = (DoCountingTask) new DoCountingTask().execute();
    }
    public void stopCounter(View view) {
        task.cancel(true);
    }
    private class DoCountingTask extends AsyncTask<Void, Integer, Void> {
        protected Void doInBackground(Void... params) {
            for (int i = 0; i < 1000; i++) {
                //---report its progress---
                publishProgress(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Log.d("Threading", e.getLocalizedMessage());
                }
                if (isCancelled()) break;
            }
            return null;
        }
        protected void onProgressUpdate(Integer... progress) {
            txtView1.setText(progress[0].toString());
            Log.d("Threading", progress[0].toString());
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        stopCounter(txtView1);
    }
    /*public void startCounter(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<=1000; i++) {
                    final int valueOfi = i;
                    //---update UI---
                    txtView1.post(new Runnable() {
                        public void run() {
                            //---UI thread for updating---
                            txtView1.setText(String.valueOf(valueOfi));
                            Log.d("Threading", String.valueOf(valueOfi));
                        }
                    });
                    //---insert a delay
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Log.d("Threading", e.getLocalizedMessage());
                    }
                }
            }
        }).start();
    }*/
    /*public void startCounter(View view) {
        new Thread(new Runnable() {
            public void run() {
                for (int i=0; i<=1000; i++) {
                    txtView1.setText(String.valueOf(i));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Log.d("Threading", e.getLocalizedMessage());
                    }
                }
            }
        }).start();
    }*/
    /*public void startCounter(View view) {
        for (int i=0; i<=1000; i++) {
            txtView1.setText(String.valueOf(i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Log.d("Threading", e.getLocalizedMessage());
            }
        }
    }*/
}
