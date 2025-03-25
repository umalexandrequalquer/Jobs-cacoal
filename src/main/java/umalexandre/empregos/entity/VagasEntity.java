package umalexandre.empregos.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.NumberFormat;
import umalexandre.empregos.dtos.VagasDTO;

import java.util.UUID;

@Entity
@Table(name = "tb_vagas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VagasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    private String titulo;
    @NotNull
    private String descricao;
    @NotNull
    @DecimalMin(value = "1518.00", message = "O valor deve ser maior ou igual = R$:1.518,00")
    @NumberFormat(pattern = "#.###,00")
    private Float valor;
    @ManyToOne()
    @JoinColumn(name = "empresa_id")
    @JsonIgnore
    private EmpresaEntity empresa;

    public VagasEntity(EmpresaEntity empresa, VagasDTO vaga) {
        this.empresa = empresa;
        this.titulo = vaga.getTitulo();
        this.descricao = vaga.getDescricao();
        this.valor = vaga.getValor();
    }


    public void atualizarDados(@Valid VagasEntity vagaDTO) {
        this.titulo = vagaDTO.getTitulo();
        this.descricao = vagaDTO.getDescricao();
        this.valor = vagaDTO.getValor();

    }
}
