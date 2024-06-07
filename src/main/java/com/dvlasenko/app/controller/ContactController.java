package com.dvlasenko.app.controller;


import com.dvlasenko.app.entity.Contact;
import com.dvlasenko.app.service.impl.contact.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/contacts")
    public String fetchAllContacts(Model model) {
        List<Contact> list = contactService.fetchAll();
        model.addAttribute("title", "Contacts");
        model.addAttribute("contacts", list);
        model.addAttribute("fragmentName", "contact_list");
        return "layout";
    }

    @RequestMapping("/create-contact")
    public String createContact(Model model) {
        model.addAttribute("title", "Add Contact");
        model.addAttribute("fragmentName", "contact_add");
        return "layout";
    }

    @RequestMapping(value = "/add-contact", method = RequestMethod.POST)
    public RedirectView addContact(@ModelAttribute Contact contact) {
        RedirectView redirectView = new RedirectView("/contacts");
        if (contactService.create(contact))
            return redirectView;
        else return redirectView;
    }

    @RequestMapping("/update-contact/{id}")
    public String updateContact(@PathVariable("id") Long id, Model model) {
        model.addAttribute("title", "Update Contact");
        Contact contact = contactService.fetchById(id);
        model.addAttribute("contact", contact);
        model.addAttribute("fragmentName", "contact_update");
        return "layout";
    }

    @RequestMapping(value = "/change-contact", method = RequestMethod.POST)
    public RedirectView changeContact(@ModelAttribute Contact contact) {
        RedirectView redirectView = new RedirectView("/contacts");
        if (contactService.update(contact.getId(), contact))
            return redirectView;
        else return redirectView;
    }

    @RequestMapping("/delete-contact/{id}")
    public RedirectView deleteContact(@PathVariable("id") Long id) {
        RedirectView redirectView = new RedirectView("/contacts");
        if (contactService.delete(id)) return redirectView;
        else return redirectView;
    }
}
