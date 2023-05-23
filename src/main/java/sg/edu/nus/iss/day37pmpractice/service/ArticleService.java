package sg.edu.nus.iss.day37pmpractice.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import sg.edu.nus.iss.day37pmpractice.repository.ArticleRepository;

@Service
public class ArticleService {
    
    @Autowired
    private ArticleRepository articleRepo;

    @Transactional(rollbackFor = IOException.class)
    public String postImage(MultipartFile multipart, String title, 
        String contentPara) throws IOException {
            
        String post_id = UUID.randomUUID().toString().substring(0, 8);

        // MySQL will rollback if MongoDB operation throws an exception
        articleRepo.saveImage(post_id, multipart.getContentType(), multipart.getBytes());
        
        // MongoDB
        articleRepo.saveDetails(post_id, title, contentPara);

        return post_id;
    }

}
