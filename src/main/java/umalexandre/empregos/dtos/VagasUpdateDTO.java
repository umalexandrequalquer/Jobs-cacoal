package umalexandre.empregos.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VagasUpdateDTO {

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    @NotNull
    private Float salario;

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Float getSalario() { return salario; }
    public void setSalario(Float salario) { this.salario = salario; }
}
