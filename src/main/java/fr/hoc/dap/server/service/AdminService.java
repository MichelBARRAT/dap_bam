package fr.hoc.dap.server.service;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.util.store.DataStore;

/** Service to display admin service.
 * @author jack et michette
 */
@Service
public class AdminService extends GoogleService {

    /** Get data store.
     * @return data store.
     * @throws GeneralSecurityException cannot connect to google sever.
     * @throws IOException if the credentials.json file cannot be found.
     */
    public DataStore<StoredCredential> getCredentialMap() throws GeneralSecurityException, IOException {
        GoogleAuthorizationCodeFlow flow = getFlow();

        DataStore<StoredCredential> datas = flow.getCredentialDataStore();

        return datas;
    }

    /** Delete an account.
     * @param userKey which user wanted access. //TODO bam by Djer |JavaDoc| NON "user name to DELETE"
     * @throws GeneralSecurityException cannot connect to google sever.
     * @throws IOException if the credentials.json file cannot be found.
     */
    public void delCredential(final String userKey) throws GeneralSecurityException, IOException {
        GoogleAuthorizationCodeFlow flow = getFlow();
        flow.getCredentialDataStore().delete(userKey);
    }

    /** Change our user name. //TODO bam by Djer |JavaDoc| Ce n'est pas "our" userName mais "a" username (en tant qu'admin on peut changer n'importe quel nom de compte)
     * @param userKey which user wanted access. //TODO bam by Djer |JavaDoc| Non "current user name to be changed" serait mieu
     * @param newUserKey new user name for change it.
     * @throws GeneralSecurityException cannot connect to google sever.
     * @throws IOException if the credentials.json file cannot be found.
     */
    public void changeCredential(final String userKey, final String newUserKey)
            throws GeneralSecurityException, IOException {
        GoogleAuthorizationCodeFlow flow = getFlow();
        StoredCredential userCredentials = flow.getCredentialDataStore().get(userKey);
        flow.getCredentialDataStore().set(newUserKey, userCredentials);
        flow.getCredentialDataStore().delete(userKey);
    }
}
