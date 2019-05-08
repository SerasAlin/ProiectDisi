package com.ps.frontend.controller;

import com.ps.common.dto.BugDTO;
import com.ps.common.dto.UserDTO;
import com.ps.common.enumeration.UserRole;
import com.ps.frontend.controller.command.UserCommand;
import com.ps.frontend.gateway.BugGateway;
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
    private final BugGateway bugGateway;


    @Autowired
    public UserController(UserGateway userGateway , ContextHolder contextHolder,BugGateway bugGateway) {
        this.userGateway = userGateway;
        this.contextHolder=contextHolder;
        this.bugGateway= bugGateway;
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

    @GetMapping("/GuestList")
    public ModelAndView GuestList(ModelAndView mav) {
        List<BugDTO> all = bugGateway.findAll();

        mav.addObject("bugs", all);
        mav.setViewName("user/GuestList");
        return mav;
    }

    @GetMapping("/create")
    public ModelAndView openCreate(ModelAndView mav) {

        if(contextHolder.getLoggedIn().getRole().toString().equals("ADMINISTRATOR")) {
            mav.addObject("user", new UserDTO());
            mav.setViewName("user/create");
        }
        else
            mav.setViewName("user/error");
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
        if(contextHolder.getLoggedIn().getRole().toString().equals("ADMINISTRATOR")) {

            mav.addObject("user", user);
            mav.setViewName("user/edit");
        }else{
            mav.setViewName("user/error");
        }
        return mav;


    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable("id") String id,@RequestParam("username") String username,@RequestParam("password") String password ) {

        UserDTO user = userGateway.findById(Long.parseLong(id));

        user.setUsername(username);
        user.setPassword(password);

        userGateway.update(user.getId(), user);

        return "redirect:/user/list";

    }


    @GetMapping("/logOut")
    public ModelAndView logOut(ModelAndView mav){
        contextHolder.setLoggedIn(null);
        mav.setViewName("user/logIN");
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


    public UserDTO userCommandToUserDTO (UserCommand userCommand) {

        UserDTO userDTO=new UserDTO();
        userDTO.setUsername(userCommand.getUsername());
        userDTO.setPassword(userCommand.getPassword());

        return userDTO;
    }


}
