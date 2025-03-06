package com.recipe.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * The type Recipe.
 *
 * @author Vishal
 */
@Entity
@Table(name = "recipes")
@Data
public class Recipe {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column
    private String name;

    @Column
    private String ingredients;
    @Column
    private String instructions;
    @Column
    private String type;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column
    private int numberOfServings;

}
