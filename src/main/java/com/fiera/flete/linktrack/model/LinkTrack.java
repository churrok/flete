package com.fiera.flete.linktrack.model;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class LinkTrack {
	
	@Id
	private String id;
	private String target;
	private boolean valid;
	private int usos;
	private String password;

}
