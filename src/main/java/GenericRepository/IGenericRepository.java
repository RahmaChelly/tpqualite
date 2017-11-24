package GenericRepository;

import java.io.Serializable;
import java.util.List;

public interface IGenericRepository<T, PK extends Serializable> {

	boolean create(T object_in);

	T findByID(PK id);

	List<T> findAll();

	void update(T object_in);

	void delete(PK id);

	boolean reserver(long clientId, long chambreId);

}
