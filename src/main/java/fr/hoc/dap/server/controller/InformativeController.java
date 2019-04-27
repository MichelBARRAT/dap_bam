package fr.hoc.dap.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Manage informative pages.
 *
 * @author Michette & Thomas
 */
@RestController
public class InformativeController {

    /**
     * Account added successfully.
     *
     * @return added successfully message.
     */
    @RequestMapping("/account/added")
    private String accountAdded() {
        return "Vous avez bien reussi à ajouter votre compte !";
    }
}
