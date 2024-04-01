package br.com.alura.farmaciaSemana8Turma2.repository;

import br.com.alura.farmaciaSemana8Turma2.model.Produto;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository<Produto, Integer> {
}
