package com.example.demo.web.controller;
import com.example.demo.service.EventService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
class CalendarController {

    private final EventService eventService;

    public CalendarController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(value="/calendar", method=RequestMethod.GET)
    public ModelAndView calendar() {
        return new ModelAndView("calendar");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/calendar-admin", method=RequestMethod.GET)
    public ModelAndView adminCalendar() {
        return new ModelAndView("calendar-admin");
    }

    @RequestMapping(value="/eventlist", method=RequestMethod.GET)
    public String events(HttpServletRequest request, Model model) {
        model.addAttribute("events", this.eventService.findAll());
        return "events";
    }
}
