package com.dvlasenko.app.repository.impl.contact;

import com.dvlasenko.app.entity.Contact;
import com.dvlasenko.app.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends BaseRepository<Contact> {
    boolean create(Contact member);
    Optional<List<Contact>> fetchAll();
    Optional<Contact> fetchById(Long id);
    boolean update(Long id, Contact member);
    boolean delete(Long id);
}
