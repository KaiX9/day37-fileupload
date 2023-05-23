package sg.edu.nus.iss.day37pmpractice.repository;

public class SQLQueries {
    
    public static final String SAVE_IMAGE = """
        insert into article(post_id, media_type, content) 
        values (?, ?, ?)        
    """;

    public static final String GET_PHOTO_BY_ID = """
        select * from article where post_id = ?     
    """;

}