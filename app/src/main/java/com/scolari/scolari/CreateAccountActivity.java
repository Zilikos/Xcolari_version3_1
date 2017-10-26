package com.scolari.scolari;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.scolari.scolari.model.Estudiante;


public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {

    // Declaring String variable ( In which we are storing firebase server URL ).
    public static final String Firebase_Server_URL = "https://xcolariv3.firebaseio.com/";
    Firebase firebase;
    DatabaseReference databaseReference;
    //root database name
    public static final String Database_Path = "XUser_Database";

    private EditText email,user,password_createaccount,confirmPassword;
    private Button joinUs;
    private String correo,usuario,contrasena,confirmacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        showToolbar(getResources().getString(R.string.toolbar_tittle_createaccount),true);

        email = (EditText) findViewById(R.id.email);
        user = (EditText) findViewById(R.id.user);
        password_createaccount = (EditText) findViewById(R.id.password_createaccount);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);
        joinUs = (Button) findViewById(R.id.joinUs);
        joinUs.setOnClickListener(this);

    }

    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PATTERN_USER = "^[A-Za-z\\d_]{4,15}$";

    /**
     * Validate given email with regular expression.
     *
     * @param email
     *            email for validation
     * @return true valid email, otherwise false
     */
    public static boolean validateEmail(String email) {

        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);

        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }

    public static boolean validateUser(String user) {

        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_USER);

        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(user);
        return matcher.matches();

    }

    public void showToolbar(String tittle, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }

    @Override
    public void onClick(View view) {
        correo = String.valueOf(email.getText());
        usuario = String.valueOf(user.getText());
        contrasena = String.valueOf(password_createaccount.getText());
        confirmacion = String.valueOf(confirmPassword.getText());

        Firebase.setAndroidContext(CreateAccountActivity.this);

        firebase = new Firebase(Firebase_Server_URL);

        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);

        if(validateEmail(correo) && validateUser(usuario) && contrasena.equals(confirmacion)){
            Toast.makeText(getBaseContext(),"Usuario Registrado",Toast.LENGTH_SHORT).show();

            Estudiante estudiante = new Estudiante();

            estudiante.setCorreo(correo);
            estudiante.setUsuario(usuario);
            estudiante.setContrasena(contrasena);

            // Getting the ID from firebase database.
            String studentRecordIDFromServer = databaseReference.push().getKey();
            // Adding the both name and number values using student details class object using ID.
            databaseReference.child(studentRecordIDFromServer).setValue(estudiante);

        }
        if(!validateEmail(correo)){
            Toast.makeText(getBaseContext(),"Correo invalido",Toast.LENGTH_SHORT).show();
        }
        if(!validateUser(usuario)){
            Toast.makeText(getBaseContext(),"Usuario invalido",Toast.LENGTH_SHORT).show();
        }

        if(!contrasena.equals(confirmacion)){
            Toast.makeText(getBaseContext(),"Contraseña no coincide",Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(getBaseContext(),correo+" "+validateEmail(correo),Toast.LENGTH_SHORT).show();

    }
}
