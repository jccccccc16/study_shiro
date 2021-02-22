package com.cjc.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2021/2/22
 * Time: 13:12
 * To change this template use File | Settings | File Templates.
 **/
@Controller
public class MyController {

    private Logger log = LoggerFactory.getLogger(MyController.class);

    @RequestMapping({"/", "/index"})
    public String toIndex(Model model) {

        model.addAttribute("msg", "enjoy the knowledge");
        return "index";
    }

    @RequestMapping("/user/add")
    public String add(Model model) {

        return "user/add";
    }

    @RequestMapping("/user/update")
    public String update(Model model) {

        return "user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {

        return "login";
    }


    @RequestMapping("/login")
    public String login(
            Model model,
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            return "index";
        } catch (UnknownAccountException uae) {
            model.addAttribute("msg", "There is no user with username of " + token.getPrincipal());
            log.info("There is no user with username of " + token.getPrincipal());
            return "login";
        } catch (IncorrectCredentialsException ice) {
            model.addAttribute("msg", "Password for account " + token.getPrincipal() + " was incorrect!");
            log.info("Password for account " + token.getPrincipal() + " was incorrect!");
            return "login";
        } catch (LockedAccountException lae) {
            model.addAttribute("msg", "The account for username " + token.getPrincipal() + " is locked.  " +
                    "Please contact your administrator to unlock it.");
            log.info("The account for username " + token.getPrincipal() + " is locked.  " +
                    "Please contact your administrator to unlock it.");
            return "login";
        }
        // ... catch more exceptions here (maybe custom ones specific to your application?
        catch (AuthenticationException ae) {
            //unexpected condition?  error?
            ae.printStackTrace();
            return "login";
        }
    }

    @ResponseBody
    @RequestMapping("/unauthorized")
    public String unauthorized(){
        return "没有授权";
    }


}
