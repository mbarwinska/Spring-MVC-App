package pl.sda.intermediate.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.sda.intermediate.app.categories.CategoryDTO;
import pl.sda.intermediate.app.categories.CategoryService;
import pl.sda.intermediate.app.login.LoginDTO;
import pl.sda.intermediate.app.login.LoginService;
import pl.sda.intermediate.app.registration.RegistrationDTO;
import pl.sda.intermediate.app.registration.RegistrationService;
import pl.sda.intermediate.app.registration.RegistrationValidationService;
import pl.sda.intermediate.app.users.UserContextHolder;
import pl.sda.intermediate.app.users.UserDAO;
import pl.sda.intermediate.app.weather.WeatherService;
import pl.sda.intermediate.app.weather.WeatherWrapper;

import java.util.List;
import java.util.Map;

@Controller
public class OnlyOneController {
    private UserDAO userDAO = new UserDAO();
    private LoginService loginService = new LoginService(userDAO);
    private RegistrationService registrationService = new RegistrationService(userDAO);
    private RegistrationValidationService registrationValidationService = new RegistrationValidationService();
    private WeatherService weatherService = new WeatherService(userDAO);

    @RequestMapping("/categories")
    public String categoriesPage(@RequestParam(required = false) String input, Model model) {
        CategoryService categoryService = new CategoryService();
        List<CategoryDTO> categories = categoryService.findCategories(input);
        model.addAttribute("catsdata", categories);
        return "catspage";//to jest nazwa strony html jaka nam sie wyświetli
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm(Model model) {
        RegistrationDTO registrationDTO = new RegistrationDTO();//pusty obiekt na dane
        model.addAttribute("form", registrationDTO);
        model.addAttribute("countries", Countries.values());
        return "registerPage";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerEffect(RegistrationDTO registrationDTO, Model model) {
        Map<String, String> errorsMap = registrationValidationService.validate(registrationDTO);
        model.addAttribute("form", registrationDTO);
        if (errorsMap.isEmpty()) {
            RegistrationService registrationService = new RegistrationService(userDAO);
            return tryToRegisterUser(registrationDTO, model);
        }
        model.addAllAttributes(errorsMap);
        model.addAttribute("countries", Countries.values());
        return "registerPage";
    }

    private String tryToRegisterUser(RegistrationDTO registrationDTO, Model model) {
        try {
            registrationService.register(registrationDTO);
        } catch (Exception e) {
            model.addAttribute("countries", Countries.values());
            model.addAttribute("userExistsExceptionMessage", e.getMessage());
            return "registerPage";
        }
        return "helloPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(Model model) {
        LoginDTO loginDTO = new LoginDTO();
        model.addAttribute("form", loginDTO);
        return "loginPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginEffect(LoginDTO dto, Model model) {

        boolean ableToLogin = loginService.login(dto);
        if (ableToLogin) {
            UserContextHolder.addUserLogin(dto.getLogin());
            return "index";
        } else {
            model.addAttribute("form", dto);
            model.addAttribute("errorMessage", "Błąd logowania");
            return "loginPage";
        }
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutEffect(Model model) {
        model.addAttribute("form", new LoginDTO());
        model.addAttribute("logoutMessage", "Zostałeś wylogowany");
        UserContextHolder.logout();
        return"loginPage";
    }
    @ResponseBody //to powoduje że metoda zwróci JSONa
    @RequestMapping(value = "/weather", method = RequestMethod.GET)
    public WeatherWrapper weather(){
        return weatherService.getWeatherForCurrentUser();

    }
}



