package br.imd.ufrn.sge.controller;

import br.imd.ufrn.sge.models.turma.Turma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/discente", produces="application/json")
public class NotaController {
    //TODO: Criar controller de nota
}
