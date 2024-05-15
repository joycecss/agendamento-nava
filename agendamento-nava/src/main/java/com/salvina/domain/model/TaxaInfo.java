package com.salvina.domain.model;

/**
 * Classe responsável por aplicar lógica de taxa e desconto.
 * @Author joyce.silva
 * */
public class TaxaInfo {
    private int limiteDias;
    private double taxa;
    private double desconto;

    public TaxaInfo(int limiteDias, double taxa, double desconto) {
        this.limiteDias = limiteDias;
        this.taxa = taxa;
        this.desconto = desconto;
    }

    public TaxaInfo(int limiteDias, double taxa) {
        this(limiteDias, taxa, 0.0);
    }

    public int getLimiteDias() {
        return limiteDias;
    }

    public double getTaxa() {
        return taxa;
    }

    public double getDesconto() {
        return desconto;
    }
}