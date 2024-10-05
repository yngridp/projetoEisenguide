package model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "tb_tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O Atributo título é obrigatório!") 
    @Size(min = 5, max = 100, message = "O atributo título deve ter no minímo 05 e no máximo 100 de caracteres.")
    private String titulo;

    @NotBlank(message = "A descrição da tarefa é obrigatória")
    @Size(min = 5, max = 100, message = "O atributo descrição deve ter no minímo 10 e no máximo 1000 de caracteres.")
    private String descricao;

    @Enumerated(EnumType.STRING)
    private TaskCategory category;

    @UpdateTimestamp   // create data e hora da criaçao e update atualização data e hora da atualização
	private LocalDateTime data; // classe mais moderna no java para data */

    private boolean isCompleted = false;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    
    private String objective; // Meta ou objetivo da tarefa
    private LocalDateTime goalDeadline; // Prazo para a meta
    
    @ManyToOne // tipo de relacionamento task , um para muitos
    @JsonIgnoreProperties("task")
    private TaskCategory taskCategory; // inserir o objeto da classe taskcategory ,
    
    @ManyToOne 
    @JsonIgnoreProperties("task")
    private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TaskCategory getCategory() {
		return category;
	}

	public void setCategory(TaskCategory category) {
		this.category = category;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public LocalDateTime getGoalDeadline() {
		return goalDeadline;
	}

	public void setGoalDeadline(LocalDateTime goalDeadline) {
		this.goalDeadline = goalDeadline;
	}

	public TaskCategory getTaskCategory() {
		return taskCategory;
	}

	public void setTaskCategory(TaskCategory taskCategory) {
		this.taskCategory = taskCategory;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	} 

   
    
}
