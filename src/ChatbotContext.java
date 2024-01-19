// Context class that uses a strategy (ChatCommand)
public class ChatbotContext {
    private ChatCommand chatCommand;

    public void setChatCommand(ChatCommand chatCommand) {
        this.chatCommand = chatCommand;
    }

    public String executeCommand(String userInput) {
        return chatCommand.execute(userInput);
    }
}
