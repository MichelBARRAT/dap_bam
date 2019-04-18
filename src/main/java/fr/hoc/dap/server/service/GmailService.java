package fr.hoc.dap.server.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

/** Service for display number of messages unread.
 * @author Michette & Thomas
 */
@Service
public final class GmailService extends GoogleService {

    /** Get gmail service.
     * @param userKey which user wanted access.
     * @return gmail service instance.
     * @throws GeneralSecurityException cannot connect to google sever.
     * @throws IOException if the credentials.json file cannot be found.
     */
    //TODO bam by Djer |POO| "BuildService" serait mieu
    public Gmail getService(final String userKey) throws GeneralSecurityException, IOException {
        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();

        Gmail service = new Gmail.Builder(httpTransport, JSON_FACTORY, getCredentials(userKey))
                .setApplicationName(getMyConf().getApplicationName()).build();

        return service;
    }

    /** Display number of unread messages. //TODO bam by Djer |JavaDoc| Ne "display" plus mais "retrieve"
     * @param userKey which user wanted access.
     * @return list of messages match with the query.
     * @throws IOException IOException if the credentials.json file cannot be found.
     * @throws GeneralSecurityException cannot connect to google sever.
     */
    public int displayMessageUnread(final String userKey) throws IOException, GeneralSecurityException {

        listMessagesMatchingQuery(userKey);

        return listMessagesMatchingQuery(userKey);

    }

    /** List of messages matching with query.
     * @param userKey which user wanted access.
     * @return how many messages match with the query.
     * @throws IOException if the credentials.json file cannot be found.
     * @throws GeneralSecurityException cannot connect to google sever.
     */
    public int listMessagesMatchingQuery(final String userKey) throws IOException, GeneralSecurityException {
        String query = "is:unread";
        String userId = "me";

        //TODO bam by Djer |Log4J| Une petite log ? "Searching for message with filter " + query + " for " + userKey

        ListMessagesResponse response = getService(userKey).users().messages().list(userId).setQ(query).execute();
        List<Message> messages = new ArrayList<Message>();
        while (response.getMessages() != null) {
            messages.addAll(response.getMessages());
            if (response.getNextPageToken() != null) {
                String pageToken = response.getNextPageToken();
                response = getService(userKey).users().messages().list(userId).setQ(query).setPageToken(pageToken)
                        .execute();
            } else {
                break;
            }
        }
        return messages.size();
    }
}
