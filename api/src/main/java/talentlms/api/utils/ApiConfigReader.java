package talentlms.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApiConfigReader {

    private static Properties properties;

    static {
        try {
            InputStream inputStream = ApiConfigReader.class.getClassLoader().getResourceAsStream("api.properties");
            if (inputStream != null) {
                properties = new Properties();
                properties.load(inputStream);
            } else {
                System.out.println("ресурс не найден");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {
        return properties.getProperty(key).trim();
    }

    public static void main(String[] args) {
        System.out.println(getValue("base.url"));
    }
}

//            static {
//                try{
//            String path = "src/main/resources/api.properties";
//            FileInputStream fileInputStream = new FileInputStream(path);
//            properties = new Properties();
//            properties.load(fileInputStream);
//            fileInputStream.close();
//        } catch (IOException e) {
//            throw new RuntimeException("File not found");
//        }

