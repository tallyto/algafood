package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.controller.UsuarioController;
import com.algaworks.algafood.api.controller.UsuarioGrupoController;
import com.algaworks.algafood.api.model.UsuarioModel;
import com.algaworks.algafood.api.model.input.UsuarioInput;
import com.algaworks.algafood.api.model.input.UsuarioWithoutPasswordInput;
import com.algaworks.algafood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UsuarioAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioModel> {
    @Autowired
    ModelMapper modelMapper;

    public UsuarioAssembler() {
        super(UsuarioController.class, UsuarioModel.class);
    }

    @Override
    public UsuarioModel toModel(Usuario usuario) {
        UsuarioModel usuarioModel = createModelWithId(usuario.getId(), usuario);
        modelMapper.map(usuario, usuarioModel);

        usuarioModel.add(linkTo(UsuarioController.class).withRel("usuarios"));

        usuarioModel.add(linkTo(methodOn(UsuarioGrupoController.class)
            .listar(usuario.getId())).withRel("grupos-usuario"));

        return usuarioModel;
    }


    public Collection<UsuarioModel> toCollectionDTO(Collection<Usuario> usuarios) {
        return usuarios.stream().map(this::toModel).collect(Collectors.toList());
    }

    public Usuario toEntity(UsuarioInput usuarioInput) {
        return modelMapper.map(usuarioInput, Usuario.class);
    }

    public Usuario toEntityUpdate(UsuarioWithoutPasswordInput usuarioInput) {
        return modelMapper.map(usuarioInput, Usuario.class);
    }
}
