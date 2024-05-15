package com.salvina.repository;

import com.salvina.domain.model.Agendamentos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface para CRUD dos métodos de agendamento
 * @Author joyce.silva
 * */
@Repository
public interface IAgendamentoRepository extends CrudRepository<Agendamentos, Long> {
}
