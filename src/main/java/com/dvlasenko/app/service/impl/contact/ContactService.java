package com.dvlasenko.app.service.impl.contact;

import com.dvlasenko.app.entity.Contact;
import com.dvlasenko.app.service.BaseService;

import java.util.List;

public interface ContactService extends BaseService<Contact> {
    boolean create(Contact member);
    List<Contact> fetchAll();
    Contact fetchById(Long id);
    boolean update(Long id, Contact member);
    boolean delete(Long id);
}
