package umalexandre.empregos.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import umalexandre.empregos.dtos.*;
import umalexandre.empregos.entity.EmpresaEntity;
import umalexandre.empregos.entity.VagasEntity;
import umalexandre.empregos.repository.EmpresaRepository;
import umalexandre.empregos.repository.VagasRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private VagasRepository vagasRepository;

    public List<EmpresaDTO> GetEmpresa() {
        List<EmpresaEntity> list = empresaRepository.findAll();
        return list.stream().map(EmpresaDTO::new).collect(Collectors.toList());
    }

    public ResponseEntity<EmpresaDTOWithID> GetEmpresaDTOWithID(UUID id) {
        return empresaRepository.findById(id)
                .map(empresa -> ResponseEntity.ok(new EmpresaDTOWithID(empresa)))
                .orElse(ResponseEntity.notFound().build());
    }

    public EmpresaEntity makeEmpresa(@NotNull @Valid EmpresaCreateDTO empresa) {
        EmpresaEntity empresaEntity = new EmpresaEntity(empresa);
        return empresaRepository.save(empresaEntity);
    }

    @Transactional
    public EmpresaEntity empresaUpdateDTO(UUID id, EmpresaUpdateDTO empresaDTO) {
        EmpresaEntity empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada com ID: " + id));

        empresa.EmpresaUpdateDTO(empresaDTO);
        return empresaRepository.save(empresa);
    }

    @Transactional
    public void deleteEmpresa(UUID id) {
        if (!empresaRepository.existsById(id)) {
            throw new RuntimeException("Empresa não encontrada com ID: " + id);
        }
        empresaRepository.deleteById(id);
    }

    @Transactional
    public void makeVagas(UUID id, VagasDTO vaga) {
        empresaRepository.findById(id).ifPresent(empresa -> {
            EmpresaEntity empresaEntity = empresaRepository.findById(id).orElseThrow();
            VagasEntity vagas = new VagasEntity(empresaEntity,vaga);
            vagasRepository.save(vagas);
        });
    }

    @Transactional
    public VagasEntity updateVaga(UUID idEmpresa, UUID idVaga, @Valid VagasEntity vagaDTO) {
        EmpresaEntity empresa = empresaRepository.findById(idEmpresa)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada com ID: " + idEmpresa));

        VagasEntity vaga = empresa.getVagas().stream()
                .filter(v -> v.getId().equals(idVaga))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Vaga não encontrada com ID: " + idVaga));

        vaga.atualizarDados(vagaDTO);
        return vagasRepository.save(vaga);
    }

    @Transactional
    public void deleteVaga(UUID idEmpresa, UUID idVaga) {
        empresaRepository.findById(idEmpresa).ifPresent(empresa -> {
            empresa.getVagas().removeIf(vaga -> vaga.getId().equals(idVaga));
            empresaRepository.save(empresa);
        });
    }
}
