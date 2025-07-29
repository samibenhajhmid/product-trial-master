package com.alten.config;

import com.alten.domain.Product;
import com.alten.domain.User;
import com.alten.enums.InventoryStatus;
import com.alten.repository.ProductRepository;
import com.alten.repository.UserRepository;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Locale;
import java.util.Random;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final Random random = new Random();

    @Bean
    public CommandLineRunner initData(
            UserRepository userRepository,
            ProductRepository productRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            Faker faker = new Faker(new Locale("fr"));

            // 🔐 Créer un admin par défaut
            if (userRepository.findByEmail("admin@admin.com").isEmpty()) {
                User admin = new User();
                admin.setEmail("admin@admin.com");
                admin.setUsername("admin");
                admin.setFirstname("Super Admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole("ADMIN");
                userRepository.save(admin);
            }

            // 👤 Créer 5 utilisateurs
            for (int i = 0; i < 5; i++) {
                User user = new User();
                user.setEmail(faker.internet().emailAddress());
                user.setUsername(faker.name().username());
                user.setFirstname(faker.name().firstName());
                user.setPassword(passwordEncoder.encode("password")); // mot de passe commun
                user.setRole("USER");
                userRepository.save(user);
            }

            // 📦 Créer 20 produits
            for (int i = 0; i < 20; i++) {
                Product product = new Product();
                product.setCode("PRD-" + faker.number().digits(6));
                product.setName(faker.commerce().productName());
                product.setDescription(faker.lorem().sentence());
                product.setImage("https://picsum.photos/200?random=" + i);
                product.setCategory(faker.commerce().department());
                product.setPrice(Double.parseDouble(faker.commerce().price(10.0, 500.0).replace(',', '.')));
                product.setQuantity(faker.number().numberBetween(0, 100));
                product.setInternalReference("REF-" + faker.number().digits(5));
                product.setShellId((long) faker.number().numberBetween(1, 10));
                product.setInventoryStatus(randomStatus());
                product.setRating(faker.number().randomDouble(1, 1, 5));
                product.setCreatedAt(System.currentTimeMillis());
                product.setUpdatedAt(System.currentTimeMillis());
                productRepository.save(product);
            }

            System.out.println("✅ Données initialisées avec succès !");
        };
    }

    private InventoryStatus randomStatus() {
        InventoryStatus[] statuses = InventoryStatus.values();
        return statuses[random.nextInt(statuses.length)];
    }
}
