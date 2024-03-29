package com.d2956987215.mow.rxjava;



import com.d2956987215.mow.util.User;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class TokenAuthenticator implements Authenticator {
    @Override
    public Request authenticate(Route route, Response response) throws IOException {

        if( responseCount(response)<2){
            Request build = response.request().newBuilder()
                    .header("Authorization", "Bearer " +User.token())
                    .build();
            return build;
        }

        return  null;
    }

    private int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;
        }
        return result;
    }
}

