package modelo;

//ItemIgrendientes são os igrendiente que vão em receitas, justamente por conta disso precisam tem quantidade e medida
public class ItemIngrediente extends Ingrediente{

    private double quantidade;
    private UnidadeMedida medida;

    public ItemIngrediente(Ingrediente ingrediente, double quantidade, UnidadeMedida medida) {
        super(ingrediente.getNome());
        this.quantidade = quantidade;
        this.medida = medida;
    }


    //gets e setters adicionais
    public UnidadeMedida getMedida() {
        return medida;
    }

    public void setMedida(UnidadeMedida medida) {
        this.medida = medida;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }
}
