package nb_wallet_api.asserts;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import nb_wallet_api.entity.BaseEntity;

@Slf4j
public abstract class EntityAssert {
    BaseEntity actualBaseEntity;

    public EntityAssert(BaseEntity actualBaseEntity) {
        this.actualBaseEntity = actualBaseEntity;
    }

    public void isEqualTo(BaseEntity expectedEntity) {
        Assertions.assertThat(actualBaseEntity)
                .usingRecursiveComparison()
                .ignoringActualNullFields()
                .ignoringExpectedNullFields()
                .isEqualTo(expectedEntity);
        log.info("Actual entity is equal to expected, \nActual: {},\nExpected: {}"
                , actualBaseEntity, expectedEntity);
    }
}