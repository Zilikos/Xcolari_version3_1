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
import com.google.firebase.database.FirebaseDatabase;


public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {

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


        if(validateEmail(correo)){
            Toast.makeText(getBaseContext(),"Correo valido",Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(getBaseContext(),"Correo invalido",Toast.LENGTH_SHORT).show();
        }
        if(validateUser(usuario)){
            Toast.makeText(getBaseContext(),"Usuario valido",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getBaseContext(),"Usuario invalido",Toast.LENGTH_SHORT).show();
        }

        contrasena = String.valueOf(password_createaccount.getText());
        confirmacion = String.valueOf(confirmPassword.getText());

        if(!contrasena.equals(confirmacion)){
            Toast.makeText(getBaseContext(),"Contraseña no coincide",Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(getBaseContext(),correo+" "+validateEmail(correo),Toast.LENGTH_SHORT).show();

    }
}
