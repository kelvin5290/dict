package test.dict;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by user on 26/5/2017.
 */

public class Dbhelper extends SQLiteOpenHelper {
    public static final String dbname="ecdict4";
    public static String DB_path="/data/data/test.dict/databases/";
    public static final int dbver=1;
    private static SQLiteDatabase db;
    private final Context mycontext;
    public Dbhelper(Context context) {

        super(context, dbname, null, dbver);
        this.mycontext = context;
            }
    public void createDatabase() throws IOException{
        boolean dbexist= checkdb();
        if(!dbexist) {
        }else
        {
            this.getReadableDatabase();
            try{
                copyDataBase();
            }catch (IOException e){
                throw new Error("error1");
            }
        }
    }
    public void copyDataBase() throws IOException{
        InputStream myinput=mycontext.getAssets().open("ecdict2.db");
        //String out=DB_path+dbname;
        //OutputStream output= new FileOutputStream(out);

        byte[] buffer=new byte[1024];
        int length;
//        while ((length=myinput.read(buffer))>0){
 //           output.write(buffer,0,length);
   //     }
  //      output.flush();
   //     output.close();
    myinput.close();
}
//    public Cursor showAllTables(){
//        String mySql = " SELECT name FROM sqlite_master " + " WHERE type='table'             "
//                + "   AND name LIKE 'PR_%' ";
//        return db.rawQuery(mySql, null);
//    }
public void opendb() throws SQLiteException{
    String myPath= DB_path+dbname;
    db=SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READONLY);
}
    @Override
    public void onCreate(SQLiteDatabase db) {
    }
    public boolean checkdb(){
SQLiteDatabase checkDB=null;
        try{
            String mypath=DB_path+dbname;
            checkDB= SQLiteDatabase.openDatabase(mypath,null,SQLiteDatabase.OPEN_READONLY);
        }catch(SQLiteException e){

        }
        if(checkDB!=null)
            checkDB.close();
        return checkDB!=null ?true:false;
    }
public synchronized void close(){
    if(db!=null)
        db.close();
    super.close();
}
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
