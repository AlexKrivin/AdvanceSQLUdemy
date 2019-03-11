package com.example.worck.advancesqludemy;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            SQLiteDatabase myDataBase = this.openOrCreateDatabase("Users",MODE_PRIVATE,null);
            myDataBase.execSQL("CREATE TABLE IF NOT EXISTS users(name VARCHAR, age INT(3))");
            myDataBase.execSQL("INSERT INTO users(name, age) VALUES('Bob', 34)");
            myDataBase.execSQL("INSERT INTO users(name, age) VALUES('Bill', 18)");
            myDataBase.execSQL("INSERT INTO users(name, age) VALUES('Karl', 33)");
            myDataBase.execSQL("INSERT INTO users(name, age) VALUES('Wiliam', 40)");
            myDataBase.execSQL("INSERT INTO users(name, age) VALUES('Bobby', 15)");
            Cursor c = myDataBase.rawQuery("SELECT * FROM users WHERE age < 20  AND name = 'Bob' ", null);
            //Select person from table users name starting K letter
            Cursor c1 = myDataBase.rawQuery("SELECT * FROM users WHERE name LIKE'%K' ", null);
            //Select person from table users where name contain letter o and pull only one result
            Cursor c2 = myDataBase.rawQuery("SELECT * FROM users WHERE name LIKE'%o%'LIMIT 1 ", null);
            //Delete from database user with name karl and delete only one person
            myDataBase.execSQL("DELETE FROM users WHERE name = 'Karl' LIMIT 1");
            //update table users field age of Bob set to 22
            myDataBase.execSQL("UPDATE users SET age = 22 WHERE name = 'Bob'");
            //Delete Bob -- Important you cant't add LIMIT field after DELETE command need worck with IDs
            myDataBase.execSQL("DELETE FROM users WHERE name = 'Bob'");

            int nameINDEX = c.getColumnIndex("name");
            int ageINDEX = c.getColumnIndex("age");
            c.moveToNext();
            while(c != null){
                Log.i ("name", c.getString(nameINDEX));
                Log.i ("age", Integer.toString(c.getInt(ageINDEX)));
                c.moveToNext();            }


            myDataBase.execSQL("CREATE TABLE IF NOT EXISTS newUsers(name VARCHAR, age INT(3), id INTEGER PRIMARY KEY)");
            myDataBase.execSQL("INSERT INTO newUsers (name, age)VALUES('Piter', 22)");
            myDataBase.execSQL("INSERT INTO newUsers (name, age)VALUES('Vasya', 17)");
            myDataBase.execSQL("INSERT INTO newUsers (name, age)VALUES('Lesha', 27)");
            // Now deleting row by reference to id
            myDataBase.execSQL("DELETE FROM newUsers WHERE id = 1");

            Cursor c3 = myDataBase.rawQuery("SELECT * FROM newUsers", null);
            int name1INDEX = c3.getColumnIndex("name");
            int age1INDEX = c3.getColumnIndex("age");
            int idINDEX = c3.getColumnIndex("id");
            c3.moveToNext();
            while(c3 != null){
                Log.i ("name", c3.getString(name1INDEX));
                Log.i ("age", Integer.toString(c.getInt(age1INDEX)));
                Log.i ("age", Integer.toString(c.getInt(idINDEX)));
                c3.moveToNext();            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
