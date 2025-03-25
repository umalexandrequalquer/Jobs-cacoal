package umalexandre.empregos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmpresaCreateDTO {

    private String name;
    private String cnpj;
    private String email;
    private String phoneNumber;
    private String address;
}
