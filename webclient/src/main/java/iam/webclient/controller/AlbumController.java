package iam.webclient.controller;

import iam.webclient.response.Album;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@Controller
public class AlbumController {

    @GetMapping("/albums")
    public String getAlbums(Model model, @AuthenticationPrincipal OidcUser principal, Authentication authentication){

        Album album1 = new Album();
        album1.setAlbumId("albumOne");
        album1.setAlbumTitle("albumOne Title");
        album1.setAlbumUrl("http://localhost:8082/albums/1");

        Album album2 = new Album();
        album2.setAlbumId("albumTwo");
        album2.setAlbumTitle("albumTwo Title");
        album2.setAlbumUrl("http://localhost:8082/albums/2");

        model.addAttribute("albums", Arrays.asList(album1, album2));

        return "albums";
    }
}
