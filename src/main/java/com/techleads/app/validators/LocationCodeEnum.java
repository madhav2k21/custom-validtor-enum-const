package com.techleads.app.validators;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public enum LocationCodeEnum {

	HYD("HYD"), BLR("HYD"), CHN("CHN");

	private final String location;

	public boolean isHYDCode() {
		return HYD.getLocation().equals(this.location);
	}

	public boolean isBLRCode() {
		return BLR.getLocation().equals(this.location);
	}

	public boolean isCHNCode() {
		return CHN.getLocation().equals(this.location);
	}
}
