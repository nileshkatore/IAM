package iam.webclient.controller;

import iam.webclient.response.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@Controller
public class AlbumController {

    @Autowired
    OAuth2AuthorizedClientService oauth2ClientService;

    @GetMapping("/albums")
    public String getAlbums(Model model, @AuthenticationPrincipal OidcUser principal){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;

        OAuth2AuthorizedClient oauth2Client = oauth2ClientService.loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(), oauthToken.getName());
        String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();

        System.out.println("JWT Access token - " + jwtAccessToken);

        System.out.println("Principal - " + principal);

        OidcIdToken idToken = principal.getIdToken();
        String idTokenValue = idToken.getTokenValue();

        System.out.println("IdToken - " + idTokenValue);

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
