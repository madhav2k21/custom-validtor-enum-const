package com.techleads.app.validators;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public enum LocationCodeEnum {

	HYD_CDE("HYD"), BLR_CDE("HYD"), CHN_CDE("CHN");

	private final String location;

	public boolean isHYDCode() {
		return HYD_CDE.getLocation().equals(this.location);
	}

	public boolean isBLRCode() {
		return BLR_CDE.getLocation().equals(this.location);
	}

	public boolean isCHNCode() {
		return CHN_CDE.getLocation().equals(this.location);
	}
}
