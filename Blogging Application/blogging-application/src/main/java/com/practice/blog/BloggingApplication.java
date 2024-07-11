package com.practice.blog;

import com.practice.blog.entity.Category;
import com.practice.blog.entity.Role;
import com.practice.blog.repository.CategoryRepository;
import com.practice.blog.repository.RoleRepository;
import com.practice.blog.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class BloggingApplication implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public static void main(String[] args) {
        SpringApplication.run(BloggingApplication.class, args);
    }

    @Override
    public void run(String... args) {
        List<Role> roles = getAllRoles();
        roleRepository.saveAll(roles);

//        List<Category> categories = getAllCategories();
//        categoryRepository.saveAll(categories);
    }

    private List<Role> getAllRoles() {
        Role role1 = new Role();
        role1.setId(AppConstants.ADMIN_ROLE_ID);
        role1.setName(AppConstants.ADMIN_ROLE_NAME);

        Role role2 = new Role();
        role2.setId(AppConstants.NORMAL_ROLE_ID);
        role2.setName(AppConstants.NORMAL_ROLE_NAME);

        return List.of(role1, role2);
    }

    private List<Category> getAllCategories() {
        Category category1 = new Category();
		category1.setId(UUID.randomUUID().toString());
        category1.setCategoryTitle("Programming");
        category1.setCategoryDescription("Posts related to programming");

        Category category2 = new Category();
		category2.setId(UUID.randomUUID().toString());
        category2.setCategoryTitle("Science");
        category2.setCategoryDescription("Posts related to science");

        Category category3 = new Category();
		category3.setId(UUID.randomUUID().toString());
        category3.setCategoryTitle("Bollywood");
        category3.setCategoryDescription("Posts related to bollywood");

        Category category4 = new Category();
		category4.setId(UUID.randomUUID().toString());
        category4.setCategoryTitle("Sports");
        category4.setCategoryDescription("Posts related to sports");

		Category category5 = new Category();
		category5.setId(UUID.randomUUID().toString());
		category5.setCategoryTitle("Others");
		category5.setCategoryDescription("Posts related to miscellaneous");

        return List.of(category1, category2, category3, category4, category5);
    }
}
