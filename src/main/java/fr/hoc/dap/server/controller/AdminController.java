package fr.hoc.dap.server.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.DataStore;

import fr.hoc.dap.server.service.AdminService;

/** Manage Admin service.
 * @author jack et michette
 */
@Controller
public class AdminController {

    //TODO bam by Djer |JavaDoc| Il faut documenter ton attribut, l'annotation est deja documentée (par Spring)
    /** Dependency injection. */
    @Autowired
    private AdminService admService;

    /** Display admin panel in server client.
     * @param model push parameter in HTML.
     * @return web browser window. //TODO bam by Djer |JavaDoc| Non ! "The name of the view to buid. This view xill return HTMML content"
     * @throws GeneralSecurityException cannot connect to google sever.
     * @throws IOException if the credentials.json file cannot be found.
     */
    @RequestMapping("/admin")
    public String adminDisplay(final ModelMap model) throws GeneralSecurityException, IOException {
        DataStore<StoredCredential> credentialMap;
        Map<String, StoredCredential> dataMap = new HashMap<String, StoredCredential>();
        credentialMap = admService.getCredentialMap();

        for (String user : credentialMap.keySet()) {
            dataMap.put(user, credentialMap.get(user));
        }
        model.addAttribute("map", dataMap);
        return "admin";
    }

    //TODO bam by Djer |JavaDoc|  NON ! Traite la supression lors que cette URL est appelée. L'affichage est traité dans ta View "admin"
    /** Display delete button in server client.
     * @param userKey which user wanted access. //TODO bam by Djer |JavaDoc| NON : "Dap user ID to delete"
     * @return redirect admin browser window. //TODO bam by Djer |JavaDoc| NON : retourne le nom de la vue qui va produire le HTML à renvoyer au naviguateur
     * @throws GeneralSecurityException cannot connect to google sever.
     * @throws IOException if the credentials.json file cannot be found.
     */
    @RequestMapping("/delete")
    public String adminDelete(@RequestParam("userKey") final String userKey)
            throws GeneralSecurityException, IOException {
        admService.delCredential(userKey);
        return "redirect:/admin";
    }

    /** Display admin panel in server client.
     * @param userKey which user wanted access. /TODO bam by Djer |JavaDoc| Non, "which user current user name" serait plus juste
     * @param newUserKey new user name for change it.
     * @return redirect admin browser window.
     * @throws GeneralSecurityException cannot connect to google sever.
     * @throws IOException if the credentials.json file cannot be found.
     */
    @RequestMapping("/changeUserName")
    public String adminChange(@RequestParam("userKey") final String userKey,
            @RequestParam("newUserKey") final String newUserKey) throws GeneralSecurityException, IOException {
        admService.changeCredential(userKey, newUserKey);
        return "redirect:/admin";
    }
}
