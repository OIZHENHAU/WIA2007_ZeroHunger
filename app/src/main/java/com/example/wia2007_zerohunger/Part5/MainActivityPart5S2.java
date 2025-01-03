package com.example.wia2007_zerohunger.Part5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wia2007_zerohunger.Part5.FinancialDatabase.Note;
import com.example.wia2007_zerohunger.Part5.FinancialDatabase.NoteAdapter;
import com.example.wia2007_zerohunger.Part5.FinancialDatabase.NoteViewModel;
import com.example.wia2007_zerohunger.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivityPart5S2 extends AppCompatActivity {

    Button backButtonMainP5S2;
    RecyclerView locationListViewP5S2;
    private NoteViewModel noteViewModel;
    ActivityResultLauncher<Intent> activityResultLauncherForUpdateNote;

    private SearchView editTextDonationP5S2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_part5_s2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        registerActivityForUpdateNote();

        Intent intent = getIntent();
        int isUpdated = intent.getIntExtra("isUpdated", 0);

        backButtonMainP5S2 = findViewById(R.id.backButtonMainP5S2);
        editTextDonationP5S2 = findViewById(R.id.editTextDonationP5S2);
        editTextDonationP5S2.clearFocus();

        editTextDonationP5S2.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        backButtonMainP5S2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityPart5S2.this, MainActivityPart5S1.class);
                startActivity(intent);
            }
        });

        locationListViewP5S2 = findViewById(R.id.locationListViewP5S2);
        locationListViewP5S2.setLayoutManager(new LinearLayoutManager(this));

        NoteAdapter noteAdapter = new NoteAdapter();
        locationListViewP5S2.setAdapter(noteAdapter);

        noteViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(NoteViewModel.class);

        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                //update Recycler View
                noteAdapter.setNotes(notes);
            }
        });

        noteAdapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Note note) {
                Intent intent = new Intent(MainActivityPart5S2.this, MainActivityPart5S3.class);
                intent.putExtra("aidName", note.getAidName());
                intent.putExtra("donationAmount", note.getAidAmount());
                intent.putExtra("availableSlot", note.getAidSlots());
                intent.putExtra("aidDateLine", note.getAidDateLine());
                intent.putExtra("financialID", note.getAidId());
                intent.putExtra("imageID", note.getImageID());


                //activity launcher
                activityResultLauncherForUpdateNote.launch(intent);

            }
        });

        if (isUpdated == 1) {
            String aidName = intent.getStringExtra("aidName");
            int donationAmount = intent.getIntExtra("donationAmount", 0);
            int availableSlot = intent.getIntExtra("availableSlot", 0);
            String aidDateLine = intent.getStringExtra("aidDateLine");
            int financialID = intent.getIntExtra("financialID", 0);
            int imageID = intent.getIntExtra("imageID", 0);

            Note note = new Note(aidName, donationAmount, availableSlot, aidDateLine, imageID);
            note.setAidId(financialID);
            noteViewModel.update(note);
        }

    }

    public void registerActivityForUpdateNote() {
        activityResultLauncherForUpdateNote = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        int resultCode = result.getResultCode();
                        Intent data = result.getData();
                        Log.d("Update Made P5S2: ", "Yes");

                        if (resultCode == RESULT_OK && data != null) {
                            /*String title = data.getStringExtra("titleLast");
                            String description = data.getStringExtra("descriptionLast");
                            int id = data.getIntExtra("noteId", -1);

                            Note note = new Note(title, description);
                            note.setId(id);
                            noteViewModel.update(note);
                             */
                            String aidName = data.getStringExtra("aidName");
                            int donationAmount = data.getIntExtra("donationAmount", 0);
                            int availableSlot = data.getIntExtra("availableSlot", 0);
                            String aidDateLine = data.getStringExtra("aidDateLine");
                            int financialID = data.getIntExtra("financialID", 0);
                            int imageID = data.getIntExtra("imageID", 0);


                            Note note = new Note(aidName, donationAmount, availableSlot, aidDateLine, imageID);
                            note.setAidId(financialID);
                            noteViewModel.update(note);
                        }
                    }
                });
    }

    public void filterList(String newText) {
        List<Note> filteredList = new ArrayList<>();
        String lowerNewText = newText.toLowerCase();

        noteViewModel.getAllNotes().observe(MainActivityPart5S2.this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {

                for(Note note : notes) {
                    String noteName = note.getAidName().toLowerCase();

                    if(noteName.equals(lowerNewText)) {
                        filteredList.add(note);
                    }
                }

                NoteAdapter noteAdapter = new NoteAdapter();

                if (filteredList.isEmpty()) {
                    noteAdapter.setNotes(notes);
                    locationListViewP5S2.setAdapter(noteAdapter);

                } else {
                    Toast.makeText(getApplicationContext(), "Data Found", Toast.LENGTH_SHORT).show();
                    noteAdapter.setNotes(filteredList);
                    locationListViewP5S2.setAdapter(noteAdapter);
                }

                noteAdapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Note note) {
                        Intent intent = new Intent(MainActivityPart5S2.this, MainActivityPart5S3.class);
                        intent.putExtra("aidName", note.getAidName());
                        intent.putExtra("donationAmount", note.getAidAmount());
                        intent.putExtra("availableSlot", note.getAidSlots());
                        intent.putExtra("aidDateLine", note.getAidDateLine());
                        intent.putExtra("financialID", note.getAidId());
                        intent.putExtra("imageID", note.getImageID());


                        //activity launcher
                        activityResultLauncherForUpdateNote.launch(intent);

                    }
                });

            }
        });

    }
}