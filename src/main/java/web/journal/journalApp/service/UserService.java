package web.journal.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import web.journal.journalApp.entity.User;
import web.journal.journalApp.repository.UserRepo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepo userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



    public boolean SaveNewUser(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("User"));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            log.info("hahahahaha");
            log.error("hahahahaha");
            log.warn("hahahahaha");
            log.debug("hahahahaha");
            log.trace("hahahahaha");
            throw new RuntimeException(e);
        }
    }

    public void SaveUser(User user){
        userRepository.save(user);
    }


    public List<User> getAll (){
        return userRepository.findAll();
    }

    public Optional<User> getById (ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteaById(ObjectId myId){
        userRepository.deleteById(myId);
    }

    public User findByUsername(String username){
        return userRepository.findByUserName(username);
    }

    public void createadmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("User", "ADMIN"));
        userRepository.save(user);
    }
}
