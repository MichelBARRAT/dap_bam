package fr.hoc.dap.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.hoc.dap.server.data.DapUser;
import fr.hoc.dap.server.data.DapUserRepository;

/**
 * @author house
 *
 */
@RestController
public class TestController {
    /** . */
    @Autowired
    private DapUserRepository repo;

    /**
     * @param loginName loginName
     * @param userKey   userKey
     * @return test result
     */
    @RequestMapping("/test")
    private Iterable<DapUser> test(@RequestParam("loginName") final String loginName,
            @RequestParam("userKey") final String userKey) {
        DapUser user = new DapUser();
        user.setLoginName(loginName);
        user.setUserKey(userKey);
        repo.save(user);
        return repo.findAll();
    }
    // http://localhost:8080/test?loginName=thomas&userKey=test

    /**
     * @param userKey TODO
     * @return TODO
     */
    @RequestMapping("/test/loadDapUser")
    private DapUser loadDapUser(@RequestParam("userKey") final String userKey) {
        // return repo.findDapUser(userKey);
        return null;
    }
}
