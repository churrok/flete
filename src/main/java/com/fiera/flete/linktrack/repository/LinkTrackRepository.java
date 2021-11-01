package com.fiera.flete.linktrack.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fiera.flete.linktrack.model.LinkTrack;


public interface LinkTrackRepository extends MongoRepository<LinkTrack, String> {

}
