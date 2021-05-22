package com.hobarb.sountry.ui.user.activities;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.hobarb.sountry.R;
import com.hobarb.sountry.ui.signup.adapters.GridAdapter;
import com.hobarb.sountry.utilities.constants;
import com.hobarb.sountry.utilities.views.Loader;

import java.util.concurrent.TimeUnit;

public class UploadActivity extends AppCompatActivity {

    private static final int VIDEO_FROM_GALLERY = 101;
    private static final int VIDEO_FROM_CAMERA = 102;
    VideoView videoView;
    TextView uri_tv;
    Uri videoUri;
    FirebaseStorage storage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);


        GridView genres_gv = findViewById(R.id.gv_genres_ac_upload);
        GridAdapter gridAdapter = new GridAdapter(this, constants.genres);
        genres_gv.setAdapter(gridAdapter);

        videoView = findViewById(R.id.vv_userVideo_ac_upload);
        uri_tv = findViewById(R.id.tv_uri_ac_upload);

        findViewById(R.id.iv_camera_ac_upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(i, VIDEO_FROM_CAMERA);
            }
        });

        findViewById(R.id.iv_gallery_ac_upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, VIDEO_FROM_GALLERY);
            }
        });
        
        findViewById(R.id.btn_upload_ac_upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(videoUri == null)
                    Toast.makeText(UploadActivity.this, "Please select a video.", Toast.LENGTH_SHORT).show();
                else{
                    uploadVideo(videoUri);
                }
            }
        });

    }

    private void uploadVideo(Uri videoUri) {

        Loader loader = new Loader(this);
        loader.showAlertDialog();
        storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference riversRef = storageRef.child("videos");
        riversRef.putFile(videoUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        //if the upload is successfull
                        //hiding the progress dialog
                        loader.dismissAlertDialog();

                        //and displaying a success toast
                        Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){

                    videoUri = data.getData();
                    long duration = getVideoDuration(videoUri);
                    /*if(duration>30)*/
                    uri_tv.setText(""+ videoUri.getPath());
                    videoView.setVideoURI(videoUri);
                    videoView.start();
            }


        }

        float getVideoSize(Uri videoUri)
        {
            String[] mediaColumns = {MediaStore.Video.Media.SIZE};
            Cursor cursor = this.getContentResolver().query(videoUri, mediaColumns, null, null, null);
            cursor.moveToFirst();
            int sizeColInd = cursor.getColumnIndex(mediaColumns[0]);
            long fileSize = cursor.getLong(sizeColInd);
            cursor.close();
            return fileSize/1024;
        }

        long getVideoDuration(Uri videoUri)
        {
            MediaPlayer mp = MediaPlayer.create(this, Uri.parse(String.valueOf(videoUri)));
            int duration = mp.getDuration();
            mp.release();
            return ((TimeUnit.MILLISECONDS.toMinutes(duration)*60) + TimeUnit.MILLISECONDS.toSeconds(duration) -
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
            /*convert millis to appropriate time*/
            /*return String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes(duration),
                    TimeUnit.MILLISECONDS.toSeconds(duration) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration))
            );*/
        }
}
