package com.alcode.beactiveapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alcode.beactiveapp.R;
import com.facebook.FacebookException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;

import barreto.simpleloginlibrary.api_login.FacebookSign;
import barreto.simpleloginlibrary.api_login.GoogleSign;

/**
 * Created by MOMANI on 2016/03/17.
 */
public class CustomSlide extends Fragment implements GoogleSign.InfoLoginGoogleCallback, FacebookSign.InfoLoginFaceCallback
{
    GoogleSign googleSign; // Google sign-in
    FacebookSign facebookSign; // Facebook sign-in
    private Button googleButton;
    private Button facebookButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_custom_slide, container, false);

        // FragmentActivity and interface listener
        googleSign      = new GoogleSign(getActivity(),this);

        // FragmentActivity and interface listener
        facebookSign    = new FacebookSign(getActivity(),this);
        googleButton= (Button)rootView.findViewById(R.id.googlePlusButton);
        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButtonGoogle(null);
            }
        });
        facebookButton= (Button)rootView.findViewById(R.id.facebookButton);
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButtonFacebook(null);
            }
        });


        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        googleSign.resultGoogleLogin(requestCode, resultCode, data); // result
        facebookSign.resultFaceLogin(requestCode,resultCode,data); // result
    }

   //  EVENT CLICK BUTTON SIMPLE
    public void loginButtonGoogle (View view) {
        googleSign.signIn();
    }

    public void loginButtonFacebook (View view) {
        facebookSign.signIn();
    }

    // LISTNER GOOGLE SIGN-IN

    @Override
    public void getInfoLoginGoogle(GoogleSignInAccount account) {
        Log.w("LOG","Name "+account.getDisplayName());
        Log.v("LOG","Email " + account.getEmail());
        Log.v("LOG","Photo "+account.getPhotoUrl());
    }

    @Override
    public void connectionFailedApiClient(ConnectionResult connectionResult) {
        Log.e("LOG","Connection Failed API "+connectionResult.getErrorMessage());
    }

    @Override
    public void loginFailed() {
        Log.e("LOG", "Login Failed");
    }

    // LISTNER FACEBOOK SIGN-IN

    @Override
    public void getInfoFace(String id, String name, String email, String photo) {
        Log.w("LOG","Name "+name);
        Log.w("LOG","Email "+email);
        Log.w("LOG","Photo "+photo);
    }

    @Override
    public void cancelLoginFace() {
        Log.e("LOG","Login cancel");
    }

    @Override
    public void erroLoginFace(FacebookException e) {
        Log.e("LOG","Erro Login Face "+e.getMessage());
    }
}
