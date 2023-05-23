package sg.edu.nus.iss.day37pmpractice.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import sg.edu.nus.iss.day37pmpractice.model.Article;
import sg.edu.nus.iss.day37pmpractice.repository.ArticleRepository;
import sg.edu.nus.iss.day37pmpractice.service.ArticleService;

@Controller
@RequestMapping
public class ArticleController {
    
    @Autowired
    private ArticleService articleSvc;

    @Autowired
    private ArticleRepository articleRepo;

    @GetMapping(path="/image/{post_id}", produces = MediaType.ALL_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]> getImage(@PathVariable String post_id) {
        Optional<Article> opt = articleRepo.findImageById(post_id);

        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Article a = opt.get();
        return ResponseEntity.status(200)
            .header("Content-Type", a.getMediaType())
            .body(a.getContent());
    }

    @PostMapping(path="/post", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String postArticle(@RequestPart String title, 
        @RequestPart MultipartFile image, @RequestPart String contentPara, 
        Model model) throws IOException {

        String post_id = "";

        try {
        post_id = articleSvc.postImage(image, title, contentPara);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        model.addAttribute("post_id", post_id);
        model.addAttribute("title", title);
        model.addAttribute("contentPara", contentPara);
        model.addAttribute("imagename", image.getOriginalFilename());
        model.addAttribute("image", "/image/%s".formatted(post_id));
        model.addAttribute("imagesize", image.getSize());
        model.addAttribute("contentType", image.getContentType());

        return "posted";
        
    }

}
