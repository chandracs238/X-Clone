package com.pcs.x.user;

import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.server.RepresentationModelAssembler;

@Configuration
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>>{

	@Override
	public EntityModel<User> toModel(User user) {
		return EntityModel.of(user,
				linkTo(methodOn(UserController.class).getUser(user.getId())).withSelfRel());
	}

}
