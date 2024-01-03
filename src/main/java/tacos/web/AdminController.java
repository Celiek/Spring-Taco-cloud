package tacos.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.data.service.OrderAdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private OrderAdminService adminSevice;

    public AdminController(OrderAdminService adminSevice){
        this.adminSevice = adminSevice;
    }

    @GetMapping
    public String showAdminPage(){
        return "admin";
    }

    @PostMapping("/deleteOrders")
    public String deleteAllOrders() {
        adminSevice.deleteAllOrders();
        return "redirect:/admin";
    }
}
