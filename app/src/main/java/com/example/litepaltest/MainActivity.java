package com.example.litepaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //LitePal.initialize(this);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonCreate:
                SQLiteDatabase db = LitePal.getDatabase();
                break;
            case R.id.buttonADD:
                book bk=new book();
                bk.setName("xyj");
                bk.setAuthor("z3");
                bk.setPress("rmjy");
                bk.setPages(100);
                bk.setPrice(10.5);
                bk.save();
                break;
            case R.id.buttonModify:
                book bku=new book();
                bku.setPrice(20.5);
                bku.updateAll("name=? and author=?","xyj","z3");
                break;
            case R.id.buttonDel:
                LitePal.deleteAll(book.class,"price<?","20.0");
                break;
            case R.id.buttonQuery:
                //List<book> bks = LitePal.findAll(book.class);
                List<book> bks=LitePal.select("name","author","price").where("price<?","100").find(book.class);

                for(book bki:bks){
                    Log.d("Main",bki.getName());
                    Log.d("Main",bki.getAuthor());
                    Log.d("Main","price"+bki.getPrice());
                }
                break;
        }
    }
}
