
// // Ստուգել, արդյոք ձեր laptop-ը ունակ է կպնել այս site-երին, եթե այո տպի ամեն մեկի համար e.g. www.google.com is accessible from your device
// // Եթե ոչ մեկը հասանելի չէ, տպի check your network connection
// // եթե կոնկրետ մեկը հասանելի չէ, տպի e.g. www.google.com is not accessible from your device

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Websites {
    public static void main(String[] args) {
        String[] websites = {"www.google.com", "www.youtube.com", "www.facebook.com", "www.imdb.com"};
        
        int accessibleCount = 0;

        for (String website : websites) {
            if (isWebsiteAccessible(website)) {
                accessibleCount++;
                System.out.println(website + " is accessible from your device");
            } else {
                System.out.println(website + " is not accessible from your device");
            }
        }

        if (accessibleCount == 0) {
            System.out.println("Check your network connection");
        }
    }

    // Check website accessibility by sending a request to the URL
    public static boolean isWebsiteAccessible(String url) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://" + url))
                    .build();
            HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
            int statusCode = response.statusCode();
            return statusCode == 200;
        } catch (IOException | InterruptedException e) {
            return false;
        }
    }
}
