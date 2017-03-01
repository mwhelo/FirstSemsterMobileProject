package com.example.x1243.musicappmobiledevproject;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PlaylistClass extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText editSong, editArtist, editGenre,editId;
    Button saveBtn;
    Button viewBtn;
    Button updateBtn;
    Button deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlists);
        myDB = new DatabaseHelper(this);

        editSong = (EditText) findViewById(R.id.editText_song);
        editArtist = (EditText) findViewById(R.id.editText_artist);
        editGenre = (EditText) findViewById(R.id.editText_genre);
        editId = (EditText) findViewById(R.id.editText_id);
        saveBtn = (Button) findViewById(R.id.button_save);
        viewBtn = (Button) findViewById(R.id.button_view);
        updateBtn = (Button) findViewById(R.id.button_update);
        deleteBtn = (Button) findViewById(R.id.button_delete);

        SaveData();
        viewPlaylist();
        SaveData();
        UpdatePlaylist();
        DeleteSong();

    }

    public void DeleteSong(){
        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Integer deleteRows = myDB.deleteSong(editId.getText().toString());
                if(deleteRows > 0)
                    Toast.makeText(PlaylistClass.this, "Song Deleted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(PlaylistClass.this, "Song not Deleted", Toast.LENGTH_LONG).show();

            }
        });

    }

    public void UpdatePlaylist(){
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isUpdated = myDB.updatePlaylist(editId.getText().toString(),
                        editSong.getText().toString(),
                        editArtist.getText().toString(),
                        editGenre.getText().toString());

                if(isUpdated == true)
                    Toast.makeText(PlaylistClass.this, "Playlist Updated", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(PlaylistClass.this, "Playlist not Updated", Toast.LENGTH_LONG).show();



            }
        });
    }

    public void SaveData(){
        saveBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                boolean isInserted = myDB.insertData(editSong.getText().toString(),
                        editArtist.getText().toString(),
                        editGenre.getText().toString() );

                if(isInserted == true)
                    Toast.makeText(PlaylistClass.this, "Playlist Saved", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(PlaylistClass.this, "Playlist not Saved", Toast.LENGTH_LONG).show();


            }
        });
    }

    public void viewPlaylist(){
        viewBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Cursor res = myDB.getPlaylist();
                if(res.getCount() == 0){
                    displayMessage("No Playlist Found", "Please Start Adding Songs to your Playlist");

                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Id :" + res.getString(0)+"\n");
                    buffer.append("Song :" + res.getString(1)+"\n");
                    buffer.append("Artist :" + res.getString(2)+"\n");
                    buffer.append("Genre :" + res.getString(3)+"\n\n");

                }

                displayMessage("Data",buffer.toString());

            }
        });
    }

    public void displayMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
