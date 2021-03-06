package my.examples.pouch.controller;

import my.examples.pouch.controller.api.CategoriesController;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {CategoriesController.class})
public class CategoriesControllerTest {
}
