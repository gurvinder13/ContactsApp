package com.example.contactsapp.edit;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.contactsapp.R;
import com.example.contactsapp.data.db.AppDatabase;
import com.example.contactsapp.data.db.entity.Person;
import com.example.contactsapp.utils.Constants;
import com.google.android.material.textfield.TextInputLayout;

public class EditActivity extends AppCompatActivity implements EditContract.View {

    private EditContract.Presenter mPresenter;

    private EditText mNameEditText;
    private EditText mPhoneEditText;

    private TextInputLayout mNameTextInputLayout;

    private TextInputLayout mPhoneTextInputLayout;

    private Button button;

    private Person person;
    private boolean mEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        person = new Person();
        checkMode();

        AppDatabase db = AppDatabase.getDatabase(getApplication());
        mPresenter = new EditPresenter(this, db.personModel());

        initViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mEditMode) {
            mPresenter.getPersonAndPopulate(person.id);
        }
    }

    private void checkMode() {
        if (getIntent().getExtras() != null) {
            person.id = getIntent().getLongExtra(Constants.PERSON_ID, 0);
            mEditMode = true;
        }
    }

    private void initViews() {
        mNameEditText = (EditText) findViewById(R.id.nameEditText);

        mPhoneEditText = (EditText) findViewById(R.id.phoneEditText);

        mNameTextInputLayout = (TextInputLayout) findViewById(R.id.nameTextInputLayout);

        mPhoneTextInputLayout = (TextInputLayout) findViewById(R.id.phoneTextInputLayout);

        button = findViewById(R.id.update);
        button.setText(mEditMode ? "Update" : "Add");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                person.name = mNameEditText.getText().toString();
                person.phone = mPhoneEditText.getText().toString();

                boolean valid = mPresenter.validate(person);

                if (!valid) return;

                if (mEditMode) {
                    mPresenter.update(person);
                } else {
                    mPresenter.save(person);
                }
            }
        });
    }

    @Override
    public void setPresenter(EditContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showErrorMessage(int field) {
        if (field == Constants.FIELD_NAME) {
            mNameTextInputLayout.setError(getString(R.string.invalid_name));
        } else if (field == Constants.FIELD_PHONE) {
            mPhoneTextInputLayout.setError(getString(R.string.invalid_phone));
        }
    }

    @Override
    public void clearPreErrors() {
        mNameTextInputLayout.setErrorEnabled(false);
        mPhoneTextInputLayout.setErrorEnabled(false);

    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public void populate(Person person) {
        this.person = person;
        mNameEditText.setText(person.name);
        mPhoneEditText.setText(person.phone);
    }


}
