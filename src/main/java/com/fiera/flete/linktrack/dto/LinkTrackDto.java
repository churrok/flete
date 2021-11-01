package com.fiera.flete.linktrack.dto;

import lombok.Data;

@Data
public class LinkTrackDto {
	private String url;
	private String target;
	private boolean valid;
	private int usos;
	private String password;
}
