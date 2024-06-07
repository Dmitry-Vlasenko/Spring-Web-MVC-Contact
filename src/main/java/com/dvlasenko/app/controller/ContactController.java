package com.dvlasenko.app.controller;

import com.dvlasenko.app.entity.Contact;
import com.dvlasenko.app.service.impl.member.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        return "contact_list";
    }

    @GetMapping("/create-contact")
    public String createContact(Model model) {
        model.addAttribute("title", "Add Contact");
        return "contact_add";
    }

    @PostMapping("/add-contact")
    public String addContact(@ModelAttribute Contact contact, Model model) {
        contactService.create(contact);
        return "redirect:/contacts";
    }

    @GetMapping("/update-contact/{id}")
    public String updateContact(@PathVariable("id") Long id, Model model) {
        model.addAttribute("title", "Update Contact");
        Contact contact = contactService.fetchById(id);
        model.addAttribute("contact", contact);
        return "contact_update";
    }

    @PostMapping("/change-contact")
    public String changeContact(@ModelAttribute Contact contact, Model model) {
        contactService.update(contact.getId(), contact);
        return "redirect:/contacts";
    }

    @GetMapping("/delete-contact/{id}")
    public String deleteContact(@PathVariable("id") Long id, Model model) {
        contactService.delete(id);
        return "redirect:/contacts";
    }
}
