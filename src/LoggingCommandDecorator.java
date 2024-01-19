import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// Decorator class for logging user interactions
public class LoggingCommandDecorator implements ChatCommand {
    private ChatCommand decoratedCommand;

    // Specify the file path for the log
    private String logFilePath = "chatbot_log.txt";

    public LoggingCommandDecorator(ChatCommand decoratedCommand) {
        this.decoratedCommand = decoratedCommand;
    }

    @Override
    public String execute(String userInput) {
        logInteraction(userInput);
        return decoratedCommand.execute(userInput);
    }

    private void logInteraction(String userInput) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFilePath, true))) {
            writer.println("User input: " + userInput);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }
}
