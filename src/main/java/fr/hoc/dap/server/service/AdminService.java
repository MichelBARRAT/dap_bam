package fr.hoc.dap.server.service;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.util.store.DataStore;

/**
 * Service to display admin service.
 *
 * @author Michette & Thomas
 */
@Service
public class AdminService extends GoogleService {

    /**
     * Get data store.
     *
     * @return data store.
     * @throws GeneralSecurityException cannot connect to google sever.
     * @throws IOException              if the credentials.json file cannot be found.
     */
    public DataStore<StoredCredential> getCredentialMap() throws GeneralSecurityException, IOException {
        GoogleAuthorizationCodeFlow flow = getFlow();
        DataStore<StoredCredential> datas = flow.getCredentialDataStore();
        return datas;
    }

    /**
     * Delete an account.
     *
     * @param userKey user name to delete.
     * @throws GeneralSecurityException cannot connect to google sever.
     * @throws IOException              if the credentials.json file cannot be found.
     */
    public void delCredential(final String userKey) throws GeneralSecurityException, IOException {
        GoogleAuthorizationCodeFlow flow = getFlow();
        flow.getCredentialDataStore().delete(userKey);
    }

    /**
     * Change a user name.
     *
     * @param userKey    current user name to be changed.
     * @param newUserKey new user name for change it.
     * @throws GeneralSecurityException cannot connect to google sever.
     * @throws IOException              if the credentials.json file cannot be found.
     */
    public void changeCredential(final String userKey, final String newUserKey)
            throws GeneralSecurityException, IOException {
        GoogleAuthorizationCodeFlow flow = getFlow();
        StoredCredential userCredentials = flow.getCredentialDataStore().get(userKey);
        flow.getCredentialDataStore().set(newUserKey, userCredentials);
        flow.getCredentialDataStore().delete(userKey);
    }
}
