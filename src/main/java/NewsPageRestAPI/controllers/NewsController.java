package NewsPageRestAPI.controllers;

import NewsPageRestAPI.models.News;
import NewsPageRestAPI.services.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @PostMapping("/news")
    public ResponseEntity addNews(@RequestBody News news) {
        newsService.addNews(news);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/news")
    public ResponseEntity<List<News>> getAllNews() {
        List<News> newsList = newsService.getAllNews();
        if (newsList != null && !newsList.isEmpty()) {
            return new ResponseEntity<>(newsList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/news/{id}")
    public ResponseEntity<News> showNews(@PathVariable int id) {
        News news = newsService.getNews(id);
        if (news != null) {
            return new ResponseEntity<>(news, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/news/{id}")
    public ResponseEntity editNews(@PathVariable int id, @RequestBody News news) {
        final boolean updated = newsService.editNews(id, news);
        if (updated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("/news/{id}")
    public ResponseEntity deleteNews(@PathVariable int id) {
        final boolean deleted = newsService.deleteNews(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
}
