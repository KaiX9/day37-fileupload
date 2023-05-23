package sg.edu.nus.iss.day37pmpractice.model;

import java.util.Arrays;

public class Article {
    
    private String post_id;
    private String title;
    private String contentPara;
    private String mediaType;
    private byte[] content;

    public Article() {

    }
    
    public Article(String post_id, String title, String contentPara, String mediaType, byte[] content) {
        this.post_id = post_id;
        this.title = title;
        this.contentPara = contentPara;
        this.mediaType = mediaType;
        this.content = content;
    }

    public String getPost_id() {
        return post_id;
    }
    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getMediaType() {
        return mediaType;
    }
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
    public byte[] getContent() {
        return content;
    }
    public void setContent(byte[] content) {
        this.content = content;
    }
    public String getContentPara() {
        return contentPara;
    }
    public void setContentPara(String contentPara) {
        this.contentPara = contentPara;
    }

    @Override
    public String toString() {
        return "Article [post_id=" + post_id + ", title=" + title + ", contentPara=" + contentPara + ", mediaType="
                + mediaType + ", content=" + Arrays.toString(content) + "]";
    }

}
