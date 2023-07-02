package com.thanathip.training.backend;

import com.thanathip.training.backend.entity.Address;
import com.thanathip.training.backend.entity.Social;
import com.thanathip.training.backend.entity.User;
import com.thanathip.training.backend.exception.BaseException;
import com.thanathip.training.backend.service.AddressService;
import com.thanathip.training.backend.service.SocialService;
import com.thanathip.training.backend.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestUserService {

    @Autowired
    private UserService userService;

    @Autowired
    private SocialService socialService;

    @Autowired
    private AddressService addressService;

    @Order(1)
    @Test
    void TestCreate() throws BaseException {
        User user = userService.create(TestData.email, TestData.password, TestData.name);

        //check not null
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());

        //check equals
        Assertions.assertEquals(TestData.email, user.getEmail());
        Assertions.assertTrue(userService.mathPassword(TestData.password, user.getPassword()));
        Assertions.assertEquals(TestData.name, user.getName());
    }

    @Order(2)
    @Test
    void TestUpdate() throws BaseException {
        Optional<User> opt = userService.findByEmail(TestData.email);
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();


        User updatedUser = userService.updateName(user.getId(), TestUpdatable.name);

        Assertions.assertNotNull(updatedUser);
        Assertions.assertEquals(TestUpdatable.name, updatedUser.getName());


    }

    @Order(3)
    @Test
    void TestCreateSocail() throws BaseException {

        Optional<User> opt = userService.findByEmail(TestData.email);
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();

        Social social = user.getSocial();

        Assertions.assertNull(social);

        social = socialService.create(
                user,
                SocialTestData.facebook,
                SocialTestData.line,
                SocialTestData.instagram,
                SocialTestData.tiktok);

        Assertions.assertNotNull(social);

        Assertions.assertEquals(SocialTestData.facebook, social.getFacebook());
        Assertions.assertEquals(SocialTestData.line, social.getLine());
        Assertions.assertEquals(SocialTestData.instagram, social.getInstagram());
        Assertions.assertEquals(SocialTestData.tiktok, social.getTiktok());

    }

    @Order(4)
    @Test
    void TestCreateAddress() throws BaseException {

        Optional<User> opt = userService.findByEmail(TestData.email);
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();
        List<Address> addresses = user.getAddresses();
        Assertions.assertTrue(addresses.isEmpty());

        CreateAddress(user, AddressTestData.line1, AddressTestData.line2, AddressTestData.zipCode);
        CreateAddress(user,AddressTestData2.line1,AddressTestData2.line2,AddressTestData2.zipCode);
    }

    private void CreateAddress(User user, String line1, String line2, String zioCode) {
        Address address = addressService.create(
                user,
                line1,
                line2,
                zioCode);

        Assertions.assertNotNull(address);
        Assertions.assertEquals(line1, address.getLine1());
        Assertions.assertEquals(line2, address.getLine2());
        Assertions.assertEquals(zioCode, address.getZipCode());
    }


    @Order(5)
    @Test
    void TestDelete() throws BaseException {
        Optional<User> opt = userService.findByEmail(TestData.email);
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();

        //check social
        Social social = user.getSocial();
        Assertions.assertNotNull(social);
        Assertions.assertEquals(SocialTestData.facebook, social.getFacebook());

        //check address

        List<Address> addresses = user.getAddresses();
        Assertions.assertFalse(addresses.isEmpty());
        Assertions.assertEquals(2, addresses.size());


        userService.deleteById(user.getId());

        Optional<User> opt2 = userService.findByEmail(TestData.email);
        Assertions.assertTrue(opt2.isEmpty());

    }


    interface TestData {
        String email = "thanthiptest@test.com";
        String password = "TestPassword";

        String name = "Thanathip";
    }

    interface TestUpdatable {
        String email = "test@test.com";
        String password = "TestPassword";

        String name = "Thanathip";
    }

    interface SocialTestData {
        String facebook = "Thanathip Chanasri";
        String line = "chuwapp";
        String instagram = "oatwant";

        String tiktok = "SLotty";

    }

    interface AddressTestData {
        String line1 = "123/456";
        String line2 = "Line2";

        String zipCode = "12345";
    }

    interface AddressTestData2 {
        String line1 = "123/456789";
        String line2 = "Line21";

        String zipCode = "12323";
    }


}
