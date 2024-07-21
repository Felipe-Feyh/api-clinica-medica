package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @Transactional
    @PostMapping
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
       repository.save(new Medico(dados));
    }

    /* o pageable implementa a paginação
    exemplos de parametros de paginação
    size=1&page=1&sort=nome&desc
    */

    // @PageableDefault(size = 10, sort = "nome" é default, se for passado um valor diferente do default, o valor passado será o valor default)
    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemMedico::new);
    }

    @GetMapping
    @RequestMapping("/findbyid/{id}")
    public DadosListagemMedico buscarPorId(@PathVariable Long id) {
        Medico medico = repository.getReferenceById(id);
        return new DadosListagemMedico(medico);
    }
}
