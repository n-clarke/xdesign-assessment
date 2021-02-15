package com.xdesign.service.api.controller;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@DisplayName("Performing a Unit Test for Controller")
public class ControllerTest {

    @Nested
    @DisplayName("When Class Is Instantiated")
    class WhenClassIsInstantiated {

        @Nested
        @DisplayName("Is Valid")
        class ValidTest {

            @Test
            @DisplayName("Class Should Not Be Null")
            @Tag("Validation")
            void thenClassShouldNotBeNull() {
                final Controller controller = new Controller();
                Assert.assertNotNull(controller);
            }
        }
    }

}
