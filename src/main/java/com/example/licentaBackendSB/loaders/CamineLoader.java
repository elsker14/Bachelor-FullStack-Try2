package com.example.licentaBackendSB.loaders;

import com.example.licentaBackendSB.entities.Camin;
import com.example.licentaBackendSB.repositories.CaminRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(3)
public class CamineLoader implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(CamineLoader.class);

    @Autowired
    private CaminRepository caminRepository;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Loading data from CamineLoader...");

        List <Camin> camineDB = Camin.hardcodedCamine;

        caminRepository.saveAll(camineDB);
    }
}
