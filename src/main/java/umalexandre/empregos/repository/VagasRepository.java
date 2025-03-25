package umalexandre.empregos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umalexandre.empregos.entity.VagasEntity;

import java.util.UUID;

public interface VagasRepository extends JpaRepository<VagasEntity, UUID> {
}
