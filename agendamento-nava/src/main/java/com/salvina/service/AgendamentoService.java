package com.salvina.service;

import com.salvina.domain.model.Agendamentos;
import com.salvina.domain.model.TaxaInfo;
import com.salvina.repository.IAgendamentoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Classe responsável por fazer aplicar as regras de negocios de agendamentos.
 * @Author joyce.silva
 * */
@Service
public class AgendamentoService {

    private IAgendamentoRepository agendamentoRepository;
    private static final double VALOR_MINIMO_TRANSFERENCIA = 0.0;

    public AgendamentoService(IAgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    private double taxa = 0.0;
    private double vlTotal = 0.0;
    private List<TaxaInfo> taxas = Arrays.asList(
            new TaxaInfo(0, 2.5, 3.0),
            new TaxaInfo(10, 0.0, 12.0),
            new TaxaInfo(20, 8.2),
            new TaxaInfo(30, 6.9),
            new TaxaInfo(40, 4.7),
            new TaxaInfo(50, 1.7)
    );

    /**
     * Responsável pela criação de um agendamento
     * Quando o vlTransferencia é menor ou igual a zero acontece um BAD_REQUEST
     * Quando a dtTransferencia é menor que a dtAgendamento acontece um BAD_REQUEST
     * Quando a taxa é calculada com um intervalo maior que 50 dias acontece um INTERNAL_SERVER_ERROR
     * @param infoAgendamento é um model do Agendamento a ser utilizado na criação
     * @return retorna um ResponseEntity ok quando esta tudo certo com as informações de criação no body
     * */
    public ResponseEntity agendar(Agendamentos infoAgendamento) {
        Agendamentos agendamentos = new Agendamentos();
        agendamentos.setContaOrigem(infoAgendamento.getContaOrigem());
        agendamentos.setContaDestino(infoAgendamento.getContaDestino());
        agendamentos.setVlTransferencia(infoAgendamento.getVlTransferencia());
        agendamentos.setDtAgendamento(infoAgendamento.getDtAgendamento());
        agendamentos.setDtTransferencia(infoAgendamento.getDtTransferencia());

        Date dtAgendamento = infoAgendamento.getDtAgendamento();
        Date dtTransferencia = infoAgendamento.getDtTransferencia();
        Double vlTransferencia = infoAgendamento.getVlTransferencia();

        if(vlTransferencia <= VALOR_MINIMO_TRANSFERENCIA){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("O valor de transferência não pode ser menor ou igual a zero.");
        }

        if (dtTransferencia.before(dtAgendamento)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de transferência é anterior à data de agendamento.");
        }

        if(calcularTaxa(dtAgendamento, dtTransferencia, vlTransferencia) == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Não há taxa aplicável. \n A data da transferência deve ser menor que 50 dias.");
        }

        agendamentos.setTaxa(taxa);
        agendamentos.setVlTotal(vlTotal);

        agendamentoRepository.save(agendamentos);
        return ResponseEntity.status(HttpStatus.CREATED).body(agendamentos);
    }

    /**
     * Responsável por trazer a lista de agendamentos
     * @return retorna um ResponseEntity ok quando possui informações ou noContent quando esta vazio
     * */
    public ResponseEntity listaAgendamentos() {
        List<Agendamentos> listaAgendamento = (List<Agendamentos>) agendamentoRepository.findAll();

        return !listaAgendamento.isEmpty()
                ? ResponseEntity.ok(listaAgendamento)
                : ResponseEntity.noContent().build();
    }

    /**
     * Responsável por fazer a logica de taxa e descontos
     * @param dtAgendamento é o campo de data de agendamento usado para calculo de diferença de dias para encontrar a taxa
     * @param dtTransferencia é o campo de data de transferencia usado para calculo de diferença de dias para encontrar a taxa
     * @param vlTransferencia é o campo de valor de transferencia usado para calculo de descontos com base na taxa
     * @return retorna um ResponseEntity ok quando possui informações ou noContent quando esta vazio
     * */
    public Double calcularTaxa(Date dtAgendamento, Date dtTransferencia, Double vlTransferencia) {
        long diferencaDias = (dtTransferencia.getTime() - dtAgendamento.getTime()) / (1000 * 60 * 60 * 24);

        for (TaxaInfo taxaInfo : taxas) {
            if (diferencaDias <= taxaInfo.getLimiteDias()) {
                double taxa = taxaInfo.getTaxa();
                this.taxa = taxa;
                this.vlTotal = (vlTransferencia - ((taxa / 100.0) * vlTransferencia)) - taxaInfo.getDesconto();
                return this.taxa;
            }
        }

        return null;
    }

    /**
     * Responsável por buscar o valor total atualizado
     * @return retorna um double com o valor total
     * */
    public double getValorAtualizado() {
        return vlTotal;
    }
}
