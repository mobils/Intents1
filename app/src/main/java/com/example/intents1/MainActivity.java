package com.example.intents1;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String nom = "";
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1 = findViewById(R.id.b1);
        b1.setOnClickListener(this);

        Button b2 = findViewById(R.id.b2);
        b2.setOnClickListener(this);

        Button b3 = findViewById(R.id.b3);
        b3.setOnClickListener(this);

        Button b4 = findViewById(R.id.b4);
        b4.setOnClickListener(this);

        Button b5 = findViewById(R.id.b5);
        b5.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Button b = (Button)view;

        if (b.getId() == R.id.b1 ) {
            //Obrim l'activitat 2 amb el Intent

            Intent intent = new Intent(this, Activity2.class);  //.class és l'arxiu java compilat
            startActivity(intent);
        }
        else if (b.getId() == R.id.b2 )
        {
            //truquem per telèfon : ACTION_DIAL  = ACTION_VIEW

            Intent intent2 = new Intent(Intent.ACTION_CALL, Uri.parse("tel:112"));
            startActivity(intent2);
        }
        else if (b.getId() == R.id.b3 )
        {
            Intent intent3 = new Intent(Intent.ACTION_WEB_SEARCH);
            intent3.putExtra(SearchManager.QUERY, "Texto a buscar");
            startActivity(intent3);

        }
        else if (b.getId() == R.id.b4 ) {

            //obre la llista de contactes per triar un.
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
            startActivity(intent);

            //Si volguessim recuperar un contacte que tingui correu electrònic
/*
            Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Email.CONTENT_URI);
            startActivity(i);
*/
        }
        else if (b.getId() == R.id.b5 ) {
//busquem una imatge perquè la uri comença per image.
// De fet només estem cridant al launcher i ell busca la manera d'obrir el uri
                mGetContent.launch("image/*");



        }
    }
    // GetContent creates an ActivityResultLauncher<String> to allow you to pass
// in the mime type you'd like to allow the user to select
    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    // TODO: Què farà quan torni amb l'objecte.
                    //if (uri.)


                        ((ImageView)findViewById(R.id.imatgeCanvia)).setImageURI(uri);

                }
            });
}