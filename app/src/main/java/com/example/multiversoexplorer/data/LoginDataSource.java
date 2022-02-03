package com.example.multiversoexplorer.data;

import androidx.annotation.NonNull;

import com.example.multiversoexplorer.data.model.LoggedInUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){
            LoggedInUser usuarioIdentificado =
                    new LoggedInUser(
                            user.getProviderId(),
                            user.getEmail());
            //.substring(0,user.getEmail().indexOf('@'))
            return new Result.Success<>(usuarioIdentificado);
        } else {
            return new Result.Error(new IOException("Error logging in", null));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}