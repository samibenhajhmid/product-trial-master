package com.alten.config;

import com.alten.domain.CartItem;
import com.alten.domain.Product;
import com.alten.domain.User;
import com.alten.domain.WishlistItem;
import com.alten.enums.InventoryStatus;
import com.alten.repository.CartItemRepository;
import com.alten.repository.ProductRepository;
import com.alten.repository.UserRepository;
import com.alten.repository.WishlistItemRepository;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
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
            CartItemRepository cartItemRepository,
            WishlistItemRepository wishlistItemRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            Faker faker = new Faker(new Locale("fr"));

            // Créer un admin par défaut
            if (userRepository.findByEmail("admin@admin.com").isEmpty()) {
                User admin = new User();
                admin.setEmail("admin@admin.com");
                admin.setUsername("admin");
                admin.setFirstname("Super Admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole("ADMIN");
                userRepository.save(admin);
            }

            //  Créer 5 utilisateurs
            for (int i = 0; i < 5; i++) {
                User user = new User();
                user.setEmail(faker.internet().emailAddress());
                user.setUsername(faker.name().username());
                user.setFirstname(faker.name().firstName());
                user.setPassword(passwordEncoder.encode("password")); // mot de passe commun
                user.setRole("USER");
                userRepository.save(user);
            }

            //  Créer 20 produits
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
                product.setRating(faker.number().randomDouble(1, 1, 5));
                product.setCreatedAt(System.currentTimeMillis());
                product.setUpdatedAt(System.currentTimeMillis());
                productRepository.save(product);
            }

            // Récupérer tous les utilisateurs et produits
            List<User> users = userRepository.findAll();
            List<Product> products = productRepository.findAll();

            //  Créer des cart items (panier) pour chaque utilisateur
            for (User user : users) {
                int cartItemsCount = random.nextInt(3) + 1; // entre 1 et 3 items

                for (int i = 0; i < cartItemsCount; i++) {
                    Product product = products.get(random.nextInt(products.size()));

                    CartItem cartItem = new CartItem();
                    cartItem.setUser(user);
                    cartItem.setProduct(product);
                    cartItem.setQuantity(random.nextInt(5) + 1); // quantité entre 1 et 5
                    cartItemRepository.save(cartItem);
                }
            }

            // Créer des wishlist items pour chaque utilisateur
            for (User user : users) {
                int wishlistItemsCount = random.nextInt(3) + 1; // entre 1 et 3 items

                for (int i = 0; i < wishlistItemsCount; i++) {
                    Product product = products.get(random.nextInt(products.size()));

                    WishlistItem wishlistItem = new WishlistItem();
                    wishlistItem.setUser(user);
                    wishlistItem.setProduct(product);
                    wishlistItemRepository.save(wishlistItem);
                }
            }

            System.out.println("✅ Données initialisées avec succès !");
        };
    }

    private InventoryStatus randomStatus() {
        InventoryStatus[] statuses = InventoryStatus.values();
        return statuses[random.nextInt(statuses.length)];
    }
}
