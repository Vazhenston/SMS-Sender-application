package com.smssender.services;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.SheetsScopes;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Service
public class AuthorizationService extends BasicService {
    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS); //изменить на readonly
    private static final String CLIENT_SECRET_FILE_PATH = "/client_secret.json";
    private static GoogleTokenResponse response;
    private static Credential credential;

    private void setCredential() throws IOException, GeneralSecurityException {
        new GoogleCredential.Builder()
                .setClientSecrets(getClientSecrets())
                .setJsonFactory(getJsonFactory())
                .setTransport(getNetHttpTransport())
                .build()
                .setAccessToken(getGoogleTokenResponse().getAccessToken())
                .setRefreshToken(getGoogleTokenResponse().getRefreshToken());
    }

    public static Credential getCredential() {
        return credential;
    }

    public void setGoogleTokenResponse(String code) throws IOException, GeneralSecurityException {
        response = new GoogleAuthorizationCodeTokenRequest(getNetHttpTransport(), getJsonFactory(),
                getClientSecrets().getDetails().getClientId(), getClientSecrets().getDetails().getClientSecret(),
                code, getClientSecrets().getDetails().getRedirectUris().get(0)).execute();
        setCredential();
    }

    private GoogleTokenResponse getGoogleTokenResponse() {
        return response;
    }

    public GoogleClientSecrets getClientSecrets() throws IOException {
        InputStream in = AuthorizationService.class.getResourceAsStream(CLIENT_SECRET_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found " + CLIENT_SECRET_FILE_PATH);
        }
        return GoogleClientSecrets.load(getJsonFactory(), new InputStreamReader(in));
    }

    public String getStartUrl() throws IOException {
        return new GoogleAuthorizationCodeRequestUrl(getClientSecrets(),
                getClientSecrets().getDetails().getRedirectUris().get(0), SCOPES)
                .setApprovalPrompt("force")
                .build();
    }
}
