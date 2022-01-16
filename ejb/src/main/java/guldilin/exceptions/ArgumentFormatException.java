package guldilin.exceptions;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ArgumentFormatException extends Exception {
    private final String argument;

    public ArgumentFormatException(String argument, String message) {
        super(message);
        this.argument = argument;
    }
}
