package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {



    @Autowired
   private UserRepo userRepo;

    @Disabled
    @Test
    public void testFindByUserName() {
       User user =  userRepo.findByUserName("ram");
        assertTrue(!user.getJournalEntries().isEmpty());
    }

    @Disabled
    @CsvSource({
            "1,1,2",
            "2,2,4",
            "8,8,1"
    })
    @ParameterizedTest
    public void test(int a , int b , int expected){
        assertEquals(expected, a + b);
    }
}
