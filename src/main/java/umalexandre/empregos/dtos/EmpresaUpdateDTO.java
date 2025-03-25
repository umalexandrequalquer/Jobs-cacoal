package umalexandre.empregos.dtos;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

@Getter
@Setter
public class EmpresaUpdateDTO {

    private String name;
    @CNPJ
    private String cnpj;
    @Email(message = "Email deve ser válido")
    private String email;
    private String phoneNumber;
    private String address;
}
