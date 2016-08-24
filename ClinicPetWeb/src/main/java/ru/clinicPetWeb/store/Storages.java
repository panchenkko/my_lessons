package ru.clinicPetWeb.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Аннотация Service, говорит о том что этот класс будет являться сервисом.
@Service
public class Storages {
    public final MemoryStorage memoryStorage;

    public final JdbcStorage jdbcStorage;

    public final HibernateStorage hibernateStorage;

    @Autowired
    public Storages(final MemoryStorage memoryStorage,
                    final JdbcStorage jdbcStorage,
                    final HibernateStorage hibernateStorage) {

        this.memoryStorage = memoryStorage;
        this.jdbcStorage = jdbcStorage;
        this.hibernateStorage = hibernateStorage;
    }
}
