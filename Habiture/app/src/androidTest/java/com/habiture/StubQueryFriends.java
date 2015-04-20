package com.habiture;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yeh on 2015/4/14.
 */
public class StubQueryFriends implements NetworkInterface {

    @Override
    public boolean httpGetLoginResult(String account, String password) {
        return true;
    }

    @Override
    public List<Friend> httpGetFriends(String account, String password) {
        List<Friend> friends = new ArrayList<>();

        Friend amanda = new Friend();
        amanda.setId(1);
        amanda.setName("Amanda");

        friends.add(amanda);
        return friends;
    }

}