package org.tudogostoso.modelo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
@Getter@Setter@ToString
//ItemIgrendientes são os igrendiente que vão em receitas, justamente por conta disso precisam tem quantidade e medida
public class ItemIngrediente implements Serializable {

    private Ingrediente ingrediente;
    private double quantidade;
    private UnidadeMedida medida;

    public ItemIngrediente(Ingrediente ingrediente, double quantidade, UnidadeMedida medida) {
        this.ingrediente = ingrediente;
        this.quantidade = quantidade;
        this.medida = medida;
    }


    public void setMedida(UnidadeMedida medida) {
        this.medida = medida;
    }




}

