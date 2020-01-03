package test.icod.com.sqltest;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void add(View view) {
        new Thread(){
            @Override
            public void run() {
                long l = SystemClock.currentThreadTimeMillis();
                for (int i = 0; i < 100000; i++) {
                    Note note=new Note();
                    note.comment="1111"+i;
                   ObjectBox.get().boxFor(Note.class).put(note);
                }
                long end = SystemClock.currentThreadTimeMillis();
                Log.e("time:",""+(end-l));
            }
        }.start();



    }

    public void list(View view) {

        new Thread(){
            @Override
            public void run() {
                long l = SystemClock.currentThreadTimeMillis();

                List<Note> all = ObjectBox.get().boxFor(Note.class).getAll();

                long end = SystemClock.currentThreadTimeMillis();
                Log.e("time:",""+(end-l));
            }
        }.start();

    }
}
