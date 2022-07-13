package com.example.finalproject2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button imageButton, registerButton;
    EditText name, email, password, reEnterPassword;
    CheckBox agreeCheckBox;
    RadioGroup genderRadioGroup;
    RadioButton female, male, nonBinary;
    Spinner country;
    TextView nameWarning, emailWarning, passwordWarning, reEnterPasswordWarning;
    ConstraintLayout layout;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        imageButton.setOnClickListener(view -> Toast.makeText(MainActivity.this, "Yet To Be Talked About...", Toast.LENGTH_SHORT).show());

        ArrayList<String> allCountries = new ArrayList<>();
        allCountries.add("Germany");
        allCountries.add("Italy");
        allCountries.add("England");
        ArrayAdapter<String> countriesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, allCountries);
        country.setAdapter(countriesAdapter);
        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, allCountries.get(i) + " Selected.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        allFilled();
        agreeToTheTerms();
        radioIsChecked();

        registerButton.setOnClickListener(view -> {
            if (allFilled()) {
                if (!password.getText().toString().equals(reEnterPassword.getText().toString())) {
                    reEnterPasswordWarning.setText("Password Doesn't Match.");
                } else {
                    if (agreeToTheTerms() && radioIsChecked()) {
                        nameWarning.setText("");
                        emailWarning.setText("");
                        passwordWarning.setText("");
                        reEnterPasswordWarning.setText("");
                        Snackbar.make(layout, "User " + name.getText().toString() + " Registered Successfully.", Snackbar.LENGTH_INDEFINITE).setAction("OK", view1 -> {
                        }).show();
                        name.setText("");
                        email.setText("");
                        password.setText("");
                        reEnterPassword.setText("");
                    }
                }
            }
        });

    }

    private void initialize() {
        imageButton = findViewById(R.id.imageButton);
        registerButton = findViewById(R.id.registerButton);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        reEnterPassword = findViewById(R.id.reEnterPassword);
        agreeCheckBox = findViewById(R.id.agreeCheckBox);
        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        female = findViewById(R.id.femaleRadioButton);
        male = findViewById(R.id.maleRadioButton);
        nonBinary = findViewById(R.id.nonBinaryRadioButton);
        country = findViewById(R.id.countrySpinner);
        nameWarning = findViewById(R.id.nameWarning);
        emailWarning = findViewById(R.id.emailWarning);
        passwordWarning = findViewById(R.id.passwordWarning);
        reEnterPasswordWarning = findViewById(R.id.reEnterPasswordWarning);
        layout = findViewById(R.id.constraintLayout);
    }

    @SuppressLint("SetTextI18n")
    protected boolean allFilled() {

        boolean result = true;

        nameWarning.setText("");
        emailWarning.setText("");
        passwordWarning.setText("");
        reEnterPasswordWarning.setText("");

        if (name.getText().toString().equals("")) {
            nameWarning.setText("Enter Your Name.");
            result = false;
        }
        if (email.getText().toString().equals("")) {
            emailWarning.setText("Enter Your Email.");
            result = false;
        }
        if (password.getText().toString().equals("")) {
            passwordWarning.setText("Choose A Password.");
            result = false;
        }
        if (reEnterPassword.getText().toString().equals("")) {
            reEnterPasswordWarning.setText("Enter Your Password Again.");
            result = false;
        }

        return result;
    }

    protected boolean agreeToTheTerms() {
        if (!agreeCheckBox.isChecked()) {
            Toast.makeText(this, "Please Agree To Our Policy Terms.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    protected boolean radioIsChecked() {
        if (female.isChecked()) {
            Toast.makeText(this, "You Have Chosen FEMALE As Your Gender.", Toast.LENGTH_SHORT).show();
        } else if (male.isChecked()) {
            Toast.makeText(this, "You Have Chosen MALE As Your Gender.", Toast.LENGTH_SHORT).show();
        } else if (nonBinary.isChecked()) {
            Toast.makeText(this, "You Have Chosen NON BINARY As Your Gender.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please Choose Your Gender.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}