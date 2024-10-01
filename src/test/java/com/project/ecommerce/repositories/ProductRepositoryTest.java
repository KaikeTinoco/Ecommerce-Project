package com.project.ecommerce.repositories;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@RunWith(SpringRunner.class)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void init() {

    }

    @Test
    void findByProductClassification() {
    }
}