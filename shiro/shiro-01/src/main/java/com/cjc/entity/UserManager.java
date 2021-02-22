package com.cjc.entity;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2021/2/22
 * Time: 16:35
 * To change this template use File | Settings | File Templates.
 **/
public class UserManager {

    private static Map<String,User> users = new HashMap<>();

    static {
        users.put("cjc1316",new User("cjc1316", "cjc1316", new HashSet<String>(Arrays.asList("user:add"))));
        users.put("cjc1317",new User("cjc1317", "cjc1317", new HashSet<String>(Arrays.asList("user:update"))));
        users.put("root",new User("root", "root", new HashSet<String>(Arrays.asList("user:add","user:update"))));
    }

    public static User getUserByUsername(String username){

        return users.get(username);
    }



}
