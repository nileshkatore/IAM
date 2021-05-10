package iam.resource.albumresource.controller;

import iam.resource.albumresource.model.Album;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/albumresource")
public class AlbumController {

        @GetMapping("/albums")
        public List<Album> getAlbums(){
            Album album1 = new Album();
            album1.setAlbumId("albumOne");
            album1.setAlbumTitle("albumOne Title");
            album1.setAlbumUrl("http://localhost:8082/albums/1");

            Album album2 = new Album();
            album2.setAlbumId("albumTwo");
            album2.setAlbumTitle("albumTwo Title");
            album2.setAlbumUrl("http://localhost:8082/albums/2");

            List<Album> albums = new ArrayList<>();
            albums.add(album1);
            albums.add(album2);

            return albums;
        }

        @GetMapping("/status")
        public String getStatus(){
            return "Status : OK";
        }
}
