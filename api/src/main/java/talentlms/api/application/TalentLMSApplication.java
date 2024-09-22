package talentlms.api.application;

import lombok.Data;
import talentlms.api.controllers.CourseController;
import talentlms.api.controllers.UserController;


@Data
public class TalentLMSApplication {
    private UserController userController;
    private CourseController courseController;

    public TalentLMSApplication(String url) {
        this.userController = new UserController(url);
        this.courseController = new CourseController(url);
    }
}