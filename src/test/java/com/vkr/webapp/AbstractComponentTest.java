package com.vkr.webapp;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
это аннотация уровня класса, которая используется для объявления того,
какие профили определения активного компонента следует использовать
при загрузке ApplicationContext тестовых классов for.
На входе принимает файл профиль
 */
@ActiveProfiles("localtest")

/**
это повторяемая аннотация, которая используется для регистрации расширений для аннотированного
тестового класса или тестового метода.
 */
@ExtendWith(SpringExtension.class)

/**
подключает все бины, которые ниже по иерархии по пакету
 */
@ComponentScan(lazyInit = true)
public abstract class AbstractComponentTest {
}
