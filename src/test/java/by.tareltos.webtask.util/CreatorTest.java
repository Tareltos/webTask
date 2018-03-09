package by.tareltos.webtask.util;

import by.tareltos.webtask.entity.User;
import by.tareltos.webtask.wherehouse.UserWherehouse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreatorTest {

    final static Logger LOGGER = LogManager.getLogger();
    private final String EXPECTED_USER_MAIL = "tareltos@mail.ru";

    @Test(priority = 1)
    public void userCreatorTest() {
        Creator.createUser();
        User user = UserWherehouse.getInstance().get(EXPECTED_USER_MAIL);
        LOGGER.log(Level.INFO, "TEST: userCreator, expected: " + EXPECTED_USER_MAIL + ", result: " + user.getMail());
        Assert.assertEquals( user.getMail(), EXPECTED_USER_MAIL);
    }

    @Test(priority = 1)
    public void userCreatorCountTest() {
        int expected = 1;
        int result;
        Creator.createUser();
        result = UserWherehouse.getInstance().size();
        LOGGER.log(Level.INFO, "TEST: userCreatorCount, expected: " + expected + ", result: " + result);
        Assert.assertEquals(expected, result);
    }

}
