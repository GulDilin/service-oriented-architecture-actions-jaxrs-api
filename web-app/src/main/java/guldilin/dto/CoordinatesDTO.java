package guldilin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoordinatesDTO implements Serializable {
    private Integer id;
    private Long x;
    private Integer y;
}