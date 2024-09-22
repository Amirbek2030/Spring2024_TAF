package talentlms.api;

import talentlms.api.utils.ApiConfigReader;

public class TalentLMSEndpoints {
    public static final String URL = ApiConfigReader.getValue("url");
    public static final String API = "api";
    public static final String V1 = "v1";
    public static final String USERS = "users";
    public static final String USER_SIGNUP = "usersignup";
    public static final String EDIT_USER = "edituser";
    public static final String DELETE_USER = "deleteuser";
    public static final String COURSES = "courses";
    public static final String GET_ALL_COURSES = "get_all_courses";
    public static final String CREATE_COURSE = "createcourse";
    public static final String ADD_USER_TO_COURSE = "addusertocourse";
    public static final String CATEGORIES = "categories";
    public static final String DELETE_COURSE = "deletecourse";
}