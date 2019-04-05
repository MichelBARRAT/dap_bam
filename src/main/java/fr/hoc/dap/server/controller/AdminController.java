package fr.hoc.dap.server.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.DataStore;

import fr.hoc.dap.server.service.AdminService;

/**
 * @author jack et michette
 *
 */
@Controller
public class AdminController {
    @Autowired
    AdminService admService;
    ArrayList<String> dataList;
    DataStore<StoredCredential> cMap;
    ArrayList<ArrayList<String>> dataMap = new ArrayList<ArrayList<String>>();

    @RequestMapping("/admin")
    public String adminDisplay(ModelMap model) throws GeneralSecurityException, IOException {
        cMap = admService.getCredentialMap();
        dataMap.clear();

        for (String user : cMap.keySet()) {
            dataList = new ArrayList<String>();
            dataList.add(user);
            dataList.add(cMap.get(user).getAccessToken());
            dataList.add(cMap.get(user).getRefreshToken());
            Integer expiration = (int) (cMap.get(user).getExpirationTimeMilliseconds() / 86400000);
            dataList.add(expiration.toString());
            dataMap.add(dataList);
        }
        model.addAttribute("Map", dataMap);
        return "admin";

    }

}
