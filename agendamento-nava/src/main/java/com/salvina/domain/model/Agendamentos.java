package com.salvina.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

/**
 * Classe model responsável por criar o objeto Agendamentos.
 * @Author joyce.silva
 * */
@Entity
@Table(name = "TB_AGENDAMENTOS")
public class Agendamentos {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="agendamentos_seq")
    @SequenceGenerator(name="agendamentos_seq", sequenceName="sq_agendamentos", initialValue = 1, allocationSize = 1)
    private Long idAgendamentos;

    @Column(name = "conta_origem", nullable = false)
    private Long contaOrigem;

    @Column(name = "conta_destino", nullable = false)
    private Long contaDestino;

    @Column(name = "valor_transferencia", nullable = false)
    private Double vlTransferencia;

    @Column(name = "valor_total", nullable = false)
    private Double vlTotal;

    @Column(name = "taxa", nullable = false)
    private Double taxa;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "dt_transferencia", nullable = false)
    private Date dtTransferencia;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "dt_agendamento", nullable = false)
    private Date dtAgendamento;

    public Long getIdAgendamentos() {
        return idAgendamentos;
    }

    public void setIdAgendamentos(Long idAgendamentos) {
        this.idAgendamentos = idAgendamentos;
    }

    public Long getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(Long contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public Long getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(Long contaDestino) {
        this.contaDestino = contaDestino;
    }

    public Double getVlTotal() {
        return vlTotal;
    }

    public void setVlTotal(Double vlTotal) {
        this.vlTotal = vlTotal;
    }

    public Double getVlTransferencia() {
        return vlTransferencia;
    }

    public void setVlTransferencia(Double vlTransferencia) {
        this.vlTransferencia = vlTransferencia;
    }

    public Double getTaxa() {
        return taxa;
    }

    public void setTaxa(Double taxa) {
        this.taxa = taxa;
    }

    public Date getDtTransferencia() {
        return dtTransferencia;
    }

    public void setDtTransferencia(Date dtTranferencia) {
        this.dtTransferencia = dtTranferencia;
    }

    public Date getDtAgendamento() {
        return dtAgendamento;
    }

    public void setDtAgendamento(Date dtAgendamento) {
        this.dtAgendamento = dtAgendamento;
    }
}
