package validators;

public class InputValidator {

    public static String validateName(String name) {
        name = name.strip();
        if(name.isBlank() || !name.matches("[a-zA-Z]+")) return null;
        name = name.toLowerCase();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return name;
    }

    //TODO add validate if email is taken
    public static String validateEmail(String email){
        email = email.strip();
        if(email.isBlank() || !email.contains("@")) return null;
        else return email;
    }

    //TODO now it does 2 things, refactor
    public static String checkBlank(String password){
        password = password.strip();
        if(password.isBlank()) return null;
        else return password;
    }

    public static boolean validateSignificance(String significance){
        int num = Integer.parseInt(significance);
        return num > 0 && num <= 5;
    }


}
