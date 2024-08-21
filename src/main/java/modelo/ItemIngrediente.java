package modelo;

//ItemIgrendientes são os igrendiente que vão em receitas, justamente por conta disso precisam tem quantidade e medida
public class ItemIngrediente {

    private Ingrediente ingrediente;
    private double quantidade;
    private UnidadeMedida medida;

    public ItemIngrediente(Ingrediente ingrediente, double quantidade, UnidadeMedida medida) {
        this.ingrediente = ingrediente;
        this.quantidade = quantidade;
        this.medida = medida;
    }



    public UnidadeMedida getMedida() {
        return medida;
    }

    public Ingrediente getIngrediente(){
        return ingrediente;
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


    @Override
    public String toString() {
        return "ItemIngrediente{" +
                "ingrediente=" + ingrediente +
                ", quantidade=" + quantidade +
                ", medida=" + medida +
                '}';
    }
}
