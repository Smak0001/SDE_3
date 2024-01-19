// LoggingCommandDecorator class
public class LoggingCommandDecorator implements ChatCommand {
    private ChatCommand decoratedCommand;

    public LoggingCommandDecorator(ChatCommand decoratedCommand) {
        this.decoratedCommand = decoratedCommand;
    }

    @Override
    public String execute(String userInput) {
        logInteraction(userInput);
        return decoratedCommand.execute(userInput);
    }

    private void logInteraction(String userInput) {
        System.out.println("Logging: User input - " + userInput);
    }
}
