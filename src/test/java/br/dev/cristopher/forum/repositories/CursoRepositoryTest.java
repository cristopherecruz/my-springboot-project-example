package br.dev.cristopher.forum.repositories;

import br.dev.cristopher.forum.models.Curso;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CursoRepositoryTest {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findByNome() {

        String nomeCurso = "HTML 5";

        Curso html5 = new Curso();
        html5.setNome(nomeCurso);
        html5.setCategoria("Programação");

        entityManager.persist(html5);

        Curso curso = cursoRepository.findByNome(nomeCurso);

        Assertions.assertNotNull(nomeCurso);
        Assertions.assertEquals(nomeCurso, curso.getNome());

    }

    @Test
    public void notFindByNome() {

        String nomeCurso = "Curso que não existe aqui";

        Curso curso = cursoRepository.findByNome(nomeCurso);

        Assertions.assertNull(curso);

    }

}