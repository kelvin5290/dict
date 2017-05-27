package test.dict;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Dbhelper dh = null;
    private Button enter;
    private EditText esearch;
    private String eng;
    private String chin;
    private String values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        esearch = (EditText) findViewById(R.id.editText);

        openDB();
        enter = (Button) findViewById(R.id.button);
        enter.setOnClickListener(click);
    }

    public void openDB() {
        dh = new Dbhelper(this);
        try{
            dh.createDatabase();
        }
    catch(IOException ioe) {
        new Error("error");
    }
        try{
dh.copyDataBase();}
        catch (IOException e){
            new Error("e");
        }
        try{
            dh.opendb();
        }catch (SQLiteException e){
            throw e;
        }
        Toast.makeText(MainActivity.this,String.valueOf(dh.checkdb())+dh.getDatabaseName(),Toast.LENGTH_SHORT).show();

    }

    public void closeDB() {
        dh.close();
    }

    private void search(String eng) {
        SQLiteDatabase db = dh.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM dict_a WHERE word= \'apple\'",null);
//        Cursor c = db.rawQuery("SELECT name FROM my_db.sqlite_master WHERE type='table", null);
//        eng = c.getString(0);
//        chin = c.getString(1);
//        values = c.getString(2);

        db.close();
    }
    View.OnClickListener click= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.button)
                search(esearch.getText().toString());
            TextView result = (TextView) findViewById(R.id.textView2);
            result.setText(chin);
        }
    };
}
//    private OnClickListener click = new VOnClickListener() {
//        @Override
//        public void onClick(View v) {
//            if (v.getId() == R.id.button)
//                search(esearch.getText().toString());
//            TextView result = (TextView) findViewById(R.id.textView2);
//            result.setText(chin);
//        }
//    };



