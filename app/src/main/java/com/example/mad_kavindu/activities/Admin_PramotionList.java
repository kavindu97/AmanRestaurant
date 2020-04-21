package com.example.mad_kavindu.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_kavindu.R;
import com.example.mad_kavindu.adapters.AdminListAdapter;
import com.example.mad_kavindu.database.MyDatabaseHelper;
import com.example.mad_kavindu.listeners.AdminListClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Admin_PramotionList extends AppCompatActivity implements AdminListClickListener, View.OnClickListener {
    private static final int EDIT_REQUEST = 54;
    private static final int ADD_ITEM_REQUEST = 877;
    private MyDatabaseHelper myDatabaseHelper;
    private RecyclerView recyclerView;
    private AdminListAdapter adminListAdapter;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__pramotion_list);
        myDatabaseHelper = new MyDatabaseHelper(this);
        recyclerView = findViewById(R.id.adminlist_recyclerview);
        floatingActionButton = findViewById(R.id.floatingButton);
        floatingActionButton.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Cursor allRecordsCursor = myDatabaseHelper.getAllRecords();
        adminListAdapter = new AdminListAdapter(allRecordsCursor, this, this);
        recyclerView.setAdapter(adminListAdapter);

    }

    @Override
    public void onAdminListEditClicked(String rowId) {
        Intent i = new Intent(this, Admin_EditPramotionItem.class);
        i.putExtra(MyDatabaseHelper.ROW_ID, rowId);
        startActivityForResult(i, EDIT_REQUEST);
    }

    @Override
    public void onAdminListDeleteClicked(final String rowId) {
        AlertDialog.Builder confirm = new AlertDialog.Builder(this);
        confirm.setTitle("Confirm delete ?");
        confirm.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean deleted = myDatabaseHelper.deleteRecord(rowId);
                if (deleted) {
                    Toast.makeText(Admin_PramotionList.this, "Record deleted", Toast.LENGTH_SHORT).show();
                    startActivity(getIntent());
                } else {
                    Toast.makeText(Admin_PramotionList.this, "Record could not be deleted", Toast.LENGTH_SHORT).show();
                }
            }

        });
        confirm.create().show();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, Admin_AddPramotionItem.class);
        startActivityForResult(intent, ADD_ITEM_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            startActivity(getIntent());
//            adminListAdapter.notifyDataSetChanged();
        }
    }
}
