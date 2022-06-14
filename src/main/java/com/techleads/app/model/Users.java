package com.techleads.app.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import com.techleads.app.validators.IConstraintEnumLocation;
import com.techleads.app.validators.IConstraintEnumRoute;
import com.techleads.app.validators.LocationCodeEnum;
import com.techleads.app.validators.RouteCodeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@IConstraintEnumLocation(enumClass = LocationCodeEnum.class)
	@NotBlank(message = "Location field must not be empty")
	private String location;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@IConstraintEnumRoute(enumClass = RouteCodeEnum.class)
	List<Route> routes;

}
