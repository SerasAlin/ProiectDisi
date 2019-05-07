package com.ps.frontend.controller;


import com.ps.common.dto.BugDTO;
import com.ps.common.dto.EmailIDTO;
import com.ps.common.dto.NameIdDTO;
import com.ps.common.dto.UserDTO;
import com.ps.frontend.controller.command.BugCommand;
import com.ps.frontend.gateway.*;
import com.ps.frontend.log.ContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/bug")
public class BugController {

    private final BugGateway bugGateway;
    private final EmailGateway emailGateway;
    private final UserGateway userGateway;
    private final ContextHolder contextHolder;

    @Autowired
    public BugController(BugGateway bugGateway,
                           EmailGateway emailGateway,
                           UserGateway userGateway,
                           ContextHolder contextHolder) {
        this.bugGateway = bugGateway;
        this.emailGateway = emailGateway;
        this.userGateway = userGateway;
        this.contextHolder=contextHolder;
    }

    @GetMapping("/{id}")
    public ModelAndView details(@PathVariable("id") Long id, ModelAndView mav) {
        BugDTO bug = bugGateway.findById(id);

        mav.addObject("bug", bug);
        mav.setViewName("bug/details");
        return mav;
    }



    @GetMapping("/list")
    public ModelAndView list(ModelAndView mav) {
        List<BugDTO> all = bugGateway.findAll();
        List<UserDTO> users=userGateway.findAll();

        mav.addObject("bug", all);
        mav.addObject("users",users);
        mav.setViewName("bug/list");
        return mav;
    }



    @GetMapping("/create")
    public ModelAndView openCreate(ModelAndView mav) {
        if(contextHolder.getLoggedIn()!=null) {
            mav.addObject("bug", new BugDTO());
            List<UserDTO> users = userGateway.findAll();


            mav.addObject("users", users);

            mav.setViewName("bug/create");
        }
        else
            mav.setViewName("user/logIN");
        return mav;
    }

    @PostMapping("/create")
    public String create(BugDTO bugDTO) {

        bugGateway.save(bugDTO);
        return "redirect:/bug/list";
    }

   /* @GetMapping("/{id}/edit")
    public ModelAndView getEdit(@PathVariable("id") Long id, ModelAndView mav) {
        // List<UserDTO> users = userGateway.findAll();
        DrinkDTO drinkDTO = drinkGateway.findById(id);
        // CategCommand categCommand = categDTOToCategCommand(categDTO);

        mav.addObject("drink", drinkDTO);
        mav.setViewName("drink/create");
        return mav;
    }*/

    @PostMapping("/save")
    public String save(ModelAndView mav, BugCommand bugCommand) {
        bugGateway.save(bugCommandToBugDTO(bugCommand));
        return "redirect:/bug/list" ;
    }

    private BugDTO bugCommandToBugDTO(BugCommand bugCommand) {
        BugDTO bugDTO = new BugDTO();
        bugDTO.setId(bugCommand.getId());
        bugDTO.setName(bugCommand.getName());
        bugDTO.setDescription(bugCommand.getDescription());
        bugDTO.setStatus(bugCommand.getStatus());


        //NameIdDTO categ = new NameIdDTO();
        //categ.setId(pizzaCommand.getCateg());
        //categ.setName(categGateway.findById(pizzaCommand.getCateg()).getNume());
        // pizzaDTO.setCateg(categ);



        NameIdDTO user = new NameIdDTO();
        user.setId(bugCommand.getUser());
        user.setName(bugGateway.findById(bugCommand.getUser()).getName());
        bugDTO.setUser(user);



        EmailIDTO emailIDTO = new EmailIDTO();
        emailIDTO.setId(bugCommand.getEmail());
        emailIDTO.setName(emailGateway.findById(bugCommand.getEmail()).getName());
        bugDTO.setEmail(emailIDTO);

        //   orderDTO.setTotal((pizza.getPrice()+drink.getPrice()) - voucher.getPrice());


        return bugDTO;
    }


}
