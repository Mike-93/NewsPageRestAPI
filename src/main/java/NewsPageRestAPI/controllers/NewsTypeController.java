package NewsPageRestAPI.controllers;

import NewsPageRestAPI.models.News;
import NewsPageRestAPI.models.NewsType;
import NewsPageRestAPI.services.NewsService;
import NewsPageRestAPI.services.NewsTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NewsTypeController {

    private final NewsTypeService newsTypeService;
    private final NewsService newsService;

    @PostMapping("/news-types")
    public ResponseEntity addNewsType(@RequestBody NewsType newsType) {
        newsTypeService.addNewsType(newsType);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/news-types/{id}")
    public ResponseEntity editNewsType(@PathVariable int id, @RequestBody NewsType newsType) {
        final boolean updated = newsTypeService.editNewsType(id, newsType);
        if (updated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("/news-types/{id}")
    public ResponseEntity newsTypeRemove(@PathVariable int id) {
        final boolean deleted = newsTypeService.deleteNewsType(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @GetMapping("/news-types/{id}")
    public ResponseEntity<NewsType> showNewsType(@PathVariable int id) {
        NewsType newsType = newsTypeService.getNewsType(id);
        if (newsType != null) {
            return new ResponseEntity<>(newsType, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/news-types")
    public ResponseEntity<List<NewsType>> getAllNewsTypes() {
        List<NewsType> newsTypeList = newsTypeService.getAllNewsType();
        if (newsTypeList != null && !newsTypeList.isEmpty()) {
            return new ResponseEntity<>(newsTypeList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/news-types/{id}/list")
    public ResponseEntity<List<News>> getNewsByType(@PathVariable int id) {
        List<News> newsList = newsService.getNewsByNewsType(id);
        if (newsList != null && !newsList.isEmpty()) {
            return new ResponseEntity<>(newsList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
