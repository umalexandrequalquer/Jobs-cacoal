package umalexandre.empregos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umalexandre.empregos.entity.EmpresaEntity;

import java.util.UUID;
@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaEntity, UUID> {

}
