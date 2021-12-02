package br.dev.cristopher.forum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.cristopher.forum.models.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

}
