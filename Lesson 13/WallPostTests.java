package API_Tests;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import java.net.URISyntaxException;

public class WallPostTests {
    private static final String ACCESS_TOKEN = "6b7b10dd23fd2b81ef006c5dd3f1e9bd453138a0329cd2787e4c0af52c6a2e1ece9d2a49508e1537e507e";
    private static final String VERSION = "5.103";
    private static final String OWNER_ID = "201147152";
    private static final String MESSAGE_TEXT = "TEST MESSAGE";
    private static final String MESSAGE_TEXT_UPDATED = "+++UPDATED+++TEST MESSAGE+++UPDATED+++";


    private static String postID;

    @Test
    public void addPost() throws IOException, URISyntaxException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder("https://api.vk.com/method/wall.post?");
        builder.setParameter("access_token", ACCESS_TOKEN)
                .setParameter("owner_id", OWNER_ID)
                .setParameter("message", MESSAGE_TEXT)
                .setParameter("v", VERSION);
        HttpGet request = new HttpGet(builder.build());
        HttpResponse response = client.execute(request);
        postID = EntityUtils.toString(response.getEntity()).replaceAll("\\D", "");
        Assert.assertTrue(postID.matches("\\d+"));
        builder = new URIBuilder("https://api.vk.com/method/wall.getById?");
        builder.setParameter("access_token", ACCESS_TOKEN)
                .setParameter("posts", OWNER_ID + "_" + postID)
                .setParameter("v", VERSION);
        request = new HttpGet(builder.build());
        response = client.execute(request);
        Assert.assertTrue(EntityUtils.toString(response.getEntity()).contains(MESSAGE_TEXT));
    }

    @Test
    public void editPost() throws IOException, URISyntaxException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder("https://api.vk.com/method/wall.edit?");
        builder.setParameter("access_token", ACCESS_TOKEN)
                .setParameter("owner_id", OWNER_ID)
                .setParameter("post_id", postID)
                .setParameter("message", MESSAGE_TEXT_UPDATED)
                .setParameter("v", VERSION);
        HttpGet request = new HttpGet(builder.build());
        HttpResponse response = client.execute(request);
        Assert.assertTrue(EntityUtils.toString(response.getEntity()).contains(postID));
        builder = new URIBuilder("https://api.vk.com/method/wall.getById?");
        builder.setParameter("access_token", ACCESS_TOKEN)
                .setParameter("posts", OWNER_ID + "_" + postID)
                .setParameter("v", VERSION);
        request = new HttpGet(builder.build());
        response = client.execute(request);
        Assert.assertTrue(EntityUtils.toString(response.getEntity()).contains(MESSAGE_TEXT_UPDATED));
    }

    @Test
    public void deletePost() throws IOException, URISyntaxException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder("https://api.vk.com/method/wall.delete?");
        builder.setParameter("access_token", ACCESS_TOKEN)
                .setParameter("owner_id", OWNER_ID)
                .setParameter("post_id", postID)
                .setParameter("v", VERSION);
        HttpGet request = new HttpGet(builder.build());
        HttpResponse response = client.execute(request);
        String responseCode = EntityUtils.toString(response.getEntity()).replaceAll("\\D", "");
        Assert.assertEquals("1", responseCode);
        builder = new URIBuilder("https://api.vk.com/method/wall.getById?");
        builder.setParameter("access_token", ACCESS_TOKEN)
                .setParameter("posts", OWNER_ID + "_" + postID)
                .setParameter("v", VERSION);
        request = new HttpGet(builder.build());
        response = client.execute(request);
        Assert.assertFalse(EntityUtils.toString(response.getEntity()).contains(MESSAGE_TEXT_UPDATED));
    }

}
