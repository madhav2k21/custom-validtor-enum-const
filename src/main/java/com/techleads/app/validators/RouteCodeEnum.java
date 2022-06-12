package com.techleads.app.validators;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public enum RouteCodeEnum {

	A("A"), B("B"), C("C");

	private final String location;

	public boolean isHYDCode() {
		return A.getLocation().equals(this.location);
	}

	public boolean isBLRCode() {
		return B.getLocation().equals(this.location);
	}

	public boolean isCHNCode() {
		return C.getLocation().equals(this.location);
	}
}
