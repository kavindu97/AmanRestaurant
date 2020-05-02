package com.example.mad_kavindu.activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.mad_kavindu.R;
import com.example.mad_kavindu.database.MyDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class CardActivity extends AppCompatActivity {

    SQLiteDatabase mDatabase;
    List<Cards> cardList;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        mDatabase=openOrCreateDatabase(MyDatabaseHelper.DATABASE_NAME, MODE_PRIVATE, null);

        cardList = new ArrayList<>();

        listView = (ListView)findViewById(R.id.listViewCards);

        findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
        allPaymentCardDatabase();


    }

    private void allPaymentCardDatabase()
    {
        String sql ="SELECT * FROM payment";

        Cursor cv =  mDatabase.rawQuery(sql,null);

        if(cv.moveToFirst()){
            do{
                cardList.add(new Cards(
                        cv.getInt(0),
                        cv.getString(1),
                        cv.getString(2),
                        cv.getString(3),
                        cv.getInt(4),
                        cv.getString(5),
                        cv.getInt(6)

                ) );

            }while (cv.moveToNext());
            findViewById(R.id.progressBar).setVisibility(View.GONE);
            CardAdapter adapter = new CardAdapter(this, R.layout.list_layout_cards,cardList,mDatabase);
            listView.setAdapter(adapter);
        }
    }

}
