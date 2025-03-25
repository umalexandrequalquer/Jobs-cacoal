package umalexandre.empregos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import umalexandre.empregos.entity.EmpresaEntity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmpresaDTOWithID {
    private UUID id;
    private String name;
    private String cnpj;
    private String email;
    private String phoneNumber;
    private String address;
    private List<VagasDTO> vagas; //

    public EmpresaDTOWithID(@NotNull EmpresaEntity empresa) {
        this.id = empresa.getId();
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
