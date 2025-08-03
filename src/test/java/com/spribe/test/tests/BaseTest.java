package com.spribe.test.tests;

import com.spribe.test.TestApplication;
import com.spribe.test.rest.services.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@SpringBootTest(classes = TestApplication.class)
public class BaseTest extends AbstractTestNGSpringContextTests {
    @Autowired
    public PlayerService playerService;
}
