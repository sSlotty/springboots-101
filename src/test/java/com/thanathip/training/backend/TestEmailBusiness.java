package com.thanathip.training.backend;

import com.thanathip.training.backend.business.EmailBusiness;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.class)
public class TestEmailBusiness {

    @Autowired
    private EmailBusiness emailBusiness;

    @Order(1)
    @Test
    void testSendActivateEmail() throws Exception {
        emailBusiness.sendActivateUserEmail(
                TestData.email,
                TestData.name,
                TestData.token
        );

    }


    interface TestData {
        String email = "piya.not@g.swu.ac.th";

        String name = "Kittawat Kittiparikun";

        String token = "gndfasdnvbkffajksdd";

    }

}
