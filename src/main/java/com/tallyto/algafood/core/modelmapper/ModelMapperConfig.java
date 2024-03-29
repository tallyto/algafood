package com.tallyto.algafood.core.modelmapper;

import com.tallyto.algafood.api.v1.model.EnderecoModel;
import com.tallyto.algafood.api.v1.model.input.ItemPedidoInput;
import com.tallyto.algafood.domain.model.Endereco;
import com.tallyto.algafood.domain.model.ItemPedido;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Impede que o setId de itemPedido seja atribuído na serialização
        modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
            .addMappings(mapper -> mapper.skip(ItemPedido::setId));

        var enderecoToEnderecoDTOTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoModel.class);

        // Mapeia o nome do estado para o atributo estado no endereçoDTO
        enderecoToEnderecoDTOTypeMap.<String>addMapping(
            src -> src.getCidade().getEstado().getNome(),
            (dest, value) -> dest.getCidade().setEstado(value)
        );

        return modelMapper;
    }
}
