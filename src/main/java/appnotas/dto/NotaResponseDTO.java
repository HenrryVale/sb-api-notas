package appnotas.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NotaResponseDTO {

    @ApiModelProperty(position = 0)
    private Integer id;
    @ApiModelProperty(position = 1)
    private String titulo;
    @ApiModelProperty(position = 2)
    private String contenido;
    @ApiModelProperty(position = 3)
    private Integer usuarioId;
}
