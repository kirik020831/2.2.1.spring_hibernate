package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru",
                new Car("Nissan Skyline GT-R R34", 30112013)));

        userService.add(new User("User2", "Lastname2", "user2@mail.ru",
                new Car("MAZDA RX-7", 13061986)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru",
                new Car("Mitsubishi Eclipse III", 20091999)));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru",
                new Car("Toyota Supra", 8041974)));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Model Car = " + user.getCar().getModel());
            System.out.println("Model Car = " + user.getCar().getSeries());
            System.out.println();
        }

        List<User> getUser = userService.getUserByCar("Toyota Supra", 8041974);
        System.out.println(getUser);
        context.close();
    }
}
