package fr.hoc.dap.server.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.hoc.dap.server.service.CalendarService;

/** Manage calendar service.
 * @author Michette & Thomas
 */
@RestController
public class EventController {

    /** Dependency injection. */
    @Autowired
    private CalendarService gcService;

    /** Display next event in server client.
     * @param userKey which user wanted access.
     * @param nb number of event wanted by user.
     * @return list of next event.
     * @throws IOException if the credentials.json file cannot be found.
     * @throws GeneralSecurityException cannot connect to google sever.
     */
    @RequestMapping("/event/next")
    public List<String> displayNextEvent(@RequestParam(value = "nb", defaultValue = "1") final Integer nb,
            @RequestParam("userKey") final String userKey) throws IOException, GeneralSecurityException {

        return gcService.displayNextEvent(nb, userKey);

    }

}
