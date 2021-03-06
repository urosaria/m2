package jungjin.user.controller;

import jungjin.user.domain.User;
import jungjin.user.service.SecurityService;
import jungjin.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;


    @RequestMapping(value="/register", method= RequestMethod.GET)
    public String userRegister(Model model){

        model.addAttribute("userForm", new User());
        return "/user/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String userInsert(@ModelAttribute("userForm") User userForm, BindingResult bindingResult,
                             Model model , String[] roles) {

        userService.saveUser(userForm, roles);
        return "redirect:/user/list";
    }

    @RequestMapping(value="/modify/{num}", method=RequestMethod.GET)
    public String userModify(Model model, @PathVariable Long num){

        User user=userService.showUser(num);
        model.addAttribute("user", user);

        return "/user/modify";
    }

    @RequestMapping(value = "/modify/{num}", method = RequestMethod.POST)
    public String update(@PathVariable Long num, User user) {

        userService.updateUser(num, user);

        return "redirect:/user/list";
    }

    @GetMapping(value="/remove/{num}")
    @ResponseBody
    public String userRemove(@PathVariable Long num){

        User user = userService.deleteUser(num);
        String success="success";

        if(user==null){
            success="fail";
        }

        return success;
    }

    @GetMapping(value="/findId")
    @ResponseBody
    public String findId(@RequestParam String id){

        User user = new User();
        //User user = userService.findById(id);
        String success="success";

        if(user!=null){
            success="fail";
        }

        return success;
    }
}
