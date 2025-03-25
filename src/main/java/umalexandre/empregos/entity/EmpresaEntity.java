package umalexandre.empregos.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import umalexandre.empregos.dtos.EmpresaCreateDTO;
import umalexandre.empregos.dtos.EmpresaUpdateDTO;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_empresas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank(message = "O campo de nome da empresa é obrigatório")
    @Column(unique = true, nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    @CNPJ()
    private String cnpj;
    @Email
    @Column(unique = true, nullable = false)
    private String email;
    private String phoneNumber;
    private String address;

    @OneToMany(mappedBy = "empresa",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VagasEntity> vagas;


    public EmpresaEntity(@Valid EmpresaCreateDTO empresaDTO) {
        this.name = empresaDTO.getName();
        this.cnpj = empresaDTO.getCnpj();
        this.email = empresaDTO.getEmail();
        this.phoneNumber = empresaDTO.getPhoneNumber();
        this.address = empresaDTO.getAddress();
        this.vagas = new ArrayList<>();
    }

    public void EmpresaUpdateDTO(EmpresaUpdateDTO empresaDTO) {
        for (Field field : EmpresaUpdateDTO.class.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(empresaDTO);
                if (value != null) {
                    Field entityField = this.getClass().getDeclaredField(field.getName());
                    entityField.setAccessible(true);
                    entityField.set(this, value);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException("Erro ao atualizar campo: " + field.getName(), e);
            }
        }
    }



}
