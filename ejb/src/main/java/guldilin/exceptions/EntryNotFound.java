package guldilin.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EntryNotFound extends Exception {
    private Long id;

    public EntryNotFound() {
        super(ErrorMessage.NOT_FOUND);
    }

    public EntryNotFound(String m) {
        super(m);
    }

    public EntryNotFound(Long id) {
        super(ErrorMessage.NOT_FOUND + " with id " + id);
        this.id = id;
    }

    public EntryNotFound(Long id, String m) {
        super(m + " with id " + id);
        this.id = id;
    }

    @Override
    public String toString() {
        return "EntryNotFound;" + id + ";" + getMessage();
    }
}
