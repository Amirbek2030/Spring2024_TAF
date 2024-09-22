package talentlms.api.controllers;

import com.google.gson.Gson;
import lombok.Getter;
import org.testng.Assert;
import talentlms.api.ApiRequest;
import talentlms.api.asserts.ApiAssert;
import talentlms.api.entity.User;
import talentlms.api.enums.ResponseValidationMessage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static talentlms.api.TalentLMSEndpoints.*;



public class UserController extends ApiRequest {
    public UserController(String url) {
        super(url);
    }

    public User[] getUsers() {
        return super.get(getEndpoint(API, V1, USERS)).as(User[].class);
    }

    public User getUserBy(By by, String value) {
        HashMap<String, String> parameters = new HashMap<>() {{
            put(by.getKey(), value);
        }};
        return super.get(getEndpoint(API, V1, USERS
                , formatParameter(parameters))).as(User.class);
    }

    public User createUser(User user) {
        return super.post(getEndpoint(API, V1, USER_SIGNUP), user.toJson()).as(User.class);
    }

    public void deleteAllUsers() {
        List<User> users = Arrays.asList(getUsers());
        for(User user : users) {
            if (!user.getId().equals("1")) {
                deleteUser(user.getId());
            }
        }
    }
    public void editUser(User user, String user_id) {
        Map<String, String> params = new HashMap<>() {{
            put("first_name", user.getFirstName());
            put("last_name", user.getLastName());
            put("user_id", "6");
        }};
        super.post(getEndpoint(API, V1, EDIT_USER), params.toString());
    }

    public void deleteUser(String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("user_id", userId);
        params.put("deleted_by_user_id", 1);
        String jsonBody = new Gson().toJson(params);

        // Важно: убедись, что передаешь jsonBody вместо params.toString()
        String validationMessage = super.post(getEndpoint(API, V1, DELETE_USER), jsonBody)
                .jsonPath().getString("message");

        Assert.assertEquals(validationMessage, ResponseValidationMessage.SUCCESS_OPERATION_USER.getMessage());
    }


    @Getter
    public enum By {
        ID("id"),
        USERNAME("username"),
        EMAIL("email");
        public final String key;

        By(String key) {
            this.key = key;
        }
    }
}