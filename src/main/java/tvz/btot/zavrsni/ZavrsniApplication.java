package tvz.btot.zavrsni;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static tvz.btot.zavrsni.infrastructure.utils.Constants.PASSWORD_ENCODER;

@SpringBootApplication
public class ZavrsniApplication {

    public static void main(String[] args) {
        System.out.println("superadmin:" + PASSWORD_ENCODER.encode("superadmin"));
        System.out.println("admin:" + PASSWORD_ENCODER.encode("admin"));
        System.out.println("teacher:" + PASSWORD_ENCODER.encode("teacher"));
        System.out.println("student:" + PASSWORD_ENCODER.encode("student"));
        SpringApplication.run(ZavrsniApplication.class, args);
    }

}
