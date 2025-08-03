package com.spribe.test.tests;

import com.spribe.test.TestApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@SpringBootTest(classes = TestApplication.class)
public class BaseTest extends AbstractTestNGSpringContextTests {
    @Value("${base.url}")
    public String baseUrl;
}
