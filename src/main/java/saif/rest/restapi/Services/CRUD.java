package saif.rest.restapi.Services;

public interface CRUD<T> {

    public boolean add(T object) throws Exception;

    public void update(int id) ;

    public void delete(int id) ;

    public T get(String id) throws Exception;
}
