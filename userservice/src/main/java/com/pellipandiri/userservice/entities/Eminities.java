package com.pellipandiri.userservice.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "amenities")
public class Eminities {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @ManyToMany(mappedBy = "amenities", fetch = FetchType.LAZY)
    private Set<FunctionHalls> functionHalls = new HashSet<>();
    
    // Constructors
    public Eminities() {
    }
    
    public Eminities(String name) {
        this.name = name;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Set<FunctionHalls> getFunctionHalls() {
        return functionHalls;
    }
    
    public void setFunctionHalls(Set<FunctionHalls> functionHalls) {
        this.functionHalls = functionHalls;
    }
    
    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Eminities eminities = (Eminities) o;
        return Objects.equals(id, eminities.id) && 
               Objects.equals(name, eminities.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
    
    @Override
    public String toString() {
        return "Eminities{" +
               "id=" + id +
               ", name='" + name + '\'' +
               '}';
    }
}
