package com.ps.frontend.controller;


import com.ps.common.dto.EmailIDTO;
import com.ps.common.dto.UserDTO;
import com.ps.frontend.controller.command.EmailCommand;
import com.ps.frontend.gateway.EmailGateway;
import com.ps.frontend.gateway.UserGateway;
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
@RequestMapping("/email")
public class EmailController {

    private final EmailGateway emailGateway;
    private final UserGateway userGateway;
    private final ContextHolder contextHolder;

    @Autowired
    public EmailController(EmailGateway emailGateway,
                             UserGateway userGateway,
                             ContextHolder contextHolder) {
        this.emailGateway = emailGateway;
        this.userGateway = userGateway;
        this.contextHolder=contextHolder;
    }

    @GetMapping("/{id}")
    public ModelAndView details(@PathVariable("id") Long id, ModelAndView mav) {
        EmailIDTO email =emailGateway.findById(id);

        mav.addObject("email", email);
        mav.setViewName("email/details");
        return mav;
    }

    @GetMapping("/list")
    public ModelAndView list(ModelAndView mav) {
        List<EmailIDTO> all = emailGateway.findAll();
        List<UserDTO> users=userGateway.findAll();

        mav.addObject("emails", all);
        mav.addObject("users",users);
        mav.setViewName("email/list");
        return mav;
    }

    @GetMapping("/create")
    public ModelAndView openCreate(ModelAndView mav) {
        if(contextHolder.getLoggedIn()!=null) {
            mav.addObject("email", new EmailIDTO());
            mav.setViewName("email/create");
        }
        else
            mav.setViewName("user/logIN");
        return mav;
    }

    @PostMapping("/create")
    public String create(EmailIDTO addressDTO) {

        emailGateway.save(addressDTO);
        return "redirect:/email/list";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView getEdit(@PathVariable("id") Long id, ModelAndView mav) {
        // List<UserDTO> users = userGateway.findAll();
        EmailIDTO emailIDTO = emailGateway.findById(id);
        // CategCommand categCommand = categDTOToCategCommand(categDTO);

        mav.addObject("email", emailIDTO);
        mav.setViewName("email/create");
        return mav;
    }

    @PostMapping("/save")
    public String save(ModelAndView mav, EmailCommand addressCommand) {
        emailGateway.save(emailCommandToAddressDTO(addressCommand));
        return "redirect:/email/list" ;
    }

    private EmailIDTO emailCommandToAddressDTO(EmailCommand emailCommand) {
        EmailIDTO emailIDTO = new EmailIDTO();
        emailIDTO.setId(emailCommand.getId());
        emailIDTO.setName(emailCommand.getName());


        return emailIDTO;
    }


}
