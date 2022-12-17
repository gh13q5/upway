package com.mobile.upway.controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobile.upway.R;
import com.mobile.upway.dao.UserDAO;
import com.mobile.upway.dto.User;

public class RegistActivity extends AppCompatActivity {

    static final String TAG = "RegistActivity";

    // FIREBASE
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener firebaseAuthListener;

    // VIEW
    EditText idForm;
    EditText pwdForm;
    EditText nicknameForm;
    TextView registBtn;

    // DAO
    UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        firebaseAuth = FirebaseAuth.getInstance();

        idForm = (EditText) findViewById(R.id.regist_id_form);
        pwdForm = (EditText) findViewById(R.id.regist_pwd_form);
        nicknameForm = (EditText) findViewById(R.id.regist_nickname_form);

        registBtn = findViewById(R.id.regist_form_btn);
        registBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = idForm.getText().toString();
                String pwd = pwdForm.getText().toString();
                String nickname = nicknameForm.getText().toString();

                if(id.equals("") || pwd.equals("") || nickname.equals("")){
                    Toast.makeText(getApplicationContext(), "폼을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                regist(id, pwd, nickname);
            }
        });
    }

    public void regist(String id, String pwd, String nickname){
        firebaseAuth.createUserWithEmailAndPassword(id, pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            // regist success
                            Log.d(TAG, id + ": 회원가입 성공");
                            FirebaseUser user = firebaseAuth.getCurrentUser();

                            String userUID = user.getUid();
                            User userDTO = new User(id, pwd, nickname);

                            userDAO = new UserDAO();
                            userDAO.createUser(userDTO, userUID);

                            Intent loginIntent = new Intent(RegistActivity.this, LoginActivity.class);
                            startActivity(loginIntent);
                            finish();
                        }
                        else{
                            // regist false
                            Log.d(TAG, id + ": 회원가입 실패");
                            Toast.makeText(getApplicationContext(), "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onStop(){
        super.onStop();
        if(firebaseAuthListener != null){
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }
    }
}
