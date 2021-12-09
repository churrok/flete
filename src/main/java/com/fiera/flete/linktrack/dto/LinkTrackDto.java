package com.fiera.flete.linktrack.dto;

import lombok.Data;

@Data
public class LinkTrackDto {
	private String url;
	private String target;
	private boolean valid;
	private int usos;
	private String password;

	private LinkTrackDto(Builder builder) {
		setUrl(builder.url);
		setTarget(builder.target);
		setValid(builder.valid);
		setUsos(builder.usos);
		setPassword(builder.password);
	}


	public static final class Builder {
		private String url;
		private String target;
		private boolean valid;
		private int usos;
		private String password;

		public Builder() {
		}

		public Builder url(String val) {
			url = val;
			return this;
		}

		public Builder target(String val) {
			target = val;
			return this;
		}

		public Builder valid(boolean val) {
			valid = val;
			return this;
		}

		public Builder usos(int val) {
			usos = val;
			return this;
		}

		public Builder password(String val) {
			password = val;
			return this;
		}

		public LinkTrackDto build() {
			return new LinkTrackDto(this);
		}
	}
}
