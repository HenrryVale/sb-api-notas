package appnotas.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotaDTO {

    @ApiModelProperty(position = 0)
    private String titulo;
    @ApiModelProperty(position = 1)
    private String contenido;


}
