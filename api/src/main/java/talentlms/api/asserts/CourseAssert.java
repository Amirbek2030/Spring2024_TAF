package talentlms.api.asserts;

import talentlms.api.entity.BaseEntity;
import talentlms.api.entity.Course;

public class CourseAssert extends EntityAssert {
    public CourseAssert(BaseEntity actualBaseEntity) {
        super(actualBaseEntity);
    }
    public static CourseAssert assertThat(Course course) {
        return new CourseAssert(course);
    }
}
