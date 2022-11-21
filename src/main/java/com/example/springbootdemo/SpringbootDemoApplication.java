package com.example.springbootdemo;

import com.example.springbootdemo.entity.Staff;
import com.example.springbootdemo.repository.StaffRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication

public class SpringbootDemoApplication {

    public SpringbootDemoApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoApplication.class, args);
    }


}

/**
 * Create a class call @ValidateStaffService with @method boolean validate
 * which takes a string as input (uuid) and returns a boolean value.
 * <p>
 * Inject staff repository and call @method findStaffByUuid from repository,
 * then pass in the uuid.
 * <p>
 * If the method returns a staff -> return true else return false.
 * <p>
 * Inject @ValidateStaffService into the controllers and check validate method first in each method.
 * If validate returns true, execute the method else return invalid user and status code 401
 */

@Service
class CustomInstaller implements CommandLineRunner {

    private final StaffRepository staffRepository;

    CustomInstaller(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Long count = staffRepository.count();
        if (count > 0) {
            return;
        }

        List<String> names = List.of("Paul", "Mike", "Smith", "John", "Cole");

        for (int i = 0; i < names.size(); i++) {
            staffRepository.save(Staff.builder()
                    .name(names.get(i))
                    .uuid(UUID.randomUUID().toString())
                    .registrationDate(new Timestamp(System.currentTimeMillis()))
                    .build());
        }
    }
}