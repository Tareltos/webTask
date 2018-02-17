package by.tareltos.webtask.wherehouse;

import by.tareltos.webtask.entity.User;
import by.tareltos.webtask.exception.WebAppException;

import java.util.HashMap;
import java.util.Map;

public class UserWherehouse {
    private static Map<String, User> usersMap;

    private UserWherehouse() {

    }

    public static Map<String, User> getInstance() {
        return usersMap = usersMap == null ? new HashMap<>() : usersMap;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {

        try {
            throw new WebAppException("Not allowed to clone this object");
        } catch (WebAppException e) {
            e.printStackTrace();
        }

        return null;
    }

}
