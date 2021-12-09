package com.fiera.flete.linktrack.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fiera.flete.traker.entities.LinkTrack;


public interface LinkTrackRepository extends MongoRepository<LinkTrack, String> {

}
