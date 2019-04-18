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

/** Service to stock Credentials.
 * @author Michette & Thomas
 */

public class GoogleService {

    //TODO bam by Djer |JavaDoc| Il faut documenter ton attribut, l'annotation est deja documentée (par Spring)
    /** Dependency injection. */
    @Autowired
    private Configuration myConf;

    /** Make an instance of Json Factory. */
    protected static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    /**Users Port selected for GOOGLE.*/
    //private static final Integer PORT = 8888;
    //TODO bam by Djer |JavaDoc| Evite les verbes d'actions pour documenter les attributs, c'est en général "angiüe". "The list of scopes." serait mieu
    /**Create a list of scopes. */
    private static List<String> scopes;

    /** Get Credential for user connection.
     * @param userKey which user wanted access.
     * @return an authorized Credential object.
     * @throws IOException IOException if the credentials.json file cannot be found.
     * @throws GeneralSecurityException cannot connect to google sever.
     */
    public Credential getCredentials(final String userKey) throws IOException, GeneralSecurityException {
        GoogleAuthorizationCodeFlow flow = getFlow();
        //LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(PORT).build();
        //return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

        return flow.loadCredential(userKey);
    }

    //TODO bam by Djer |POO| les getter/setter vont vers la fin de la classe.
    /** Get actual configuration.
     * @return actual configuration.
     */
    protected Configuration getMyConf() {
        return myConf;
    }

    /** Set actual configuration.
     * @param myconf acual configuration.
     */
    public void setMyConf(final Configuration myconf) {
        this.myConf = myconf;
    }

    /** Build a google Flow.
     * @return a configured Google flow .
     * @throws GeneralSecurityException cannot connect to google sever.
     * @throws IOException if the credentials.json file cannot be found.
     */
    public GoogleAuthorizationCodeFlow getFlow() throws GeneralSecurityException, IOException {
        //TODO bam by Djer |POO| Pas top de créer/alimenter ici, dans le constructeur ca serait mieu. A Chaque "getFlow" la liste va être reconstruite "pour rien"
        scopes = new ArrayList<String>();
        scopes.add(CalendarScopes.CALENDAR_READONLY);
        scopes.add(GmailScopes.GMAIL_READONLY);
        scopes.add(GmailScopes.GMAIL_LABELS);
        scopes.add(PeopleServiceScopes.CONTACTS_READONLY);
        scopes.add(PeopleServiceScopes.USER_EMAILS_READ);
        scopes.add(PeopleServiceScopes.USERINFO_PROFILE);

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
}
