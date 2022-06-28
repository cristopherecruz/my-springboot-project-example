package br.dev.cristopher.forum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.cristopher.forum.models.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	Curso findByNome(String nome);

}
