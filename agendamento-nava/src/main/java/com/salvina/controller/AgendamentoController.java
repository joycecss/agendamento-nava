package com.salvina.controller;

import com.salvina.domain.model.Agendamentos;
import com.salvina.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Classe responsável por fazer o controller dos métodos de agendamentos.
 * @Author joyce.silva
 * */
@RestController
@RequestMapping(value = "/agendamentos")
public class AgendamentoController {

    private AgendamentoService service;

    @Autowired
    public AgendamentoController(AgendamentoService agendamentoService) {
        this.service = agendamentoService;
    }

    @PostMapping("/agendar")
    public ResponseEntity agendar(@RequestBody Agendamentos infoAgendamento) {
        return service.agendar(infoAgendamento);
    }

    @GetMapping("/buscar")
    public ResponseEntity listaAgendamentos() {
        return service.listaAgendamentos();
    }

    @GetMapping("/taxa/{dtAgendamento}/{dtTransferencia}/{vlTransferencia}")
    public ResponseEntity calcularTaxa(
            @PathVariable("dtAgendamento") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dtAgendamento,
            @PathVariable("dtTransferencia") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dtTransferencia,
            @PathVariable("vlTransferencia") Double vlTransferencia
    ) {
        return ResponseEntity.status(200).body(service.calcularTaxa(dtAgendamento, dtTransferencia, vlTransferencia));
    }

    @GetMapping("/valor")
    public ResponseEntity getValorAtualizado() {
        return ResponseEntity.status(200).body(service.getValorAtualizado());
    }
}
