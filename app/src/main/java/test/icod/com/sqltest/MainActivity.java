package test.icod.com.sqltest;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.reactive.DataSubscriptionList;



public class MainActivity extends AppCompatActivity {
    private EditText eTnum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eTnum=findViewById(R.id.et_num);
    }
    private long id=10000;
    public void add(View view) {
        new Thread(){
            @Override
            public void run() {
                long l = System.currentTimeMillis();

                int i1 = Integer.parseInt(eTnum.getText().toString());
                for (int i = 0; i < i1; i++) {
                   Note note=new Note();

                    note.comment="1111"+i;
                   ObjectBox.get().boxFor(Note.class).put(note);


                }
            //    long count = ObjectBox.get().boxFor(Note.class).count();
                long end =  System.currentTimeMillis();
                Log.e("time:","num:"+":count time: "+(end-l));
            }
        }.start();



    }

    public void list(View view) {

        new Thread(){
            @Override
            public void run() {
                long l = SystemClock.currentThreadTimeMillis();
                Box<Note> noteBox = ObjectBox.get().boxFor(Note.class);
               // noteBox.removeAll();
                //List<Note> all = noteBox.getMap(all);

               // Realm realm = App.getRealm();
                int i1 = Integer.parseInt(eTnum.getText().toString());

             /*   NoteRealm id =
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {

                            }
                        });*/


                for (int i = 1; i < i1; i++) {
                    try {
                        ObjectBox.get().boxFor(Note.class).get(i );
                    }catch (Exception e){
                        Log.e("time:",e.getMessage());
                    }
                }
                long end = SystemClock.currentThreadTimeMillis();
                Log.e("time:","num:"+i1+"查询"+(end-l));
            }
        }.start();

    }

    public void delete(View view) {
        new Thread(){
            @Override
            public void run() {
                long l = SystemClock.currentThreadTimeMillis();
                Box<Note> noteBox = ObjectBox.get().boxFor(Note.class);
                // noteBox.removeAll();
                //List<Note> all = noteBox.getMap(all);
                int i1 = Integer.parseInt(eTnum.getText().toString());
                for (int i = 1; i < i1; i++) {
                    try {

                     noteBox.remove(i);
                 /*     *//*  NoteRealm id =
                                App.getRealm().where(NoteRealm.class).equalTo("id", i).d;*//*
                        final RealmResults<NoteRealm> results =   App.getRealm().where(NoteRealm.class).findAll();

                        // All changes to data must happen in a transaction
                        final int finalI = i;
                        App.getRealm().executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                // remove single match


                                // remove a single object
                                NoteRealm dog = results.get(finalI);
                                dog.deleteFromRealm();

                                // Delete all matches

                            }
                        });*/
                    }catch (Exception e){
                        Log.e("time:",e.getMessage());
                    }
                }
                long end = SystemClock.currentThreadTimeMillis();
                Log.e("time:","num:"+i1+"删除"+(end-l));
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ObjectBox.get().close();
    }

    public void count(View view) {

        new Thread(){
            @Override
            public void run() {
                long l = System.currentTimeMillis();


                long count = ObjectBox.get().boxFor(Note.class).count();
                long end =  System.currentTimeMillis();
                Log.e("time:","num: "+count +":count time: "+(end-l));
            }
        }.start();
    }
}
