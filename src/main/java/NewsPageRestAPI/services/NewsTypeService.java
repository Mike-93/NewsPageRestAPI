package NewsPageRestAPI.services;

import NewsPageRestAPI.models.NewsType;
import NewsPageRestAPI.repositories.NewsTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class NewsTypeService {

    @Autowired
    private final NewsTypeRepository newsTypeRepository;

    public void addNewsType(NewsType newsType) {
        NewsType freshNewsType = new NewsType();
        freshNewsType.setName(newsType.getName());
        freshNewsType.setColor(newsType.getColor());
        newsTypeRepository.save(freshNewsType);
    }

    public NewsType getNewsType(int id) {
        NewsType newsType = newsTypeRepository.findById(id).orElseThrow(() -> new NoSuchElementException(""));
        return newsType;
    }

    public boolean editNewsType(int id, NewsType newsType) {
        NewsType freshNewType = newsTypeRepository.findById(id).orElse(null);
        if (freshNewType != null) {
            freshNewType.setName(newsType.getName());
            freshNewType.setColor(newsType.getColor());
            newsTypeRepository.save(newsType);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteNewsType(int id) {
        newsTypeRepository.deleteById(id);
        NewsType newsType = newsTypeRepository.findById(id).orElse(null);
        if (newsType != null) {
            return false;
        } else {
            return true;
        }
    }

    public List<NewsType> getAllNewsType() {
        Iterable<NewsType> newsTypes = newsTypeRepository.findAll();
        List<NewsType> newsTypeList = new ArrayList<>();
        for (NewsType type : newsTypes) {
            newsTypeList.add(type);
        }
        return newsTypeList;
    }
}

