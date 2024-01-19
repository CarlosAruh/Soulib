package com.soulcode.soullib.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "emprestimos")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmprestimo;

    @Column(nullable = false)
    private LocalDate dataRealizada;

    @Column(nullable = false)
    private LocalDate dataDevolução;

    private Boolean finalizado;

    // Muitos emprestimos para um cliente
    @ManyToOne
    @JoinColumn(name = "fk_cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "fk_livro_id")
    private Livro livro;

    public Emprestimo() {

    }

    public Emprestimo(Integer idEmprestimo, LocalDate dataRealizada, LocalDate dataDevolução, Boolean finalizado,
            Cliente cliente, Livro livro) {
        this.idEmprestimo = idEmprestimo;
        this.dataRealizada = dataRealizada;
        this.dataDevolução = dataDevolução;
        this.finalizado = false;
        this.cliente = cliente;
        this.livro = livro;
    }

    public Integer getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(Integer idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public LocalDate getDataRealizada() {
        return dataRealizada;
    }

    public void setDataRealizada(LocalDate dataRealizada) {
        this.dataRealizada = dataRealizada;
    }

    public LocalDate getDataDevolução() {
        return dataDevolução;
    }

    public void setDataDevolução(LocalDate dataDevolução) {
        this.dataDevolução = dataDevolução;
    }

    public Boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

}
