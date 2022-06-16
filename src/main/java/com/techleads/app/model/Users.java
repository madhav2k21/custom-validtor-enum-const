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
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.executable.ValidateOnExecution;

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
	@NotBlank(message = "{Users.not-empty.location.msg}")
	private String location;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@IConstraintEnumRoute(enumClass = RouteCodeEnum.class)
	@NotEmpty(message = "{Route.not-empty.routing-sequence-codes.msg}")
	List<Route> routes;
	@Valid
	Skill skills;

}
