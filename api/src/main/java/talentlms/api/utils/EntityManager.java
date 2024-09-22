package talentlms.api.utils;


import com.github.javafaker.Faker;
import talentlms.api.entity.Course;
import talentlms.api.entity.User;

public class EntityManager {
    private static final Faker faker = new Faker();

    public static User generateUser() {
        faker.number().digits(3);
        return User.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .login(faker.name().username())
                .password(faker.internet().password(10, 15, true, true, true))
                .build();
    }

    public static Course generateCourse(){
        return Course.builder()
                .name(faker.educator().course())
                .description(faker.funnyName().name())
                .build();
    }

    public static Course generateCourseAndCategoryId(String id) {
        return Course.builder()
                .name(faker.educator().course())
                .description(faker.funnyName().name())
                .category_id(id) // Генерация случайной цены
                .build();
    }
}