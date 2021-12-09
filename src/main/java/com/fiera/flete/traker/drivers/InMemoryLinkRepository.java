package com.fiera.flete.traker.drivers;

import com.fiera.flete.traker.adapters.ILinkRepository;
import com.fiera.flete.traker.entities.LinkTrack;
import java.util.*;

public class InMemoryLinkRepository implements ILinkRepository {
  private final Map<String, LinkTrack> database = new HashMap<>();

  @Override
  public LinkTrack save(LinkTrack linkTrack) {
    database.put(linkTrack.getId(), linkTrack);
    return linkTrack;
  }

  @Override
  public Optional<LinkTrack> findById(String trackId) {
    return Optional.ofNullable(database.get(trackId));
  }
}
