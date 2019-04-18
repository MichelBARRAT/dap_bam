package fr.hoc.dap.server.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.hoc.dap.server.service.GmailService;

/** Manage gmail service.
 * @author Michette & Thomas
 */
@RestController
public class MailController {

  //TODO bam by Djer |JavaDoc| Il faut documenter ton attribut, l'annotation est deja documentée (par Spring)
    /** Dependency injection. */
    @Autowired
    private GmailService gmService;

    /** Display message unread in client server.
     * @return number of messages unread.
     * @throws GeneralSecurityException cannot connect to google sever.
     * @throws IOException if the credentials.json file cannot be found.
     * @param userKey which user wanted access.
     */
    @RequestMapping("/email/nbunread")
    public Integer displayMessageUnread(@RequestParam("userKey") final String userKey)
            throws IOException, GeneralSecurityException {

        return gmService.displayMessageUnread(userKey);
    }

}
