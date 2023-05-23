package sg.edu.nus.iss.day37pmpractice.repository;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day37pmpractice.model.Article;

import static sg.edu.nus.iss.day37pmpractice.repository.SQLQueries.*;

import java.util.Optional;

@Repository
public class ArticleRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Optional<Article> findImageById(String post_id) {
        Optional<Article> opt = jdbcTemplate.query(GET_PHOTO_BY_ID,
            rs -> {
                if (!rs.next()) {
                    return Optional.empty();
                }
                Article art = new Article();
                art.setPost_id(post_id);
                art.setMediaType(rs.getString("media_type"));
                art.setContent(rs.getBytes("content"));
                return Optional.of(art);
            },
            post_id
        );
        return opt;
    }

    public boolean saveImage(String post_id, String mediaType, byte[] content) {
        return jdbcTemplate.update(
            SAVE_IMAGE, 
            post_id, 
            mediaType, 
            content
        ) > 0;
    }

    public Document saveDetails(String post_id, String title, String contentPara) {

        Document doc = new Document();
        doc.append("post_id", post_id);
        doc.append("title", title);
        doc.append("contentPara", contentPara);

        return mongoTemplate.insert(doc, "article");
    
    }

}
