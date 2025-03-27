package ru.kata.spring.boot_security.demo.controller;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class NewErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.FORBIDDEN.value()) {
                model.addAttribute("message", "Доступ запрещён.");
            } else if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("message", "Запрашиваемая страница не найдена.");
            } else {
                model.addAttribute("message", "Произошла непредвиденная ошибка.");
            }
        }

        return "error";
    }
}