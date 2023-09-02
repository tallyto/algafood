package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.DTO.UsuarioDTO;
import com.algaworks.algafood.api.model.input.UsuarioInput;
import com.algaworks.algafood.api.model.input.UsuarioWithoutPasswordInput;
import com.algaworks.algafood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class UsuarioAssembler {
    @Autowired
    ModelMapper modelMapper;

    public UsuarioDTO toDTO(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    public Collection<UsuarioDTO> toCollectionDTO(Collection<Usuario> usuarios) {
        return usuarios.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Usuario toEntity(UsuarioInput usuarioInput) {
        return modelMapper.map(usuarioInput, Usuario.class);
    }

    public Usuario toEntityUpdate(UsuarioWithoutPasswordInput usuarioInput) {
        return modelMapper.map(usuarioInput, Usuario.class);
    }
}
