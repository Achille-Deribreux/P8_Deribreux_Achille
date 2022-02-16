package tourGuide.user.Repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import tourGuide.user.Entity.User;
import tourGuide.user.Exceptions.UserNotFoundException;
import tourGuide.user.Repository.UserRepository;
import tourGuide.user.Utils.Data;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.setInternalUserMap(Data.getInternalUserMap());
    }

    @Test
    void findAllUsersTest() {
        //Given
        List<User> expected = new ArrayList<>(Data.getInternalUserMap().values());
        List<User> result;
        //When
        result = userRepository.findAllUsers();
        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void getUserTest() {
        //Given
        User expected = Data.getUser1();
        User result;
        //When
        result = userRepository.getUser(expected.getUserName());
        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void getUserExceptionTest() {
        //Given
        String username = "random username";
        //When
       Assertions.assertThrows(UserNotFoundException.class,()->userRepository.getUser(username));
    }
}
