package guldilin.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.ws.WebFault;

@EqualsAndHashCode(callSuper = true)
@Data
@WebFault(name = "entryNotFound")
public class EntryNotFound extends Exception {
    private Long id;

    public EntryNotFound() {
        super(ErrorMessage.NOT_FOUND);
    }

    public EntryNotFound(String m) {
        super(m);
    }

    public EntryNotFound(Long id) {
        super(ErrorMessage.NOT_FOUND);
        this.id = id;
    }

    public EntryNotFound(Long id, String m) {
        super(m);
        this.id = id;
    }

    @Override
    public String toString() {
        return "EntryNotFound;" + id + ";" + getMessage();
    }
}
