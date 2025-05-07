package com.artist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/artists")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    //1. create artist details
    @RequestMapping(method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Artist createArtist(@RequestBody Artist artist)
    {
        return artistService.createArtist(artist);
    }

    //2.get all artist details
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Artist> getAllArtists() {
        return artistService.getAllArtists();
    }

    // 3.get artist details by id

    @RequestMapping(value="/{id}",method=RequestMethod.GET)
    @ResponseBody
    public Artist getArtistById(@PathVariable("id") Long id)
    {
        return artistService.getArtistById(id);
    }

    //4.delete id

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtistById(@PathVariable("id") Long id) {
        artistService.deleteArtistById(id);
    }





}
