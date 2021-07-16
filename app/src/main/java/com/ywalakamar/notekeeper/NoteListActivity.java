package com.ywalakamar.notekeeper;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_note_list);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initializeDisplayContent();
    }

    private void initializeDisplayContent(){
        /*use final to make variable accessible from an anonymous class*/
        final ListView listNotes=findViewById(R.id.list_notes);
        List<NoteInfo> notes=DataManager.getInstance().getNotes();

        /*create adapter*/
        ArrayAdapter<NoteInfo> notesAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);

        listNotes.setAdapter(notesAdapter);

        /*Create an onclick event that uses an anonymous function*/
        listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent(NoteListActivity.this, NoteActivity.class);

                /*Get note info that corresponds to a selection*/
                NoteInfo note= (NoteInfo) listNotes.getItemAtPosition(position);
                intent.putExtra(NoteActivity.NOTE_INFO, note);

                /*Launch activity*/
                startActivity(intent);
            }
        });
    }
}