package com.xdesign.service.api.utils;

import com.xdesign.service.api.model.MunroModel;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@DisplayName("Performing a Unit Test for CSV Util")
public class CSVUtilTest {

    @Nested
    @DisplayName("Valid ")
    class ValidTest {
        @Test
        @DisplayName("Case 1:  Check If Munro Data Has Loaded Successfully")
        @Tag("Validation")
        void testValidCase1() {
            MunroModel munroModel = CSVUtil.getMunroData();
            Assert.assertNotNull(munroModel);
            Assert.assertNotNull(munroModel.getItems());
            Assert.assertEquals(602, munroModel.getItems().size());
        }
    }

}
