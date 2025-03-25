package umalexandre.empregos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import umalexandre.empregos.entity.EmpresaEntity;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmpresaDTO {

    private String name;
    private String cnpj;
    private String email;
    private String phoneNumber;
    private String address;
    private List<VagasDTO> vagas; //

    public EmpresaDTO(@NotNull EmpresaEntity empresa) {
        this.name = empresa.getName();
        this.cnpj = empresa.getCnpj();
        this.email = empresa.getEmail();
        this.phoneNumber = empresa.getPhoneNumber();
        this.address = empresa.getAddress();

        this.vagas = empresa.getVagas().stream()
                .map(VagasDTO::new)
                .collect(Collectors.toList());
    }


}
