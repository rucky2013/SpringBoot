package com.resume.resource;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.resume.converter.Converter;
import com.resume.dao.RepositoryDao;
import com.resume.dao.UserDao;
import com.resume.factory.UserStatusFactory;
import com.resume.model.User;
import com.resume.model.UserLanguage;
import com.resume.model.UserRepository;

@Component
@Path("/resume")
public class ResumeResource {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RepositoryDao repositoryDao;
    @Autowired
    private Converter<List<UserRepository>, List<UserLanguage>> converter;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GET
    @Path("/user/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(final @PathParam("userName") String userName) {
        logger.info("Processing a user level request for user {}", userName);
        final User user = userDao.getUser(userName);
        user.setUserType(UserStatusFactory.getUserStatus(user));
        return user;
    }

    @GET
    @Path("/languages/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserLanguage> getUserLanguages(final @PathParam("userName") String userName) {
        logger.info("Processing a repository level request for user {}", userName);
        final List<UserRepository> userRepositories = repositoryDao.getRepositories(userName);
        final List<UserLanguage> userLanguages = converter.convert(userRepositories);
        return userLanguages;
    }
}
