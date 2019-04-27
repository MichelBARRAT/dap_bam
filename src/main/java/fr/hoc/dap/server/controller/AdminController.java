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

/**
 * Manage Admin service.
 *
 * @author jack et michette
 */
@Controller
public class AdminController {
    /** Initialize AdminService instance. */
    @Autowired
    private AdminService admService;

    /**
     * Display admin panel in server client.
     *
     * @param model push parameter in HTML.
     * @return The name of the view to build. This view will return HTMML content.
     * @throws GeneralSecurityException cannot connect to google sever.
     * @throws IOException              if the credentials.json file cannot be found.
     */
    @RequestMapping("/admin")
    private String adminDisplay(final ModelMap model) throws GeneralSecurityException, IOException {
        DataStore<StoredCredential> credentialMap;
        Map<String, StoredCredential> dataMap = new HashMap<String, StoredCredential>();
        credentialMap = admService.getCredentialMap();
        for (String user : credentialMap.keySet()) {
            dataMap.put(user, credentialMap.get(user));
        }
        model.addAttribute("map", dataMap);
        return "admin";
    }

    /**
     * Used to delete en userID.
     *
     * @param userKey Dap user ID to delete.
     * @return The name of the view to build. This view will return HTMML content.
     * @throws GeneralSecurityException cannot connect to google sever.
     * @throws IOException              if the credentials.json file cannot be found.
     */
    @RequestMapping("/delete")
    private String adminDelete(@RequestParam("userKey") final String userKey)
            throws GeneralSecurityException, IOException {
        admService.delCredential(userKey);
        return "redirect:/admin";
    }

    /**
     * Display admin panel in server client.
     *
     * @param userKey    which user current user name.
     * @param newUserKey new user name for change it.
     * @return redirect admin browser window.
     * @throws GeneralSecurityException cannot connect to google sever.
     * @throws IOException              if the credentials.json file cannot be found.
     */
    @RequestMapping("/changeUserName")
    private String adminChange(@RequestParam("userKey") final String userKey,
            @RequestParam("newUserKey") final String newUserKey) throws GeneralSecurityException, IOException {
        admService.changeCredential(userKey, newUserKey);
        return "redirect:/admin";
    }
}
