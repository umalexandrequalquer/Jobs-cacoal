package umalexandre.empregos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import umalexandre.empregos.entity.VagasEntity;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VagasDTO {

    private String titulo;
    private String descricao;
    private Float valor;

    public VagasDTO(VagasEntity vaga) {
        this.titulo = vaga.getTitulo();
        this.descricao = vaga.getDescricao();
        this.valor = vaga.getValor();
    }
}
