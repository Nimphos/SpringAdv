package beans.mvc.services;

import beans.daos.AuditoriumDAO;
import beans.daos.EventDAO;
import beans.daos.UserDAO;
import beans.models.Auditorium;
import beans.models.Event;
import beans.models.User;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class UploadDataService {

    @Autowired
    @Qualifier("eventDAO")
    EventDAO eventDao;
    @Autowired
    @Qualifier("userDAO")
    UserDAO userDao;
    @Autowired
    @Qualifier("auditoriumDAO")
    AuditoriumDAO auditoriumDao;

    @Autowired
    private HttpServletRequest request;

    public String loadDataFromFile(MultipartFile file) {

        /*File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+file.getOriginalFilename());
        try {
            file.transferTo(convFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("!!!!!!!!!!convFile.getAbsolutePath(): " + convFile.getAbsolutePath());


*/

        StringBuilder msg = new StringBuilder();
        String fileName = null;
        try {
            fileName = file.getOriginalFilename();
            byte[] bytes = file.getBytes();
            String strFile = new String(bytes);
            ObjectMapper mapper = new ObjectMapper();
            if (strFile.contains("birthday"))//users
            {
                List<User> users = null;
                users = mapper.readValue(strFile, new TypeReference<List<User>>() {
                });
                for (User user : users) {
                    msg.append("<br />").append(addUserDb(user));
                }
                msg.append("<br />Uploading ").append(fileName).append(": ").append(users.size());
            }
            if (strFile.contains("basePrice"))//events
            {
                List<Event> events = null;
                events = mapper.readValue(strFile, new TypeReference<List<Event>>() {});
                for (Event event : events) {
                    msg.append("<br />").append(addEventDb(event));
                }
                msg.append("<br />Uploading ").append(fileName).append(": ").append(events.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.append("<br />Failed to upload ").append(fileName).append(": ").append(e.getMessage());
        }
        return msg.toString();
    }

    String addUserDb(User user) {
        StringBuilder msg = new StringBuilder("user " + user.getId());
        User findUser = null;
        try
        {
            findUser = userDao.get(user.getId());
            if (findUser != null)
                msg.append(" already exists");
        }
        catch(org.springframework.dao.EmptyResultDataAccessException e)
        {
            userDao.create(user);
            msg.append(" successfully added");
        }
        return msg.toString();
    }

    String addEventDb(Event event) {
        StringBuilder msg = new StringBuilder();
        //Looking for related auditorium. Add if it doesn't exist
        Auditorium auditorium = event.getAuditorium();
        String auditoriumName = auditorium.getName();
        Auditorium findAuditorium = null;
        try
        {
            findAuditorium = auditoriumDao.getByName(auditoriumName);
            msg.append("auditoriums ").append(findAuditorium.getId()).append(" already exists");
        }
        catch(org.springframework.dao.EmptyResultDataAccessException e)
        {
            auditoriumDao.add(auditorium);
            msg.append("auditoriums ").append(auditoriumName).append("  successfully added");
        }

        //Add event if it doesn't exist
        Event findEvent = null;
        try
        {
            findEvent = eventDao.get(event.getName(), auditorium, event.getDateTime());
            msg.append("<br /> - event ").append(findEvent.getId()).append(" already exists");
        }
        catch(org.springframework.dao.EmptyResultDataAccessException e)
        {
            eventDao.create(event);
            msg.append("<br /> - event ").append(event.getId()).append("  successfully added");
        }

        return msg.toString();
    }
}
