package com.ywalakamar.notekeeper;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

public class NoteActivity extends AppCompatActivity {
    public static final String NOTE_POSITION ="com.ywalakamar.notekeeper.NOTE_POSITION";
    public static final int POSITION_NOT_SET = -1;
    private NoteInfo note;
    private boolean isNewNote;
    private Spinner spinnerCourses;
    private EditText textNoteTitle;
    private EditText textNoteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_note);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*Spinner is a drop down menu*/
        spinnerCourses = findViewById(R.id.spinner_courses);
        List<CourseInfo> courses=DataManager.getInstance().getCourses();

        /*Create adapter*/
        ArrayAdapter<CourseInfo> coursesAdapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courses);
        coursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        /*Set the adapter to the spinner*/
        spinnerCourses.setAdapter(coursesAdapter);

        readDisplayStateValues();

        textNoteTitle = findViewById(R.id.text_note_title);
        textNoteText = findViewById(R.id.text_note_text);

        /*
          if isNewNote is false, then display note
          Else display create new note
          */
        if(!isNewNote)
            displayNote(spinnerCourses, textNoteTitle, textNoteText);
    }

    private void displayNote(Spinner spinnerCourses, EditText textNoteTitle, EditText textNoteText) {
        List<CourseInfo> courses=DataManager.getInstance().getCourses();
        int courseIndex=courses.indexOf(note.getCourse());
        spinnerCourses.setSelection(courseIndex);
        textNoteTitle.setText(note.getTitle());
        textNoteText.setText(note.getText());
    }

    private void readDisplayStateValues() {
        Intent intent=getIntent();

        /*Get selected item position from the EXTRA*/
        int position=intent.getIntExtra(NOTE_POSITION, POSITION_NOT_SET);

        /*set isNewNote to false(POSITION_NOT_SET)*/
        isNewNote = position==POSITION_NOT_SET;

        /*is isNewNote is false*/
        if(!isNewNote){
            /*retrieve note from position*/
            note=DataManager.getInstance().getNotes().get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_send_email) {
            sendEmail();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void sendEmail() {
        CourseInfo course= (CourseInfo) spinnerCourses.getSelectedItem();
        String subject=textNoteTitle.getText().toString();
        String body="Check out what I learned on Pluralsight course\""+course.getTitle()+"\"\n"+textNoteText.getText();

        /*create an implicit intent*/
        Intent intent=new Intent(Intent.ACTION_SEND);
        /*set MIMETYPE*/
        intent.setType("message/rfc2822");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        startActivity(intent);
    }
}