package by.tareltos.webtask.util;

import by.tareltos.webtask.entity.User;
import by.tareltos.webtask.wherehouse.UserWherehouse;

public class Creator {

    public static void createUser(){

        UserWherehouse.getInstance().put("tareltos@mail.ru", new User("tareltos@mail.ru", "Vitali", "Tarelko", "123"));
    }
}
