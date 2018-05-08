package sg.edu.rp.c346.demosql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetTasks;
    TextView tvResults;
    ListView lv;

    ArrayList<Task> objects;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper db = new DBHelper(this);
        db.getWritableDatabase();
        db.close();

        btnInsert = (Button) findViewById(R.id.buttonInsert);
        btnGetTasks = (Button) findViewById(R.id.buttonGet);
        tvResults = (TextView)  findViewById(R.id.textViewResult);
        lv = (ListView) findViewById(R.id.lvData);


        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                db.insertTask("Submit RJ", "25 Apr 2016");
                db.close();
            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = db.getTaskContent();
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);

                // objects can be retrieved from database
                DBHelper db2 = new DBHelper(MainActivity.this);

                objects = db2.getTasks();

                // Create the ArrayAdapter object.
                adapter = new CustomAdapter(MainActivity.this, R.layout.row, objects);
                lv.setAdapter(adapter);
                db2.close();
            }
        });



    }
}
