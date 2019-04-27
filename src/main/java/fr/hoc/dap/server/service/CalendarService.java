package fr.hoc.dap.server.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

/**
 * Service to display next event of the user.
 *
 * @author Michette & Thomas
 */
@Service
public final class CalendarService extends GoogleService {

    /**
     * Build calendar service.
     *
     * @return calendar service instance.
     * @throws GeneralSecurityException cannot connect to google sever.
     * @throws IOException              if the credentials.json file cannot be found.
     * @param userKey which user wanted access.
     */
    private Calendar getService(final String userKey) throws GeneralSecurityException, IOException {
        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(httpTransport, getJsonFactory(), getCredentials(userKey))
                .setApplicationName(getMyConf().getApplicationName()).build();
        return service;
    }

    /**
     * Retrieve a list of next event(s).
     *
     * @return list of next event(s).
     * @throws GeneralSecurityException cannot connect to google sever.
     * @throws IOException              if the credentials.json file cannot be found.
     * @param userKey which user wanted access.
     * @param nb      number of event wanted by user.
     */
    public List<String> displayNextEvent(final Integer nb, final String userKey)
            throws IOException, GeneralSecurityException {
        List<String> nextEvents = new ArrayList<String>();
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = getService(userKey).events().list("primary").setMaxResults(nb).setTimeMin(now)
                .setOrderBy("startTime").setSingleEvents(true).execute();
        List<Event> items = events.getItems();
        if (items.isEmpty()) {
            nextEvents.add("No events found");
        } else {
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                nextEvents.add(event.getSummary() + " " + start);
            }
        }
        return nextEvents;
    }
}
