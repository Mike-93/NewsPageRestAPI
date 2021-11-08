package NewsPageRestAPI.services;

import NewsPageRestAPI.models.News;
import NewsPageRestAPI.repositories.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class NewsService {

    @Autowired
    private final NewsRepository newsRepository;

    public void addNews(News news) {
        News freshNews = new News();
        freshNews.setName(news.getName());
        freshNews.setShortDesc(news.getShortDesc());
        freshNews.setFullDesc(news.getFullDesc());
        freshNews.setType(news.getType());
        newsRepository.save(freshNews);
    }

    public List<News> getAllNews() {
        Iterable<News> news = newsRepository.findAll();
        List<News> newsList = new ArrayList<>();
        for (News n : news) {
            newsList.add(n);
        }
        return newsList;
    }

    public News getNews(int id) {
        News news = newsRepository.findById(id).orElseThrow(() -> new NoSuchElementException(""));
        return news;
    }

    public boolean editNews(int id, News news) {
        News freshNews = newsRepository.findById(id).orElse(null);
        if (freshNews != null) {
            freshNews.setName(news.getName());
            freshNews.setShortDesc(news.getShortDesc());
            freshNews.setFullDesc(news.getFullDesc());
            freshNews.setType(news.getType());
            newsRepository.save(news);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteNews(int id) {
        newsRepository.deleteById(id);
        News news = newsRepository.findById(id).orElse(null);
        if (news != null) {
            return false;
        } else {
            return true;
        }
    }

    public List<News> getNewsByNewsType(int typeId) {
        Iterable<News> news = newsRepository.findAll();
        List<News> newsList = new ArrayList<>();
        for (News n : news) {
            if (n.getType().getId() == typeId) {
                newsList.add(n);
            }
        }
        return newsList;
    }
}
