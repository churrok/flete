package com.fiera.flete.traker.drivers;

import com.fiera.flete.traker.adapters.IIdGenerator;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class IdGenerator implements IIdGenerator {
  @Override
  public String generate() {
    return UUID.randomUUID().toString();
  }
}
