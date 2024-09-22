package talentLMS.api_tests;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import talentlms.api.controllers.CourseController;
import talentlms.api.controllers.UserController;
import talentlms.api.entity.AddUserToCourse;
import talentlms.api.entity.Course;
import talentlms.api.entity.User;
import talentlms.api.entity.courses.DemoCourse;
import talentlms.api.utils.EntityManager;

public class CourseTest extends BaseApiTest {
    CourseController courseController;
    UserController userController;
    Course course;
    User user;

        @BeforeClass(alwaysRun = true)
        public void before() {
            userController = application.getUserController();
            courseController = application.getCourseController();
        }

        @Test
        public void getAllCourses() {
            courseController.getCourses();
        }

        @Test
        public void createCourse() {
            Course expectedCourse = EntityManager.generateCourse();
            course = courseController.creatCourse(expectedCourse);
            Assert.assertNotNull(course, "Курс не был создан!");
        }

        @Test
        public void createCourseWithCat4() {
            Course expectedCourse = EntityManager.generateCourseAndCategoryId("4");
            course = courseController.creatCourse(expectedCourse);
            Assert.assertNotNull(course, "Курс не был создан!");
        }

        @Test
        public void createUser() {
            User expectedUser = EntityManager.generateUser();
            user = userController.createUser(expectedUser);

            // Ensure user is created successfully before proceeding.
            Assert.assertNotNull(user, "Пользователь не был создан!");
        }

            @Test
        public void addUserToCourse() {
            createCourse(); // Убедимся, что курс создан
            createUser();   // Убедимся, что пользователь создан

            AddUserToCourse addUserToCourse = AddUserToCourse.builder()
                    .user_id(user.getId())
                    .course_id(course.getId())
                    .role("learner")
                    .build();

            courseController.addUserToCourse(addUserToCourse);
        }

        @Test
        public void addUserToCourseWithCategory4() {
            createCourseWithCat4(); // Убедимся, что курс создан
            createUser();           // Убедимся, что пользователь создан

            AddUserToCourse addUserToCourse = AddUserToCourse.builder()
                    .user_id(user.getId())
                    .course_id(course.getId())
                    .role("learner")
                    .build();

            AddUserToCourse[] actual = courseController.addUserToCourse(addUserToCourse);
            Assert.assertEquals(actual[0].getUser_id(), user.getId());
            Assert.assertEquals(actual[0].getCourse_id(), course.getId());
            Assert.assertEquals(actual[0].getRole(), addUserToCourse.getRole());
        }

        @Test
        public void getDemoCourse(){
            DemoCourse demoCourse = courseController.getDemoCourse("126");
            System.out.println(demoCourse.getUsers());
        }


        @AfterTest
        public void afterAddingUserToCourse() {
            if (user != null) {
                userController.deleteUser(user.getId());
            }
            if (course != null) {
                courseController.deleteCourse(Long.valueOf(course.getId()));
            }
        }
    }

