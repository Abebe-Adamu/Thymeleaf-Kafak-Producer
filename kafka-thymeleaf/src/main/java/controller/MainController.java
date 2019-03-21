package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import model.KafkaForm;
import model.Menu;
import model.MenuForm;

@Controller
public class MainController {
	@Autowired
	private KafkaTemplate<String,KafkaForm> KafkaTemplate;
	private static final String TOPIC="test";
    private static List<Menu> menus = new ArrayList<Menu>();
 
    static {
        menus.add(new Menu("Burger", "Coffee"));
        menus.add(new Menu("Salad", "SweetTea"));
    }
 
    // Inject via application.properties
    @Value("${welcome.message}")
    private String message;
 
    @Value("${error.message}")
    private String errorMessage;
 
    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
 
        model.addAttribute("message", message);
 
        return "index";
    }
 
    @RequestMapping(value = { "/menuList" }, method = RequestMethod.GET)
    public String menuList(Model model) {
 
        model.addAttribute("menus", menus);
 
        return "menuList";
    }
 
    @RequestMapping(value = { "/order" }, method = RequestMethod.GET)
    public String showOrder(Model model) {
 
        MenuForm menuForm = new MenuForm();
        model.addAttribute("menuForm", menuForm);
 
        return "order";
    }
 
    @RequestMapping(value = { "/order" }, method = RequestMethod.POST)
    public String saveMenu(Model model, //
            @ModelAttribute("menuForm") MenuForm menuForm) {
 
        String food = menuForm.getFood();
        String drink = menuForm.getDrink();
        KafkaTemplate.send(TOPIC,new KafkaForm(food,drink));
        if (food != null && food.length() > 0 //
                && drink != null && drink.length() > 0) {
            Menu newMenu = new Menu(food, drink);
            menus.add(newMenu);
            
            return "redirect:/menuList";
        }
 
        model.addAttribute("errorMessage", errorMessage);
        return "order";
    }
 
}