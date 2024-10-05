package repository;


	import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import model.Task;

	public interface TaskRepository extends JpaRepository<Task, Long>{
		
		List<Task> findAllByTituloContainingIgnoreCase(@Param("titulo")String Titulo); //consulta,colletciton lista armazaena postagem, containig = like %f%, ignorecase= ignorar letras minusculas ou maiusculas
	    //SELECT * FROM tb_postagens WHERE titulo LIKE "texto que quero encontrar";
	}

