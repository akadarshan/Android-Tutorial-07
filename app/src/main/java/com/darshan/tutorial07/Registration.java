package com.darshan.tutorial07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Registration extends AppCompatActivity {

    TextInputLayout tl_fName,tl_lName,tl_email,tl_password;
    TextInputEditText tv_fName,tv_lName,tv_email,tv_password;
    MaterialButton btnRegister;
    RadioGroup gender;
    SwitchMaterial sw_branch;
    RadioButton rb_Checked;
    private TextInputLayout textInputLayout;
    AutoCompleteTextView dropDownList;
    int idSelected;
    String CEIT;
    DatabaseHelper myDbHelper;

    String[] itemDropDown = {"Select City","Ahmedabad", "Baroda", "Bhavnagar", "Gondal", "Junagadh", "Kutch", "Rajkot", "Surat"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myDbHelper=new DatabaseHelper(Registration.this);
        setContentView(R.layout.activity_registration);

        tl_fName = findViewById(R.id.tl_fName);
        tv_fName = findViewById(R.id.tv_fName);
        tl_lName = findViewById(R.id.tl_lName);
        tv_lName = findViewById(R.id.tv_lName);
        tl_email = findViewById(R.id.tl_email);
        tv_email = findViewById(R.id.tv_email);
        tl_password = findViewById(R.id.tl_password);
        tv_password = findViewById(R.id.tv_password);
        sw_branch = findViewById(R.id.sw_branch);
        gender = findViewById(R.id.gender);
        dropDownList = findViewById(R.id.dropdown);
        textInputLayout = findViewById(R.id.city_dropdown);
        btnRegister = findViewById(R.id.btnRegister);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.dropdown_item,itemDropDown);
        dropDownList.setAdapter(adapter);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String valEmail = tv_email.getText().toString();
                if (tv_fName.getText().toString().equals("")){
                    tl_fName.setError("Enter First Name");
                }else if (tv_lName.getText().toString().equals("")) {
                    tl_lName.setError("Enter Last Name");
                }else if (tv_email.getText().toString().equals("")){
                    tl_email.setError("Please Enter Email!");
                }else if(!Patterns.EMAIL_ADDRESS.matcher(valEmail).matches()){
                    tl_email.setError("Please Enter Valid Email!");
                }else if (tv_password.getText().toString().equals("")){
                    tl_password.setError("Password should not be empty");
                }else if (dropDownList.getText().toString().equals("Select City")){
                    textInputLayout.setError("Please Select City");
                }else{
                    tl_fName.setErrorEnabled(false);
                    tl_lName.setErrorEnabled(false);
                    tl_email.setErrorEnabled(false);
                    tl_email.setErrorEnabled(false);
                    tl_password.setErrorEnabled(false);
                    textInputLayout.setErrorEnabled(false);
                    idSelected=gender.getCheckedRadioButtonId();
                    rb_Checked=findViewById(idSelected);
                    if(sw_branch.isChecked())
                        CEIT=sw_branch.getTextOn().toString();
                    else
                        CEIT=sw_branch.getTextOff().toString();
                    myDbHelper.InsertData(tv_fName.getText().toString(),tv_lName.getText().toString(),tv_email.getText().toString(),tv_password.getText().toString(),rb_Checked.getText().toString(),dropDownList.getText().toString(),CEIT);
                    startActivity(new Intent(Registration.this,Login.class));
                }
            }
        });
    }
}