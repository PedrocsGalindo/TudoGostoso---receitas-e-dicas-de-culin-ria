package repositorio;

import java.util.List;

public interface IRepositorio <Objeto>{

     void salvar(Objeto valor);
     List<Objeto> buscar();
     Objeto recuperar(int id);


}
