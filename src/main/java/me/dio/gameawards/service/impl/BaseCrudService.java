package me.dio.gameawards.service.impl;

import me.dio.gameawards.domain.BaseEntity;
import me.dio.gameawards.service.CrudService;
import me.dio.gameawards.service.exception.BusinessException;
import me.dio.gameawards.service.exception.NoContentException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class BaseCrudService<Entity extends BaseEntity, Repository extends JpaRepository<Entity, Long>> implements CrudService<Entity> {

    protected Repository repository;

    public BaseCrudService(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void insert(Entity entity) {
        this.repository.save(entity);
    }

    @Override
    public List<Entity> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Entity findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new NoContentException("Jogo não encontrado."));
    }

    @Override
    public void update(Long id, Entity entidade) {
        Entity entityDb = this.findById(id);
        if (entityDb.getId().equals(entidade.getId())) {
            this.repository.save(entidade);
        } else {
            throw new BusinessException("Os identificadores para alteração são divergentes.");
        }
    }

    @Override
    public void delete(Long id) {
        Entity entityDb = this.findById(id);
        this.repository.delete(entityDb);
    }
}
