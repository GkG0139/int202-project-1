package sit.int202.classicmodels.utils;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EnvironmentUtils {

    private static final Dotenv dotenv = Dotenv
        .configure().ignoreIfMalformed().ignoreIfMissing()
        .load();

    public static String getDatabaseUsername() {
        return dotenv.get("DB_USER");
    }

    public static String getDatabasePassword() {
        return dotenv.get("DB_PASSWORD");
    }
}
