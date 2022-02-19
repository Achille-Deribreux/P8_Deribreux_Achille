package tourGuide.user.Exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String username) {
        super("User not found for username : "+username);
    }
}
