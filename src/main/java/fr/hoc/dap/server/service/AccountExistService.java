package fr.hoc.dap.server.service;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.Credential;

/** Service to display account existence.
 * @author Michette & Thomas
 */
@Service
public class AccountExistService extends GoogleService {
    /**
     * @param userKey which user wanted access.
     * @return account exist true/false.
     * @throws IOException if the credentials.json file cannot be found.
     * @throws GeneralSecurityException cannot connect to google sever.
     */
    public Boolean doesAccountExist(final String userKey) throws IOException, GeneralSecurityException {
        final Credential credential = getCredentials(userKey);
        Boolean answer = false;
        if (null != credential && null != credential.getAccessToken()) {
            answer = true;
        }
        return answer;
    }
}
