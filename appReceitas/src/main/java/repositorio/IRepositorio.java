package repositorio;

import java.util.List;

public interface IRepositorio <T>{

     void salvar(T valor);
     List<T> buscar();
     T recuperar(int id);


}
