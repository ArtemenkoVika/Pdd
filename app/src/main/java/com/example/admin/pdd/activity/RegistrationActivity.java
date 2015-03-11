package com.example.admin.pdd.activity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.admin.pdd.R;
import com.example.admin.pdd.fragment.RegistrationFragment;

public class RegistrationActivity extends BaseActivity {
    private EditText editText;
    private RegistrationFragment registrationFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        registrationFragment = (RegistrationFragment) getSupportFragmentManager().
                findFragmentById(R.id.fragment_registration);
        editText = (EditText) registrationFragment.getView().findViewById(R.id.edit_user_name);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("editUserName", editText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        editText.setText(savedInstanceState.getString("editUserName"));
    }
}
