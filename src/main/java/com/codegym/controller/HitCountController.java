package com.codegym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HitCountController {
    @GetMapping("/")
    public String index(@CookieValue(value = "hitCounter", defaultValue = "0") Long hitCounter,
                        HttpServletResponse response, HttpServletRequest request, Model model) {
        hitCounter++;
        Cookie cookie = new Cookie("hitCounter", hitCounter.toString());
        response.addCookie(cookie);
        Cookie[] cookies = request.getCookies();
        for (Cookie ck : cookies) {
            if (ck.getName().equals("hitCounter")){
                model.addAttribute("cookieValue", ck);
                break;
            }
        }
        return "index";
    }
}
