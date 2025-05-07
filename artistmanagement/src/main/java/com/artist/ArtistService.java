package com.artist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    //1.create artist
    public Artist createArtist(Artist artist)
    {
        return artistRepository.save(artist);
    }

    //2.get all artist
    public List<Artist> getAllArtists()
    {
        return artistRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));
    }

    //3.find an artist by id
    public Artist getArtistById(Long id)

    {
        return artistRepository.findById(id).orElse(null);
    }

    //4.delete by an id
    public void deleteArtistById(Long id)
    {
        artistRepository.deleteById(id);
    }




}
