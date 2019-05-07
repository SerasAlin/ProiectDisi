package com.ps.frontend.controller;

import com.ps.common.dto.UserDTO;
import com.ps.common.enumeration.UserRole;
import com.ps.frontend.controller.command.UserCommand;
import com.ps.frontend.gateway.UserGateway;
import com.ps.frontend.log.ContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserGateway userGateway;
    private final ContextHolder contextHolder;

    @Autowired
    public UserController(UserGateway userGateway , ContextHolder contextHolder) {
        this.userGateway = userGateway;
        this.contextHolder=contextHolder;
    }

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public void getTest() {
        String test = userGateway.test();
        System.out.println(test);
    }

    @GetMapping("/{id}")
    public ModelAndView details(@PathVariable("id") Long id, ModelAndView mav) {
        UserDTO user = userGateway.findById(id);

        mav.addObject("user", user);
        mav.setViewName("user/details");
        return mav;
    }

    @GetMapping("/list")
    public ModelAndView list(ModelAndView mav) {
        List<UserDTO> all = userGateway.findAll();

        mav.addObject("users", all);
        mav.setViewName("user/list");
        return mav;
    }

    @GetMapping("/create")
    public ModelAndView openCreate(ModelAndView mav) {
        mav.addObject("user", new UserDTO());
        mav.setViewName("user/create");
        return mav;
    }

    @PostMapping("/create")
    public String create(UserDTO userDTO) {

       Long u= userGateway.save(userDTO);
//        return "redirect:/user/list";

        if (u==null)
        {
            System.out.println("cacatu merge");
            return "redirect:/user/create";
        }
        else {
            System.out.println("cacatu nuuuu merge");

            return "redirect:/user/list";
        }
    }


    @GetMapping("/{id}/edit")
    public ModelAndView openEdit(@PathVariable("id") Long id, ModelAndView mav) {
        UserDTO user = userGateway.findById(id);

        mav.addObject("user", user);
        mav.setViewName("user/edit");
        return mav;

    }




    @GetMapping("/logIN")
    public ModelAndView openLog(ModelAndView mav){
        mav.setViewName("user/logIN");
        return mav;
    }

    @PostMapping("/logIN")
    public ModelAndView log(@RequestParam("username") String username, @RequestParam("password") String password, ModelAndView mav){

        UserDTO userDTO=userGateway.logIN(username,password);

        contextHolder.setLoggedIn(userDTO);

        String s="";

        if(userDTO==null)
        {

            s="redirect:/user/logIN";
        }
        else if(userDTO.getRole().equals(UserRole.ADMINISTRATOR))
        {

            s="redirect:/user/list";
        }
        else if (userDTO.getRole().equals(UserRole.USER))
        {

            s="redirect:/user/list";
        }

        return new ModelAndView(s);

    }


    @PostMapping("/save")
    public String save(ModelAndView mav, UserCommand userCommand) {
       Long u= userGateway.save(userCommandToUserDTO (userCommand));
        if (u.equals(null))
         {
             System.out.println("cacatu merge");
             return "redirect:/user/create";
        }
        else {
            System.out.println("cacatu nuuuu merge");

            return "redirect:/user/list";
        }
    }



    public UserDTO userCommandToUserDTO (UserCommand userCommand) {

        UserDTO userDTO=new UserDTO();
        userDTO.setUsername(userCommand.getUsername());
        userDTO.setPassword(userCommand.getPassword());

        return userDTO;
    }

}
