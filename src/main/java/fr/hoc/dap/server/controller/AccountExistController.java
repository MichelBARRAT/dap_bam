package fr.hoc.dap.server.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.hoc.dap.server.service.AccountExistService;

/** Manage account existence.
 * @author Michette & Thomas
 */
@RestController
public class AccountExistController {

    //TODO bam by Djer |JavaDoc| Il faut documenter ton attribut, l'annotation est deja documentée (par Spring)
    /**Dependency injection. */
    @Autowired
    private AccountExistService ggService;

    /** Display if account exist.
     * @param userKey which user wanted access.
     * @return account exist true/false.
     * @throws IOException if the credentials.json file cannot be found.
     * @throws GeneralSecurityException cannot connect to google sever.
     */
    @RequestMapping("/account/exist")
    //TODO bam by Djer |JavaDoc| Pourrais être dans le GoogleAccount ?
    public Boolean accountNotExist(@RequestParam("userKey") final String userKey)
            throws IOException, GeneralSecurityException {
        return ggService.doesAccountExist(userKey);
    }
}
