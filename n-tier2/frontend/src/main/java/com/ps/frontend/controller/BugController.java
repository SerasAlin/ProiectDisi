package com.ps.frontend.controller;


import com.ps.common.dto.BugDTO;
import com.ps.common.dto.EmailIDTO;
import com.ps.common.dto.NameIdDTO;
import com.ps.common.dto.UserDTO;
import com.ps.frontend.controller.command.BugCommand;
import com.ps.frontend.gateway.*;
import com.ps.frontend.log.ContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bug")
public class BugController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BugController.class);
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

    @GetMapping("/{id}/edit")
    public ModelAndView openEdit(@PathVariable("id") Long id, ModelAndView mav) {

//        if(contextHolder.getLoggedIn() == null){
//            mav.setViewName("user/error");
//        }
//        else {
            BugDTO bug = bugGateway.findById(id);

            mav.addObject("bug", bug);
            mav.setViewName("bug/edit");
       // }
        return mav;

    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable("id") String id, @RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("status") String status ) {

        BugDTO bug = bugGateway.findById(Long.parseLong(id));

        bug.setName(name);
        bug.setDescription(description);
        bug.setStatus(status);

        bugGateway.update(bug.getId(), bug);

        return "redirect:/bug/list";

    }

    @GetMapping("/list")
    public ModelAndView list(ModelAndView mav) {
        List<BugDTO> all = bugGateway.findAll();

        mav.addObject("bugs", all);
        mav.setViewName("bug/list");
        return mav;
    }

    @GetMapping("/find")
    public ModelAndView findByName(@RequestParam("name") String name,ModelAndView mav) {
        List<BugDTO> all = bugGateway.findAll();

        List<BugDTO> listByName = new ArrayList<>() ;
        List<BugDTO> listByStatus = new ArrayList<>() ;

        for(BugDTO bug: all){
            if(bug.getName().equals(name)){
                listByName.add(bug);
            }
        }

        for(BugDTO bug: all){
            if(bug.getStatus().equals(name)){
                listByStatus.add(bug);
            }
        }
        if(!(listByName.isEmpty())) {
            LOGGER.info("LISTA DE BUGURI GASITA ::::::::::" + listByName);
            mav.addObject("bugs", listByName);
            mav.setViewName("bug/list");
        } else if(!(listByStatus.isEmpty())) {
            LOGGER.info("LISTA DE BUGURI GASITA ::::::::::" + listByStatus);
            mav.addObject("bugs", listByStatus);
            mav.setViewName("bug/list");
        } else {
            mav.addObject("bugs", all);
            mav.setViewName("bug/list");
        }



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

    @PostMapping("/save")
    public String save( BugCommand bugCommand) {
        bugCommand.setUser(contextHolder.getLoggedIn().getId());
        bugCommand.setEmail(contextHolder.getLoggedIn().getId());

        bugGateway.save(bugCommandToBugDTO(bugCommand));
        return "redirect:/bug/list" ;
    }

    private BugDTO bugCommandToBugDTO(BugCommand bugCommand) {
        BugDTO bugDTO = new BugDTO();
        bugDTO.setId(bugCommand.getId());
        bugDTO.setName(bugCommand.getName());
        bugDTO.setDescription(bugCommand.getDescription());
        bugDTO.setStatus(bugCommand.getStatus());

        NameIdDTO user = new NameIdDTO();
        user.setId(bugCommand.getUser());
//        user.setName(bugGateway.findById(bugCommand.getUser()).getName());
        bugDTO.setUser(user);



        EmailIDTO emailIDTO = new EmailIDTO();
        emailIDTO.setId(bugCommand.getEmail());
        //  emailIDTO.setName(emailGateway.findById(bugCommand.getEmail()).getName());
        bugDTO.setEmail(emailIDTO);

        //   orderDTO.setTotal((pizza.getPrice()+drink.getPrice()) - voucher.getPrice());


        return bugDTO;
    }


}