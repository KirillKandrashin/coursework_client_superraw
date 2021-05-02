package sample.models;

import sample.utils.ApiSessionUser;


public class UserModel {
    private ApiSessionUser apiSessionUser = new ApiSessionUser();

    public void add(String json) {
        apiSessionUser.createUser(json);
    }
}
