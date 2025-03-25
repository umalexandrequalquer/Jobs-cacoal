package umalexandre.empregos.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umalexandre.empregos.dtos.*;
import umalexandre.empregos.entity.EmpresaEntity;
import umalexandre.empregos.entity.VagasEntity;
import umalexandre.empregos.service.EmpresaService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public ResponseEntity<List<EmpresaDTO>> listarEmpresas() {
        return ResponseEntity.ok(empresaService.GetEmpresa());
    }

    @PostMapping
    public ResponseEntity<EmpresaEntity> criarEmpresa(@Valid @RequestBody EmpresaCreateDTO empresa) {
        return ResponseEntity.ok(empresaService.makeEmpresa(empresa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaEntity> atualizarEmpresa(
            @PathVariable UUID id,
            @Valid @RequestBody EmpresaUpdateDTO empresaUpdateDTO) {

        EmpresaEntity empresaAtualizada = empresaService.empresaUpdateDTO(id, empresaUpdateDTO);
        return ResponseEntity.ok(empresaAtualizada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDTOWithID> buscarEmpresaPorId(@PathVariable UUID id) {
        return empresaService.GetEmpresaDTOWithID(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmpresa(@PathVariable UUID id) {
        empresaService.deleteEmpresa(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/vagas")
    public ResponseEntity<Void> criarVaga(@PathVariable UUID id, @Valid @RequestBody VagasDTO vaga) {
        empresaService.makeVagas(id, vaga);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{idEmpresa}/vagas/{idVaga}")
    public ResponseEntity<VagasEntity> atualizarVaga(
            @PathVariable UUID idEmpresa,
            @PathVariable UUID idVaga,
            @Valid @RequestBody VagasEntity vagaAtualizada) {

        VagasEntity vaga = empresaService.updateVaga(idEmpresa, idVaga, vagaAtualizada);
        return ResponseEntity.ok(vaga);
    }

    @DeleteMapping("/{idEmpresa}/vagas/{idVaga}")
    public ResponseEntity<Void> deletarVaga(@PathVariable UUID idEmpresa, @PathVariable UUID idVaga) {
        empresaService.deleteVaga(idEmpresa, idVaga);
        return ResponseEntity.noContent().build();
    }
}
