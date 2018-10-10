package com.example.potatopaloozac.traveldomainproj.ui.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.potatopaloozac.traveldomainproj.R;
import com.example.potatopaloozac.traveldomainproj.ui.HomeActivity;
import com.example.potatopaloozac.traveldomainproj.utils.MySharedPreference;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivityTAG";

    static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MySharedPreference.getSharedPreferences(this);

        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.FacebookBuilder().build());

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setLogo(R.drawable.logo_greyhound_small)
                        .setTheme(R.style.LoginTheme)
                        .build(),
                RC_SIGN_IN);

        //TODO move this into a button setclicklistener
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Log.d(TAG, "onActivityResult: " + user.getDisplayName() + " " + user.getEmail());

                MySharedPreference.writeString(MySharedPreference.USER_ID, user.getUid());
                MySharedPreference.writeString(MySharedPreference.USER_NAME, user.getDisplayName());
                MySharedPreference.writeString(MySharedPreference.USER_EMAIL, user.getEmail());

                Intent i = new Intent(this, HomeActivity.class);
                startActivity(i);
                finish();
            } else {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle(R.string.loginUnsuccessfulTitle);
                alertDialogBuilder.setMessage(R.string.loginUnsuccessfulMessage);

                alertDialogBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialogBuilder.show();
            }
        }
    }
}
