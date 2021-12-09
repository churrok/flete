package com.fiera.flete.traker.drivers;

import com.fiera.flete.linktrack.repository.LinkTrackRepository;
import com.fiera.flete.traker.adapters.ILinkRepository;
import com.fiera.flete.traker.entities.LinkTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class MongoLinkRepository implements ILinkRepository {

  @Autowired
  private LinkTrackRepository linkTrackRepository;

  @Override
  public LinkTrack save(LinkTrack linkTrack) {
    return linkTrackRepository.save(linkTrack);
  }

  @Override
  public Optional<LinkTrack> findById(String trackId) {
    return linkTrackRepository.findById(trackId);
  }
}
