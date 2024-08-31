package org.tudogostoso.controle;

import org.tudogostoso.modelo.UnidadeMedida;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ControleUnidadeDeMedida {
    public List<UnidadeMedida> buscarUnidadeMedidas(){
        return new ArrayList<>(Arrays.asList(UnidadeMedida.values()));
    }
}
