package fr.hoc.dap.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Manage home page.
 * @author Michette & Thomas
 */
@RestController
public class HelloController {

    /** Say hello to user.
     * @return welcome message for users.
     * @param userKey which user wanted access.
     */
    @RequestMapping("/")
    public String index(@RequestParam(value = "userKey", defaultValue = "utilisateur") final String userKey) {
        return "Salut bienvenue sur la meilleure appli " + userKey;
    }

    /** Account added successfully.
     * @return added successfully message.
     */
    @RequestMapping("/account/added")
    public String index2() {
        return "Vous avez bien reussi à ajouter votre compte !";
    }

}
