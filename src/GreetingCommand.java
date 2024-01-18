// GreetingCommand class
public class GreetingCommand implements ChatCommand {
    @Override
    public String execute(String userInput) {
        return "Hello! How can I help you?";
    }
}
