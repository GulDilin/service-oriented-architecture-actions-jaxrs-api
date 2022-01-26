package guldilin.exceptions;

import lombok.Getter;

import javax.xml.ws.WebFault;

@Getter
@WebFault(name = "incorrectArgumentFormat")
public class ArgumentFormatException extends Exception {
    private final String argument;

    public ArgumentFormatException(String argument, String message) {
        super(message);
        this.argument = argument;
    }

    @Override
    public String toString() {
        return "EntryNotFound;" + argument + ";" + getMessage();
    }
}
