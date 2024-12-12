package com.pcs.x.post;

import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PostModelAssembler implements RepresentationModelAssembler<Post, EntityModel<Post>>{

	@Override
	public EntityModel<Post> toModel(Post entity) {
		EntityModel<Post> postModel = EntityModel.of(entity,
				linkTo(methodOn(PostController.class)
						.onePost(entity.getUser().getName(), entity.getId())).withSelfRel(),
				linkTo(methodOn(PostController.class).allPost(entity.getUser().getName())).withRel("all_posts"));
		return postModel;
	}

}
