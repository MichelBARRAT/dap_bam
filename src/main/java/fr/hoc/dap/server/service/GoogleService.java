package fr.hoc.dap.server.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.people.v1.PeopleServiceScopes;

import fr.hoc.dap.server.Configuration;

/**
 * Service to stock Credentials.
 *
 * @author Michette & Thomas
 */
public class GoogleService {
    /** Configuration instance. */
    @Autowired
    private Configuration myConf;
    /** Instance of Json Factory. */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    /** The list of scopes. */
    private static List<String> scopes;

    /**
     * GoogleService constructor.
     *
     * Create the list of scopes.
     */
    protected GoogleService() {
        scopes = new ArrayList<String>();
        scopes.add(CalendarScopes.CALENDAR_READONLY);
        scopes.add(GmailScopes.GMAIL_READONLY);
        scopes.add(GmailScopes.GMAIL_LABELS);
        scopes.add(PeopleServiceScopes.CONTACTS_READONLY);
        scopes.add(PeopleServiceScopes.USER_EMAILS_READ);
        scopes.add(PeopleServiceScopes.USERINFO_PROFILE);
    }

    /**
     * @param userKey which user wanted access.
     * @return account exist true/false.
     * @throws IOException              if the credentials.json file cannot be found.
     * @throws GeneralSecurityException cannot connect to google sever.
     */
    protected Boolean doesAccountExist(final String userKey) throws IOException, GeneralSecurityException {
        final Credential credential = getCredentials(userKey);
        Boolean answer = false;
        if (null != credential && null != credential.getAccessToken()) {
            answer = true;
        }
        return answer;
    }

    /**
     * Get Credential for user connection.
     *
     * @param userKey which user wanted access.
     * @return an authorized Credential object.
     * @throws IOException              IOException if the credentials.json file cannot be found.
     * @throws GeneralSecurityException cannot connect to google sever.
     */
    protected Credential getCredentials(final String userKey) throws IOException, GeneralSecurityException {
        GoogleAuthorizationCodeFlow flow = getFlow();
        // return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        return flow.loadCredential(userKey);
    }

    /**
     * Build a google Flow.
     *
     * @return a configured Google flow .
     * @throws GeneralSecurityException cannot connect to google sever.
     * @throws IOException              if the credentials.json file cannot be found.
     */
    protected GoogleAuthorizationCodeFlow getFlow() throws GeneralSecurityException, IOException {
        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        File in = new java.io.File(myConf.getCredentialsFilePath());
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new FileReader(in));
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY,
                clientSecrets, scopes)
                        .setDataStoreFactory(
                                new FileDataStoreFactory(new java.io.File(myConf.getTokensDirectoryPath())))
                        .setAccessType("offline").build();
        return flow;
    }

    /**
     * Get actual configuration.
     *
     * @return actual configuration.
     */
    protected Configuration getMyConf() {
        return myConf;
    }

    /**
     * Set actual configuration.
     *
     * @param myconf acual configuration.
     */
    protected void setMyConf(final Configuration myconf) {
        this.myConf = myconf;
    }

    /**
     * @return JSON_FACTORY.
     */
    protected static JsonFactory getJsonFactory() {
        return JSON_FACTORY;
    }
}
