package fooddelivery.common;

import fooddelivery.CsApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { CsApplication.class })
public class CucumberSpingConfiguration {}
