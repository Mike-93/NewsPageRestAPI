package NewsPageRestAPI.repositories;


import NewsPageRestAPI.models.NewsType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsTypeRepository extends CrudRepository<NewsType, Integer> {
}
