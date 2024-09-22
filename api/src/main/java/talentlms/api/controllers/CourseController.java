package talentlms.api.controllers;

import com.google.gson.Gson;
import io.restassured.response.Response;
import lombok.Getter;
import talentlms.api.ApiRequest;
import talentlms.api.entity.AddUserToCourse;
import talentlms.api.entity.Course;
import talentlms.api.entity.courses.DemoCourse;

import java.util.HashMap;
import java.util.Map;

import static talentlms.api.TalentLMSEndpoints.*;

public class CourseController extends ApiRequest {


    public CourseController(String url) {
        super(url);
    }

    public Course[] getCourses() {

        return super.get(getEndpoint(API, V1, COURSES)).as(Course[].class);
    }


    public Course getCourseBy(By by,String value) {
        HashMap<String,String> parameters = new HashMap<>(){{
            put(by.getKey(),value);
        }};
        return super.get(getEndpoint(API,V1,COURSES
                ,formatParameter(parameters))).as(Course.class);
    }

    public DemoCourse getDemoCourse(String id) {
        HashMap<String,String> parameters = new HashMap<>(){{
            put("id", id);
        }};
        return super.get(getEndpoint(API,V1,COURSES ,formatParameter(parameters))).as(DemoCourse.class);
    }

    public Course creatCourse(Course course) {
        return super.post(getEndpoint(API, V1, CREATE_COURSE), course.toJson()).as(Course.class);
    }

    public AddUserToCourse[] addUserToCourse(AddUserToCourse user) {
        return super.post(getEndpoint(API, V1, ADD_USER_TO_COURSE), user.toJson()).as(AddUserToCourse[].class);
    }

    public Response deleteCourse(Long courseId) {
        Map<String, Object> params = new HashMap<>();
        params.put("course_id", courseId);
        params.put("deleted_by_user_id", 1);
        return post(getEndpoint(API, V1, DELETE_COURSE), new Gson().toJson(params));
    }

    @Getter
    public enum By{
        ID("id");
        public final String key;
        By (String key){this.key = key;}
    }

}