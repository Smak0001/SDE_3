// DefaultCommand class
public class DefaultCommand implements ChatCommand {
    @Override
    public String execute(String userInput) {
        return "I'm sorry, I don't understand. Can you please rephrase or ask something else?";
    }
}
