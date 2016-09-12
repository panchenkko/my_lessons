package ru.clinicPetWeb.store;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dispatcher-servlet.xml"})
public class StoragesTest {

    @Autowired
    private Storages storages;

    @Test
    public void initStorage() {
        assertNotNull(storages.memoryStorage);
        assertNotNull(storages.jdbcStorage);
        assertNotNull(storages.hibernateStorage);
        assertNotNull(storages.hibernateTemplate);
    }
}