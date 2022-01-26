package guldilin.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class StatusDTO implements Serializable {
    private String status;
}
