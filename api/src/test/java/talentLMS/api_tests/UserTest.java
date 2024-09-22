package talentLMS.api_tests;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import talentlms.api.asserts.ApiAssert;
import talentlms.api.controllers.UserController;
import talentlms.api.entity.User;
import talentlms.api.utils.EntityManager;

import java.util.Arrays;

@Slf4j
public class UserTest extends BaseApiTest {
    UserController userController;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        userController = application.getUserController();

    }
        @Test
        public void getUsers() {
        User[] users = userController.getUsers();
            System.out.println(Arrays.toString(users));
            ApiAssert.assertThat(userController.getResponse())
                    .isCorrectStatusCode(200);
    }

    @Test
    public void getUserByEmailTest() {
        User user = userController.getUserBy(UserController.By.EMAIL, "amirbek.arynbaev@mail.ru"); // Вызов метода getUserBy с использованием By.EMAIL.
        Assert.assertNotNull(user); // Проверяем что метод действительно возвращает объект User (не null).
        Assert.assertEquals("amirbek.arynbaev@mail.ru", user.getEmail()); // Проверяяем что email пользователя соответствует ожидаемому значению.
    }

    @Test
    public void getUserByIdTest() {
        User user = userController.getUserBy(UserController.By.ID, "1");
        Assert.assertNotNull(user);
        Assert.assertEquals("1", user.getId());
    }
    @Test
    public void deleteAllUsersTest() {
        userController.deleteAllUsers();
        User [] users = userController.getUsers();
        Assert.assertEquals(users.length, 1);
        Assert.assertEquals(users[0].getId(), "1");
    }

    @Test
    public void userDelete() {
        User[] users = userController.getUsers();
        System.out.println(Arrays.toString(users));
        userController.deleteUser(users[0].getId());  // Здесь передаётся ID из массива
        ApiAssert.assertThat(userController.getResponse())
                .isCorrectStatusCode(200);
    }

    @Test
    public void createUserTest() {
        // Get the list of users
        User[] users = userController.getUsers();
        ApiAssert.assertThat(userController.getResponse())
                .isCorrectStatusCode(200);

        // If there are 5 users, delete the last one
        if (users.length == 5) {
            userController.deleteUser(users[users.length - 1].getId());
            ApiAssert.assertThat(userController.getResponse())
                    .isCorrectStatusCode(200);
        }

        // Generate and create a new user
        User expectedUser = EntityManager.generateUser();
        System.out.println("Generated User: " + expectedUser);
        User actualUser = userController.createUser(expectedUser);

        // Assert that the response is successful and the user data is correct
        ApiAssert.assertThat(userController.getResponse())
                .isCorrectStatusCode(200)
                .assertUser(actualUser)
                .isEqualTo(expectedUser);
        // No return is needed here for a test
    }

    @Test
    public void editUserTest() {
        User user = User.builder()
                .firstName("Lottie2")
                .lastName("Parker2")
                .id("12")
                .build();
        userController.editUser(user, user.getId());
        ApiAssert.assertThat(userController.getResponse())
                .isCorrectStatusCode(200);
    }
}