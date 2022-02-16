package tourGuide.user.Exceptions;

public class UserNotFoundException extends RuntimeException{
    String username;

    public UserNotFoundException(String username) {
        super("User not found for username : "+username);
        this.username = username;
    }
}
